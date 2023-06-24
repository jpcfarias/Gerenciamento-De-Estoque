package com.projeto.view;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.control.Controller;
import com.projeto.model.Filial;

import java.awt.GridBagLayout;

import java.io.FileReader;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaUpdateFilial extends JDialog{
    //JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    private DefaultListModel<Filial> modelFilial = new DefaultListModel<Filial>();
    private JList<Filial> listaFilial = new JList<Filial>();
    private JLabel labelnome = new JLabel("Digite o Nome da Filial: ");
    private JLabel labelendereco = new JLabel("Digite o Endereco da Filial: ");
    private JTextField textonome = new JTextField();
    private JTextField textoendereco = new JTextField();
    private JSplitPane splitPane = new JSplitPane();
    private JButton salvar = new JButton("SALVAR");
    private Controller controller = new Controller();

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
                    controller.updateFilial(listaFilial, textonome.getText(), textoendereco.getText());

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

