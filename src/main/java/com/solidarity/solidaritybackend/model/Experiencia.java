package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity(name = "tb09_experiencia")
public class Experiencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb09_id")
    private Long id;
    @Column(name = "tb09_nome_empresa")
    private String nomeEmpresa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tb09_data_entrada", columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tb09_data_saida", columnDefinition = "DATE")
    private LocalDate dataSaida;

    @Column(name = "tb09_atribuicoes")
    private String atribuicoes;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "curriculo_id")
    private MiniCurriculo curriculo;

    public Experiencia() {
    }

    public Experiencia(Long id, String nomeEmpresa, LocalDate dataEntrada, LocalDate dataSaida, String atribuicoes, MiniCurriculo curriculo) {
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

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
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
