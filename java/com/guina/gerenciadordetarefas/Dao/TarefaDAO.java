package com.guina.gerenciadordetarefas.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.guina.gerenciadordetarefas.Tarefa;

import java.util.List;

@Dao
public interface TarefaDAO {

    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAll();

    @Query("select * from tarefa where id = :id")
    Tarefa listarUm(int id);

    @Insert
    void insert(Tarefa tarefa);

    @Delete
    void delete(Tarefa tarefa);

    @Update
    void alterar(Tarefa tarefa);

}
