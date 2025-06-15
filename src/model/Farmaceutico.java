package model;

public class Farmaceutico extends Funcionario{
    public Farmaceutico(String nome) {
        super(nome);
    }
    @Override
    public String toString() {
        return super.toString() + "Cargo: Farmacêutico";
    }
}
