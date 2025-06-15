package model;

public class Atendente extends Funcionario{
    public Atendente(String nome) {
        super(nome);
    }
    @Override
    public String toString() {
        return super.toString() + "Cargo: Atendente";
    }
}
