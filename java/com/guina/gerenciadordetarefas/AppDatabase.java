package com.guina.gerenciadordetarefas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.guina.gerenciadordetarefas.Dao.TarefaDAO;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TarefaDAO tarefaDAO();
    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "dbtarefas").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }return appDatabase;
    }

}
