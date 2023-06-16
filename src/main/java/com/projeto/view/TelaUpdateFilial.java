package com.projeto.view;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.model.Filial;

import java.awt.GridBagLayout;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaUpdateFilial extends JDialog{
    JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    DefaultListModel<Filial> modelFilial = new DefaultListModel<Filial>();
    JList<Filial> listaFilial = new JList<Filial>();
    JLabel labelnome = new JLabel("Digite o Nome da Filial: ");
    JLabel labelendereco = new JLabel("Digite o Endereco da Filial: ");
    JTextField textonome = new JTextField();
    JTextField textoendereco = new JTextField();
    JSplitPane splitPane = new JSplitPane();
    JButton salvar = new JButton("SALVAR");

    public TelaUpdateFilial(){
        setTitle("Tela Update");
        setSize(480, 480);
        setLocationRelativeTo(null);

        GridBagConstraints gridBagConstains = new GridBagConstraints();

        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        
        try {
            FileReader readerf = new FileReader("filiais.json");
            JsonArray jsonArrayf = (JsonArray) JsonParser.parseReader(readerf);
            
            for (JsonElement jsonElement : jsonArrayf){
                Filial cadastroFilial = new Gson().fromJson(jsonElement, Filial.class);
                modelFilial.addElement(cadastroFilial);
            }
            listaFilial.setModel(modelFilial);

            splitPane.setLeftComponent(new JScrollPane(listaFilial));

            splitPane.setRightComponent(jPanel);
            gridBagConstains.gridy ++;
            jPanel.add(labelnome, gridBagConstains);
            gridBagConstains.gridy ++;
            textonome.setPreferredSize(new Dimension(200,25));
            jPanel.add(textonome, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labelendereco, gridBagConstains);
            gridBagConstains.gridy ++;
            textoendereco.setPreferredSize(new Dimension(200,25));
            jPanel.add(textoendereco, gridBagConstains);
            gridBagConstains.gridy ++;

            gridBagConstains.gridy = 13;
            salvar.setPreferredSize(new Dimension(200,25));
            jPanel.add(salvar, gridBagConstains);
            
            salvar.addActionListener(acao -> {     
                try {
                        Filial p = listaFilial.getSelectedValue();
                        if(textonome.getText() != null && !textonome.getText().isEmpty()){
                            p.setNome(textonome.getText());
                        }
                        if (textoendereco.getText() != null && !textoendereco.getText().isEmpty()) {
                            p.setEndereco(textoendereco.getText());
                        }
                        
                        try {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            FileReader reader = new FileReader("filiais.json");
                            JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                            java.util.List<Filial> listacadastro = new ArrayList<Filial>();
                            for (JsonElement jsonElement : jsonArray){
                                Filial cadastro1 = new Gson().fromJson(jsonElement, Filial.class);
                                listacadastro.add(cadastro1);
                            }
                            listacadastro.set(listaFilial.getSelectedIndex(), p);
                            String updatedJsonString = gson.toJson(listacadastro);
                            Files.write(Paths.get("cadastrosfisico.json"), updatedJsonString.getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    JOptionPane.showMessageDialog(this, "Atualizacao realizada com sucesso");
                    TelaUpdateFilial.this.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o produto");
                }
            }
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //add(jPanel, BorderLayout.WEST);
        add(splitPane);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

