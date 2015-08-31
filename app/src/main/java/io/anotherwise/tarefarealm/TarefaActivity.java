package io.anotherwise.tarefarealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

import java.util.UUID;

import io.anotherwise.tarefarealm.model.Tarefa;
import io.realm.Realm;

public class TarefaActivity extends AppCompatActivity {

    EditText tituloEdit;
    EditText descricaoEdit;
    Switch concluidaSwitch;

    Tarefa tarefa;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        tituloEdit = (EditText) findViewById(R.id.tituloEdit);
        descricaoEdit = (EditText) findViewById(R.id.descricaoEdit);
        concluidaSwitch = (Switch) findViewById(R.id.concluidaSwitch);

        realm = Realm.getInstance(this);
        Intent intent = getIntent();

        if(intent.getStringExtra("id") != null){
            getTarefa(intent.getStringExtra("id"));
        }
    }

    private void getTarefa(String id) {
        tarefa = realm.where(Tarefa.class).equalTo("id", id).findFirst();

        tituloEdit.setText(tarefa.getTitulo());
        descricaoEdit.setText(tarefa.getDescricao());
        concluidaSwitch.setChecked(tarefa.isConcluida());

        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_salvar) {
            salvar();
        }
        if (id == R.id.action_deletar) {
            deletar();
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvar() {
        realm.beginTransaction();

        if(tarefa == null) {
            tarefa = realm.createObject(Tarefa.class);
            tarefa.setId(UUID.randomUUID().toString());
        }

        tarefa.setTitulo(tituloEdit.getText().toString());
        tarefa.setDescricao(descricaoEdit.getText().toString());
        tarefa.setConcluida(concluidaSwitch.isChecked());

        realm.commitTransaction();
        finish();
    }

    private void deletar() {
        if(tarefa != null){
            realm.beginTransaction();

            tarefa.removeFromRealm();

            realm.commitTransaction();
        }
        finish();
    }
}
