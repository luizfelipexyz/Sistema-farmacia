package model;

public class Vendas extends Produto{
    String formaPagamento;

    public Vendas(int codigo, String nome, float preco, String categoria, String formaPagamento) {
        super(codigo, nome, preco, categoria);
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public String toText() {
        return codigo + ";" + nome + ";" + preco + ";" + categoria + ";" + formaPagamento;
    }

    public static Vendas fromText(String text) {

        String[] partes = text.split(";");
        int codigo = Integer.parseInt(partes[0]);
        String nome = partes[1];
        float preco = Float.parseFloat(partes[2]);
        String categoria = partes[3];
        String formaPagamento = partes[4];

        return new Vendas(codigo, nome, preco, categoria, formaPagamento);
    }
    
}
