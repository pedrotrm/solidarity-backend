package com.solidarity.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "tb10_formacao")
public class Formacao implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb10_id")
    private Long id;

    @Column(name= "tb10_nome_instituicao")
    private String nomeInstituicao;

    @Column(name = "tb10_nome_curso")
    private String nomeCurso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tb10_data_inicio", columnDefinition = "DATE")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tb10_data_fim", columnDefinition = "DATE")
    private LocalDate dataFim;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fktb10tb08_curriculo_id")
    private MiniCurriculo curriculo;

    public Formacao() {
    }

    public Formacao(Long id, String nomeInstituicao, String nomeCurso, LocalDate dataInicio, LocalDate dataFim, MiniCurriculo curriculo){

        this.id = id;
        this.nomeInstituicao = nomeInstituicao;
        this.nomeCurso = nomeCurso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.curriculo = curriculo;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public MiniCurriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(MiniCurriculo curriculo) {
        this.curriculo = curriculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formacao)) return false;
        Formacao formacao = (Formacao) o;
        return Objects.equals(getId(), formacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
