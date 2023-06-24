package com.projeto.model;

/**
 * @author Leonardo, Felipe, Joao
 * Classe abstrata model Produto, Mae das classes curso e caneca
*/

public abstract class Produto {
    protected String nome;
    protected String descricao;
    protected int codigo;
    protected int quantidade;
    protected float preco;
    protected String filial;
    
    /**
     * @param nome
     * @param descricao
     * @param codigo
     * @param quantidade
     * @param preco
     * @param filial
     */
    protected Produto(String nome, String descricao, int codigo, int quantidade, float preco, String filial) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.filial = filial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }
}
