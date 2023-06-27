package com.example.projeto;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BlocoActivity extends AppCompatActivity {

    private EditText noteEditText;
    private Button saveButton;
    private ListView notesListView;

    private ArrayAdapter<String> notesAdapter;
    private ArrayList<String> notesList;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_FILE = "MyNotes";
    private static final String NOTES_KEY = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloco);

        noteEditText = findViewById(R.id.noteEditText);
        saveButton = findViewById(R.id.saveButton);
        notesListView = findViewById(R.id.notesListView);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

        notesList = new ArrayList<>();
        Set<String> notesSet = sharedPreferences.getStringSet(NOTES_KEY, new HashSet<>());
        notesList.addAll(notesSet);

        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        notesListView.setAdapter(notesAdapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = noteEditText.getText().toString().trim();
                if (!note.isEmpty()) {
                    notesList.add(note);
                    notesAdapter.notifyDataSetChanged();
                    noteEditText.setText("");
                    saveNotesToSharedPreferences();
                    Toast.makeText(BlocoActivity.this, "Nota salva", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BlocoActivity.this, "Digite uma nota", Toast.LENGTH_SHORT).show();
                }
            }
        });

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = notesList.get(position);
                noteEditText.setText(note);
            }
        });

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = notesList.get(position);
                noteEditText.setText(note);

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String editedNote = noteEditText.getText().toString().trim();
                        if (!editedNote.isEmpty()) {
                            notesList.set(position, editedNote);
                            notesAdapter.notifyDataSetChanged();
                            noteEditText.setText("");
                            saveNotesToSharedPreferences();
                            Toast.makeText(BlocoActivity.this, "Nota atualizada", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BlocoActivity.this, "Digite uma nota", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notesList.remove(position);
                notesAdapter.notifyDataSetChanged();
                saveNotesToSharedPreferences();
                Toast.makeText(BlocoActivity.this, "Nota excluída", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void saveNotesToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> notesSet = new HashSet<>(notesList);
        editor.putStringSet(NOTES_KEY, notesSet);
        editor.apply();
    }
}
