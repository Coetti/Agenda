package com.example.agenda.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agenda.R;
import com.example.agenda.utils.DataPickerListener;

import java.util.Calendar;

public class FragmentoVisualizacaoCompromisso extends Fragment implements DataPickerListener {

    private TextView textoCompromissos;
    private Button botaoHoje;
    private Button botaoOutraData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_visualizacao_compromisso, container, false);

        textoCompromissos = view.findViewById(R.id.texto_compromissos);
        botaoHoje = view.findViewById(R.id.button_hoje);
        botaoOutraData = view.findViewById(R.id.button_outra_data);

        botaoHoje.setOnClickListener(v -> mostrarCompromissosDoDiaAtual());

        botaoOutraData.setOnClickListener(v -> abrirOutraDataPickerDialog());

        return view;
    }

    private void abrirOutraDataPickerDialog() {
        OutraDataPickerDialog dialog = new OutraDataPickerDialog(this);
        dialog.show(getChildFragmentManager(), "datePicker");
    }

    private void mostrarCompromissosDoDiaAtual() {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String dataHoje ="VISUALIZANDO -> " + dia + "/" + (mes + 1) + "/" + ano;
        textoCompromissos.setText(dataHoje);
    }

    @Override
    public void onDataSelecionada(int ano, int mes, int dia) {
        String dataSelecionada ="VISUALIZANDO -> " + dia + "/" + (mes + 1) + "/" + ano;
        textoCompromissos.setText(dataSelecionada);
    }
}
