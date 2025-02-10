package com.example.exemplomatricula.controllers;

import android.content.Context;
import android.os.Message;
import android.os.Handler;
import androidx.room.Room;
import com.example.exemplomatricula.models.Estudante;
import com.example.exemplomatricula.models.MyDatabaseEstudante;

public class ManipularEstudante {

    private MyDatabaseEstudante db_estudante;

    public ManipularEstudante(Context context) {
        db_estudante = Room.databaseBuilder(context, MyDatabaseEstudante.class, "estudante-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public void inserirEstudante(Estudante estudante) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db_estudante.estudanteDao().inserirEstudante(estudante);
            }
        }).start();
    }

    public void obterTodosEstudantes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db_estudante.estudanteDao().obterTodosEstudantes();
            }
        }).start();
    }

    public String obterNomeEstudante(int idEstudante) {
        final String[] nomeEstudante = {""};
        new Thread(new Runnable() {
            @Override
            public void run() {
                nomeEstudante[0] = db_estudante.estudanteDao().obterNomeEstudante(idEstudante);
            }
        }).start();
        return nomeEstudante[0];
    }

    public void validarLogin(Estudante estudante, Handler handler) {
        final String emailEstudante = estudante.getEmail();
        final String senhaEstudante = estudante.getSenha();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Estudante estudanteResult = db_estudante.estudanteDao().validarLogin(emailEstudante, senhaEstudante);

                // Criando uma mensagem para enviar para o Handler
                Message message = handler.obtainMessage();
                // Atribuindo o resultado ao objeto
                message.obj = estudanteResult;
                // Enviando o resultado para o Handler
                handler.sendMessage(message);
            }
        }).start();
    }
}

