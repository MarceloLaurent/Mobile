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

import br.edu.fateczl.salaodebeleza.controller.ServicoController;
import br.edu.fateczl.salaodebeleza.model.Servico;
import br.edu.fateczl.salaodebeleza.persistence.ServicoDao;

public class ServiceFragment extends Fragment {
    private View view;
    private EditText etCodigoServico, etDescricao, etValor;
    private Button btnBuscarServico, btnInserirServico, btnListarServico, btnModificarServico, btnExcluirServico;
    private TextView tvListarServico;
    private ServicoController sCont;

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

        sCont = new ServicoController(new ServicoDao(view.getContext()));

        btnInserirServico.setOnClickListener(op -> acaoInserir());
        btnModificarServico.setOnClickListener(op -> acaoModificar());
        btnExcluirServico.setOnClickListener(op -> acaoExcluir());
        btnBuscarServico.setOnClickListener(op -> acaoBuscar());
        btnListarServico.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Servico s = montarServico();
        try {
            sCont.inserir(s);
            Toast.makeText(view.getContext(), "Cliente inserido com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoModificar() {
        Servico s = montarServico();
        try {
            sCont.modificar(s);
            Toast.makeText(view.getContext(), "Serviço atualizado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoExcluir() {
        Servico s = montarServico();
        try {
            sCont.deletar(s);
            Toast.makeText(view.getContext(), "Serviço excluido com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoBuscar() {
        Servico s = montarServico();
        try {
            s = sCont.buscar(s);
            if (s.getDescricao() != null) {
                preencheCampos(s);
            } else {
                Toast.makeText(view.getContext(), "Serviço não encontrado.", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    private void acaoListar() {
        try {
            List<Servico> servicos = sCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (Servico cp: servicos) {
                buffer.append(cp.toString() + "\n");
            }
            tvListarServico.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Servico montarServico() {
        Servico s = new Servico();
        s.setCodigoServico(Integer.parseInt(etCodigoServico.getText().toString()));
        s.setDescricao(etDescricao.getText().toString());
        s.setValor(Double.parseDouble(etValor.getText().toString()));

        return s;
    }

    private void preencheCampos(Servico s){
        etCodigoServico.setText(String.valueOf(s.getCodigoServico()));
        etDescricao.setText(s.getDescricao());
        etValor.setText(String.valueOf((s.getValor())));
    }

    private void limparCampos() {
        etCodigoServico.setText("");
        etDescricao.setText("");
        etValor.setText("");
    }
}