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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaRemoverFilial extends JFrame{

    JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    JLabel labelnome = new JLabel("Nome da Filial: ");
    JLabel labelendereco = new JLabel("Endereco da Filial: ");
    JTextField textonome = new JTextField();
    JTextField textoendereco = new JTextField();
    JButton remover = new JButton("Remover");

    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton buttonnome = new JRadioButton("", true) ;
    JRadioButton buttonendereco = new JRadioButton(); 

    public TelaRemoverFilial(){
        setTitle("Tela de Remoçao");
        setSize(480, 480);
        setLocationRelativeTo(null);

        GridBagConstraints gridBagConstains = new GridBagConstraints();

        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;

        buttonnome.setPreferredSize(new Dimension(20,25));
        jPanel.add(buttonnome, gridBagConstains);
        gridBagConstains.gridx ++;

        labelnome.setPreferredSize(new Dimension(200,25));
        jPanel.add(labelnome, gridBagConstains);
        gridBagConstains.gridx ++;

        textonome.setPreferredSize(new Dimension(200,25));
        jPanel.add(textonome, gridBagConstains);
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        gridBagConstains.gridx --;

        buttonendereco.setPreferredSize(new Dimension(20,25));
        jPanel.add(buttonendereco, gridBagConstains);
        gridBagConstains.gridx ++;

        labelendereco.setPreferredSize(new Dimension(200,25));
        jPanel.add(labelendereco, gridBagConstains);
        gridBagConstains.gridx ++;

        textoendereco.setPreferredSize(new Dimension(200,25));
        jPanel.add(textoendereco, gridBagConstains);
        textoendereco.setEditable(false);
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        gridBagConstains.gridx --;
        gridBagConstains.gridwidth = 3;

        remover.setPreferredSize(new Dimension(200,25));
        jPanel.add(remover, gridBagConstains);
        gridBagConstains.gridy ++;

        buttonGroup.add(buttonnome);
        buttonGroup.add(buttonendereco);

        buttonnome.addActionListener(acao -> {
            textonome.setEditable(true);
            textoendereco.setEditable(false);
        });
        buttonendereco.addActionListener(acao1 -> {
            textonome.setEditable(false);
            textoendereco.setEditable(true);
        });

        remover.addActionListener(acao2 -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {
                FileReader reader = new FileReader("filiais.json");
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                java.util.List<Filial> listacadastro = new ArrayList<Filial>();
                
                for (JsonElement jsonElement : jsonArray){
                    Filial cadastro1 = new Gson().fromJson(jsonElement, Filial.class);
                    listacadastro.add(cadastro1);
                }
                int aux = 0;

                if(buttonnome.isSelected() == true){
                    String nome = textonome.getText();
                    for (int i = 0; i < listacadastro.size(); i++) {
                        if (String.valueOf(listacadastro.get(i).getNome()).equals(nome)) {
                            listacadastro.remove(i);
                            aux = 1;
                            break;
                        }
                    }
                }

                if(buttonendereco.isSelected() == true){
                    String codigo = textoendereco.getText();
                    for (int i = 0; i < listacadastro.size(); i++) {
                        if (String.valueOf(listacadastro.get(i).getEndereco()).equals(codigo)) {
                            listacadastro.remove(i);
                            aux = 1;
                            break;
                        }
                    }
                }

                if(aux == 0){
                    throw new Exception("O item nao existe");
                }

                String updatedJsonString = gson.toJson(listacadastro);
                Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());

                JOptionPane.showMessageDialog(this, "Filial deletada com sucesso");

                TelaRemoverFilial.this.dispose();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "O item nao existe");
                e.printStackTrace();
            }
        });
        
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
