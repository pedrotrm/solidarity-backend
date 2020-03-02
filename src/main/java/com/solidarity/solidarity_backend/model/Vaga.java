package com.solidarity.solidarity_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.solidarity.solidarity_backend.model.enums.Causa;
import com.solidarity.solidarity_backend.model.enums.Habilidade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Integer causa1;
    private Integer causa2;
    private Integer habilidade;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name="endereco_da_vaga_id")
    private Endereco enderecoVaga;

    @ManyToOne
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    @OneToMany(mappedBy = "id.vaga")
    private Set<VagaVoluntario> vagas = new HashSet<>();

    public Vaga() {
    }

    public Vaga(Long id, String nome, String descricao, Causa causa1, Causa causa2, Habilidade habilidade, Endereco enderecoVaga, Entidade entidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        setCausa1(causa1);
        setCausa2(causa2);
        setHabilidade(habilidade);
        this.enderecoVaga = enderecoVaga;
        this.entidade = entidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Causa getCausa1() {
        return Causa.valorDe(causa1);
    }

    public void setCausa1 (Causa causa1) {
        if(causa1 != null)
            this.causa1 = causa1.getCode();
    }

    public Causa getCausa2() {
        return Causa.valorDe(causa2);
    }

    public void setCausa2 (Causa causa2) {
        if(causa2 != null)
            this.causa2 = causa2.getCode();
    }


    public Habilidade getHabilidade() {
        return Habilidade.valorDe(habilidade);
    }

    public void setHabilidade(Habilidade habilidade) {
        if (habilidade != null)
        this.habilidade = habilidade.getCode();
    }

    public Endereco getEnderecoVaga() {
        return enderecoVaga;
    }

    public void setEnderecoVaga(Endereco enderecoVaga) {
        this.enderecoVaga = enderecoVaga;
    }

    public Set<VagaVoluntario> getVagas() {
        return vagas;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }


    public void setVagas(Set<VagaVoluntario> vagas) {
        this.vagas = vagas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaga)) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(getId(), vaga.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
