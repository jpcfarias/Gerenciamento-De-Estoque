package com.projeto;

import com.projeto.view.TelaPrincipal;

public class Main{
    public static void main(String[] args) {
        try {
            TelaPrincipal telaprincipal = new TelaPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}