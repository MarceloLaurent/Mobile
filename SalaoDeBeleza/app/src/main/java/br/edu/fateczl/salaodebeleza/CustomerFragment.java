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

public class CustomerFragment extends Fragment {

    private View view;
    private EditText etTelefone, etNome, etEndereco;
    private Button btnBuscarCliente, btnInserirCliente, btnListarCliente, btnModificarCliente, btnExcluirCliente;
    private TextView tvListarCliente;

    public CustomerFragment() {
       super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        etTelefone = view.findViewById(R.id.etTelefone);
        etNome = view.findViewById(R.id.etNome);
        etEndereco = view.findViewById(R.id.etEndereco);
        btnBuscarCliente = view.findViewById(R.id.btnBuscarCliente);
        btnInserirCliente = view.findViewById(R.id.btnInserirCliente);
        btnListarCliente = view.findViewById(R.id.btnListarCliente);
        btnModificarCliente = view.findViewById(R.id.btnModificarCliente);
        btnExcluirCliente = view.findViewById(R.id.btnExcluirCliente);
        tvListarCliente = view.findViewById(R.id.tvListarCliente);
        tvListarCliente.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}