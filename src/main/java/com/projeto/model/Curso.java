package com.projeto.model;


/**
 * @author Leonardo, Felipe, Joao
 * Classe model Curso, para instanciar o produto Curso
*/
public class Curso extends Produto{
    
    /**
     * tipos de planos: Basico e Premium
     */
    private String plano = "Basico";

    /**
     * @param nome
     * @param descricao
     * @param codigo
     * @param quantidade
     * @param preco
     * @param filial
     */
    public Curso(String nome, String descricao, int codigo, int quantidade, float preco, String filial) {
        super(nome, descricao, codigo, quantidade, preco, filial);
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        if ("Premium".equals(plano)) {
            this.plano = plano;
            setPreco(getPreco() + 20);
        }else{
            throw new IllegalArgumentException("Erro");
        }
    }
    @Override
    public String toString() {
        return "nome: " + getNome();
    }
}
