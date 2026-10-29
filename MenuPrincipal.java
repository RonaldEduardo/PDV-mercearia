package com.pdv.mercearia.ui;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private ProdutoMenu produtoMenu;
    private CompraMenu compraMenu;

    public MenuPrincipal(Scanner scanner, ProdutoMenu produtoMenu, CompraMenu compraMenu) {
        this.scanner = scanner;
        this.produtoMenu = produtoMenu;
        this.compraMenu = compraMenu;

    }

    public void mostrarMenu() {
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos disponiveis");
        System.out.println("3 - Fazer uma nova compra");
        System.out.println("4 - Vizualizar estoque de produto");
        System.out.println("0 - Sair");
    }

    public void executar() {
        int opcao;
        do {
            mostrarMenu();
            opcao =  scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    produtoMenu.cadastrarProduto();
                    break;

                case 2:
                    produtoMenu.vizualizarProdutosDisponiveis();
                    break;

                case 3:
                    compraMenu.realizarNovaCompra();
                    break;

                case 4:
                    produtoMenu.vizualizarEstoqueProdutos();
                    break;

                case 0:
                    System.out.println("saiondo..");
                    break;

                default:
                    System.out.println("opcao invalida");
            }

        } while (opcao != 0);

    }
}
