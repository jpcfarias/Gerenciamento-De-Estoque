package com.projeto.model;

public class Caneca extends Produto{

    private float peso;

    public Caneca(String nome, String descricao, int codigo, int quantidade, float preco, String filial,float peso) {
        super(nome, descricao, codigo, quantidade, preco, filial);
        this.peso = peso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    @Override
    public String toString() {
        return "nome: " + getNome();
    }
}
