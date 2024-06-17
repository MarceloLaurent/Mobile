package br.edu.fateczl.salaodebeleza.model;

public abstract class Cliente {
    private int telefone;
    private String nome;
    private String endereco;

    public Cliente() {
        super();
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return telefone + " - " + nome ;
    }
}
