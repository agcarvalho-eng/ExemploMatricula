package com.example.exemplomatricula.controllers;

import android.content.Context;

import androidx.room.Room;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.models.DAO.DisciplinaDao;
import com.example.exemplomatricula.models.Disciplina;
import com.example.exemplomatricula.models.MyDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ManipularDisciplinas {

    private MyDatabase dbDisciplina;
    private DisciplinaDao disciplinaDao;

    public ManipularDisciplinas(Context context) {
        // Inicializando o BD
        dbDisciplina = Room.databaseBuilder(context, MyDatabase.class, "matricula")
                .fallbackToDestructiveMigration()
                .build();

        // Inicializando a disciplinaDao
        disciplinaDao = dbDisciplina.disciplinasDao();

    }

    public void inserirDisciplina(Disciplina disciplina) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbDisciplina.disciplinasDao().inserirDisciplina(disciplina);
            }
        }).start();
    }

    // Método para inserir a lista de disciplinas no BD
    public void inserirListaDisciplinas(Context context) {

        // Lendo o string-array do arquivo strings.xml
        String[] disciplinas = context.getResources().getStringArray(R.array.disciplinas);

        // Iterando sobre as strings do array
        for (int i = 0; i < disciplinas.length; i++) {
            // Dividindo cada string para obter a descrição e o seu peso
            String disciplinaString = disciplinas[i];
            // Dividindo a string usando vírgula como separador
            String[] separando = disciplinaString.split(",");

            // Pegando o nome da disciplina e o seu peso
            String nome = separando[0];
            double peso = Double.parseDouble(separando[1]);

            // Criando o objeto Disciplina
            Disciplina disciplina = new Disciplina(nome, peso);

            // Criando uma nova thread para inserir a disciplina no BD
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Inserindo a disciplina no BD
                    disciplinaDao.inserirListaDisciplinas(disciplina);
                }
            }).start();
        }
    }

    public List<Disciplina> listarTodasDisciplinas() {
        final List<Disciplina>[] disciplinas = new List[]{new ArrayList<>()};
        new Thread(new Runnable() {
            @Override
            public void run() {
                disciplinas[0] = disciplinaDao.listarTodasDisciplinas();
            }
        }).start();
        return disciplinas[0];
    }

    public Disciplina obterDisciplinaId(int idDisciplina) {
        final Disciplina[] disciplina = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                disciplina[0] = dbDisciplina.disciplinasDao().obterDisciplinaId(idDisciplina);
            }
        }).start();
        return disciplina[0];
    }

    public boolean existeDisciplina(final String nomeDisciplina) {
        final boolean[] existe = {false};
        // Criando uma thread para realizar a consulta
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Disciplina disciplina = dbDisciplina.disciplinasDao().obterDisciplinaPorNome(nomeDisciplina);
                if (disciplina != null) {
                    existe[0] = true;
                }
            }
        });

        // Inicia a execução da thread
        thread.start();

        // Como a consulta no BD é realizada em paralelo tem que aguardar
        try {
            // Aguarda a conclusão da thread antes de continuar
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Retorna o valor verificado
        return existe[0];
    }


}

