package br.edu.fateczl.salaodebeleza.persistence;

import java.sql.SQLException;

public interface IAgendamentoDao {
    public AgendamentoDao open() throws SQLException;
    public void close();
}
