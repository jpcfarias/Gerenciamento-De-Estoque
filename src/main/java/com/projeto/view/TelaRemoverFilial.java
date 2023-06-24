package com.projeto.view;

import javax.swing.*;

import com.projeto.control.Controller;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

/**
 * @author Leonardo, Felipe, Joao
 * Classe view Tela para remover Filiais cadastradas, por nome ou endereco
*/

public class TelaRemoverFilial extends JFrame{

    private JPanel jPanel = new JPanel(new GridBagLayout());
    private JLabel labelnome = new JLabel("Nome da Filial: ");
    private JLabel labelendereco = new JLabel("Endereco da Filial: ");
    private JTextField textonome = new JTextField();
    private JTextField textoendereco = new JTextField();
    private JButton remover = new JButton("Remover");

    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton buttonnome = new JRadioButton("", true) ;
    private JRadioButton buttonendereco = new JRadioButton();
    
    private Controller controller = new Controller();

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
            try {
                

                controller.removerFilial(buttonnome, textonome.getText(), buttonendereco, textoendereco.getText() );

                JOptionPane.showMessageDialog(this, "Filial deletada com sucesso");

                TelaRemoverFilial.this.dispose();
            }catch (Exception e) {
                JOptionPane.showMessageDialog(this, "O item nao existe");
                e.printStackTrace();
            }
        });
        
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
