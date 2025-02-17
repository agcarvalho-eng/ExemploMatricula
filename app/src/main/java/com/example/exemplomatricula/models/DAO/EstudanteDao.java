package com.example.exemplomatricula.models.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.exemplomatricula.models.Estudante;
import com.example.exemplomatricula.models.EstudanteComDisciplinas;

import java.util.List;

@Dao
public interface EstudanteDao {

    @Insert
    void inserirEstudante(Estudante estudante);

    @Query("SELECT * FROM estudantes")
    List<Estudante> listarTodosEstudantes();

    @Update
    void atualizarEstudante(Estudante estudante);

    @Delete
    void deletarEstudante(Estudante estudante);

    @Query("SELECT nome FROM estudantes WHERE id = :idEstudante")
    String obterNomeEstudanteId(int idEstudante);

    @Query("SELECT * FROM estudantes WHERE email = :email AND senha = :senha")
    Estudante validarLogin(String email, String senha);

    // Obter um estudante com suas disciplinas
    @Query("SELECT * FROM estudantes WHERE id = :idEstudante")
    EstudanteComDisciplinas obterEstudanteComDisciplinas (int idEstudante);
}
