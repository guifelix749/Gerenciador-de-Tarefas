package com.guina.gerenciadordetarefas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Tarefa {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "t_descricao")
    private String descricao;
    private boolean realizado = false;
    @ColumnInfo(name = "data_hora")
    private long dataHora;

    public Tarefa() {
    }

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public long getDataHora() {
        return dataHora;
    }

    public void setDataHora(long dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return id + " - " + descricao + " - " + realizado + " - " + dataHora;
    }
}
