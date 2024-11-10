package com.example.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agenda.model.Compromisso;

import java.util.ArrayList;
import java.util.List;

public class CompromissosDB {
    private SQLiteDatabase db;

    public CompromissosDB(Context context) {
        CompromissosDBHelper dbHelper = new CompromissosDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addCompromisso(Compromisso compromisso) {
        ContentValues values = new ContentValues();
        values.put(CompromissosDBSchema.CompromissoTable.Cols.DESCRICAO, compromisso.getDescricao());
        values.put(CompromissosDBSchema.CompromissoTable.Cols.DATA, compromisso.getData());
        values.put(CompromissosDBSchema.CompromissoTable.Cols.HORA, compromisso.getHora());

        db.insert(CompromissosDBSchema.CompromissoTable.NAME, null, values);
    }

    public List<Compromisso> getAllCompromissos() {
        List<Compromisso> compromissos = new ArrayList<>();
        Cursor cursor = db.query(
                CompromissosDBSchema.CompromissoTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        try {
            if (cursor.moveToFirst()) {
                do {
                    String descricao = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.DESCRICAO));
                    String data = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.DATA));
                    String hora = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.HORA));

                    Compromisso compromisso = new Compromisso(descricao, data, hora);
                    compromissos.add(compromisso);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return compromissos;
    }

    public List<Compromisso> getCompromissosByDate(String data) {
        List<Compromisso> compromissos = new ArrayList<>();

        Cursor cursor = db.query(
                CompromissosDBSchema.CompromissoTable.NAME,
                null,
                CompromissosDBSchema.CompromissoTable.Cols.DATA + " = ?",
                new String[]{data},
                null,
                null,
                null
        );

        try {
            if (cursor.moveToFirst()) {
                do {
                    String descricao = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.DESCRICAO));
                    String dataCompromisso = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.DATA));
                    String hora = cursor.getString(cursor.getColumnIndex(CompromissosDBSchema.CompromissoTable.Cols.HORA));
                    compromissos.add(new Compromisso(descricao, dataCompromisso, hora));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return compromissos;
    }


}
