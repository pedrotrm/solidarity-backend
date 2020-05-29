package com.solidarity.solidaritybackend.resources.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class StandardError {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant tempoError;
    private Integer status;
    private String error;
    private String mensagem;
    private String caminho;

    public StandardError() {
    }

    public StandardError(Instant tempoError, Integer status, String error, String mensagem, String caminho) {
        this.tempoError = tempoError;
        this.status = status;
        this.error = error;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public Instant getTempoError() {
        return tempoError;
    }

    public void setTempoError(Instant tempoError) {
        this.tempoError = tempoError;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
