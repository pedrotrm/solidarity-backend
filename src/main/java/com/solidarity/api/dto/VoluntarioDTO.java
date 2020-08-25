package com.solidarity.api.dto;

import com.solidarity.api.model.Voluntario;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.services.validation.VoluntarioUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@VoluntarioUpdate
public class VoluntarioDTO implements Serializable {

    private Long id;

    @Length(min = 5 ,max = 120, message = "O tamanho deve ser entre 5 e 200 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email invalido")
    private String email;

    private Causa causa1;
    private Causa causa2;
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private Long cidadeId;

    public VoluntarioDTO() {
    }

    public VoluntarioDTO(Voluntario obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        causa1 = obj.getCausa1();
        causa2 = obj.getCausa2();
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
