package com.solidarity.api.builders;

import com.solidarity.api.model.Cidade;
import com.solidarity.api.model.Estado;

import java.util.ArrayList;
import java.util.List;

public final class EstadoBuilder {
    private Long id;
    private String nome;
    private List<Cidade> cidades = new ArrayList<>();

    private EstadoBuilder() {
    }

    public static EstadoBuilder anEstado() {
        return new EstadoBuilder();
    }

    public EstadoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EstadoBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EstadoBuilder withCidades(List<Cidade> cidades) {
        this.cidades = cidades;
        return this;
    }

    public Estado build() {
        Estado estado = new Estado();
        estado.setId(id);
        estado.setNome(nome);
        estado.setCidades(cidades);
        return estado;
    }
}
