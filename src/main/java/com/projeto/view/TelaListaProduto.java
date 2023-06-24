package com.projeto.view;

import com.projeto.model.Caneca;
import com.projeto.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
/**
 * @author Leonardo, Felipe, Joao
 * Classe view Tela para ver todos os produtos cadastrados, com filtro de busca
*/
public class TelaListaProduto extends JDialog {
    
    private ArrayList<Caneca> listaf = new ArrayList<Caneca>();
    private ArrayList<Curso> listav = new ArrayList<Curso>();
    private JPanel jPanel = new JPanel(new GridBagLayout(), false);
    private JLabel labeltitulo = new JLabel("Lista com os produtos:");
    private JTextField textobuscar = new JTextField();
    private JButton buttonbuscar = new JButton("Buscar");
    private JButton buttonfechar = new JButton("Fechar");
    private DefaultTableCellRenderer cellRenderer;
    private JScrollPane scrollPanef = new JScrollPane();
    private JScrollPane scrollPanev = new JScrollPane();

    public TelaListaProduto() {
        setTitle("Tela Lista");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        GridBagConstraints gridBagConstains = new GridBagConstraints();
        gridBagConstains.insets = new Insets(5,0,0,0);
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        gridBagConstains.anchor = GridBagConstraints.CENTER;
        gridBagConstains.gridwidth = 2;
        
        labeltitulo.setPreferredSize(new Dimension(200,25));
        jPanel.add(labeltitulo, gridBagConstains);
        gridBagConstains.gridwidth = 1;

        gridBagConstains.gridy = 2;
        textobuscar.setPreferredSize(new Dimension(100,25));
        jPanel.add(textobuscar, gridBagConstains);
        gridBagConstains.gridx = 1;
        buttonbuscar.setPreferredSize(new Dimension(100,25));
        jPanel.add(buttonbuscar, gridBagConstains);
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;

        try {
            FileReader readerf = new FileReader("caneca.json");
            JsonArray jsonArrayf = (JsonArray) JsonParser.parseReader(readerf);
            
            for (JsonElement jsonElement : jsonArrayf){
                Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
                listaf.add(cadastro1);
            }
            gridBagConstains.gridy ++;
            DefaultTableModel modelf = new DefaultTableModel();
            modelf.addColumn("nome");
            modelf.addColumn("descricao");
            modelf.addColumn("Filial");
            modelf.addColumn("quantidade");
            modelf.addColumn("valor");
            modelf.addColumn("peso");
            for (int i = 0; i < listaf.size(); i++) {
                modelf.addRow(new Object[]{listaf.get(i).getNome(), listaf.get(i).getDescricao(), listaf.get(i).getFilial(), listaf.get(i).getQuantidade(), listaf.get(i).getPreco(), listaf.get(i).getPeso()});
            }
            JTable jtablef = new JTable(modelf){
                private static final long serialVersionUID = 1L;
        
                public boolean isCellEditable(int row, int column) {                
                        return false;               
                };
            };

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jtablef.getModel());
            jtablef.setRowSorter(sorter);
            buttonbuscar.addActionListener(acao1 -> {
                String text = textobuscar.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                }
            });

            jtablef.setCellSelectionEnabled(false);
            jtablef.setFocusable(false);
            scrollPanef.setViewportView(jtablef);
            jtablef.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtablef.getColumnModel().getColumn(2).setPreferredWidth(100);
            cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(JLabel.CENTER);
            jtablef.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
            jPanel.add(scrollPanef, gridBagConstains);

            FileReader readerv = new FileReader("curso.json");
            JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
            
            for (JsonElement jsonElement : jsonArrayv){
                Curso cadastro2 = new Gson().fromJson(jsonElement, Curso.class);
                listav.add(cadastro2);
            }
            gridBagConstains.gridx ++;
            DefaultTableModel modelv = new DefaultTableModel();
            modelv.addColumn("nome");
            modelv.addColumn("descricao");
            modelv.addColumn("Filial");
            modelv.addColumn("quantidade");
            modelv.addColumn("valor");
            modelv.addColumn("plano");
            for (int i = 0; i < listav.size(); i++) {
                modelv.addRow(new Object[]{listav.get(i).getNome(), listav.get(i).getDescricao(), listav.get(i).getFilial(), listav.get(i).getQuantidade(), listav.get(i).getPreco(), listav.get(i).getPlano()});
            }
            JTable jtablev = new JTable(modelv){
                private static final long serialVersionUID = 1L;
        
                public boolean isCellEditable(int row, int column) {                
                        return false;               
                };
            };

            TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jtablev.getModel());
            jtablev.setRowSorter(sorter2);

            buttonbuscar.addActionListener(acao2 -> {
                    String text = textobuscar.getText();
                    if (text.length() == 0) {
                        sorter.setRowFilter(null);
                        sorter2.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                        sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    }
                });

            jtablev.setCellSelectionEnabled(false);
            jtablev.setFocusable(false);
            scrollPanev.setViewportView(jtablev);
            jtablev.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtablev.getColumnModel().getColumn(2).setPreferredWidth(100);
            cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(JLabel.CENTER);
            jtablev.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
            jPanel.add(scrollPanev, gridBagConstains);
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        gridBagConstains.gridwidth = 2;
        buttonfechar.setPreferredSize(new Dimension(200,25));
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        jPanel.add(buttonfechar, gridBagConstains);
        gridBagConstains.gridwidth = 1;
        buttonfechar.addActionListener(acao -> {
            TelaListaProduto.this.dispose();
        });

        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
     
}

