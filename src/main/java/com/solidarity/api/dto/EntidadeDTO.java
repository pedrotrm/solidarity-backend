package com.solidarity.api.dto;

import com.solidarity.api.model.Entidade;
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
    }

    /*
       Contrutor para criar o DTO apartir de um Voluntario
       tratando os valores nulos.
    */
    public EntidadeDTO(Entidade entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.causa1 = entidade.getCausa1();
        this.descricao = entidade.getDescricao();
        if (entidade.getCnpj() != null)
            this.cnpj = entidade.getCnpj();
        if (entidade.getCausa2() != null)
            this.causa2 = entidade.getCausa2();
        if (entidade.getFotoPerfil() != null)
            this.fotoPerfil = entidade.getFotoPerfil();
        if (entidade.getEndereco().getLogadouro() != null)
            this.logadouro = entidade.getEndereco().getLogadouro();
        if (entidade.getEndereco().getNumero() != null)
            this.numero = entidade.getEndereco().getNumero();
        if (entidade.getEndereco().getComplemento() != null)
            this.complemento = entidade.getEndereco().getComplemento();
        if (entidade.getEndereco().getBairro() != null)
            this.bairro = entidade.getEndereco().getBairro();
        if (entidade.getEndereco().getCep() != null)
            this.cep = entidade.getEndereco().getCep();
        if (!entidade.getTelefones().isEmpty() && entidade.getTelefones() != null) {
            entidade.getTelefones().stream()
                    .findFirst()
                    .ifPresent(this::setTelefone1);
        }
        if (entidade.getEndereco().getCidade() != null)
            this.cidadeId = entidade.getEndereco().getCidade().getId();
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
