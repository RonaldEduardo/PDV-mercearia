package com.pdv.mercearia;

import com.pdv.mercearia.repository.ProdutoRepository;
import com.pdv.mercearia.ui.CompraMenu;
import com.pdv.mercearia.ui.MenuPrincipal;
import com.pdv.mercearia.ui.ProdutoMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoMenu produtoMenu = new ProdutoMenu(produtoRepository, scanner);
        CompraMenu compraMenu = new CompraMenu(scanner, produtoRepository);
        MenuPrincipal menuPrincipal = new MenuPrincipal(scanner, produtoMenu, compraMenu);

        menuPrincipal.executar();

    }
}
