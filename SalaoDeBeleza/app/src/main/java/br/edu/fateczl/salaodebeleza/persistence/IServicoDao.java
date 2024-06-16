package br.edu.fateczl.salaodebeleza.persistence;

import java.sql.SQLException;

public interface IServicoDao {
    public ServicoDao open() throws SQLException;
    public void close();
}
