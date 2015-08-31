package io.anotherwise.tarefarealm.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.anotherwise.tarefarealm.R;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by marcelobarbosa on 8/31/15.
 */
public class TarefaAdapter extends RealmBaseAdapter<Tarefa> {

    Layout layout;
    private static class Layout {
        TextView tituloText;
        TextView descricaoText;

        public Layout(View view){
            tituloText = (TextView) view.findViewById(R.id.tituloText);
            descricaoText = (TextView) view.findViewById(R.id.descricaoText);
        }
    }

    public TarefaAdapter(Context context, RealmResults<Tarefa> realmResults) {
        super(context, realmResults, true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_tarefa,parent, false);
            layout = new Layout(convertView);
            convertView.setTag(layout);
        } else {
            layout = (Layout) convertView.getTag();
        }

        Tarefa tarefa = this.getItem(position);
        layout.tituloText.setText(tarefa.getTitulo());
        layout.descricaoText.setText(tarefa.getDescricao());

        return convertView;
    }
}
