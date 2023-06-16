package com.projeto.view;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;
import com.projeto.model.Filial;

public class TelaListaFilial extends JFrame{
    private JPanel jPanel = new JPanel(new GridBagLayout(), false);
    JComboBox<String> combofilial = new JComboBox<>();
    private transient ArrayList<Caneca> listaf = new ArrayList<>();
    private transient ArrayList<Curso> listav = new ArrayList<>();
    private transient ArrayList<Filial> listafilial = new ArrayList<>();
    private transient ArrayList<Object> listatotal = new ArrayList<>();
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableCellRenderer cellRenderer;
    
    public TelaListaFilial() {
        setTitle("Tela Lista");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        GridBagConstraints gridBagConstains = new GridBagConstraints();
        gridBagConstains.insets = new Insets(5,0,0,0);
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        
        jPanel.add(combofilial, gridBagConstains);
        gridBagConstains.gridy ++;
        try{
            combofilial = listarr.filialcombobox("filiais.json", combofilial);
            listafilial = listarr.filiallista("filiais.json", listafilial);
            listaf = listarr.produtoFisico("caneca.json", listaf);
            listav = listarr.produtoVirtual("curso.json", listav);
            listatotal.addAll(listaf);
            listatotal.addAll(listav);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Filial");
            model.addColumn("Endereco");
            model.addColumn("Quantidade de Produtos");
            for (int i = 0; i < listafilial.size(); i++) {
                model.addRow(new Object[]{listafilial.get(i).getNome(), listafilial.get(i).getEndereco(), listafilial.get(i).getNumerodeprodutos()});
            }

            JTable jtable = new JTable(model){
                private static final long serialVersionUID = 1L;
        
                @Override
                public boolean isCellEditable(int row, int column) {                
                        return false;               
                }
            };
            jtable.setCellSelectionEnabled(false);
            jtable.setFocusable(false);
            scrollPane.setViewportView(jtable);
            jtable.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtable.getColumnModel().getColumn(2).setPreferredWidth(100);
            cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(JLabel.CENTER);
            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
            jPanel.add(scrollPane, gridBagConstains);
        }catch(Exception e){
            e.printStackTrace();
        }
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }  
}

class listarr{
    public static JComboBox<String> filialcombobox(String cominhodoarquivo,JComboBox<String> combofilial){
        try{
            FileReader readerfilial = new FileReader(cominhodoarquivo);
            JsonArray jsonArrayfilial = (JsonArray) JsonParser.parseReader(readerfilial);
        
            for (JsonElement jsonElement : jsonArrayfilial){
                Filial cadastrofilial = new Gson().fromJson(jsonElement, Filial.class);
                combofilial.addItem(cadastrofilial.getEndereco());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return combofilial;
    }

    public static ArrayList<Filial> filiallista(String cominhodoarquivo,ArrayList<Filial> listafilial){
        try{
            FileReader readerfilial = new FileReader(cominhodoarquivo);
            JsonArray jsonArrayfilial = (JsonArray) JsonParser.parseReader(readerfilial);
        
            for (JsonElement jsonElement : jsonArrayfilial){
                Filial cadastrofilial = new Gson().fromJson(jsonElement, Filial.class);
                listafilial.add(cadastrofilial);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listafilial;
    }

    public static ArrayList<Caneca> produtoFisico(String cominhodoarquivo, ArrayList<Caneca> listaf) throws FileNotFoundException{
        FileReader readerf = new FileReader("caneca.json");
        JsonArray jsonArrayf = (JsonArray) JsonParser.parseReader(readerf);
        
        for (JsonElement jsonElement : jsonArrayf){
            Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
            listaf.add(cadastro1);
        }
        return listaf;
    }

    public static ArrayList<Curso> produtoVirtual(String cominhodoarquivo, ArrayList<Curso> listav) throws FileNotFoundException{
        FileReader readerv = new FileReader("curso.json");
        JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
        
        for (JsonElement jsonElement : jsonArrayv){
            Curso cadastro2 = new Gson().fromJson(jsonElement, Curso.class);
            listav.add(cadastro2);
        }
        return listav;
    }
}
