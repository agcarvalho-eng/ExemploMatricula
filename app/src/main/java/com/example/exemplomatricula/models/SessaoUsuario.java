package com.example.exemplomatricula.models;

public class SessaoUsuario {
    // Atributos para armazenar id do estudante da sessão
    private static int idEstudante;

    // Método para definir o ID do estudante
    public static void setIdEstudante(int id) {
        idEstudante = id;
    }

    // Método para obter o ID do estudante
    public static int getIdEstudante() {
        return idEstudante;
    }

}
