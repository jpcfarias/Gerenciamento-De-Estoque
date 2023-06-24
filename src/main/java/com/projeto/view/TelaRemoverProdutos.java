package com.projeto.view;

import javax.swing.*;

import com.projeto.control.Controller;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

/**
 * @author Leonardo, Felipe, Joao
 * Classe view Tela para remover Produtos cadastradas, por nome ou codigo
*/

public class TelaRemoverProdutos extends JFrame{

    private JPanel jPanel = new JPanel(new GridBagLayout());
    private JLabel labelnome = new JLabel("Nome do Produto: ");
    private JLabel labelcodigo = new JLabel("Codigo do Produto: ");
    private JTextField textonome = new JTextField();
    private JTextField textocodigo = new JTextField();
    private JButton remover = new JButton("Remover");

    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton buttonnome = new JRadioButton("", true) ;
    private JRadioButton buttoncodigo = new JRadioButton(); 

    private Controller controller = new Controller();

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
            

            try {
                controller.removerProduto(buttonnome, textonome.getText(), buttoncodigo, textocodigo.getText());
                
                JOptionPane.showMessageDialog(this, "Produto deletado com sucesso");

                TelaRemoverProdutos.this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
