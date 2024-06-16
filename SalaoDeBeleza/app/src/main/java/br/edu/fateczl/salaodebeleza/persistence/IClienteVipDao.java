package br.edu.fateczl.salaodebeleza.persistence;

import java.sql.SQLException;

public interface IClienteVipDao {
    public ClienteVipDao open() throws SQLException;
    public void close();
}
