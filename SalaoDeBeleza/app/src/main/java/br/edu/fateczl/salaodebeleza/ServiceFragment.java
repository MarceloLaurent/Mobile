package br.edu.fateczl.salaodebeleza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceFragment extends Fragment {
    private View view;
    private EditText etCodigoServico, etDescricao, etValor;
    private Button btnBuscarServico, btnInserirServico, btnListarServico, btnModificarServico, btnExcluirServico;
    private TextView tvListarServico;

    public ServiceFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        etCodigoServico = view.findViewById(R.id.etCodigoServico);
        etDescricao = view.findViewById(R.id.etDescricao);
        etValor = view.findViewById(R.id.etValor);
        btnBuscarServico = view.findViewById(R.id.btnBuscarServico);
        btnInserirServico = view.findViewById(R.id.btnInserirServico);
        btnListarServico = view.findViewById(R.id.btnListarServico);
        btnModificarServico = view.findViewById(R.id.btnModificarServico);
        btnExcluirServico = view.findViewById(R.id.btnExcluirServico);
        tvListarServico = view.findViewById(R.id.tvListarServico);
        tvListarServico.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}