package com.guina.gerenciadordetarefas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public List<Tarefa> tarefas;
    public TextView txtTitulo;
    private ImageButton imgBtnAdd;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitulo = findViewById(R.id.txt_titulo);
        imgBtnAdd = findViewById(R.id.btn_CriarNovaTarefa);
        listView = findViewById(R.id.list_view);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Snackbar.make(findViewById(R.id.list_view), "Tarefa Alterada Com Sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        activityResultLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK) {
                    Snackbar.make(findViewById(R.id.list_view), "Tarefa Cadastrada Com Sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        

        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CadastrarTarefaintent = new Intent(MainActivity.this, CadastrarTarefa.class);
                activityResultLauncher2.launch(CadastrarTarefaintent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent editTarefaIntent = new Intent(MainActivity.this, EditarTarefa.class);
                editTarefaIntent.putExtra("id_tarefa", tarefas.get(position).getId());
                activityResultLauncher.launch(editTarefaIntent);
            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Tarefa tarefaSelecionada = (Tarefa) listView.getAdapter().getItem(info.position);
        switch (item.getItemId()) {
            case R.id.item_deletar:
                AppDatabase.getAppDatabase(MainActivity.this).tarefaDAO().delete(tarefaSelecionada);
                atualizarLista();
                Snackbar.make(findViewById(R.id.layout_main), "Deletado com sucesso", Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    protected void onStart() {
        super.onStart();
        atualizarLista();
    }


    public void atualizarLista(){
        tarefas = AppDatabase.getAppDatabase(this).tarefaDAO().getAll();
        ListarTarefaAdapter adapter = new ListarTarefaAdapter(this, tarefas);
        listView.setAdapter(adapter);
    }

}