package br.edu.fateczl.fuelpricecalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etGasolina;
    private EditText etAlcool;
    private TextView tvComp;

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

        etGasolina = findViewById(R.id.etGasolina);
        etGasolina.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAlcool = findViewById(R.id.etAlcool);
        etAlcool.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvComp = findViewById(R.id.tvComp);
        tvComp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnComp = findViewById(R.id.btnComp);

        btnComp.setOnClickListener(op -> comp());
    }

    private void comp() {
        float gasolina = Float.parseFloat(etGasolina.getText().toString());
        float alcool = Float.parseFloat(etAlcool.getText().toString());

        String melhorCombustivel = alcool <= gasolina * 0.7? "álcool." : "gasolina.";
        String res = getString(R.string.melhor_combustivel) + " " + melhorCombustivel;
        tvComp.setText(res);
        etGasolina.setText("");
        etAlcool.setText("");
    }
}