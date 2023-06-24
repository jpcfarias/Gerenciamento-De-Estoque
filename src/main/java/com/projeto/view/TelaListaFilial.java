package com.projeto.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.projeto.control.Controller;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;
import com.projeto.model.Filial;

/**
 * @author Leonardo, Felipe, Joao
 * Classe view Tela para ver todas as filiais cadastradas, com filtro de itens disponiveis em estoque e com estoque zerado
*/

public class TelaListaFilial extends JFrame{
    private JPanel jPanel = new JPanel(new GridBagLayout(), false);
    private JComboBox<String> combofilial = new JComboBox<>();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton buttondisponivel = new JRadioButton("", true) ;
    private JRadioButton buttonzerado = new JRadioButton(); 
    private JLabel labeldisponivel = new JLabel("Disponivel: ");
    private JLabel labelzero = new JLabel("Zerado: ");
    private ArrayList<Caneca> listaf = new ArrayList<>();
    private ArrayList<Curso> listav = new ArrayList<>();
    private ArrayList<Filial> listafilial = new ArrayList<>();
    private ArrayList<Object> listatotal = new ArrayList<>();
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableCellRenderer cellRenderer;
    private Controller controller = new Controller();
    
    public TelaListaFilial() {
        setTitle("Tela Lista");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        GridBagConstraints gridBagConstains = new GridBagConstraints();
        gridBagConstains.insets = new Insets(5,0,0,0);
        gridBagConstains.gridx = 0;
        gridBagConstains.gridy = 0;
        gridBagConstains.anchor = GridBagConstraints.CENTER;
        gridBagConstains.gridwidth = 2;
        jPanel.add(combofilial, gridBagConstains);
        gridBagConstains.gridwidth = 1;
        gridBagConstains.gridy ++;
        jPanel.add(labeldisponivel, gridBagConstains);
        gridBagConstains.gridx ++;
        jPanel.add(buttondisponivel, gridBagConstains);
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        jPanel.add(labelzero, gridBagConstains);
        gridBagConstains.gridx ++;
        jPanel.add(buttonzerado, gridBagConstains);
        gridBagConstains.gridy ++;
        gridBagConstains.gridx --;
        gridBagConstains.gridwidth = 2;

        buttonGroup.add(buttondisponivel);
        buttonGroup.add(buttonzerado);
        try{
            combofilial.addItem("Todas");
            combofilial = controller.comboFilial("filiais.json", combofilial);
            combofilial.setSelectedItem("Todas");
            listafilial = controller.listaFilial("filiais.json", listafilial);
            listaf = controller.listaCaneca("caneca.json", listaf);
            listav = controller.listaCurso("curso.json", listav);
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

            combofilial.addActionListener(actioncombobox -> {
                jPanel.remove(jtable);
                jPanel.remove(scrollPane);
                DefaultTableModel modelDeprodutos = new DefaultTableModel();
                modelDeprodutos.addColumn("Nome");
                modelDeprodutos.addColumn("Quantidade");

                for (int i = 0; i < listafilial.size(); i++) {
                    if(combofilial.getSelectedItem().equals(listafilial.get(i).getNome())){
                        for(int j = 0; j < listafilial.get(i).getListaDeCanecasNaFilial().size(); j++){
                            if(buttondisponivel.isSelected() == true){
                                if(listafilial.get(i).getCanecaNaFilial(j).getQuantidade() != 0){
                                    modelDeprodutos.addRow(new Object[]{listafilial.get(i).getCanecaNaFilial(j), listafilial.get(i).getCanecaNaFilial(j).getQuantidade()});
                                }
                            }
                            if(buttonzerado.isSelected() == true){
                                if(listafilial.get(i).getCanecaNaFilial(j).getQuantidade() == 0){
                                    modelDeprodutos.addRow(new Object[]{listafilial.get(i).getCanecaNaFilial(j), listafilial.get(i).getCanecaNaFilial(j).getQuantidade()});
                                }
                            }
                        }
                        for(int j = 0; j < listafilial.get(i).getListaDeCursosNaFilial().size(); j++){
                            if(buttondisponivel.isSelected() == true){
                                if(listafilial.get(i).getCursoNaFilial(j).getQuantidade() != 0){
                                    modelDeprodutos.addRow(new Object[]{listafilial.get(i).getCursoNaFilial(j), listafilial.get(i).getCursoNaFilial(j).getQuantidade()});
                                }
                            }
                            if(buttonzerado.isSelected() == true){
                                if(listafilial.get(i).getCursoNaFilial(j).getQuantidade() == 0){
                                    modelDeprodutos.addRow(new Object[]{listafilial.get(i).getCursoNaFilial(j), listafilial.get(i).getCursoNaFilial(j).getQuantidade()});
                                }
                            }
                            
                        }
                    }
                }

                JTable jtableDeProdutos = new JTable(modelDeprodutos){
                    private static final long serialVersionUID = 1L;
            
                    @Override
                    public boolean isCellEditable(int row, int column) {                
                            return false;               
                    }
                };

                jtableDeProdutos.setCellSelectionEnabled(false);
                jtableDeProdutos.setFocusable(false);
                scrollPane.setViewportView(jtableDeProdutos);
                cellRenderer = new DefaultTableCellRenderer();
                cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                jtableDeProdutos.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
                jPanel.add(scrollPane, gridBagConstains);
                
                jPanel.revalidate();
                jPanel.repaint();
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        add(jPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }  
}