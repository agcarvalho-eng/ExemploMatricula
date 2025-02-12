package com.example.exemplomatricula.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.exemplomatricula.controllers.AproveitamentoEscolarDao;
import com.example.exemplomatricula.controllers.DisciplinasDao;
import com.example.exemplomatricula.controllers.EstudantesDao;

@Database(entities = {Estudante.class, Disciplina.class, AproveitamentoEscolar.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract EstudantesDao estudanteDao();
    public abstract DisciplinasDao disciplinasDao();
    public abstract AproveitamentoEscolarDao aproveitamentoEscolarDao();

}

