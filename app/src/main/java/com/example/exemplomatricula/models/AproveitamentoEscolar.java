package com.example.exemplomatricula.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "aproveitamentoEscolar")
public class AproveitamentoEscolar {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "valor")
    private double valor;

    @ColumnInfo(name = "id_estudante")
    private int id_estudante;

    public AproveitamentoEscolar(float valor, int id_estudante) {
        this.valor = valor;
        this.id_estudante = id_estudante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId_estudante() {
        return id_estudante;
    }

    public void setId_estudante(int id_estudante) {
        this.id_estudante = id_estudante;
    }
}
