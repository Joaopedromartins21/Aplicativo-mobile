package com.example.projeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JogoActivity extends AppCompatActivity {

    private Button[] buttons;
    private boolean isPlayer1Turn = true;
    private int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        buttons = new Button[9];
        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn5);
        buttons[5] = findViewById(R.id.btn6);
        buttons[6] = findViewById(R.id.btn7);
        buttons[7] = findViewById(R.id.btn8);
        buttons[8] = findViewById(R.id.btn9);

        for (Button button : buttons) {
            button.setEnabled(true);
            button.setText("");
        }
    }

    public void jogar(View view) {
        Button button = (Button) view;

        if (isPlayer1Turn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        button.setEnabled(false);
        turnCount++;

        if (verificarVitoria()) {
            if (isPlayer1Turn) {
                exibirMensagem("Jogador 1 (X) venceu!");
            } else {
                exibirMensagem("Jogador 2 (O) venceu!");
            }
            desabilitarBotoes();
        } else if (turnCount == 9) {
            exibirMensagem("Empate!");
        } else {
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    public void reiniciarJogo(View view) {
        isPlayer1Turn = true;
        turnCount = 0;

        for (Button button : buttons) {
            button.setEnabled(true);
            button.setText("");
        }
    }

    private boolean verificarVitoria() {
        String[] board = new String[9];

        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText().toString();
        }

        for (int i = 0; i < 3; i++) {
            // Verificar linhas
            if (board[i * 3].equals(board[i * 3 + 1]) && board[i * 3].equals(board[i * 3 + 2]) && !board[i * 3].isEmpty()) {
                return true;
            }
            // Verificar colunas
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6]) && !board[i].isEmpty()) {
                return true;
            }
        }

        // Verificar diagonais
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].isEmpty()) {
            return true;
        }
        if (board[2].equals(board[4]) && board[2].equals(board[6]) && !board[2].isEmpty()) {
            return true;
        }

        return false;
    }

    private void exibirMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void desabilitarBotoes() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }
}
