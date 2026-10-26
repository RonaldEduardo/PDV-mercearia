package com.pdv.mercearia.domain;

import java.util.ArrayList;

public class Compra {
    private ArrayList<ItemCompra> produtosItemCompra;

    public Compra(){
        this.produtosItemCompra = new ArrayList<>();
    }

    public void adicionarItemCompra(ItemCompra itemCompra){
        produtosItemCompra.add(itemCompra);
    }

    public ArrayList<ItemCompra> produtosItemCompra(){
        return produtosItemCompra;
    }

    public double calculaValorTotal(){
        double valorTotal = 0;
        double valorProduto = 0;
        for (int i = 0; i < produtosItemCompra.size(); i++){
            valorProduto = produtosItemCompra.get(i).getProduto().getPreco() * produtosItemCompra.get(i).getQtdProduto();
            valorTotal = valorTotal + valorProduto;
        }
        return valorTotal;

    }
}
