package br.edu.fateczl.salaodebeleza.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.salaodebeleza.model.Cliente;
import br.edu.fateczl.salaodebeleza.model.ClienteVip;

public class ClienteVipDao implements IClienteVipDao, ICRUDDao<Cliente>{
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public ClienteVipDao(Context context){
        this.context = context;
    }
    @Override
    public ClienteVipDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        database.insert("cliente", null, contentValues);
    }

    @Override
    public int update(Cliente cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        return database.update("cliente", contentValues, "telefone = "
                + cliente.getTelefone(), null);
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        database.delete("cliente", "telefone = " + cliente.getTelefone(), null);
    }

    @SuppressLint("Range")
    @Override
    public Cliente findOne(Cliente cliente) throws SQLException {
        String sql = "SELECT telefone, nome, endereco FROM cliente WHERE telefone = " + cliente.getTelefone();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            cliente.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
        }

        cursor.close();
        return cliente;
    }

    @SuppressLint("Range")
    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT telefone, nome, endereco FROM cliente";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Cliente cliente = new ClienteVip();
            cliente.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));

            clientes.add(cliente);
            cursor.moveToNext();
        }

        cursor.close();
        return clientes;
    }

    private ContentValues getContentValues(Cliente cliente) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("telefone", cliente.getTelefone());
        contentValues.put("nome", cliente.getNome());
        contentValues.put("endereco", cliente.getEndereco());

        return contentValues;
    }
}
