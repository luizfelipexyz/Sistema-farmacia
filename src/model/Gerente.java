package model;

public class Gerente extends Funcionario{

    public Gerente(String nome) {
        super(nome);
    }
    @Override
    public String toString() {
        return super.toString()+"Cargo: Gerente";
    }
}
