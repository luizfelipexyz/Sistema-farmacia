package view;

import controller.Console;
import controller.Estoque;
import controller.VendasController;
import model.Vendas;
import model.Produto;
import model.RemedioOTC;
import model.RemedioTarjaPreta;

public class PrincipalView {
    Estoque estoque = new Estoque();
    private VendasController controller = new VendasController();

    int opcao;
    public void iniciar() {
        controller.limparArquivo();
        Produto shampoo = new Produto(1,"Shampoo", 19.99f, "Higiene");
        estoque.adicionarProduto(shampoo);
    
        Produto remedio1 = new RemedioOTC(2, "Paracetamol", 7.50f, "Saude");
        estoque.adicionarProduto(remedio1);
    
        do {
            exibirMenu();
            System.out.print("Digite a opção: ");
            opcao = Console.lerInt();
            verificarOpcao(opcao);
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\nDeseja acessar o menu como: ");
        System.out.println("1) Atendente (Vende produtos, consulta produtos)");
        System.out.println("2) Farmacêutico (Cadastra produtos)");
        System.out.println("3) Gerente (Controla estoque, faz relatório de vendas)");
        System.out.println("0) Sair");
    }

    private void verificarOpcao(int op) {
        switch (op) {
            case 1 -> menuAtendente();
            case 2 -> menuFarmaceutico();
            case 3 -> menuGerente();
            case 0 -> System.out.println("\nO programa foi finalizado...\n");
            default -> System.err.println("\nOpção inválida, digite novamente.");
        }
    }

    private void menuAtendente() {
    do {
        System.out.println("\n--MENU ATENDENTE--");
        System.out.println("1) Vender Produtos");
        System.out.println("2) Consultar produtos");
        System.out.println("3) Retornar");
        System.out.print("Digite a opção: ");
        opcao = Console.lerInt();

        switch (opcao) {
            case 1 -> venderProdutos();
            case 2 -> consultarProdutos();
            case 3 -> { return; }
            default -> System.err.println("Opção inválida, tente novamente.");
        }

    } while (opcao != 5);

    }

    private void menuFarmaceutico() {
        do {
        System.out.println("\n--MENU FARMACÊUTICO--");
        System.out.println("1) Cadastrar produto");
        System.out.println("2) Retornar");
        System.out.print("Digite a opção: ");
        opcao = Console.lerInt();

        switch (opcao) {
            case 1 -> cadastrarProduto();
            case 2 -> { return; }
            default -> System.err.println("Opção inválida, tente novamente.");
        }

    } while (opcao != 3);
    }

    private void menuGerente() {
        do {
        System.out.println("\n--MENU GERENTE--");
        System.out.println("1) Controlar estoque");
        System.out.println("2) Fazer relatório de vendas");
        System.out.println("3) Retornar");
        System.out.print("Digite a opção: ");
        opcao=Console.lerInt();

        switch (opcao) {
            case 1 -> controlarEstoque();
            case 2 -> relatorioVendas();
            case 3 -> { return; }
            default -> System.err.println("Opção inválida, tente novamente.");
        }

    } while (opcao != 3);
    }

    private void venderProdutos() {
    if(estoque.getListaProdutos().isEmpty()){
        System.out.println("Nenhum produto no estoque, cadastre produtos como farmacêutico ou gerente.");
        return;
    }
    estoque.listarProdutos();
    System.out.println("Informe o código do produto que deseja vender: ");
    int codigo = Console.lerInt();

    Produto p = estoque.buscarProdutoPorCodigo(codigo);

    if (p != null) {
        if (p instanceof RemedioTarjaPreta) {
            System.out.println("Este medicamento exige receita.");
            System.out.print("Informe o número da receita: ");
            int numeroReceita = Console.lerInt(); 
            RemedioTarjaPreta tarjaPreta = (RemedioTarjaPreta) p;
            if(numeroReceita==tarjaPreta.getReceita()){
                System.out.println("Receita " + numeroReceita + " verificada.");
            }
            else{
                System.out.println("Receita inválida!");
                return;
            }
        }
        System.out.print("PRODUTO SELECIONADO: ");
        System.out.print("\n| NOME: " + p.getNome());
        System.out.print(" | PREÇO: R$" + ((float)p.getPreco()));
        System.out.print(" | CATEGORIA: " + p.getCategoria());

        System.out.println("\nForma de pagamento? ");
        String formaPagamento = Console.lerString();
        Vendas venda = new Vendas(codigo, p.getNome(), ((float)p.getPreco()), p.getCategoria(), formaPagamento);

        controller.adicionarVenda(venda);
        p.vender();
        estoque.removerProduto(codigo);

    } else {
        System.out.println("Produto não encontrado com esse código.");
    }
}

    private void consultarProdutos(){
        if (estoque.getListaProdutos().isEmpty()) {
        System.out.println("Nenhum produto cadastrado no estoque.");
        return;
    }
        System.out.println("Deseja consultar:");
        System.out.println("1) Todos os produtos");
        System.out.println("2) Somente medicamentos comuns");
        System.out.println("3) Somente medicamentos tarja preta");
        System.out.println("4) Buscar por categoria");
        System.out.println("5) Retornar");
        opcao=Console.lerInt();
        switch(opcao){
            case 1:
                estoque.listarProdutos();
                break;
            case 2:
                boolean temComum = false;
                for (Produto p : estoque.getListaProdutos()) {
                if (p instanceof RemedioOTC) {
                    System.out.println(p);
                    temComum=true;
                }
            }
                if (!temComum) {
                System.out.println("Nenhum medicamento comum cadastrado.");
            }
                break;
            case 3:
                boolean temTarja = false;
                for (Produto p : estoque.getListaProdutos()) {
                    if (p instanceof RemedioTarjaPreta) {
                        System.out.print(p);
                        temTarja=true;
                    }
            }
                if (!temTarja) {
                    System.out.println("Nenhum medicamento tarja preta cadastrado.");
                }
                break;
            case 4:
                System.out.println("Informe a categoria: (Higiene, Saúde, Comida)");
                String categoria = Console.lerString().toLowerCase();
                boolean encontrado = false;

                for (Produto p : estoque.getListaProdutos()) {
                    if (p.getCategoria().toLowerCase().equals(categoria)) {
                        System.out.println(p);
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("Nenhum produto encontrado na categoria: " + categoria);
                }
                break;

            case 5:
                return;
            default:
                System.err.println("Opção inválida, digite novamente.");
        }
    }
    private void cadastrarProduto() {
    System.out.println("1) Produto comum");
    System.out.println("2) Remédio comum");
    System.out.println("3) Remédio tarja preta");
    System.out.println("Qual produto deseja cadastrar?");
    int opcao = Console.lerInt();

    switch (opcao) {
        case 1 -> {
            System.out.println("Código do produto: ");
            int codigo = Console.lerInt();
            if (estoque.buscarProdutoPorCodigo(codigo) != null) {
                estoque.listarProdutos();
                System.out.println("Já existe um produto com esse código. Cadastro cancelado.");
                return;
            }
            System.out.println("Nome do produto: ");
            String nome = Console.lerString();
            System.out.println("Preço do produto: ");
            float preco = Console.lerFloat();
            System.out.println("Categoria do produto: ");
            String categoria = Console.lerString();

            estoque.adicionarProduto(new Produto(codigo, nome, preco, categoria));
            System.out.println("Produto comum cadastrado com sucesso!");
            break;
        }
        case 2 -> {
            System.out.println("Código do produto: ");
            int codigo = Console.lerInt();
            if (estoque.buscarProdutoPorCodigo(codigo) != null) {
                estoque.listarProdutos();
                System.out.println("Já existe um produto com esse código. Cadastro cancelado.");
                return;
            }
            System.out.println("Nome do produto: ");
            String nome = Console.lerString();
            System.out.println("Preço do produto: ");
            float preco = Console.lerFloat();
            System.out.println("Categoria do produto: ");
            String categoria = Console.lerString();

            estoque.adicionarProduto(new RemedioOTC(codigo, nome, preco, categoria));
            System.out.println("Remédio comum cadastrado com sucesso!");
            break;
        }
        case 3 -> {
            System.out.println("Código do produto: ");
            int codigo = Console.lerInt();
            if (estoque.buscarProdutoPorCodigo(codigo) != null) {
                estoque.listarProdutos();
                System.out.println("Já existe um produto com esse código. Cadastro cancelado.");
                return;
            }
            System.out.println("Nome do produto: ");
            String nome = Console.lerString();
            System.out.println("Preço do produto: ");
            float preco = Console.lerFloat();
            System.out.println("Categoria do produto: ");
            String categoria = Console.lerString();
            System.out.print("Número da receita: ");
            int receita = Console.lerInt();
            if (estoque.buscarProdutoPorCodigo(codigo) != null) {
                estoque.listarProdutos();
                System.out.println("Já existe um produto com esse código. Cadastro cancelado.");
                return;
            }

            estoque.adicionarProduto(new RemedioTarjaPreta(codigo, nome, preco, categoria, receita));
            System.out.println("Remédio tarja preta cadastrado com sucesso!");
            break;
        }
        default -> {System.err.println("Opção inválida! Produto não cadastrado."); break;}
    }
  
    }

    private void controlarEstoque(){
        System.out.println("TODOS OS PRODUTOS");
        estoque.listarProdutos();
        System.out.println("1) Cadastrar produto");
        System.out.println("2) Remover produto");
        System.out.println("3) Alterar produto");
        System.out.println("4) Consultar produto");
        System.out.println("5) Retornar");
        System.out.println("Oque deseja fazer?");
        opcao=Console.lerInt();
        switch(opcao){
            case 1-> cadastrarProduto();
            case 2-> removerProduto();
            case 3-> alterarProduto();
            case 4-> consultarProdutos();
            case 5-> { return; }
            default -> System.err.println("Opção inválida, digite novamente");
        }
    }
    private void relatorioVendas(){
        controller.exibirRelatorio();
    }
    
    private void removerProduto(){
        if (estoque.getListaProdutos().isEmpty()) {
            System.out.println("Nenhum produto no estoque para remover.");
            return;
        }
        System.out.println("Informe o código do produto que deseja remover: ");
        int codigo = Console.lerInt();
        estoque.removerProduto(codigo);
        System.out.println("Produto removido com sucesso!");
    }
    private void alterarProduto(){  
        if (estoque.getListaProdutos().isEmpty()) {
            System.out.println("Nenhum produto no estoque para alterar.");
            return;
        }
        System.out.println("Informe o código do produto que deseja alterar: ");
        int codigo = Console.lerInt();
        System.out.println("Informe oque deseja alterar (1-Nome 2-Preço 3-Categoria)");
        int alterar= Console.lerInt();

        Produto p = estoque.buscarProdutoPorCodigo(codigo);

        switch (alterar) {
        case 1:
            System.out.print("Digite o novo nome: ");
            String novoNome = Console.lerString();
            p.setNome(novoNome);
            System.out.println("Nome alterado com sucesso!");
            break;

        case 2:
            System.out.print("Digite o novo preço: ");
            float novoPreco = Console.lerFloat();
            p.setPreco(novoPreco);
            System.out.println("Preço alterado com sucesso!");
            break;

        case 3:
            System.out.print("Digite a nova categoria: ");
            String novaCategoria = Console.lerString();
            p.setCategoria(novaCategoria);
            System.out.println("Categoria alterada com sucesso!");
            break;

        default:
            System.err.println("Opção inválida, digite novamente");
            break;
    }
    }
}