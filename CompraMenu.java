package com.pdv.mercearia.ui;

import com.pdv.mercearia.domain.Compra;
import com.pdv.mercearia.domain.ItemCompra;
import com.pdv.mercearia.domain.Produto;
import com.pdv.mercearia.repository.ProdutoRepository;

import java.util.Scanner;

public class CompraMenu {
    private Scanner scanner;
    private ProdutoRepository produtoRepository;

    public CompraMenu(Scanner scanner, ProdutoRepository produtoRepository) {
        this.scanner = scanner;
        this.produtoRepository = produtoRepository;
    }

    private void mostrarResumoCompra(Compra compra) {
        System.out.println("\nResumo da compra:\n");

        for (ItemCompra item : compra.produtosItemCompra()) {
            System.out.println("Produto: " + item.getProduto().getNome());
            System.out.println("quantidade do produto: " + item.getQtdProduto() + "\n");
        }
        System.out.println("O valor total ficou: R$ " + compra.calculaValorTotal());
    }

    private int perguntarSeContinuaCompra(){
        int infoCompra;
        do {

            System.out.println("Deseja comprar mais algum produto?");
            System.out.println("Sim - 1");
            System.out.println("Não - 2");
            infoCompra = scanner.nextInt();
            scanner.nextLine();

            if (infoCompra != 1 && infoCompra != 2) {
                System.out.println("Opção inválida. Digite 1 ou 2.");
            }

            // pq precisa ser diferente 2 se o cara quer sair?
        } while (infoCompra != 1 && infoCompra != 2);

        return infoCompra;
    }

    public void realizarNovaCompra() {
        boolean temProdutoDisponivel = false;
        // da pra melhorar essa logica, pq vc ta pegando cada produto e verificando um a um se esta disponivel, ok. Mas se um deles nao esta disponivel e o proximo estiver disponivel
        // temProdutoDisponivel sempre vai ser true
//        for (Produto produto : produtoRepository.ProdutosDisponiveis()) {
//            if (produto.getEstoque() > 0) {
//                System.out.println("#" + produto.getCodigo() + " | " + produto.getNome() + " | Preço: R$ " + produto.getPreco());
//
//                temProdutoDisponivel = true;
//            }
//        }
//
//        if (!temProdutoDisponivel) {
//            System.out.println("nenhum produto disponível ");
//            return;
//        }

        Compra compra = new Compra();
        int infoCompra = 1;

        do {
            System.out.println("Digite o código do produto que deseja comprar:");
            // Duas opçoes em um lugar so
            System.out.println("digite 0 finalizar compra ou voltar ao menu:");

            int codigoProdutoCompra = scanner.nextInt();
            scanner.nextLine();

            // nao entendi essa logica
            if (codigoProdutoCompra == 0) {
                if (compra.produtosItemCompra().size() == 0) {
                    System.out.println("compra cancelada");
                    return;
                }
                break;
            }

            if (codigoProdutoCompra < 1 || codigoProdutoCompra > produtoRepository.ProdutosDisponiveis().size()) {
                System.out.println("cdigo invalido");
                continue;
            }

            Produto produtoEscolhido = produtoRepository.ProdutosDisponiveis().get(codigoProdutoCompra - 1);

            double quantidadeDisponivel = compra.retornaQtdDisponivel(produtoEscolhido);
            // isso aqui pode ficar dentro do metodo que retorna a quantidade disponivel
//            if (quantidadeDisponivel <= 0) {
//                System.out.println("esse produto não tem mais unidades disponíveis, escolha outro produto ou digite 0 para finalizar.");
//                continue;
//            }

            System.out.println("Disponível: " + quantidadeDisponivel);
            System.out.println("Digite a quantidade que deseja comprar:");
            double qtdProduto = scanner.nextDouble();
            scanner.nextLine();


            // duas veficaçoes em um lugar
            if (!(qtdProduto > 0 && qtdProduto <= quantidadeDisponivel)) {
                System.out.println("quantidade invalida,  digite um valor maior que zero e menor ou igual a " + quantidadeDisponivel);
                continue;
            }

            ItemCompra itemCompra = new ItemCompra(produtoEscolhido, qtdProduto);

            compra.adicionarItemCompra(itemCompra);

            infoCompra = perguntarSeContinuaCompra();

        } while (infoCompra == 1);

        compra.baixarEstoqueDosItens();

        System.out.println("Compra finalizada!");
        System.out.println("Estoque atualizado!");

        mostrarResumoCompra(compra);

    }
}
