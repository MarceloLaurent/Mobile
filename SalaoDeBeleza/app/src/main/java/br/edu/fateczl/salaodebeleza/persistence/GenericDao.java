package br.edu.fateczl.salaodebeleza.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {
    private static final String DATABASE = "SALAO.DB";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE_CLIENTE =
            "CREATE TABLE cliente ( " +
                    "telefone INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "endereco VARCHAR(50) NOT NULL);";
    private final String CREATE_TABLE_CLIENTE_VIP =
            "CREATE TABLE cliente_vip ( " +
                    "telefone INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "endereco VARCHAR(50) NOT NULL); ";
    private final String CREATE_TABLE_SERVICO =
            "CREATE TABLE servico ( " +
                    "codigo_servico INT NOT NULL PRIMARY KEY, " +
                    "descricao VARCHAR(50) NOT NULL, " +
                    "valor DECIMAL(10, 2) NOT NULL);";
    private final String CREATE_TABLE_AGENDAMENTO =
            "CREATE TABLE agendamento ( " +
                    "codigo_agendamento INT NOT NULL PRIMARY KEY, " +
                    "data VARCHAR(20) NOT NULL, " +
                    "horario VARCHAR(20) NOT NULL, " +
                    "nome_cliente VARCHAR(100) NOT NULL, " +
                    "descricao_servico VARCHAR(50) NOT NULL, " +
                    "total DECIMAL(10, 2) NOT NULL, " +
                    "FOREIGN KEY (nome_cliente) REFERENCES cliente(nome), " +
                    "FOREIGN KEY (descricao_servico) REFERENCES servico(descricao));";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTE);
        db.execSQL(CREATE_TABLE_CLIENTE_VIP);
        db.execSQL(CREATE_TABLE_SERVICO);
        db.execSQL(CREATE_TABLE_AGENDAMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS cliente");
            db.execSQL("DROP TABLE IF EXISTS cliente_vip");
            db.execSQL("DROP TABLE IF EXISTS servico");
            db.execSQL("DROP TABLE IF EXISTS agendamento");
            onCreate(db);
        }
    }
}
