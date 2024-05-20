package br.edu.fateczl.banco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.banco.model.ContaEspecial;
import br.edu.fateczl.banco.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private EditText etCliente;
    private EditText etConta;
    private EditText etSaldo;
    private EditText etRelativo;
    private EditText etValor;
    private RadioButton rbPoupanca;
    private RadioButton rbEspecial;
    private RadioButton rbSacar;
    private RadioButton rbDepositar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCliente = findViewById(R.id.etCliente);
        etCliente.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etConta = findViewById(R.id.etConta);
        etConta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etSaldo = findViewById(R.id.etSaldo);
        etSaldo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etRelativo = findViewById(R.id.etRelativo);
        etRelativo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValor = findViewById(R.id.etValor);
        etValor.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbPoupanca = findViewById(R.id.rbPoupanca);
        rbEspecial = findViewById(R.id.rbEspecial);
        rbSacar = findViewById(R.id.rbSacar);
        rbDepositar = findViewById(R.id.rbDepositar);
        Button btnExecutar = findViewById(R.id.btnExecutar);

        btnExecutar.setOnClickListener(op -> executarOperacao());
    }

    private void executarOperacao() {
        String cliente = etCliente.getText().toString();
        int numConta = Integer.parseInt(etConta.getText().toString());
        float saldoInicial = Float.parseFloat(etSaldo.getText().toString());
        float valor = Float.parseFloat(etValor.getText().toString());

        if (rbPoupanca.isChecked()) {
            int diaDeRendimento = Integer.parseInt(etRelativo.getText().toString());
            contaPoupanca = new ContaPoupanca(nome, numConta, saldoInicial, diaDeRendimento);
            Toast.makeText(this, "Conta Poupança criada!", Toast.LENGTH_SHORT).show();
        } else if (rbEspecial.isChecked()) {
            float limite = Float.parseFloat(etRelativo.getText().toString());
            contaEspecial = new ContaEspecial(nome, numConta, saldoInicial, limite);
            Toast.makeText(this, "Conta Especial criada!", Toast.LENGTH_SHORT).show();
        }

        if (rbSacar.isChecked()) {
            if (contaPoupanca != null) {
                contaPoupanca.sacar(valor);
                Toast.makeText(this, "Saque realizado! Novo saldo: " + contaPoupanca.getSaldo(), Toast.LENGTH_SHORT).show();
            } else if (contaEspecial != null) {
                contaEspecial.sacar(valor);
                Toast.makeText(this, "Saque realizado! Novo saldo: " + contaEspecial.getSaldo(), Toast.LENGTH_SHORT).show();
            }
        } else if (rbDepositar.isChecked()) {
            if (contaPoupanca != null) {
                contaPoupanca.depositar(valor);
                Toast.makeText(this, "Depósito realizado! Novo saldo: " + contaPoupanca.getSaldo(), Toast.LENGTH_SHORT).show();
            } else if (contaEspecial != null) {
                contaEspecial.depositar(valor);
                Toast.makeText(this, "Depósito realizado! Novo saldo: " + contaEspecial.getSaldo(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}