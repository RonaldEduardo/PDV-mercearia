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
        // aqui ficou bom
        int codigoProduto = produtoRepository.ProdutosDisponiveis().size() + 1;
        Produto nProduto = new Produto(codigoProduto);

        System.out.println("Digite o nome do produto: ");
        nProduto.setNome(scanner.nextLine());

        System.out.println("Digite o preço do produto: ");
        nProduto.setPreco(scanner.nextDouble());
        scanner.nextLine();

//        // aqui ainda vc ta validando se o preco deve ser maior que 0, melhor jogar pra outro ponto
//        while (precoProduto <= 0) {
//            System.out.println("o preço do produtop deve ser positivo, digite novamente: ");
//            precoProduto =  scanner.nextDouble();
//            scanner.nextLine();
//        }

        System.out.println("Digite o estoque do produto");
        nProduto.setEstoque(scanner.nextDouble());
        scanner.nextLine();

//        // mesma coisa
//        while (estoqueProduto <= 0) {
//            System.out.println("o valor do estoque deve ser positivo, digite novamente: ");
//            estoqueProduto =  scanner.nextDouble();
//            scanner.nextLine();
//        }
        produtoRepository.adicionarProduto(nProduto);
    }

    public void vizualizarProdutosDisponiveis() {
        System.out.println("PRODUTOS DISPONIVEIS: \n");
        for (Produto produto : produtoRepository.ProdutosDisponiveis()) {
//            if (produto.getEstoque() <= 0) {
//
//            } else {
                System.out.println("#" + produto.getCodigo() + " | " + produto.getNome()
                        + " | Quantidade no estoque: " + produto.getEstoque() + "\n");
//            }
        }
    }

    public void vizualizarEstoqueProdutos() {
        for (int i = 0; i < produtoRepository.ProdutosDisponiveis().size(); i++) {
            System.out.println("Nome do produto: " + produtoRepository.ProdutosDisponiveis().get(i).getNome());
            System.out.println("Estoque: " + produtoRepository.ProdutosDisponiveis().get(i).getEstoque() + "\n");
        }
    }


}

