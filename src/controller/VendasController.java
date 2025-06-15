package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Vendas;

public class VendasController {
    private static final String ARQUIVO = "relatorioDeVendas.txt";
    private List<Vendas> historicoVendas;



    public VendasController() {
        historicoVendas= new ArrayList<>();
    }
    public void adicionarVenda(Vendas v) {
        historicoVendas.add(v); 
    }

    public void exibirRelatorio() {
        limparArquivo();
        if (historicoVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Vendas v : historicoVendas) {
            System.out.println("| PRODUTO: " + v.getNome() +
                               " | PREÇO: R$" + ((float)v.getPreco()) +
                               " | CATEGORIA: " + v.getCategoria() +
                               " | FORMA DE PAGAMENTO: " + v.getFormaPagamento());
            System.out.println("Arquivo TXT atualizado!");
        }

        salvarNoArquivo();
    }
    public void salvarNoArquivo(Vendas v) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(v.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }

    }
    public void salvarNoArquivo() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Vendas temp : historicoVendas) {
                pw.println(temp.toText());
            }
        } catch (IOException e) {
                System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    public void limparArquivo() {

    try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
    } catch (IOException e) {
        System.err.println("Erro ao limpar o arquivo de vendas: " + e.getMessage());
    }
}
    
    
}
