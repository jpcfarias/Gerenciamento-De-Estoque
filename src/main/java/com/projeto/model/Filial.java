package com.projeto.model;

import java.util.ArrayList;

public class Filial{
    private String nome;
    private String endereco;
    private int numerodeprodutos;
    private ArrayList<Caneca> listaDeCanecasNaFilial = new ArrayList<Caneca>();
    private ArrayList<Curso> listaDeCursosNaFilial = new ArrayList<Curso>();

    public Filial(String nome, String endereco) {
        this.endereco = endereco;
        this.nome = nome;
        this.numerodeprodutos = 0;
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

    public ArrayList<Caneca> getListaDeCanecasNaFilial() {
        return listaDeCanecasNaFilial;
    }

    public void setListaDeCanecasNaFilial(ArrayList<Caneca> listaDeCanecasNaFilial) {
        this.listaDeCanecasNaFilial = listaDeCanecasNaFilial;
    }

    public ArrayList<Curso> getListaDeCursosNaFilial() {
        return listaDeCursosNaFilial;
    }

    public void setListaDeCursosNaFilial(ArrayList<Curso> listaDeCursosNaFilial) {
        this.listaDeCursosNaFilial = listaDeCursosNaFilial;
    }

    /**
     * adiciona curso na lista de cursos na filial
     * @param caneca
     */
    public void addCanecaNaFilial(Caneca caneca) {
        listaDeCanecasNaFilial.add(caneca);
    }

    /**
     * adiciona curso na lista de cursos na filial
     * @param curso
     */
    public void addCursoNaFilial(Curso curso) {
        listaDeCursosNaFilial.add(curso);
    }

    public Caneca getCanecaNaFilial(int index){
        return listaDeCanecasNaFilial.get(index);
    }

    public Curso getCursoNaFilial(int index){
        return listaDeCursosNaFilial.get(index);
    }

    @Override
    public String toString() {
        return "nome: " + getNome();
    }
}
