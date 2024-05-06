package br.edu.fateczl.fuelconsumption;

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

    private EditText etCombustivel;
    private EditText etConsumo;
    private TextView tvRes;

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

        etCombustivel = findViewById(R.id.etCombustivel);
        etCombustivel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etConsumo = findViewById(R.id.etConsumo);
        etConsumo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> comp());
    }

    private void comp() {
        float combustivel = Float.parseFloat(etCombustivel.getText().toString());
        float consumo = Float.parseFloat(etConsumo.getText().toString());

        float autonomia = combustivel * consumo;
        String res = getString(R.string.autonomia) + " " + autonomia + " Km.";
        tvRes.setText(res);
        etCombustivel.setText("");
        etConsumo.setText("");
    }
}