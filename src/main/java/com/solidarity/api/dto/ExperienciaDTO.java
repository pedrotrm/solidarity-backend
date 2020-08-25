package com.solidarity.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solidarity.api.model.Experiencia;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

public class ExperienciaDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeEmpresa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Preenchimento obrigatório")
    @PastOrPresent
    private LocalDate dataEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent
    private LocalDate dataSaida;

    private String atribuicoes;

    public ExperienciaDTO() {
    }

    public ExperienciaDTO(Experiencia obj){
        this.id = obj.getId();
        this.nomeEmpresa = obj.getNomeEmpresa();
        this.dataEntrada = obj.getDataEntrada();
        this.dataSaida = obj.getDataSaida();
        this.atribuicoes = obj.getAtribuicoes();
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
}
