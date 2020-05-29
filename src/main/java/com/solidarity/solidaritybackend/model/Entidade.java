package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidarity.solidaritybackend.model.enums.Causa;

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

    private Integer causa1;
    private Integer causa2;
    private Integer numeroBeneficiarios;

    @OneToOne(cascade = CascadeType.MERGE)
    private Endereco endereco;

    @JsonBackReference
    @OneToMany(mappedBy = "entidade", targetEntity = Vaga.class, cascade = CascadeType.ALL)
    private List<Vaga> vagas = new ArrayList<>();

    @ElementCollection
    private Set<String> telefones = new HashSet<>();

    public Entidade() {
    }

    public Entidade(Long id, String email, String nome, String cnpj, String descricao, Endereco endereco, Causa  causa1, Causa causa2, Integer numeroBeneficiarios) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.endereco = endereco;
        setCausa1(causa1);
        setCausa2(causa2);
        this.numeroBeneficiarios = numeroBeneficiarios;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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

    public Integer getNumeroBeneficiarios() {
        return numeroBeneficiarios;
    }

    public void setNumeroBeneficiarios(Integer numeroBeneficiarios) {
        this.numeroBeneficiarios = numeroBeneficiarios;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
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
