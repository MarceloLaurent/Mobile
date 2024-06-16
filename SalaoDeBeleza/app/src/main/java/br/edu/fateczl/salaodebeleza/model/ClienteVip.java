package br.edu.fateczl.salaodebeleza.model;

public class ClienteVip extends Cliente implements IDesconto{

    public ClienteVip() {
        super();
    }

    public double calculaDesconto(Double valor){
        return valor * 0.8;
    };
}
