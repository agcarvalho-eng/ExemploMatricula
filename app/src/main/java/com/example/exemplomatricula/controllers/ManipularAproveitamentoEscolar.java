package com.example.exemplomatricula.controllers;

import android.content.Context;

import androidx.room.Room;

import com.example.exemplomatricula.models.AproveitamentoEscolar;
import com.example.exemplomatricula.models.MyDatabase;


public class ManipularAproveitamentoEscolar {

    private MyDatabase db_AE;
    //private MyDatabaseAproveitamentoEscolar db_AE;

    public ManipularAproveitamentoEscolar(Context context) {
        db_AE = Room.databaseBuilder(context, MyDatabase.class, "matricula")
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

    public double obterAproveitamentoEscolar(int id_estudante) {
        final double[] valorAproveitamentoEscolar = {0};
        new Thread(new Runnable() {
            @Override
            public void run() {
                valorAproveitamentoEscolar[0] = db_AE.aproveitamentoEscolarDao().obterAproveitamentoEscolar(id_estudante);
            }
        }).start();
        return valorAproveitamentoEscolar[0];
    }

}
