package br.edu.fateczl.banco.model;

public abstract class ContaBancaria {

    private String nome;
    private int nunConta;
    private float saldo;

    public ContaBancaria(){
        super();
    }

    public ContaBancaria(String nome, int nunConta, float saldoInicial) {
        this.nome = nome;
        this.nunConta = nunConta;
        this.saldo = saldoInicial;
    }

    public void sacar(float valor){
        if(saldo > saldo){
            System.out.println("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    public void depositar(float valor){
        saldo += valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNunConta() {
        return nunConta;
    }

    public void setNunConta(int nunConta) {
        this.nunConta = nunConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
