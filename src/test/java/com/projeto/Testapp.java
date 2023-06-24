package com.projeto;

import static org.junit.Assert.assertTrue;

import javax.swing.JComboBox;

import org.junit.Test;

import com.projeto.control.Controller;

public class Testapp {
    private Controller controller = new Controller();
    private JComboBox<String> combofilial = new JComboBox<String>();

    @Test
    public void testSalvarFilial(){

        assertTrue(controller.salvarFilial("Filial 1", "Rua qualquer"));
    }
    @Test
    public void testSalvarCaneca(){
        combofilial.addItem("Filial qualquer");

        assertTrue(controller.salvarCaneca("Caneca 1", "Caneca maneira", 32, 3, 25.5f, combofilial, 2.0f));
    }
    @Test
    public void testSalvarCurso(){
        combofilial.addItem("Filial qualquer");

        assertTrue(controller.salvarCurso("Curso 1", "Curso para o ita", 123, 30, 3000.0f, combofilial));
    }
}
