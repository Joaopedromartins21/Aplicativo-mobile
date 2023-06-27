package com.example.projeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvResultado;

    private StringBuilder numeroDigitado;
    private double numeroAnterior;
    private String operacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvResultado = findViewById(R.id.tvResultado);
        numeroDigitado = new StringBuilder();
        operacao = "";

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnEquals = findViewById(R.id.btnEquals);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("2");
            }
        });

        // ...

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarNumeroDigitado("9");
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "+";
                atualizarNumeroAnterior();
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "-";
                atualizarNumeroAnterior();
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "*";
                atualizarNumeroAnterior();
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "/";
                atualizarNumeroAnterior();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCalculadora();
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    private void atualizarNumeroDigitado(String numero) {
        numeroDigitado.append(numero);
        tvResultado.setText(numeroDigitado.toString());
    }

    private void atualizarNumeroAnterior() {
        numeroAnterior = Double.parseDouble(numeroDigitado.toString());
        numeroDigitado.setLength(0);
    }

    private void calcularResultado() {
        double numeroAtual = Double.parseDouble(numeroDigitado.toString());
        double resultado = 0;

        switch (operacao) {
            case "+":
                resultado = numeroAnterior + numeroAtual;
                break;
            case "-":
                resultado = numeroAnterior - numeroAtual;
                break;
            case "*":
                resultado = numeroAnterior * numeroAtual;
                break;
            case "/":
                if (numeroAtual != 0) {
                    resultado = numeroAnterior / numeroAtual;
                } else {
                    // Tratar divisão por zero
                }
                break;
        }

        tvResultado.setText(String.valueOf(resultado));
        numeroDigitado.setLength(0);
    }

    private void limparCalculadora() {
        numeroDigitado.setLength(0);
        numeroAnterior = 0;
        operacao = "";
        tvResultado.setText("");
    }
}
