package com.projeto.model;

import java.util.ArrayList;

public class Filial{
    private String nome;
    private String endereco;
    private int numerodeprodutos;
    private ArrayList<Produto> listaDeProdutosNaFilial;
    
    public Filial(String nome, String endereco) {
        this.endereco = endereco;
        this.nome = nome;
        this.numerodeprodutos = 0;
        ArrayList<Produto> listaDeProdutosNaFilial = new ArrayList<Produto>();
    }

    public int getNumerodeprodutos() {
        return numerodeprodutos;
    }

    public void setNumerodeprodutos(int numerodeprodutos) {
        this.numerodeprodutos = numerodeprodutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Produto> getListaDeProdutosNaFilial() {
        return listaDeProdutosNaFilial;
    }

    public void addListaDeProdutosNaFilial(Produto produto) {
        listaDeProdutosNaFilial.add(produto);
    }

    @Override
    public String toString() {
        return "nome: " + getNome();
    }
}
