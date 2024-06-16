package br.edu.fateczl.salaodebeleza.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.Servico;

public class ServicoDao implements IServicoDao, ICRUDDao<Servico> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public ServicoDao(Context context){
        this.context = context;
    }
    @Override
    public ServicoDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Servico servico) throws SQLException {
        ContentValues contentValues = getContentValues(servico);
        database.insert("servico", null, contentValues);
    }

    @Override
    public int update(Servico servico) throws SQLException {
        ContentValues contentValues = getContentValues(servico);
        return database.update("servico", contentValues, "codigo_servico = "
                + servico.getCodigoServico(), null);
    }

    @Override
    public void delete(Servico servico) throws SQLException {
        database.delete("servico", "codigo_servico = "
                + servico.getCodigoServico(), null);
    }

    @SuppressLint("Range")
    @Override
    public Servico findOne(Servico servico) throws SQLException {
        String sql = "SELECT codigo_servico, descricao, valor FROM servico WHERE codigo_servico = "
                + servico.getCodigoServico();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            servico.setCodigoServico(cursor.getInt(cursor.getColumnIndex("codigo_servico")));
            servico.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            servico.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
        }

        cursor.close();
        return servico;
    }

    @SuppressLint("Range")
    @Override
    public List<Servico> findAll() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT telefone, nome, endereco FROM servico";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Servico servico = new Servico();
            servico.setCodigoServico(cursor.getInt(cursor.getColumnIndex("codigo_servico")));
            servico.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            servico.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            servicos.add(servico);
            cursor.moveToNext();
        }

        cursor.close();
        return servicos;
    }

    private ContentValues getContentValues(Servico servico) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo_servico", servico.getCodigoServico());
        contentValues.put("descricao", servico.getDescricao());
        contentValues.put("valor", servico.getValor());

        return contentValues;
    }
}
