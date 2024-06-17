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
import br.edu.fateczl.salaodebeleza.model.ClientePadrao;

public class ClientePadraoDao implements IClientePadraoDao, ICRUDDao<ClientePadrao>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public ClientePadraoDao(Context context){
        this.context = context;
    }
    @Override
    public ClientePadraoDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(ClientePadrao cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        database.insert("cliente", null, contentValues);
    }

    @Override
    public int update(ClientePadrao cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        return database.update("cliente", contentValues, "telefone = "
                + cliente.getTelefone(), null);
    }

    @Override
    public void delete(ClientePadrao cliente) throws SQLException {
        database.delete("cliente", "telefone = " + cliente.getTelefone(), null);
    }

    @SuppressLint("Range")
    @Override
    public ClientePadrao findOne(ClientePadrao cliente) throws SQLException {
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
    public List<ClientePadrao> findAll() throws SQLException {
        List<ClientePadrao> clientes = new ArrayList<>();
        String sql = "SELECT telefone, nome, endereco FROM cliente";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            ClientePadrao cliente = new ClientePadrao();
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
