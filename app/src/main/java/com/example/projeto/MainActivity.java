package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar os listeners dos botões
        findViewById(R.id.btnCalculadora).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalculadora();
            }
        });

        findViewById(R.id.btnJogoVelha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirJogoVelha();
            }
        });

        findViewById(R.id.btnAgendaTelefonica).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAgendaTelefonica();
            }
        });

        findViewById(R.id.btnMostrarMapa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMapa();
            }
        });

        findViewById(R.id.btnBlocoNotas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirBlocoNotas();
            }
        });

        findViewById(R.id.btnJogoMemoria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirJogoMemoria();
            }
        });
    }

    private void abrirCalculadora() {

        Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(intent);

    }

    private void abrirJogoVelha() {
        Intent intent = new Intent(MainActivity.this, JogoActivity.class);
        startActivity(intent);
    }

    private void abrirAgendaTelefonica() {
        Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
        startActivity(intent);
    }

    private void mostrarMapa() {
        Intent intent = new Intent(MainActivity.this, MapActivityTest.class);
        startActivity(intent);
    }

    private void abrirBlocoNotas() {
        Intent intent = new Intent(MainActivity.this, BlocoActivity.class);
        startActivity(intent);
    }

    private void abrirJogoMemoria() {

    }
}
