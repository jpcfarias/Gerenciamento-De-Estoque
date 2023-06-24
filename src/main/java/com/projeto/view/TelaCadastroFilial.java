package com.projeto.view;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.control.Controller;

import java.awt.*;

public class TelaCadastroFilial extends JFrame {
    private JPanel jPanel = new JPanel(new GridBagLayout());
    private JLabel labelnome = new JLabel("Digite o Nome da Filial: ");
    private JLabel labelendereco = new JLabel("Digite a Cidade da Filial: ");
    private JTextField textonome = new JTextField();
    private JTextField textoendereco = new JTextField();
    private JButton buttoncadastrar = new JButton("Cadastrar");
    private Controller controller = new Controller();
    
    public TelaCadastroFilial(){
        setTitle("TelaCadastro");
        setSize(480, 480);
        setLocationRelativeTo(null);

        GridBagConstraints gridBagConstains = new GridBagConstraints();
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;

        //Label nome
        labelnome.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelnome, gridBagConstains);
        //textfild nome
        textonome.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textonome, gridBagConstains);
        
        //Label endereco
        labelendereco.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(labelendereco, gridBagConstains);
        //textfild endereco
        textoendereco.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        jPanel.add(textoendereco, gridBagConstains);
        
        //buttoncadastrar
        buttoncadastrar.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy = 13;
        jPanel.add(buttoncadastrar, gridBagConstains);

        buttoncadastrar.addActionListener(acao -> {
            try {
                String nome = textonome.getText();
                String endereco = textoendereco.getText();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            
                controller.salvarFilial(nome, endereco, gson);

                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso");

                TelaCadastroFilial.this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar uma filial");
            }
           
        });

        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
