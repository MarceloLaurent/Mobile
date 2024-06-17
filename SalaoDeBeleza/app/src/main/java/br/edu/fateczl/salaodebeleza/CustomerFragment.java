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
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.salaodebeleza.controller.ClientePadraoController;
import br.edu.fateczl.salaodebeleza.model.ClientePadrao;
import br.edu.fateczl.salaodebeleza.persistence.ClientePadraoDao;

public class CustomerFragment extends Fragment {

    private View view;
    private EditText etTelefone, etNome, etEndereco;
    private Button btnBuscarCliente, btnInserirCliente, btnListarCliente, btnModificarCliente, btnExcluirCliente;
    private TextView tvListarCliente;
    private ClientePadraoController cpCont;

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

        cpCont = new ClientePadraoController(new ClientePadraoDao(view.getContext()));

        btnInserirCliente.setOnClickListener(op -> acaoInserir());
        btnModificarCliente.setOnClickListener(op -> acaoModificar());
        btnExcluirCliente.setOnClickListener(op -> acaoExcluir());
        btnBuscarCliente.setOnClickListener(op -> acaoBuscar());
        btnListarCliente.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        ClientePadrao cp = montarCliente();
        try {
            cpCont.inserir(cp);
            Toast.makeText(view.getContext(), "Cliente inserido com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoModificar() {
        ClientePadrao cp = montarCliente();
        try {
            cpCont.modificar(cp);
            Toast.makeText(view.getContext(), "Cliente atualizado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoExcluir() {
        ClientePadrao cp = montarCliente();
        try {
            cpCont.deletar(cp);
            Toast.makeText(view.getContext(), "Cliente excluido com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoBuscar() {
        ClientePadrao cp = montarCliente();
        try {
            cp = cpCont.buscar(cp);
            if (cp.getNome() != null) {
                preencheCampos(cp);
            } else {
                Toast.makeText(view.getContext(), "Cliente não encontrado.", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoListar() {
        try {
            List<ClientePadrao> clientes = cpCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (ClientePadrao cp: clientes) {
                buffer.append(cp.toString() + "\n");
            }
            tvListarCliente.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private ClientePadrao montarCliente() {
        ClientePadrao c = new ClientePadrao();
        c.setTelefone(Integer.parseInt(etTelefone.getText().toString()));
        c.setNome(etNome.getText().toString());
        c.setEndereco(etEndereco.getText().toString());

        return c;
    }

    private void preencheCampos(ClientePadrao c){
        etTelefone.setText(String.valueOf(c.getTelefone()));
        etNome.setText(c.getNome());
        etEndereco.setText(c.getEndereco());
    }

    private void limparCampos() {
        etTelefone.setText("");
        etNome.setText("");
        etEndereco.setText("");
    }
}