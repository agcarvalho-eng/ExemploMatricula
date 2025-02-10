package com.example.exemplomatricula.controllers;

import android.content.Context;

import androidx.room.Room;

import com.example.exemplomatricula.models.AproveitamentoEscolar;
import com.example.exemplomatricula.models.MyDatabaseAproveitamentoEscolar;
import com.example.exemplomatricula.models.MyDatabaseEstudante;

public class ManipularAproveitamentoEscolar {

    private MyDatabaseAproveitamentoEscolar db_AE;

    public ManipularAproveitamentoEscolar(Context context) {
        db_AE = Room.databaseBuilder(context, MyDatabaseAproveitamentoEscolar.class, "aproveitamento-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public void inserirAproveitamentoEscolar(float valor, int id_estudante) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AproveitamentoEscolar aproveitamentoEscolar = new AproveitamentoEscolar(valor, id_estudante);
                db_AE.aproveitamentoEscolarDao().inserirAproveitamentoEscolar(aproveitamentoEscolar);
            }
        }).start();
    }

    public float obterAproveitamentoEscolar(int id_estudante) {
        final float[] valorAproveitamentoEscolar = {0};
        new Thread(new Runnable() {
            @Override
            public void run() {
                valorAproveitamentoEscolar[0] = db_AE.aproveitamentoEscolarDao().obterAproveitamentoEscolar(id_estudante);
            }
        }).start();
        return valorAproveitamentoEscolar[0];
    }

}
