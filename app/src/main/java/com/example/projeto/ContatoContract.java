package com.example.projeto;

import android.provider.BaseColumns;

public final class ContatoContract {

    private ContatoContract() {}

    public static class ContatoEntry implements BaseColumns {
        public static final String TABLE_NAME = "contatos";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_TELEFONE = "telefone";
    }
}
