package br.edu.fateczl.banco.model;

public class ContaEspecial extends ContaBancaria{

    private float limite;

    public ContaEspecial() {
        super();
    }

    public ContaEspecial(String nome, int numConta, float saldoInicial, float limite) {
        super(nome, numConta, saldoInicial);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(float valor) {
        if(valor >= getSaldo() + limite){
            System.out.printf("Limite Insuficiente.");
        }
        setSaldo(getSaldo() - valor);
    }
}
