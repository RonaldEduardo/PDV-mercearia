package com.pdv.mercearia.domain;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private double estoque;

    public Produto(int codigo, String nome, double preco, double estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getEstoque() {
        return estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setEstoque(double estoque) {
        if (estoque < 0) System.out.println("O estoque deve ser positivo ou zero !!"); // pq vc pode ter um estoque zerado, mas inicial nunca negativo
        this.estoque = estoque;
    }

    public void setNome(String nome){
        if(nome.isEmpty() || nome.isBlank()) System.out.println("Nome nao pode ser vazio!!");
        this.nome = nome;
    }

    public void setPreco(double preco){
        if(preco <= 0) System.out.println("O preco deve ser positivo!!");
        this.preco = preco;
    }

    public double diminuiEstoque(double qtdProduto) {
        double novoEstoque = estoque - qtdProduto;
        setEstoque(novoEstoque);
        return novoEstoque;
    }


}
