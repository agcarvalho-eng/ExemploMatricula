package com.example.exemplomatricula.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.exemplomatricula.models.DAO.AproveitamentoEscolarDao;
import com.example.exemplomatricula.models.DAO.DisciplinaDao;
import com.example.exemplomatricula.models.DAO.EstudanteDao;

@Database(entities = {Estudante.class, Disciplina.class, AproveitamentoEscolar.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract EstudanteDao estudanteDao();
    public abstract DisciplinaDao disciplinasDao();
    public abstract AproveitamentoEscolarDao aproveitamentoEscolarDao();

}

