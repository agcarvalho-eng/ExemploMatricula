package com.example.exemplomatricula.models.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exemplomatricula.models.EstudanteDisciplina;

import java.util.List;

@Dao
public interface EstudanteDisciplinaDao {

    // Inserindo um novo relacionamento entre estudante e disciplina
    @Insert
    void inserirEstudanteDisciplina(EstudanteDisciplina estudanteDisciplina);

    // Deletando um relacionamento entre estudante e disciplina
    @Query("DELETE FROM estudante_disciplina WHERE id_estudante = :idEstudante AND id_disciplina = :idDisciplina")
    void deletarEstudanteDisciplina(int idEstudante, int idDisciplina);

    // Consultando todas as disciplinas de um estudante
    @Query("SELECT * FROM estudante_disciplina WHERE id_estudante = :idEstudante")
    List<EstudanteDisciplina> obterDisciplinasDeEstudante(int idEstudante);

    // Consultando todos os estudantes que escolheram uma disciplina
    @Query("SELECT * FROM estudante_disciplina WHERE id_disciplina = :idDisciplina")
    List<EstudanteDisciplina> obterEstudantesDeDisciplina(int idDisciplina);
}

