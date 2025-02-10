package com.example.exemplomatricula.models;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.exemplomatricula.controllers.AproveitamentoEscolarDao;

@Database(entities = {AproveitamentoEscolar.class}, version = 1)
public abstract class MyDatabaseAproveitamentoEscolar extends RoomDatabase {

    public abstract AproveitamentoEscolarDao aproveitamentoEscolarDao();

}
