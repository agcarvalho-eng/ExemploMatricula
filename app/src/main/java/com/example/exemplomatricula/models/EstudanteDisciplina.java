package com.example.exemplomatricula.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estudante_disciplina")
public class EstudanteDisciplina {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "id_estudante")  // Relaciona com a tabela 'estudante'
    private int idEstudante;

    @ColumnInfo(name = "id_disciplina")  // Relaciona com a tabela 'disciplina'
    private int idDisciplina;

     // Room exige um construtor padrão
    public EstudanteDisciplina() {}

    public EstudanteDisciplina(int id_estudante, int id_disciplina) {
        this.idEstudante = id_estudante;
        this.idDisciplina = id_disciplina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(int idEstudante) {
        this.idEstudante = idEstudante;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}

