package br.edu.fateczl.salaodebeleza.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Agendamento {
    private int codigoAgendamento;
    private String data;
    private String horario;
    private Cliente cliente;
    private Servico servico;
    private double total;

    public Agendamento() {
        super();
    }

    public int getCodigoAgendamento() {
        return codigoAgendamento;
    }

    public void setCodigoAgendamento(int codigoAgendamento) {
        this.codigoAgendamento = codigoAgendamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setTotal(ClientePadrao clientePadrao){
        this.total = clientePadrao.calculaDesconto(servico.getValor());
    }

    public double getTotal() {
        return total;
    }


    @Override
    public String toString() {
        return  data + " " + horario + " - " + cliente + " - " + servico;
    }
}
