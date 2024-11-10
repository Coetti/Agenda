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
import com.example.agenda.database.CompromissosDB;
import com.example.agenda.model.Compromisso;

import java.util.Calendar;
import java.util.List;

public class FragmentoVisualizacaoCompromisso extends Fragment implements DataPickerListener {

    private TextView dataVisualizacao;
    private TextView textoCompromissos;
    private Button botaoHoje;
    private Button botaoOutraData;
    private CompromissosDB compromissosDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_visualizacao_compromisso, container, false);

        textoCompromissos = view.findViewById(R.id.texto_compromissos);
        dataVisualizacao = view.findViewById(R.id.data_visualizacao);
        botaoHoje = view.findViewById(R.id.button_hoje);
        botaoOutraData = view.findViewById(R.id.button_outra_data);

        compromissosDB = new CompromissosDB(getContext());

        mostrarTodosCompromissos();

        botaoHoje.setOnClickListener(v -> mostrarCompromissosDoDiaAtual());

        botaoOutraData.setOnClickListener(v -> abrirOutraDataPickerDialog());

        return view;
    }

    private void mostrarTodosCompromissos(){
        List<Compromisso> todosCompromissos = compromissosDB.getAllCompromissos();
        atualizarTextoCompromissos(todosCompromissos);
        dataVisualizacao.setText("TODOS");
    }


    private void abrirOutraDataPickerDialog() {
        OutraDataPickerDialog dialog = new OutraDataPickerDialog(this);
        dialog.show(getChildFragmentManager(), "datePicker");
    }

    private void mostrarCompromissosDoDiaAtual() {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        String dataHoje = dia + "/" + mes + "/" + ano;
        List<Compromisso> compromissosHoje = compromissosDB.getCompromissosByDate(dataHoje);
        atualizarTextoCompromissos(compromissosHoje);
        dataVisualizacao.setText(dataHoje);
    }

    @Override
    public void onDataSelecionada(int ano, int mes, int dia) {
        String dataSelecionada = dia + "/" + (mes + 1) + "/" + ano;
        List<Compromisso> compromissosNaData = compromissosDB.getCompromissosByDate(dataSelecionada);
        atualizarTextoCompromissos(compromissosNaData);
        dataVisualizacao.setText(dataSelecionada);
    }

    private void atualizarTextoCompromissos(List<Compromisso> compromissos) {
        if (compromissos.isEmpty()) {
            textoCompromissos.setText("Nenhum compromisso encontrado.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Compromisso compromisso : compromissos) {
                builder.append(compromisso.imprimeCompromisso()).append("\n\n");
            }
            textoCompromissos.setText(builder.toString());
        }
    }
}
