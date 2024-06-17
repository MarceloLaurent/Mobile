package br.edu.fateczl.salaodebeleza.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.Servico;
import br.edu.fateczl.salaodebeleza.persistence.ServicoDao;

public class ServicoController implements IController<Servico>{
    private final ServicoDao sDao;

    public ServicoController(ServicoDao sDao) {
        this.sDao = sDao;
    }

    @Override
    public void inserir(Servico servico) throws SQLException {
        if (sDao.open() == null) {
            sDao.open();
        }
        sDao.insert(servico);
        sDao.close();
    }

    @Override
    public void modificar(Servico servico) throws SQLException {
        if (sDao.open() == null) {
            sDao.open();
        }
        sDao.update(servico);
        sDao.close();
    }

    @Override
    public void deletar(Servico servico) throws SQLException {
        if (sDao.open() == null) {
            sDao.open();
        }
        sDao.update(servico);
        sDao.close();
    }

    @Override
    public Servico buscar(Servico servico) throws SQLException {
        if (sDao.open() == null) {
            sDao.open();
        }
        return sDao.findOne(servico);
    }

    @Override
    public List<Servico> listar() throws SQLException {
        if (sDao.open() == null) {
            sDao.open();
        }
        return sDao.findAll();
    }
}
