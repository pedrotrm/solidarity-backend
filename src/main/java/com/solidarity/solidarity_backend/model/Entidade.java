package com.solidarity.solidarity_backend.model;

import com.solidarity.solidarity_backend.model.enums.Causa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    private Integer causa1;
    private Integer causa2;

    @OneToOne
    private Endereco endereco;

    @ElementCollection
    @CollectionTable(name="telefone")
    private Set<String> telefones = new HashSet<>();

    @OneToMany
    private List<Endereco> enderecos = new ArrayList<>();


    public Entidade() {
    }

    public Entidade(Long id, String email, String nome, String cnpj, String descricao, String senha, Endereco endereco, Causa  causa1, Causa causa2) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.senha = senha;
        this.endereco = endereco;
        setCausa1(causa1);
        setCausa2(causa2);
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

    public com.solidarity.solidarity_backend.model.Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(com.solidarity.solidarity_backend.model.Endereco endereco) {
        this.endereco = endereco;
    }

    public Causa getCausa1() {
        return Causa.valorDe(causa1);
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

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
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
