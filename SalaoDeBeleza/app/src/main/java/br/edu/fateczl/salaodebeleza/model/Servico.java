package br.edu.fateczl.salaodebeleza.model;

public class Servico {
    private int codigoServico;
    private String descricao;
    private double valor;

    public Servico() {
        super();
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  codigoServico + " - " + descricao + " " + valor;
    }
}
