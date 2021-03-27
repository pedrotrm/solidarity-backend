package com.solidarity.api.builders;

import com.solidarity.api.model.Cidade;
import com.solidarity.api.model.Estado;

public final class CidadeBuilder {
    private Long id;
    private String nome;
    private Estado estado;

    private CidadeBuilder() {
    }

    public static CidadeBuilder aCidade() {
        return new CidadeBuilder();
    }

    public CidadeBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CidadeBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CidadeBuilder withEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public Cidade build() {
        Cidade cidade = new Cidade();
        cidade.setId(id);
        cidade.setNome(nome);
        cidade.setEstado(estado);
        return cidade;
    }
}
