package com.example.exemplomatricula.controllers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.exemplomatricula.models.Estudante;

import java.util.List;

@Dao
public interface EstudanteDao {

    @Insert
    void inserirEstudante(Estudante estudante);

    @Query("SELECT * FROM estudantes")
    List<Estudante> obterTodosEstudantes();

    @Update
    void atualizarEstudante(Estudante estudante);

    @Delete
    void deletarEstudante(Estudante estudante);

    @Query("SELECT nome FROM estudantes WHERE id = :idEstudante")
    String obterNomeEstudante(int idEstudante);

    @Query("SELECT * FROM estudantes WHERE email = :email AND senha = :senha")
    Estudante validarLogin(String email, String senha);

}
