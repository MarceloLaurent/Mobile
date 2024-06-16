package br.edu.fateczl.salaodebeleza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SchedulingFragment extends Fragment {
    private View view;
    private EditText etCodigoAgendamento, etData, etHorario;
    private Button btnBuscarAgendamento, btnInserirAgendamento, btnListarAgendamento,
                    btnModificarAgendamento, btnExcluirAgendamento;
    private Spinner spCliente, spServico;
    private TextView tvListarAgendamento;

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
        btnBuscarAgendamento = view.findViewById(R.id.btnBuscarAgendamento);
        btnInserirAgendamento = view.findViewById(R.id.btnInserirAgendamento);
        btnListarAgendamento = view.findViewById(R.id.btnListarAgendamento);
        btnModificarAgendamento = view.findViewById(R.id.btnModificarAgendamento);
        btnExcluirAgendamento = view.findViewById(R.id.btnExcluirAgendamento);
        spCliente = view.findViewById(R.id.spCliente);
        spServico = view.findViewById(R.id.spServico);
        tvListarAgendamento = view.findViewById(R.id.tvListarAgendamento);
        tvListarAgendamento.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}