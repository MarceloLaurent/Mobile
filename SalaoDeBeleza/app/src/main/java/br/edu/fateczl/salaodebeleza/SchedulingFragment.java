package br.edu.fateczl.salaodebeleza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.salaodebeleza.controller.AgendamentoController;
import br.edu.fateczl.salaodebeleza.controller.ClientePadraoController;
import br.edu.fateczl.salaodebeleza.controller.ServicoController;
import br.edu.fateczl.salaodebeleza.model.Agendamento;
import br.edu.fateczl.salaodebeleza.model.ClientePadrao;
import br.edu.fateczl.salaodebeleza.model.Servico;
import br.edu.fateczl.salaodebeleza.persistence.AgendamentoDao;
import br.edu.fateczl.salaodebeleza.persistence.ClientePadraoDao;
import br.edu.fateczl.salaodebeleza.persistence.ServicoDao;

public class SchedulingFragment extends Fragment {
    private View view;
    private EditText etCodigoAgendamento, etData, etHorario, etTotal;
    private Button btnBuscarAgendamento, btnInserirAgendamento, btnListarAgendamento,
                    btnModificarAgendamento, btnExcluirAgendamento;
    private Spinner spCliente, spServico;
    private TextView tvListarAgendamento;
    private ClientePadraoController cpCont;
    private ServicoController sCont;
    private AgendamentoController agCont;
    private List<ClientePadrao> clientes;
    private List<Servico> servicos;

    public SchedulingFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduling, container, false);
        etCodigoAgendamento = view.findViewById(R.id.etCodigoAgendamento);
        etData = view.findViewById(R.id.etData);
        etHorario = view.findViewById(R.id.etHorario);
        etTotal = view.findViewById(R.id.etTotal);
        btnBuscarAgendamento = view.findViewById(R.id.btnBuscarAgendamento);
        btnInserirAgendamento = view.findViewById(R.id.btnInserirAgendamento);
        btnListarAgendamento = view.findViewById(R.id.btnListarAgendamento);
        btnModificarAgendamento = view.findViewById(R.id.btnModificarAgendamento);
        btnExcluirAgendamento = view.findViewById(R.id.btnExcluirAgendamento);
        spCliente = view.findViewById(R.id.spCliente);
        spServico = view.findViewById(R.id.spServico);
        tvListarAgendamento = view.findViewById(R.id.tvListarAgendamento);
        tvListarAgendamento.setMovementMethod(new ScrollingMovementMethod());

        cpCont = new ClientePadraoController(new ClientePadraoDao(view.getContext()));
        sCont = new ServicoController(new ServicoDao(view.getContext()));
        agCont = new AgendamentoController(new AgendamentoDao(view.getContext()));

        preencheSpinnerCliente();
        preencheSpinnerServico();

        btnInserirAgendamento.setOnClickListener(op -> acaoInserir());
        btnModificarAgendamento.setOnClickListener(op -> acaoModificar());
        btnExcluirAgendamento.setOnClickListener(op -> acaoExcluir());
        btnBuscarAgendamento.setOnClickListener(op -> acaoBuscar());
        btnListarAgendamento.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        int spCli = spCliente.getSelectedItemPosition();
        int spServ = spServico.getSelectedItemPosition();

        if (spCli > 0 && spServ > 0) {
            Agendamento agendamento = montarAgendamento();
            try {
                agCont.inserir(agendamento);
                Toast.makeText(view.getContext(), "Agendamento inserido com sucesso!",
                        Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limparCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um cliente e um serviço.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void acaoModificar() {
        int spCli = spCliente.getSelectedItemPosition();
        int spServ = spServico.getSelectedItemPosition();

        if (spCli > 0 && spServ > 0) {
            Agendamento agendamento = montarAgendamento();
            try {
                agCont.modificar(agendamento);
                Toast.makeText(view.getContext(), "Agendamento atualizado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limparCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um cliente e um serviço.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void acaoExcluir() {
        Agendamento agendamento = montarAgendamento();
            try {
                agCont.deletar(agendamento);
                Toast.makeText(view.getContext(), "Agendamento excluido com sucesso!",
                        Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limparCampos();
    }

    private void acaoBuscar() {
        Agendamento agendamento = montarAgendamento();
        try {
            clientes = cpCont.listar();
            servicos = sCont.listar();
            agendamento = agCont.buscar(agendamento);

            if (agendamento.getData() != null) {
                preencheCampos(agendamento);
            } else {
                Toast.makeText(view.getContext(), "Agendamento não encontrado",
                        Toast.LENGTH_LONG).show();
                limparCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void acaoListar() {
        try {
            List<Agendamento> agendamentos = agCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (Agendamento ag: agendamentos) {
                buffer.append(ag.toString() + "\n");
            }
            tvListarAgendamento.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinnerCliente() {
        ClientePadrao c0 = new ClientePadrao();
        c0.setTelefone(0);
        c0.setNome("Selecione um Cliente");
        c0.setEndereco("");

        try {
            clientes = cpCont.listar();
            clientes.add(c0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_spinner_item, clientes);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCliente.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinnerServico() {
        Servico s0 = new Servico();
        s0.setCodigoServico(0);
        s0.setDescricao("Selecione um Servico");
        s0.setValor(0.0);

        try {
            servicos = sCont.listar();
            servicos.add(s0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_spinner_item, servicos);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spServico.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Agendamento montarAgendamento() {
        Agendamento a = new Agendamento();
        a.setCodigoAgendamento(Integer.parseInt(etCodigoAgendamento.getText().toString()));
        a.setData(etData.getText().toString());
        a.setHorario(etHorario.getText().toString());
        a.setCliente((ClientePadrao) spCliente.getSelectedItem());
        a.setServico((Servico) spServico.getSelectedItem());

        return a;
    }

    private void preencheCampos(Agendamento a){
        etCodigoAgendamento.setText(String.valueOf(a.getCodigoAgendamento()));
        etData.setText(a.getData());
        etHorario.setText(a.getHorario());

        int cont = 1;
        for  (ClientePadrao cp: clientes) {
            if (cp.getTelefone() == a.getCliente().getTelefone()) {
                spCliente.setSelection(cont);
            } else {
                cont ++;
            }
        }

        if (cont > clientes.size()) {
            spCliente.setSelection(0);
        }

        cont = 1;
        for  (Servico s: servicos) {
            if (s.getCodigoServico() == a.getServico().getCodigoServico()) {
                spServico.setSelection(cont);
            } else {
                cont ++;
            }
        }

        if (cont > servicos.size()) {
            spServico.setSelection(0);
        }
    }

    private void limparCampos() {
        etCodigoAgendamento.setText("");
        etData.setText("");
        etHorario.setText("");
        spServico.setSelection(0);
        spCliente.setSelection(0);
        etTotal.setText("");
    }
}