package com.example.exemplomatricula.controllers;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.models.DAO.DisciplinaDao;
import com.example.exemplomatricula.models.DAO.EstudanteDisciplinaDao;
import com.example.exemplomatricula.models.Disciplina;
import com.example.exemplomatricula.models.EstudanteDisciplina;
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
    private EstudanteDisciplinaDao estudanteDisciplinaDao;

    public ManipularDisciplinas(Context context) {
        // Inicializando o BD
        dbDisciplina = Room.databaseBuilder(context, MyDatabase.class, "matricula")
                .fallbackToDestructiveMigration()
                .build();

        // Inicializando  as classes Daos necessárias
        disciplinaDao = dbDisciplina.disciplinasDao();
        //estudanteDisciplinaDao = dbDisciplina.estudantedisciplinaDao();

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
        for (String disciplinaString : disciplinas) {

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

        // Alterado as threads para que se aguarde a conclusão da busca para depois fazer return
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                disciplinas[0] = disciplinaDao.listarTodasDisciplinas();
                Log.d("ManipularDisciplinas", "Disciplinas obtidas: " + disciplinas[0].size());
            }
        });
        thread.start();

        // Esperando a thread termine suas operações
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ManipularDisciplinas", "Tamanho da lista de disciplinas retornada: " + disciplinas[0].size());
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

    // Método para salvar a escolha de disciplinas pelo estudante
    public void salvarEscolhaDisciplinas(int idEstudante, List<Disciplina> disciplinasEscolhidas) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Inserir as novas escolhas de disciplinas
                for (Disciplina disciplina : disciplinasEscolhidas) {
                    EstudanteDisciplina estudanteDisciplina = new EstudanteDisciplina(idEstudante, disciplina.getId());
                    estudanteDisciplinaDao.inserirEstudanteDisciplina(estudanteDisciplina);
                }
            }
        }).start();
    }

    // Método para listar as disciplinas escolhidas por um estudante
    public List<Disciplina> listarDisciplinasEscolhidas(int idEstudante) {
        final List<Disciplina>[] disciplinasEscolhidas = new List[]{new ArrayList<>()};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<EstudanteDisciplina> escolhas = estudanteDisciplinaDao.obterDisciplinasDeEstudante(idEstudante);
                for (EstudanteDisciplina escolha : escolhas) {
                    //Disciplina disciplina = disciplinaDao.obterDisciplinaId(escolha.id_disciplina);
                    //disciplinasEscolhidas[0].add(disciplina);
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return disciplinasEscolhidas[0];
    }


}

