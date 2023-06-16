package com.projeto.view;

import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TelaPrincipal extends JDialog{

    JFrame frame = new JFrame();
    private JPanel jPanel = new JPanel(new GridBagLayout());
    
    JLabel labelfilial = new JLabel("Tela Filial");
    JButton Cadastrofilial = new JButton("Cadastro");
    JButton Listafilial = new JButton("Lista");
    JButton Updatefilial = new JButton("Update");
    JButton Deletefilial= new JButton("Delete");

    JLabel labelproduto = new JLabel("Tela Produto");
    JButton Cadastroproduto = new JButton("Cadastro");
    JButton Listaproduto = new JButton("Lista");
    JButton Updateproduto = new JButton("Update");
    JButton Deleteproduto = new JButton("Delete");

    public TelaPrincipal() {
        setTitle("Tela Princial");
        setSize(600, 480);
        setLocationRelativeTo(null);

        GridBagConstraints gridBagConstains = new GridBagConstraints();
        //alinhando a tabela
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        
        //margem entre os itens
        gridBagConstains.insets = new Insets(25,25,0,25);
        //alinhamento
        gridBagConstains.anchor = GridBagConstraints.CENTER;
        
        labelproduto.setPreferredSize(new Dimension(77,25));
        jPanel.add(labelproduto, gridBagConstains);

        gridBagConstains.gridx ++;
        labelfilial.setPreferredSize(new Dimension(77,25));
        jPanel.add(labelfilial, gridBagConstains);
        gridBagConstains.gridx --;

        Cadastroproduto.setPreferredSize(new Dimension(200,50));
        gridBagConstains.gridy ++;
        jPanel.add(Cadastroproduto, gridBagConstains);

        gridBagConstains.gridx ++;
        Cadastrofilial.setPreferredSize(new Dimension(200,50));
        jPanel.add(Cadastrofilial, gridBagConstains);
        gridBagConstains.gridx --;
       
        Listaproduto.setPreferredSize(new Dimension(200,50));
        gridBagConstains.gridy ++;
        jPanel.add(Listaproduto, gridBagConstains);

        gridBagConstains.gridx ++;
        Listafilial.setPreferredSize(new Dimension(200,50));
        jPanel.add(Listafilial, gridBagConstains);
        gridBagConstains.gridx --;

        Updateproduto.setPreferredSize(new Dimension(200,50));
        gridBagConstains.gridy ++;
        jPanel.add(Updateproduto, gridBagConstains);

        gridBagConstains.gridx ++;
        Updatefilial.setPreferredSize(new Dimension(200,50));
        jPanel.add(Updatefilial, gridBagConstains);
        gridBagConstains.gridx --;

        Deleteproduto.setPreferredSize(new Dimension(200,50));
        gridBagConstains.gridy ++;
        jPanel.add(Deleteproduto, gridBagConstains);

        gridBagConstains.gridx ++;
        Deletefilial.setPreferredSize(new Dimension(200,50));
        jPanel.add(Deletefilial, gridBagConstains);
        gridBagConstains.gridx --;
            
        Cadastroproduto.addActionListener(acao -> {
            TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
        });
        Cadastrofilial.addActionListener(acao -> {
            TelaCadastroFilial telaCadastroFilial = new TelaCadastroFilial();
        });
        Updateproduto.addActionListener(acao -> {
            TelaUpdateProduto telaUpdateproduto = new TelaUpdateProduto();
        });
        Updatefilial.addActionListener(acao -> {
            TelaUpdateFilial telaUpdatefilial = new TelaUpdateFilial();
        });
        Listaproduto.addActionListener(acao -> {
            TelaListaProduto telaListaproduto = new TelaListaProduto();
        });
        Listafilial.addActionListener(acao -> {
            TelaListaFilial telaListafilial = new TelaListaFilial();
        });
        Deleteproduto.addActionListener(acao -> {
            TelaRemoverProdutos telaRemoverProdutos = new TelaRemoverProdutos();
        });
        Deletefilial.addActionListener(acao -> {
            TelaRemoverFilial telaRemoverFilial = new TelaRemoverFilial();
        });

        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}