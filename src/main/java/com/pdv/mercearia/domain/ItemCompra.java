package com.pdv.mercearia.domain;

public class ItemCompra {
    private Produto produto;
    private double qtdProduto;

    public ItemCompra(Produto produto, double qtdProduto){
        this.produto = produto;
        this.qtdProduto = qtdProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getQtdProduto() {
        return qtdProduto;
    }
}
