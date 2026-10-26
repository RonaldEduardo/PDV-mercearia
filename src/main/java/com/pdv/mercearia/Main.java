package com.pdv.mercearia;

import com.pdv.mercearia.domain.Compra;
import com.pdv.mercearia.domain.ItemCompra;
import com.pdv.mercearia.domain.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtosDisponiveis = new ArrayList<>();

        int opcao;
        do {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos disponiveis");
            System.out.println("3 - Fazer uma nova compra");
            System.out.println("4 - Vizualizar estoque de produto");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    int codigoProduto = produtosDisponiveis.size() + 1;

                    System.out.println("Digite o nome do produto: ");
                    String nomeProduto = scanner.nextLine();

                    System.out.println("Digite o preço do produto: ");
                    double precoProduto = scanner.nextDouble();
                    scanner.nextLine();
                    while (precoProduto <= 0) {
                        System.out.println("o preço do produtop deve ser positivo, digite novamente: ");
                        precoProduto = scanner.nextDouble();
                        scanner.nextLine();
                    }

                    System.out.println("Digite o estoque do produto");
                    double estoqueProduto = scanner.nextDouble();
                    scanner.nextLine();
                    while (estoqueProduto <= 0) {
                        System.out.println("o valor do estoque deve ser positivo, digite novamente: ");
                        estoqueProduto = scanner.nextDouble();
                        scanner.nextLine();
                    }
                    Produto produto = new Produto(codigoProduto, nomeProduto, precoProduto, estoqueProduto);
                    produtosDisponiveis.add(produto);

                    System.out.println("Produto cadastrado com secesso!");
                    break;

                case 2:
                    System.out.println("PRODUTOS DISPONIVEIS: \n");
                    for (int i = 0; i < produtosDisponiveis.size(); i++) {
                        if (produtosDisponiveis.get(i).getEstoque() <= 0) {

                        } else {
                            System.out.println("#" + produtosDisponiveis.get(i).getCodigo() + " | " + produtosDisponiveis.get(i).getNome()
                                    + " | Quantidade no estoque: " + produtosDisponiveis.get(i).getEstoque() + "\n");
                        }
                    }
                    break;

                case 3:
                    boolean temProdutoDisponivel = false;

                    for (Produto produto1 : produtosDisponiveis) {
                        if (produto1.getEstoque() > 0) {
                            System.out.println("#" + produto1.getCodigo() + " | " + produto1.getNome() + " | Preço: R$ " + produto1.getPreco());

                            temProdutoDisponivel = true;
                        }
                    }

                    if (!temProdutoDisponivel) {
                        System.out.println("nenhum produto disponível ");
                        break;
                    }

                    Compra compra = new Compra();
                    int infoCompra = 1;

                    do {
                        System.out.println("Digite o código do produto que deseja comprar:");
                        int codigoProdutoCompra = scanner.nextInt();
                        scanner.nextLine();

                        if (codigoProdutoCompra < 1 || codigoProdutoCompra > produtosDisponiveis.size()) {
                            System.out.println("cdigo invalido");
                            continue;
                        }

                        Produto produtoEscolhido = produtosDisponiveis.get(codigoProdutoCompra - 1);

                        double quantidadeJaAdicionada = 0;

                        for (ItemCompra item : compra.produtosItemCompra()) {
                            if (item.getProduto().getCodigo() == produtoEscolhido.getCodigo()) {
                                quantidadeJaAdicionada += item.getQtdProduto();
                            }
                        }

                        double quantidadeDisponivel = produtoEscolhido.getEstoque() - quantidadeJaAdicionada;

                        if (quantidadeDisponivel <= 0) {
                            System.out.println("esse produto não esta disponível ");
                            continue;
                        }

                        System.out.println("Disponível: " + quantidadeDisponivel);
                        System.out.println("Digite a quantidade que deseja comprar:");
                        double qtdProduto = scanner.nextDouble();
                        scanner.nextLine();

                        if (!(qtdProduto > 0 && qtdProduto <= quantidadeDisponivel)) {
                            System.out.println("quantidade invalida, digite um valor maior que zero e menor ou igual a " + quantidadeDisponivel);
                            continue;
                        }

                        ItemCompra itemCompra = new ItemCompra(produtoEscolhido, qtdProduto);

                        compra.adicionarItemCompra(itemCompra);

                        do {
                            System.out.println("Deseja comprar mais algum produto?");
                            System.out.println("Sim - 1");
                            System.out.println("Não - 2");
                            infoCompra = scanner.nextInt();
                            scanner.nextLine();

                            if (infoCompra != 1 && infoCompra != 2) {
                                System.out.println("Opção inválida. Digite 1 ou 2.");
                            }
                        } while (infoCompra != 1 && infoCompra != 2);

                    } while (infoCompra == 1);

                    for (ItemCompra item : compra.produtosItemCompra()) {
                        double qtdProduto = item.getQtdProduto();
                        item.getProduto().diminuiEstoque(qtdProduto);
                    }

                    System.out.println("Compra finalizada!");
                    System.out.println("Estoque atualizado!");
                    System.out.println("Resumo da compra:\n");

                    for (ItemCompra item : compra.produtosItemCompra()) {
                        System.out.println("Produto: " + item.getProduto().getNome());
                        System.out.println("quantidade do produto: " + item.getQtdProduto() + "\n");
                    }

                    System.out.println("O valor total ficou: R$ " + compra.calculaValorTotal());

                    break;

                case 4:
                    for (int i = 0; i < produtosDisponiveis.size(); i++) {
                        System.out.println("Nome do produto: " + produtosDisponiveis.get(i).getNome());
                        System.out.println("Estoque: " + produtosDisponiveis.get(i).getEstoque() + "\n");
                    }
            }

        } while (opcao != 0);
    }

}