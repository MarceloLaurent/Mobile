package br.edu.fateczl.banco.model;

public class ContaPoupanca extends ContaBancaria{

    private int diaRendimento;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(String nome, int numConta, float saldoInicial, int diaRendimento) {
        super(nome, numConta, saldoInicial);
        this.diaRendimento = diaRendimento;
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public void calcularNovoSaldo(float taxa){
        setSaldo(getSaldo() + getSaldo() * taxa);
    }
}
