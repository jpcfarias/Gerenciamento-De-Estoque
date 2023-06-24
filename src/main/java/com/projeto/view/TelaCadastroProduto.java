package com.projeto.view;


import javax.swing.*;

import java.awt.*;

import com.projeto.control.Controller;

public class TelaCadastroProduto extends JDialog{
    private JPanel jPanel = new JPanel(new GridBagLayout());
    //declarando as label's
    private JLabel labelnome = new JLabel("Digite o Nome do produto: ");
    private JLabel labeldescricao = new JLabel("Digite a Descricao do produto: ");
    private JLabel labelcodigo = new JLabel("Digite o codigo do produto: ");
    private JLabel labelquantidade = new JLabel("Digite a Quantidade: ");
    private JLabel labelpreco = new JLabel("Digite o Valor: ");
    private JLabel labelpeso = new JLabel("Digite o Peso: ");
    //declarando os textfild
    private JTextField textonome = new JTextField();
    private JTextField textodescricao = new JTextField();
    private JTextField textocodigo = new JTextField();
    private JTextField textoquantidade = new JTextField();
    private JTextField textopreco = new JTextField();
    private JTextField textopeso = new JTextField();
    //declarando os botoes
    private JButton ok = new JButton("OK");
    //declarando combobox
    private JComboBox<String> combo = new JComboBox<String>();
    private JComboBox<String> combofilial = new JComboBox<String>();
    //lista
    private Controller controller = new Controller();
    
    
    public TelaCadastroProduto(){
        //Setando configuraçao da janela
        setTitle("TelaCadastro");
        setSize(480, 480);
        setLocationRelativeTo(null);

        //organizacao dos itens na janela
        GridBagConstraints gridBagConstains = new GridBagConstraints();
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        
        //comboproduto
        jPanel.add(combo, gridBagConstains);
        combo.addItem("Selecione");
        combo.addItem("Caneca");
        combo.addItem("Curso");

        //margem entre as combobox jogando a combox de filiais um pouco mais para a direita
        gridBagConstains.insets = new Insets(0,25,0,0);
        gridBagConstains.gridx ++;
        
        //combofilial
        jPanel.add(combofilial, gridBagConstains);

        //retornando ao o eixo x ao normal , zerando a marge a esquerda e o resto dos itens passa a ocupara 2 celulas
        gridBagConstains.gridx --;
        gridBagConstains.insets.left = 0;
        gridBagConstains.gridwidth = 2;

        //Label nome
        labelnome.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelnome, gridBagConstains);
        //textfild nome
        textonome.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textonome, gridBagConstains);
        
        //Label descricao
        labeldescricao.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labeldescricao, gridBagConstains);
        //textfild descricao
        textodescricao.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textodescricao, gridBagConstains);
        
        //Label codigo
        labelcodigo.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelcodigo, gridBagConstains);
        //textfild codigo
        textocodigo.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textocodigo, gridBagConstains);
        
        //Label quantidade
        labelquantidade.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelquantidade, gridBagConstains);
        //textfild quantidade
        textoquantidade.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textoquantidade, gridBagConstains);
        
        //Label preco
        labelpreco.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelpreco, gridBagConstains);
        //textfild preco
        textopreco.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textopreco, gridBagConstains);

        //okbutton
        ok.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy = 13;
        jPanel.add(ok, gridBagConstains);
        gridBagConstains.gridwidth = 1;
        
        //codigo para ler o json das filiais
        combofilial = controller.comboFilial("filiais.json", combofilial);

        //algoritimo para selecionar o tipo do produto e mudar os campos na tela
        combo.addActionListener(actioncombobox -> {
            if(combo.getSelectedItem().equals("Curso")){

                jPanel.remove(labelpeso);
                jPanel.remove(textopeso);
                jPanel.revalidate();
                jPanel.repaint();
                        
            }else if(combo.getSelectedItem().equals("Caneca")){
                //label peso
                gridBagConstains.gridwidth = 2;
                labelpeso.setPreferredSize(new Dimension(200,25));
                gridBagConstains.gridy = 11;
                jPanel.add(labelpeso, gridBagConstains);
                //textfild peso
                textopeso.setPreferredSize(new Dimension(200,25));
                gridBagConstains.gridy = 12;
                jPanel.add(textopeso, gridBagConstains);

                jPanel.revalidate();
                jPanel.repaint();
                
            }
        });
        
        //codigo para fixar as informaçoes digitadas no json
        ok.addActionListener(acao -> {
            Object isSelected = combo.getSelectedItem();
            
            if(isSelected.equals("Curso")){            
                
                try{
                    String nome = textonome.getText();
                    String descricao = textodescricao.getText();
                    int codigo = Integer.parseInt(textocodigo.getText());
                    float preco = Float.parseFloat(textopreco.getText());
                    int quantidade = Integer.parseInt(textoquantidade.getText());

                    controller.salvarCurso(nome, descricao, codigo, quantidade, preco, combofilial);

                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");

                    TelaCadastroProduto.this.dispose();

                }catch(java.lang.NumberFormatException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Curso");
                }
            }else if(isSelected.equals("Caneca")){
                
                try{
                    String nome = textonome.getText();
                    String descricao = textodescricao.getText();
                    float peso = Float.parseFloat(textopeso.getText());
                    int codigo = Integer.parseInt(textocodigo.getText());
                    float preco = Float.parseFloat(textopreco.getText());
                    int quantidade = Integer.parseInt(textoquantidade.getText());
                    
                    controller.salvarCaneca(nome, descricao, codigo, quantidade, preco, combofilial, peso);

                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
                    
                    TelaCadastroProduto.this.dispose();
                }catch(java.lang.NumberFormatException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Caneca Caneca");
                }
            }
        }
        );

        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
}
