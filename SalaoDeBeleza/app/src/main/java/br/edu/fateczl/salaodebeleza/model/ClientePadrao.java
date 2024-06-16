package br.edu.fateczl.salaodebeleza.model;

public class ClientePadrao extends Cliente implements IDesconto{

    public ClientePadrao() {
        super();
    }

    public double calculaDesconto(Double valor){
        return valor * 0.95;
    };
}
