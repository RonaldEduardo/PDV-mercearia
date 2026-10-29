package com.pdv.mercearia.ui;

import com.pdv.mercearia.domain.Produto;
import com.pdv.mercearia.repository.ProdutoRepository;

import java.util.Scanner;

public class ProdutoMenu {
    private ProdutoRepository produtoRepository;
    private Scanner scanner;

    public ProdutoMenu(ProdutoRepository produtoRepository, Scanner scanner) {
        this.produtoRepository = produtoRepository;
        this.scanner = scanner;
    }

    public void cadastrarProduto() {
        int codigoProduto = produtoRepository.ProdutosDisponiveis().size() + 1;

        System.out.println("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.println("Digite o preço do produto: ");
        double precoProduto =  scanner.nextDouble();
        scanner.nextLine();
        while (precoProduto <= 0) {
            System.out.println("o preço do produtop deve ser positivo, digite novamente: ");
            precoProduto =  scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.println("Digite o estoque do produto");
        double estoqueProduto = scanner.nextDouble();
        scanner.nextLine();
        while (estoqueProduto <= 0) {
            System.out.println("o valor do estoque deve ser positivo, digite novamente: ");
            estoqueProduto =  scanner.nextDouble();
            scanner.nextLine();
        }
        Produto produto = new Produto(codigoProduto, nomeProduto, precoProduto, estoqueProduto);
        produtoRepository.adicionarProduto(produto);

        System.out.println("Produto cadastrado com secesso!");
    }

    public void vizualizarProdutosDisponiveis() {
        System.out.println("PRODUTOS DISPONIVEIS: \n");
        for (Produto produto : produtoRepository.ProdutosDisponiveis()) {
            if (produto.getEstoque() <= 0) {

            } else {
                System.out.println("#" + produto.getCodigo() + " | " + produto.getNome()
                        + " | Quantidade no estoque: " + produto.getEstoque() + "\n");
            }
        }
    }

    public void vizualizarEstoqueProdutos() {
        for (int i = 0; i < produtoRepository.ProdutosDisponiveis().size(); i++) {
            System.out.println("Nome do produto: " + produtoRepository.ProdutosDisponiveis().get(i).getNome());
            System.out.println("Estoque: " + produtoRepository.ProdutosDisponiveis().get(i).getEstoque() + "\n");
        }
    }


}

