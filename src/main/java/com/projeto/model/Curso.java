package com.projeto.model;

public class Curso extends Produto{
    //planos disponiveis:Basico(apos a compra), Premium.
    private String plano = "Basico";

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
