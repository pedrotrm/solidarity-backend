package com.solidarity.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity(name = "tb08_mini_curriculo")
public class MiniCurriculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tb08_id",unique = true, nullable = false)
    private Long id;

    @Column(name = "tb08_descricao")
    private String descricao;

    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "fktb08tb01_voluntario_id")
    private Voluntario voluntario;

    @JsonManagedReference
    @OneToMany(mappedBy = "curriculo" , targetEntity = Experiencia.class, cascade = CascadeType.ALL)
    private Set<Experiencia> experiencias = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "curriculo", targetEntity = Formacao.class, cascade = CascadeType.ALL)
    private Set <Formacao> formacoes = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "curriculo", targetEntity = Projeto.class, cascade = CascadeType.ALL)
    private Set <Projeto> projetos = new HashSet<>();


    public MiniCurriculo() {
    }

    public MiniCurriculo(Long id, String descricao, Voluntario voluntario){

        this.id = id;
        this.descricao = descricao;
        this.voluntario = voluntario;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Set<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(Set<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public Set<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(Set<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiniCurriculo)) return false;
        MiniCurriculo that = (MiniCurriculo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
