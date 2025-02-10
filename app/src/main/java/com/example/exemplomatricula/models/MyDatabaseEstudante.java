package com.example.exemplomatricula.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.exemplomatricula.controllers.AproveitamentoEscolarDao;
import com.example.exemplomatricula.controllers.EstudanteDao;

@Database(entities = {Estudante.class}, version = 1)
public abstract class MyDatabaseEstudante extends RoomDatabase {
    public abstract EstudanteDao estudanteDao();

}

