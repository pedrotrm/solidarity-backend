package com.solidarity.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.model.enums.Habilidade;
import com.solidarity.api.model.enums.TipoVaga;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

public class VagaDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String descricao;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer causa1;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer causa2;

    private String fotoPerfil;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer habilidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @FutureOrPresent(message = "Você não pode informar uma data passada")
    private Instant dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Future(message = "Você não pode informar uma data passada")
    private Instant dataFim;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer tipoVaga;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer quantidade;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logadouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    private String complemento;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    @NotNull(message = "Preenchimento obrigatório")
    private Long cidadeId;

    @NotNull(message = "Preenchimento obrigatório")
    private Long entidadeId;

    public VagaDTO() {
    }

    public VagaDTO(Vaga vaga) {
        this.id  = vaga.getId();
        this.nome = vaga.getNome();
        this.descricao = vaga.getDescricao();
        this.causa1 = vaga.getCausa1().getCode();
        this.causa2 = vaga.getCausa2().getCode();
        this.habilidade = vaga.getHabilidade().getCode();
        this.dataInicio = vaga.getDataInicio();
        this.dataFim = vaga.getDataFim();
        this.tipoVaga = vaga.getTipoVaga().getCode();
        this.quantidade = vaga.getQuantidade();
        this.logadouro = vaga.getEnderecoVaga().getLogadouro();
        this.numero = vaga.getEnderecoVaga().getNumero();
        this.complemento = vaga.getEnderecoVaga().getComplemento();
        this.bairro = vaga.getEnderecoVaga().getBairro();
        this.cep =vaga.getEnderecoVaga().getCep();
        this.cidadeId = vaga.getEnderecoVaga().getCidade().getId();
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

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public void setDataFim(Instant dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Causa getCausa1() {
        return Causa.valorDe(causa1);
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Long entidadeId) {
        this.entidadeId = entidadeId;
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

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Habilidade getHabilidade() {
        return Habilidade.valorDe(habilidade);
    }

    public void setHabilidade(Habilidade habilidade) {
        if (habilidade != null)
            this.habilidade = habilidade.getCode();
    }

    public TipoVaga getTipoVaga() {
        return TipoVaga.valorDe(tipoVaga);
    }

    public void setTipoVaga(TipoVaga tipoVaga) {
        if(tipoVaga != null)
            this.tipoVaga = tipoVaga.getCode();
    }



}
