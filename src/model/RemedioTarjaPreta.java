package model;

public class RemedioTarjaPreta extends Produto {
    private int receita;
    public RemedioTarjaPreta(int codigo,String nome, float preco, String categoria, int receita) {
        super(codigo,nome, preco, categoria);
        this.receita = receita;
    }
    
    @Override
    public void vender() {
         System.out.println("| REMÉDIO TARJA PRETA VENDIDO: " + nome + 
                            " | PREÇO: R$" + preco+
                            " | RECEITA: "+receita);
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public int getReceita() {
        return receita;
    }

    public void setReceita(int receita) {
        this.receita = receita;
    }
}
