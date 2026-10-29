package com.pdv.mercearia.repository;

import com.pdv.mercearia.domain.Produto;

import java.util.ArrayList;

public class ProdutoRepository {
    private ArrayList<Produto> produtosDisponiveis = new ArrayList<>();

    public ArrayList<Produto> ProdutosDisponiveis(){
        return produtosDisponiveis;
    }

    public void adicionarProduto(Produto produto){
        produtosDisponiveis.add(produto);
        System.out.printf("Produto cadastrado com sucesso!!");
    }
}


