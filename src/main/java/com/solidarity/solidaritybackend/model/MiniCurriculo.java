package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class MiniCurriculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private String descricao;

    @JsonBackReference
    @MapsId
    @OneToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;


    @OneToMany(mappedBy = "curriculo" , targetEntity = Experiencia.class, cascade = CascadeType.ALL)
    private List <Experiencia> experiencias = new ArrayList<>() ;

    @OneToMany(mappedBy = "curriculo", targetEntity = Formacao.class, cascade = CascadeType.ALL)
    private List <Formacao> formacoes = new ArrayList<>();

    @OneToMany(mappedBy = "curriculo", targetEntity = Projeto.class, cascade = CascadeType.ALL)
    private List <Projeto> projetos = new ArrayList<>();


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

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
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
