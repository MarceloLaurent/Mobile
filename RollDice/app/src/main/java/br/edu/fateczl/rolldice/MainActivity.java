package br.edu.fateczl.rolldice;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbQtd1;
    private RadioButton rbQtd2;
    private RadioButton rbQtd3;
    private Spinner spQtdFaces;
    private TextView tvSaida;

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

        rbQtd1 = findViewById(R.id.rbQtd1);
        rbQtd2 = findViewById(R.id.rbQtd2);
        rbQtd3 = findViewById(R.id.rbQtd3);
        Button btnLancar = findViewById(R.id.btnLancar);
        spQtdFaces = findViewById(R.id.spQtdFaces);
        tvSaida = findViewById(R.id.tvSaida);

        preencheSpinner();
        btnLancar.setOnClickListener(op -> lancar());
    }

    private void lancar(){
        StringBuffer buffer = new StringBuffer();
        Integer qtdFaces = (Integer)(spQtdFaces.getSelectedItem());

        if (rbQtd1.isChecked()){
            int diceResult = (int) (qtdFaces * Math.random() + 1);
            buffer.append("Dado 1: ").append(diceResult);
        }

        if (rbQtd2.isChecked()){
            for (int i = 1; i <= 2; i++){
                int diceResult = (int) (qtdFaces * Math.random() + 1);
                buffer.append("\nDado ").append(i).append(": ").append(diceResult);
            }
        }

        if (rbQtd3.isChecked()){
            for (int i = 1; i <= 3; i++){
                int diceResult = (int) (qtdFaces * Math.random() + 1);
                buffer.append("\nDado ").append(i).append(": ").append(diceResult);
            }
        }

        tvSaida.setText(buffer.toString());
    }

    private void preencheSpinner(){
        List<Integer> faces = new ArrayList<>();
        faces.add(4);
        faces.add(6);
        faces.add(8);
        faces.add(10);
        faces.add(12);
        faces.add(20);
        faces.add(100);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, faces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spQtdFaces.setAdapter(adapter);
    }
}