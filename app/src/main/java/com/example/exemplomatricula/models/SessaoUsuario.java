package com.example.exemplomatricula.models;

public class SessaoUsuario {
    // Atributos para armazenar id do estudante da sessão
    private static int idEstudante;
    private static String nomeEstudante;
    private static String emailEstudante;

    // Método para definir o ID do estudante
    public static void setIdEstudante(int id) {
        idEstudante = id;
    }

    // Método para obter o ID do estudante
    public static int getIdEstudante() {
        return idEstudante;
    }

    public static String getNomeEstudante() {
        return nomeEstudante;
    }

    public static void setNomeEstudante(String nomeEstudante) {
        SessaoUsuario.nomeEstudante = nomeEstudante;
    }

    public static String getEmailEstudante() {
        return emailEstudante;
    }

    public static void setEmailEstudante(String emailEstudante) {
        SessaoUsuario.emailEstudante = emailEstudante;
    }
}
