package com.example.projeto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgendaActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etTelefone;
    private ListView listViewContatos;

    private SQLiteDatabase database;
    private ContatoDatabaseHelper dbHelper;
    private SimpleCursorAdapter cursorAdapter;

    private int contatoSelecionadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        listViewContatos = findViewById(R.id.listViewContatos);

        dbHelper = new ContatoDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        atualizarListaContatos();

        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listViewContatos.getItemAtPosition(position);
                contatoSelecionadoId = cursor.getInt(cursor.getColumnIndexOrThrow(ContatoContract.ContatoEntry._ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(ContatoContract.ContatoEntry.COLUMN_NOME));
                String telefone = cursor.getString(cursor.getColumnIndexOrThrow(ContatoContract.ContatoEntry.COLUMN_TELEFONE));

                etNome.setText(nome);
                etTelefone.setText(telefone);
            }
        });
    }

    public void adicionarContato(View view) {
        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();

        if (nome.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(ContatoContract.ContatoEntry.COLUMN_NOME, nome);
        values.put(ContatoContract.ContatoEntry.COLUMN_TELEFONE, telefone);

        long newRowId = database.insert(ContatoContract.ContatoEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Erro ao adicionar contato", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Contato adicionado com sucesso", Toast.LENGTH_SHORT).show();
            etNome.setText("");
            etTelefone.setText("");
            atualizarListaContatos();
        }
    }

    public void editarContato(View view) {
        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();

        if (contatoSelecionadoId == -1) {
            Toast.makeText(this, "Selecione um contato para editar", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(ContatoContract.ContatoEntry.COLUMN_NOME, nome);
        values.put(ContatoContract.ContatoEntry.COLUMN_TELEFONE, telefone);

        String selection = ContatoContract.ContatoEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(contatoSelecionadoId)};

        int count = database.update(
                ContatoContract.ContatoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if (count == 1) {
            Toast.makeText(this, "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();
            etNome.setText("");
            etTelefone.setText("");
            contatoSelecionadoId = -1;
            atualizarListaContatos();
        } else {
            Toast.makeText(this, "Erro ao atualizar contato", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluirContato(View view) {
        if (contatoSelecionadoId == -1) {
            Toast.makeText(this, "Selecione um contato para excluir", Toast.LENGTH_SHORT).show();
            return;
        }

        String selection = ContatoContract.ContatoEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(contatoSelecionadoId)};

        int count = database.delete(
                ContatoContract.ContatoEntry.TABLE_NAME,
                selection,
                selectionArgs
        );

        if (count == 1) {
            Toast.makeText(this, "Contato excluído com sucesso", Toast.LENGTH_SHORT).show();
            etNome.setText("");
            etTelefone.setText("");
            contatoSelecionadoId = -1;
            atualizarListaContatos();
        } else {
            Toast.makeText(this, "Erro ao excluir contato", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizarListaContatos() {
        Cursor cursor = database.query(
                ContatoContract.ContatoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        String[] fromColumns = {
                ContatoContract.ContatoEntry.COLUMN_NOME,
                ContatoContract.ContatoEntry.COLUMN_TELEFONE
        };

        int[] toViews = {R.id.tvNome, R.id.tvTelefone};

        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item_contato,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listViewContatos.setAdapter(cursorAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
