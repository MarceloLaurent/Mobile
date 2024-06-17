package br.edu.fateczl.salaodebeleza.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.ClientePadrao;
import br.edu.fateczl.salaodebeleza.persistence.ClientePadraoDao;

public class ClientePadraoController implements IController<ClientePadrao> {

    private final ClientePadraoDao cpDao;

    public ClientePadraoController(ClientePadraoDao cpDao) {
        this.cpDao = cpDao;
    }

    @Override
    public void inserir(ClientePadrao clientePadrao) throws SQLException {
        if (cpDao.open() == null) {
            cpDao.open();
        }
        cpDao.insert(clientePadrao);
        cpDao.close();
    }

    @Override
    public void modificar(ClientePadrao clientePadrao) throws SQLException {
        if (cpDao.open() == null) {
            cpDao.open();
        }
        cpDao.update(clientePadrao);
        cpDao.close();
    }

    @Override
    public void deletar(ClientePadrao clientePadrao) throws SQLException {
        if (cpDao.open() == null) {
            cpDao.open();
        }
        cpDao.update(clientePadrao);
        cpDao.close();
    }

    @Override
    public ClientePadrao buscar(ClientePadrao clientePadrao) throws SQLException {
        if (cpDao.open() == null) {
            cpDao.open();
        }
        return cpDao.findOne(clientePadrao);
    }

    @Override
    public List<ClientePadrao> listar() throws SQLException {
        if (cpDao.open() == null) {
            cpDao.open();
        }
        return cpDao.findAll();
    }
}
