package io.anotherwise.tarefarealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.anotherwise.tarefarealm.model.Tarefa;
import io.anotherwise.tarefarealm.model.TarefaAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TarefaAdapter adapter;
    RealmResults<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = tarefas.get(position);

                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("id", tarefa.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadListView();
    }

    private void loadListView() {
        Realm realm = Realm.getInstance(this);
        tarefas = realm.where(Tarefa.class).findAll();

        adapter = new TarefaAdapter(this, tarefas);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_nova) {
            Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
