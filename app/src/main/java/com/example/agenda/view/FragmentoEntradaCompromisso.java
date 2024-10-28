package com.example.agenda.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agenda.R;
import com.example.agenda.model.Compromisso;

public class FragmentoEntradaCompromisso extends Fragment {

    private Button buttonData;
    private Button buttonHora;
    private EditText editTextDescricao;
    private Button buttonOk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_entrada_compromisso, container, false);

        buttonData = view.findViewById(R.id.button_data);
        buttonHora = view.findViewById(R.id.button_hora);
        editTextDescricao = view.findViewById(R.id.edittext_descricao);
        buttonOk = view.findViewById(R.id.button_ok);

        buttonData.setOnClickListener(v -> {
            DataPickerDialog dialog = new DataPickerDialog((view1, year, month, dayOfMonth) -> {
                buttonData.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            });
            dialog.show(getParentFragmentManager(), "dataPicker");
        });

        buttonHora.setOnClickListener(v -> {
            HoraPickerDialog dialog = new HoraPickerDialog((view12, hourOfDay, minute) -> {
                buttonHora.setText(hourOfDay + ":" + String.format("%02d", minute));
            });
            dialog.show(getParentFragmentManager(), "horaPicker");
        });

        buttonOk.setOnClickListener(v -> {
            String nome = "";
            String descricao = editTextDescricao.getText().toString();
            String dataSelecionada = buttonData.getText().toString();
            String horaSelecionada = buttonHora.getText().toString();

            if (!descricao.isEmpty() && !dataSelecionada.equals("Data") && !horaSelecionada.equals("Hora")) {
                Compromisso novoCompromisso = new Compromisso(nome, descricao, dataSelecionada, horaSelecionada);
                editTextDescricao.setText("");
                buttonData.setText("Data");
                buttonHora.setText("Hora");
            } else {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
