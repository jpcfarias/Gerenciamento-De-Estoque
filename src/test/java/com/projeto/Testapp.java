package com.projeto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.projeto.control.Controller;

public class Testapp {
    @Test
    public void testSalvarFilial(){
        Controller controller = new Controller();

        assertTrue(controller.salvarFilial("sla", "sla2"));
    }
    @Test
    public void testSalvarCaneca(){
        Controller controller = new Controller();

        assertTrue(controller.salvarCaneca("sla", "sla2"));
    }
    @Test
    public void testSalvarCurso(){
        Controller controller = new Controller();

        assertTrue(controller.salvarFilial("sla", "sla2"));
    }
}
