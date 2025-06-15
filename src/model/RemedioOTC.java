package model;

public class RemedioOTC extends Produto{

    public RemedioOTC(int codigo,String nome, float preco, String categoria) {
        super(codigo, nome, preco, categoria);
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public void vender() {
         System.out.println("| REMÉDIO OTC VENDIDO: " + nome + 
                            " | Preço: R$" + preco);
    }
}
