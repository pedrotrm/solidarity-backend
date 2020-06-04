package com.solidarity.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "tb11_projeto")
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb11_id")
    private Long id;

    @Column(name = "tb11_nome_projeto")
    private String nomeProjeto;

    @Column(name = "tb11_descricao")
    private String descricao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fktb11tb08_curriculo_id")
    private MiniCurriculo curriculo;

    public Projeto() {
    }

    public Projeto(Long id, String nomeProjeto, String descricao, MiniCurriculo curriculo) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.curriculo = curriculo;

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

    public MiniCurriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(MiniCurriculo miniCurriculo) {
        this.curriculo = miniCurriculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projeto)) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(getId(), projeto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
