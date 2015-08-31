package io.anotherwise.tarefarealm.model;

import io.realm.RealmObject;
import io.realm.annotations.*;

/**
 * Created by marcelobarbosa on 8/31/15.
 */
public class Tarefa extends RealmObject {

    @PrimaryKey
    private String id;

    private String titulo;
    private String descricao;
    private boolean concluida;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
