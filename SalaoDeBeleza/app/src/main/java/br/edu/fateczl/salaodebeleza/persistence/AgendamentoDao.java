package br.edu.fateczl.salaodebeleza.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.Agendamento;
import br.edu.fateczl.salaodebeleza.model.Cliente;
import br.edu.fateczl.salaodebeleza.model.ClientePadrao;
import br.edu.fateczl.salaodebeleza.model.Servico;

public class AgendamentoDao implements IAgendamentoDao, ICRUDDao<Agendamento>{
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public AgendamentoDao(Context context){
        this.context = context;
    }
    @Override
    public AgendamentoDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Agendamento agendamento) throws SQLException {
        ContentValues contentValues = getContentValues(agendamento);
        database.insert("agendamento", null, contentValues);
    }

    @Override
    public int update(Agendamento agendamento) throws SQLException {
        ContentValues contentValues = getContentValues(agendamento);
        return database.update("agendamento", contentValues, "codigo_agendamento = "
                + agendamento.getCodigoAgendamento(), null);
    }

    @Override
    public void delete(Agendamento agendamento) throws SQLException {
        database.delete("agendamento", "codigo_agendamento = "
                + agendamento.getCodigoAgendamento(), null);
    }

    @SuppressLint("Range")
    @Override
    public Agendamento findOne(Agendamento agendamento) throws SQLException {
        String sql = "SELECT codigo_agendamento, data, horario, c.nome AS nome_cliente" +
                ", s.descricao AS descricao_servico, total FROM agendamento, cliente c, servico s" +
                " WHERE codigo_agendamento = " + agendamento.getCodigoAgendamento() +
                " AND agendamento.nome_cliente = c.nome " +
                " AND agendamento.descricao_servico = s.descricao";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            ClientePadrao cliente = new ClientePadrao();
            cliente.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));

            Servico servico = new Servico();
            servico.setCodigoServico(cursor.getInt(cursor.getColumnIndex("codigo_servico")));
            servico.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            servico.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            agendamento.setCodigoAgendamento(cursor.getInt(cursor.getColumnIndex("codigo_agendamento")));
            agendamento.setData(cursor.getString(cursor.getColumnIndex("data")));
            agendamento.setHorario(cursor.getString(cursor.getColumnIndex("horario")));
            agendamento.setCliente(cliente);
            agendamento.setServico(servico);
            agendamento.setTotal(cliente);
        }

        cursor.close();
        return agendamento;
    }

    @SuppressLint("Range")
    @Override
    public List<Agendamento> findAll() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT codigo_agendamento, data, horario, c.nome AS nome_cliente" +
                ", s.descricao AS descricao_servico, total FROM agendamento, cliente c, servico s" +
                " WHERE agendamento.nome_cliente = c.nome " +
                " AND agendamento.descricao_servico = s.descricao";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            Agendamento agendamento = new Agendamento();

            ClientePadrao cliente = new ClientePadrao();
            cliente.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));

            Servico servico = new Servico();
            servico.setCodigoServico(cursor.getInt(cursor.getColumnIndex("codigo_servico")));
            servico.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            servico.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            agendamento.setCodigoAgendamento(cursor.getInt(cursor.getColumnIndex("codigo_agendamento")));
            agendamento.setData(cursor.getString(cursor.getColumnIndex("data")));
            agendamento.setHorario(cursor.getString(cursor.getColumnIndex("horario")));
            agendamento.setCliente(cliente);
            agendamento.setServico(servico);
            agendamento.setTotal(cliente);

            agendamentos.add(agendamento);
            cursor.moveToNext();
        }

        cursor.close();
        return agendamentos;
    }

    private ContentValues getContentValues(Agendamento agendamento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo_agendamento", agendamento.getCodigoAgendamento());
        contentValues.put("data", agendamento.getData());
        contentValues.put("horario", agendamento.getHorario());
        contentValues.put("nome_cliente", agendamento.getCliente().getNome());
        contentValues.put("descricao_servico", agendamento.getServico().getDescricao());
        contentValues.put("total", agendamento.getTotal());

        return contentValues;
    }
}
