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

public class VipFragment extends Fragment {

    private View view;
    private EditText etTelefoneVip, etNomeVip, etEnderecoVip, etCodigoVip;
    private Button btnBuscarClienteVip, btnInserirClienteVip, btnListarClienteVip,
                    btnModificarClienteVip, btnExcluirClienteVip;
    private TextView tvListarClienteVip;

    public VipFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vip, container, false);
        etTelefoneVip = view.findViewById(R.id.etTelefoneVip);
        etNomeVip = view.findViewById(R.id.etNomeVip);
        etEnderecoVip = view.findViewById(R.id.etEnderecoVip);
        etCodigoVip = view.findViewById(R.id.etCodigoVip);
        btnBuscarClienteVip = view.findViewById(R.id.btnBuscarClienteVip);
        btnInserirClienteVip = view.findViewById(R.id.btnInserirClienteVip);
        btnListarClienteVip = view.findViewById(R.id.btnListarClienteVip);
        btnModificarClienteVip = view.findViewById(R.id.btnModificarClienteVip);
        btnExcluirClienteVip = view.findViewById(R.id.btnExcluirClienteVip);
        tvListarClienteVip = view.findViewById(R.id.tvListarClienteVip);
        tvListarClienteVip.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}