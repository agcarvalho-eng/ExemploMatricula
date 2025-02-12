package com.example.exemplomatricula.controllers;

import android.content.Context;

import androidx.room.Room;

import com.example.exemplomatricula.models.Disciplina;
import com.example.exemplomatricula.models.MyDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CarregarDisciplinasIniciais implements Runnable {

    private Context context;

    public CarregarDisciplinasIniciais(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        // Inicializando o banco de dados
        MyDatabase db = Room.databaseBuilder(context, MyDatabase.class, "matricula")
                // Exclua e recrie o banco de dados (evita falha de migração)
                .fallbackToDestructiveMigration()
                .build();

        DisciplinasDao disciplinasDao = db.disciplinasDao();

        try {
            // Abrindo o arquivo sql com as disciplinas iniciais
            InputStream inputStream = context.getAssets().open("disciplinas_iniciais.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Ignorando comentários e linhas vazias
                if (linha.trim().startsWith("--") || linha.trim().isEmpty()) {
                    continue;
                }

                // Verificando se a linha é uma inserção de disciplina
                if (linha.contains("INSERT INTO disciplinas")) {
                    // Dividindo a linha para obter os valores após a palavra "VALUES"
                    // Fazendo a extração após a palavra "VALUES"
                    String valores = linha.split("VALUES")[1].trim();
                    // Retirando parênteses e apóstrofos
                    valores = valores.replace("(", "").replace(")",
                            "").replace("'", "").trim();
                    // Agora os valores estão no formato Matemática, 1.5

                    // Separando nome e peso pela vírgula
                    String[] partes = valores.split(",");

                    if (partes.length == 2) {
                        // Pegando o nome da disciplina
                        String nomeDisciplina = partes[0].trim();
                        // Pegando o peso da disciplina
                        double peso = Double.parseDouble(partes[1].trim());

                        // Criando a disciplina e inserindo no banco
                        Disciplina disciplina = new Disciplina(nomeDisciplina, peso);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                disciplinasDao.insert(disciplina);
                            }
                        }).start();

                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

