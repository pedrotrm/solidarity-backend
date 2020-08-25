package com.solidarity.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solidarity.api.model.Formacao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

public class FormacaoDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeCurso;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeInstituicao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Preenchimento obrigatório")
    @PastOrPresent
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;


    public FormacaoDTO() {
    }

    public FormacaoDTO(Formacao obj){
        this.nomeCurso = obj.getNomeCurso();
        this.nomeInstituicao = obj.getNomeInstituicao();
        this.dataInicio = obj.getDataInicio();
        this.dataFim = obj.getDataFim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
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
}

