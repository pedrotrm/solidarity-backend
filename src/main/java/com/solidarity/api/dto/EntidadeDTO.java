package com.solidarity.api.dto;

import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.services.validation.EntidadeUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import java.io.Serializable;


@EntidadeUpdate
public class EntidadeDTO implements Serializable {

    private Long id;

    @Length(min = 5 ,max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @Email(message = "Email invalido")
    private String email;

    @CNPJ(message = "CNPJ invalido")
    private String cnpj;

    private Causa causa1;
    private Causa causa2;
    private String fotoPerfil;
    private String descricao;
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;
    private String telefone1;
    private String telefone2;
    private String telefone3;

    public EntidadeDTO() {
        // Construtor padrão para instaciar a classe
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Causa getCausa1() {
        return causa1;
    }

    public void setCausa1(Causa causa1) {
        this.causa1 = causa1;
    }

    public Causa getCausa2() {
        return causa2;
    }

    public void setCausa2(Causa causa2) {
        this.causa2 = causa2;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
