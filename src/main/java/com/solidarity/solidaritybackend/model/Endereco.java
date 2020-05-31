package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "tb07_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tb07_id")
    private Long id;

    @Column(name = "tb07_logadouro")
    private String logadouro;

    @Column(name = "tb07_numero")
    private String numero;

    @Column(name = "tb07_complemento")
    private String complemento;

    @Column(name = "tb07_bairro")
    private String bairro;

    @Column(name = "tb07_cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fktb07tb06_cidade_id")
    private Cidade cidade;


    public Endereco() {
    }

    public Endereco(Long id, String logadouro,String numero, String complemento, String bairro, String cep, Cidade cidade) {
        this.id = id;
        this.logadouro = logadouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public com.solidarity.solidaritybackend.model.Cidade getCidade() {
        return cidade;
    }

    public void setCidade(com.solidarity.solidaritybackend.model.Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getId(), endereco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
