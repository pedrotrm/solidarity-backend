package com.solidarity.api.dto;

import com.solidarity.api.model.Projeto;

import java.io.Serializable;

public class ProjetoDTO implements Serializable {

    private Long id;
    private String nomeProjeto;
    private String descricao;

    public ProjetoDTO() {
    }

    public ProjetoDTO(Projeto obj) {
        this.nomeProjeto = obj.getNomeProjeto();
        this.descricao = obj.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
