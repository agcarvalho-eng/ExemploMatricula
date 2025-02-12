package com.example.exemplomatricula.controllers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exemplomatricula.models.Disciplina;

import java.util.List;

@Dao
public interface DisciplinasDao {

    @Insert
    void insert(Disciplina disciplina);

    @Query("SELECT * FROM disciplinas")
    List<Disciplina> listarTodasDisciplinas();

    @Query("SELECT * FROM disciplinas WHERE id = :id")
    Disciplina obterDisciplinaId(int id);
}

