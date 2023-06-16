package com.projeto.view;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;

import java.awt.GridBagLayout;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaRemoverProdutos extends JFrame{

    JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    JLabel labelnome = new JLabel("Nome do Produto: ");
    JLabel labelcodigo = new JLabel("Codigo do Produto: ");
    JTextField textonome = new JTextField();
    JTextField textocodigo = new JTextField();
    JButton remover = new JButton("Remover");

    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton buttonnome = new JRadioButton("", true) ;
    JRadioButton buttoncodigo = new JRadioButton(); 

    public TelaRemoverProdutos(){
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

        buttoncodigo.setPreferredSize(new Dimension(20,25));
        jPanel.add(buttoncodigo, gridBagConstains);
        gridBagConstains.gridx ++;

        labelcodigo.setPreferredSize(new Dimension(200,25));
        jPanel.add(labelcodigo, gridBagConstains);
        gridBagConstains.gridx ++;

        textocodigo.setPreferredSize(new Dimension(200,25));
        jPanel.add(textocodigo, gridBagConstains);
        textocodigo.setEditable(false);
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        gridBagConstains.gridx --;
        gridBagConstains.gridwidth = 3;

        remover.setPreferredSize(new Dimension(200,25));
        jPanel.add(remover, gridBagConstains);
        gridBagConstains.gridy ++;

        buttonGroup.add(buttonnome);
        buttonGroup.add(buttoncodigo);

        buttonnome.addActionListener(acao -> {
            textonome.setEditable(true);
            textocodigo.setEditable(false);
        });
        buttoncodigo.addActionListener(acao1 -> {
            textonome.setEditable(false);
            textocodigo.setEditable(true);
        });

        remover.addActionListener(acao2 -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {
                FileReader reader = new FileReader("caneca.json");
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                java.util.List<Caneca> listacadastro = new ArrayList<Caneca>();

                FileReader readerv = new FileReader("curso.json");
                JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
                java.util.List<Curso> listacadastrov = new ArrayList<Curso>();
                
                for (JsonElement jsonElement : jsonArrayv){
                    Curso cadastro2 = new Gson().fromJson(jsonElement, Curso.class);
                    listacadastrov.add(cadastro2);
                }
                
                for (JsonElement jsonElement : jsonArray){
                    Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
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
                    for (int i = 0; i < listacadastrov.size(); i++) {
                        if (String.valueOf(listacadastrov.get(i).getNome()).equals(nome)) {
                            listacadastrov.remove(i);
                            aux = 1;
                            break;
                        }
                    }
                }

                if(buttoncodigo.isSelected() == true){
                    String codigo = textocodigo.getText();
                    for (int i = 0; i < listacadastro.size(); i++) {
                        if (String.valueOf(listacadastro.get(i).getCodigo()).equals(codigo)) {
                            listacadastro.remove(i);
                            aux = 1;
                            break;
                        }
                    }
                    for (int i = 0; i < listacadastrov.size(); i++) {
                        if (String.valueOf(listacadastrov.get(i).getCodigo()).equals(codigo)) {
                            listacadastrov.remove(i);
                            aux = 1;
                            break;
                        }
                    }
                }

                if(aux == 0){
                    throw new Exception("O item nao existe");
                }

                String updatedJsonString = gson.toJson(listacadastro);
                Files.write(Paths.get("caneca.json"), updatedJsonString.getBytes());

                String updatedJsonStringv = gson.toJson(listacadastrov);
                Files.write(Paths.get("curso.json"), updatedJsonStringv.getBytes());
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
