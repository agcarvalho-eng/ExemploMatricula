package com.example.exemplomatricula.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;
@Entity(tableName = "estudante_com_disciplina")
public class EstudanteComDisciplinas {

    @Embedded
    // Atributo da classe Estudante
    public Estudante estudante;  // Atributo da classe Estudante

    @Relation(
            parentColumn = "id",  // A coluna da tabela 'estudantes'
            entityColumn = "id",  // A coluna da tabela 'disciplinas'
            associateBy = @Junction(EstudanteDisciplina.class)  // Define a tabela intermediária
    )
    // A lista de Disciplinas associadas ao Estudante
    public List<Disciplina> disciplinas;

    // Construtor padrão para Room
    public EstudanteComDisciplinas () {}

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}

