package com.pdv.mercearia.domain;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private double estoque;

    public Produto(int codigo, String nome, double preco, double estoque){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome(){
        return nome;
    }

    public int getCodigo(){
        return codigo;
    }

    public double getEstoque(){
        return estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public double diminuiEstoque(double qtdProduto){
       double novoEstoque = estoque - qtdProduto;
       setEstoque(novoEstoque);
       return novoEstoque;
    }

}
