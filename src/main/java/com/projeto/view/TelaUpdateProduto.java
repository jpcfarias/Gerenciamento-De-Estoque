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
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaUpdateProduto extends JDialog{
    JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    DefaultListModel<Caneca> modelf = new DefaultListModel<Caneca>();
    DefaultListModel<Curso> modelv = new DefaultListModel<Curso>();
    JList<Caneca> listaf = new JList<Caneca>();
    JList<Curso> listav = new JList<Curso>();
    JLabel labelnome = new JLabel("Digite o Nome do produto: ");
    JLabel labeldescricao = new JLabel("Digite a Descricao do produto: ");
    JLabel labelcodigo = new JLabel("Digite o CODIGO do produto: ");
    JLabel labelquantidade = new JLabel("Digite a QUANTIDADE do produto: ");
    JLabel labelpreco = new JLabel("Digite o Valor do produto: ");
    JLabel labelpeso = new JLabel("Digite o Peso do produto: ");
    JLabel labelplano = new JLabel("Digite o Plano do produto: ");
    JTextField textonome = new JTextField();
    JTextField textodescricao = new JTextField();
    JTextField textocodigo = new JTextField();
    JTextField textoquantidade = new JTextField();
    JTextField textopreco = new JTextField();
    JTextField textopeso = new JTextField();
    JTextField textoplano = new JTextField();
    JSplitPane splitPane = new JSplitPane();
    JButton salvar = new JButton("SALVAR");
    JComboBox<String> combo = new JComboBox<String>();

    public TelaUpdateProduto(){
        setTitle("Tela Update");
        setSize(480, 480);
        setLocationRelativeTo(null);

        GridBagConstraints gridBagConstains = new GridBagConstraints();

        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;

        combo.addItem("Canecas");
        combo.addItem("Cursos");
        
        try {
            FileReader readerf = new FileReader("caneca.json");
            JsonArray jsonArrayf = (JsonArray) JsonParser.parseReader(readerf);
            
            for (JsonElement jsonElement : jsonArrayf){
                Caneca cadastrof = new Gson().fromJson(jsonElement, Caneca.class);
                modelf.addElement(cadastrof);
            }
            listaf.setModel(modelf);

            FileReader readerv = new FileReader("curso.json");
            JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
            
            for (JsonElement jsonElement : jsonArrayv){
                Curso cadastrov = new Gson().fromJson(jsonElement, Curso.class);
                modelv.addElement(cadastrov);
            }
            listav.setModel(modelv);

            splitPane.setLeftComponent(new JScrollPane(listaf));

            combo.addActionListener(actioncombobox -> {
                if(combo.getSelectedItem().equals("Canecas")){

                    jPanel.remove(labelplano);
                    jPanel.remove(textoplano);

                    splitPane.setLeftComponent(new JScrollPane(listaf));
                    gridBagConstains.gridy = 11;
                    jPanel.add(labelpeso, gridBagConstains);
                    gridBagConstains.gridy = 12;
                    textopeso.setPreferredSize(new Dimension(200,25));
                    jPanel.add(textopeso, gridBagConstains);

                    jPanel.revalidate();
                    jPanel.repaint();

                }else if(combo.getSelectedItem().equals("Cursos")){

                    jPanel.remove(labelpeso);
                    jPanel.remove(textopeso);

                    splitPane.setLeftComponent(new JScrollPane(listav));
                    gridBagConstains.gridy = 11;
                    jPanel.add(labelplano, gridBagConstains);
                    gridBagConstains.gridy = 12;
                    textoplano.setPreferredSize(new Dimension(200,25));
                    jPanel.add(textoplano, gridBagConstains);
                    
                    jPanel.revalidate();
                    jPanel.repaint();
                }
                
            });

            splitPane.setRightComponent(jPanel);
            jPanel.add(combo, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labelnome, gridBagConstains);
            gridBagConstains.gridy ++;
            textonome.setPreferredSize(new Dimension(200,25));
            jPanel.add(textonome, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labeldescricao, gridBagConstains);
            gridBagConstains.gridy ++;
            textodescricao.setPreferredSize(new Dimension(200,25));
            jPanel.add(textodescricao, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labelcodigo, gridBagConstains);
            gridBagConstains.gridy ++;
            textocodigo.setPreferredSize(new Dimension(200,25));
            jPanel.add(textocodigo, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labelquantidade, gridBagConstains);
            gridBagConstains.gridy ++;
            textoquantidade.setPreferredSize(new Dimension(200,25));
            jPanel.add(textoquantidade, gridBagConstains);
            gridBagConstains.gridy ++;
            jPanel.add(labelpreco, gridBagConstains);
            gridBagConstains.gridy ++;
            textopreco.setPreferredSize(new Dimension(200,25));
            jPanel.add(textopreco, gridBagConstains);

            gridBagConstains.gridy = 13;
            salvar.setPreferredSize(new Dimension(200,25));
            jPanel.add(salvar, gridBagConstains);
            
            salvar.addActionListener(acao -> {     
                try {
                    if(combo.getSelectedItem().equals("Canecas")){
                        Caneca p = listaf.getSelectedValue();
                        if(textonome.getText() != null && !textonome.getText().isEmpty()){
                            p.setNome(textonome.getText());
                        }
                        if (textodescricao.getText() != null && !textodescricao.getText().isEmpty()) {
                            p.setDescricao(textodescricao.getText());
                        }
                        if (textocodigo.getText() != null && !textocodigo.getText().isEmpty()) {
                            int codigo = Integer.parseInt(textocodigo.getText());
                            p.setCodigo(codigo);
                        }
                        if (textoquantidade.getText() != null && !textoquantidade.getText().isEmpty()) {
                            int quantidade = Integer.parseInt(textoquantidade.getText());
                            p.setQuantidade(quantidade);
                        }
                        if (textopreco.getText() != null && !textopreco.getText().isEmpty()) {
                            float preco = Float.parseFloat(textopreco.getText());
                            p.setPreco(preco); 
                        }
                        if (textopeso.getText() != null && !textopeso.getText().isEmpty()) {
                            float peso = Float.parseFloat(textopeso.getText());
                            p.setPeso(peso);   
                        }
                        try {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            FileReader reader = new FileReader("caneca.json");
                            JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                            java.util.List<Caneca> listacadastro = new ArrayList<Caneca>();
                            for (JsonElement jsonElement : jsonArray){
                                Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
                                listacadastro.add(cadastro1);
                            }
                            listacadastro.set(listaf.getSelectedIndex(), p);
                            String updatedJsonString = gson.toJson(listacadastro);
                            Files.write(Paths.get("caneca.json"), updatedJsonString.getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if(combo.getSelectedItem().equals("Cursos")){
                        Curso p = listav.getSelectedValue();
                        if(textonome.getText() != null && !textonome.getText().isEmpty()){
                            p.setNome(textonome.getText());
                        }
                        if (textodescricao.getText() != null && !textodescricao.getText().isEmpty()) {
                            p.setDescricao(textodescricao.getText());
                        }
                        if (textocodigo.getText() != null && !textocodigo.getText().isEmpty()) {
                            int codigo = Integer.parseInt(textocodigo.getText());
                            p.setCodigo(codigo);
                        }
                        if (textoquantidade.getText() != null && !textoquantidade.getText().isEmpty()) {
                            int quantidade = Integer.parseInt(textoquantidade.getText());
                            p.setQuantidade(quantidade);
                        }
                        if (textopreco.getText() != null && !textopreco.getText().isEmpty()) {
                            float preco = Float.parseFloat(textopreco.getText());
                            p.setPreco(preco); 
                        }
                        if (textoplano.getText() != null && !textoplano.getText().isEmpty()) {
                            String plano = textoplano.getText();
                            p.setPlano(plano);   
                        }
                        try {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            FileReader reader = new FileReader("curso.json");
                            JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                            java.util.List<Curso> listacadastro = new ArrayList<Curso>();
                            for (JsonElement jsonElement : jsonArray){
                                Curso cadastro1 = new Gson().fromJson(jsonElement, Curso.class);
                                listacadastro.add(cadastro1);
                            }
                            listacadastro.set(listav.getSelectedIndex(), p);
                            String updatedJsonString = gson.toJson(listacadastro);
                            Files.write(Paths.get("curso.json"), updatedJsonString.getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Atualizacao realizada com sucesso");
                    TelaUpdateProduto.this.dispose();
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
