package com.example.exemplomatricula.controllers;

import android.content.Context;
import android.os.Message;
import android.os.Handler;
import android.util.Log;

import androidx.room.Room;
import com.example.exemplomatricula.models.Estudante;
import com.example.exemplomatricula.models.MyDatabase;
import com.example.exemplomatricula.models.SessaoUsuario;

public class ManipularEstudante {

    private MyDatabase db_estudante;

    public ManipularEstudante(Context context) {
        // Inicializando o BD
        db_estudante = Room.databaseBuilder(context, MyDatabase.class, "matricula")
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
                db_estudante.estudanteDao().listarTodosEstudantes();
            }
        }).start();
    }

    public String obterNomeEstudante(int idEstudante) {
        final String[] nomeEstudante = {""};
        new Thread(new Runnable() {
            @Override
            public void run() {
                nomeEstudante[0] = db_estudante.estudanteDao().obterNomeEstudanteId(idEstudante);

            }
        }).start();
        return nomeEstudante[0];
    }

    public void validarLogin(Estudante estudante, Handler handler) {
        final String emailEstudante = estudante.getEmail();
        final String senhaEstudante = estudante.getSenha();

        // Criando uma nova Thread para consultar o BD
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Chamando o DAO para fazer a consulta
                Estudante estudanteResultado = db_estudante.estudanteDao().validarLogin(emailEstudante, senhaEstudante);

                // Criando um objeto mensagem para enviar para o Handler (permite comunicação entre as Threads)
                Message mensagem = handler.obtainMessage();
                if(estudanteResultado != null) {
                    // Guardando as informações do estudante na sessão
                    SessaoUsuario.setIdEstudante(estudanteResultado.getId());
                    SessaoUsuario.setNomeEstudante(estudanteResultado.getNome());
                    SessaoUsuario.setEmailEstudante(estudanteResultado.getEmail());
                    // Atribuindo o resultado ao objeto message
                    mensagem.obj = estudanteResultado;
                } else {
                    mensagem.obj = null;
                }

                // Enviando o resultado para o Handler
                handler.sendMessage(mensagem);
            }
        }).start();
    }
}

