package com.example.exemplomatricula.controllers;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.exemplomatricula.models.AproveitamentoEscolar;

@Dao
public interface AproveitamentoEscolarDao {

    @Insert
    void inserirAproveitamentoEscolar(AproveitamentoEscolar aproveitamentoEscolar);

    @Query("SELECT valor FROM aproveitamentoEscolar WHERE id_estudante = :idEstudante")
    float obterAproveitamentoEscolar(int idEstudante);

    @Update
    void atualizarAproveitamentoEscolar(AproveitamentoEscolar aproveitamentoEscolar);

    @Delete
    void deletarAproveitamentoEscolar(AproveitamentoEscolar aproveitamentoEscolar);


}
