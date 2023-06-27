package com.example.projeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContatoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_CONTATOS_TABLE =
            "CREATE TABLE " + ContatoContract.ContatoEntry.TABLE_NAME + " (" +
                    ContatoContract.ContatoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ContatoContract.ContatoEntry.COLUMN_NOME + " TEXT," +
                    ContatoContract.ContatoEntry.COLUMN_TELEFONE + " TEXT)";

    private static final String SQL_DELETE_CONTATOS_TABLE =
            "DROP TABLE IF EXISTS " + ContatoContract.ContatoEntry.TABLE_NAME;

    public ContatoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONTATOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CONTATOS_TABLE);
        onCreate(db);
    }
}
