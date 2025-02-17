package com.example.exemplomatricula.models.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exemplomatricula.models.Disciplina;

import java.util.List;

@Dao
public interface DisciplinaDao {

    @Insert
    void inserirDisciplina(Disciplina disciplina);

    @Insert
    void inserirListaDisciplinas(Disciplina disciplina);

    @Query("SELECT * FROM disciplinas")
    List<Disciplina> listarTodasDisciplinas();

    @Query("SELECT * FROM disciplinas WHERE id = :idDisciplina")
    Disciplina obterDisciplinaId(int idDisciplina);

    @Query("SELECT * FROM disciplinas WHERE nome = :nome")
    Disciplina obterDisciplinaPorNome(String nome);
}

