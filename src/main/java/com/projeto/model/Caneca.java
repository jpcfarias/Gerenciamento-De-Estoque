package com.projeto.model;


/**
 * @author Leonardo, Felipe, Joao
 * Classe model caneca, para instanciar o produto caneca
*/
public class Caneca extends Produto{

    private float peso;

    /**
     * @param nome
     * @param descricao
     * @param codigo
     * @param quantidade
     * @param preco
     * @param filial
     * @param peso
     */
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
