package com.guina.gerenciadordetarefas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListarTarefaAdapter extends ArrayAdapter<Tarefa> {

    private final Context context;


    public ListarTarefaAdapter(Context context, List<Tarefa> tarefas){
        super(context, R.layout.tarefa_item, tarefas);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.tarefa_item, null);

        TextView textDescricao = v.findViewById(R.id.txt_item_descricao);
        TextView textDataHora = v.findViewById(R.id.txt_item_data_hora);

        Tarefa tarefa = getItem(position);

        textDescricao.setText(tarefa.getDescricao());
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        textDataHora.setText(formatacao.format(tarefa.getDataHora()));
        if (tarefa.isRealizado()){
            textDescricao.setTextColor(Color.RED);
            textDescricao.setPaintFlags(textDescricao.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return v;

    }
}
