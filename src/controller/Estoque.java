package controller;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Integer> receitasValidas = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }
    public boolean removerProduto(int codigo) {

        for (Produto temp : listaProdutos) {
            if (temp.getCodigo()==codigo){
                listaProdutos.remove(temp);
                return true;
            }
        }
        return false;
    }

    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto no estoque.");
        } else {
            for (Produto p : listaProdutos) {
                System.out.println(p);
            }
        }
    }
    public Produto buscarProdutoPorCodigo(int codigo) {
    for (Produto p : listaProdutos) {
        if (p.getCodigo() == codigo) {
            return p;
        }
    }
    return null;
    }
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void adicionarReceita(int numero) {
        receitasValidas.add(numero);
    }

    public void listarReceitas() {
        if (receitasValidas.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada.");
        } else {
            System.out.println("Receitas válidas:");
            for (int r : receitasValidas) {
                System.out.println("RECEITA: " + r);
            }
        }
    }

    public boolean receitaEhValida(int numero) {
        return receitasValidas.contains(numero);
    }

}
