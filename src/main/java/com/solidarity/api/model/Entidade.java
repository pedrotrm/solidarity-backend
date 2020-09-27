package com.solidarity.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solidarity.api.model.enums.Causa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "tb02_entidade")
public class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb02_id", nullable = false)
    private Long id;

    @Column(name = "tb02_email", unique=true)
    private String email;

    @Column(name = "tb02_nome")
    private String nome;

    @Column(name = "tb02_cnpj", unique = true)
    private String cnpj;

    @Column(name = "tb02_descricao")
    private String descricao;

    @Column(name = "tb02_causa1")
    private Integer causa1;

    @Column(name = "tb02_causa2")
    private Integer causa2;

    @Column(name = "tb02_foto_perfil_url")
    private String fotoPerfil;

    @Column(name = "tb02_numero_beneficiarios")
    private Integer numeroBeneficiarios;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fktb02tb07_endereco_id")
    private Endereco endereco;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fktb02tb12_cod_usuario")
    private Usuario usuario;

    @JsonBackReference
    @OneToMany(mappedBy = "entidade", targetEntity = Vaga.class, cascade = CascadeType.ALL)
    private List<Vaga> vagas = new ArrayList<>();

    @ElementCollection
    private Set<String> telefones = new HashSet<>();

    public Entidade() {
    }

    public Entidade(Long id, String nome,String email,String cnpj, Causa causa1, Causa causa2,String descricao, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.causa1 = causa1.getCode();
        this.causa2 = causa2.getCode();
        this.descricao = descricao;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
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
