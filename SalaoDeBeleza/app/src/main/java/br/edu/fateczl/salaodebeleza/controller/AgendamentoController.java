package br.edu.fateczl.salaodebeleza.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.Agendamento;
import br.edu.fateczl.salaodebeleza.persistence.AgendamentoDao;

public class AgendamentoController implements IController<Agendamento>{
    private final AgendamentoDao agDao;

    public AgendamentoController(AgendamentoDao agDao) {
        this.agDao = agDao;
    }

    @Override
    public void inserir(Agendamento agendamento) throws SQLException {
        if (agDao.open() == null) {
            agDao.open();
        }
        agDao.insert(agendamento);
        agDao.close();
    }

    @Override
    public void modificar(Agendamento agendamento) throws SQLException {
        if (agDao.open() == null) {
            agDao.open();
        }
        agDao.update(agendamento);
        agDao.close();
    }

    @Override
    public void deletar(Agendamento agendamento) throws SQLException {
        if (agDao.open() == null) {
            agDao.open();
        }
        agDao.update(agendamento);
        agDao.close();
    }

    @Override
    public Agendamento buscar(Agendamento agendamento) throws SQLException {
        if (agDao.open() == null) {
            agDao.open();
        }
        return agDao.findOne(agendamento);
    }

    @Override
    public List<Agendamento> listar() throws SQLException {
        if (agDao.open() == null) {
            agDao.open();
        }
        return agDao.findAll();
    }
}
