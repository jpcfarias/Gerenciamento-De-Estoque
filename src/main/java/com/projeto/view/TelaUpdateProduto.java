package com.projeto.view;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.control.Controller;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;

import java.awt.GridBagLayout;

import java.io.FileReader;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

/**
 * @author Leonardo, Felipe, Joao
 * Classe view Tela para atualizar informaçoes de Produtos já cadastradas
*/

public class TelaUpdateProduto extends JDialog{

    private JPanel jPanel = new JPanel(new GridBagLayout());
    private DefaultListModel<Caneca> modelf = new DefaultListModel<Caneca>();
    private DefaultListModel<Curso> modelv = new DefaultListModel<Curso>();
    private JList<Caneca> listaf = new JList<Caneca>();
    private JList<Curso> listav = new JList<Curso>();
    private JLabel labelnome = new JLabel("Digite o Nome do produto: ");
    private JLabel labeldescricao = new JLabel("Digite a Descricao do produto: ");
    private JLabel labelcodigo = new JLabel("Digite o CODIGO do produto: ");
    private JLabel labelquantidade = new JLabel("Digite a QUANTIDADE do produto: ");
    private JLabel labelpreco = new JLabel("Digite o Valor do produto: ");
    private JLabel labelpeso = new JLabel("Digite o Peso do produto: ");
    private JLabel labelplano = new JLabel("Digite o Plano do produto: ");
    private JTextField textonome = new JTextField();
    private JTextField textodescricao = new JTextField();
    private JTextField textocodigo = new JTextField();
    private JTextField textoquantidade = new JTextField();
    private JTextField textopreco = new JTextField();
    private JTextField textopeso = new JTextField();
    private JTextField textoplano = new JTextField();
    private JSplitPane splitPane = new JSplitPane();
    private JButton salvar = new JButton("SALVAR");
    private JComboBox<String> combo = new JComboBox<String>();
    private Controller controller = new Controller();

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
                        controller.updateProdutoCaneca(listaf, textonome.getText(), textodescricao.getText(), textocodigo.getText(), textoquantidade.getText(), textopreco.getText(), textopeso.getText());
                    }else if(combo.getSelectedItem().equals("Cursos")){
                        controller.updateProdutoCurso(listav, textonome.getText(), textodescricao.getText(), textocodigo.getText(), textoquantidade.getText(), textopreco.getText(), textoplano.getText());
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
