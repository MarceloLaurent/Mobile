package br.edu.fateczl.crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StartFragment extends Fragment {

    private View view;
    private TextView tvInicio;

    public StartFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start, container, false);
        tvInicio = view.findViewById(R.id.tvInicio);
        return view;
    }
}