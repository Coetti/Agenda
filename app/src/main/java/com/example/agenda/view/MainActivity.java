package com.example.agenda.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.agenda.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmento_entrada_container, new FragmentoEntradaCompromisso());


        transaction.replace(R.id.fragmento_visualizacao_container, new FragmentoVisualizacaoCompromisso());

        transaction.commit();
    }
}
