package com.example.agenda.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.agenda.utils.DataPickerListener;

import java.util.Calendar;

public class OutraDataPickerDialog extends DialogFragment {

    private DataPickerListener listener;

    public OutraDataPickerDialog(DataPickerListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DatePickerDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, anoSelecionado, mesSelecionado, diaSelecionado) -> {
            if (listener != null) {
                listener.onDataSelecionada(anoSelecionado, mesSelecionado, diaSelecionado);
            }
        }, ano, mes, dia);

        return datePickerDialog;
    }
}
