package model;

public class Produto implements Vendavel {
    int codigo;
    String nome;
    float preco;
    String categoria;
    
    public Produto(int codigo,String nome, float preco, String categoria) {
        this.codigo=codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public void vender() {
         System.out.println("| PRODUTO VENDIDO: " + nome+ 
                            " | PREÇO: R$" + preco);
    }
    @Override
    public String toString() {
        return "| CÓDIGO: "+codigo+" | PRODUTO: "+nome+" | PREÇO: "+preco+" | CATEGORIA: "+categoria+" |";
    }

}
