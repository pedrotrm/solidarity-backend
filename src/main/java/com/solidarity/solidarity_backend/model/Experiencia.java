package com.solidarity.solidarity_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class Experiencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpresa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;

    private String atribuicoes;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "curriculo_id")
    private MiniCurriculo curriculo;

    public Experiencia() {
    }

    public Experiencia(Long id, String nomeEmpresa, Date dataEntrada, Date dataSaida, String atribuicoes, MiniCurriculo curriculo) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.atribuicoes = atribuicoes;
        this.curriculo = curriculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getAtribuicoes() {
        return atribuicoes;
    }

    public void setAtribuicoes(String atribuicoes) {
        this.atribuicoes = atribuicoes;
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
        if (!(o instanceof Experiencia)) return false;
        Experiencia experiencia = (Experiencia) o;
        return Objects.equals(getId(), experiencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
