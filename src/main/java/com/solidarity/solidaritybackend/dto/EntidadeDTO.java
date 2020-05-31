package com.solidarity.solidaritybackend.dto;

import com.solidarity.solidaritybackend.model.Entidade;
import com.solidarity.solidaritybackend.model.enums.Causa;
import com.solidarity.solidaritybackend.services.validation.EntidadeUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@EntidadeUpdate
public class EntidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    @Length(min = 5 ,max = 120, message = "O tamanho deve ser entre 5 e 200 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email invalido")
    private String email;

    @CNPJ(message = "CNPJ invalido")
    private String cnpj;

    private Integer causa1;
    private Integer causa2;
    private String descricao;
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;

    public EntidadeDTO() {
    }

    public EntidadeDTO(Entidade obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        cnpj = obj.getCnpj();
        causa1 = obj.getCausa1().getCode();
        causa2 = obj.getCausa2().getCode();
        descricao = obj.getDescricao();
        logadouro = obj.getEndereco().getLogadouro();
        numero = obj.getEndereco().getNumero();
        complemento = obj.getEndereco().getComplemento();
        bairro = obj.getEndereco().getBairro();
        cep = obj.getEndereco().getCep();
        cidadeId = obj.getEndereco().getCidade().getId();
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

    public Integer getCausa1() {
        return causa1;
    }

    public void setCausa1(Integer causa1) {
        this.causa1 = causa1;
    }

    public Integer getCausa2() {
        return causa2;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCausa2(Integer causa2) {
        this.causa2 = causa2;
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
}
