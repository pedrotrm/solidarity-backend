package com.solidarity.solidarity_alpha.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String email;

    private String nome;
    private String cnpj;
    private String descricao;
    private String senha;

    @OneToOne
    private Endereco endereco;

    public Entidade() {
    }

    public Entidade(Long id, String email, String nome, String cnpj, String descricao, String senha, Endereco endereco) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade)) return false;
        Entidade entidade = (Entidade) o;
        return Objects.equals(getId(), entidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
