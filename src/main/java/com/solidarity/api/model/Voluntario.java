package com.solidarity.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solidarity.api.model.enums.Causa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "tb01_voluntario")
public class Voluntario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb01_id",unique = true, nullable = false)
    private Long id;

    @Column(name = "tb01_nome")
    private String nome;

    @Column(name = "tb01_email",unique = true)
    private String email;

    @Column(name = "tb01_causa1")
    private Integer causa1;

    @Column(name = "tb01_causa2")
    private Integer causa2;

    @Column(name = "tb01_foto_perfil_url")
    private String fotoPerfil;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fktb01tb07_endereco_id")
    private Endereco endereco;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "voluntario")
    private MiniCurriculo miniCurriculo;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkqg01qg12_cod_usuario")
    private Usuario usuario;

    @ElementCollection
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.voluntario")
    private Set<VagaVoluntario> vagas = new HashSet<>();



    public Voluntario() {
    }

    public Voluntario(Long id, String nome,String email, Causa causa1,Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.causa1 = causa1.getCode();
        this.endereco = endereco;
    }

    @JsonIgnore
    public List<Vaga> getVagasInscritas(){
        List<Vaga> lista = new ArrayList<>();
        for(VagaVoluntario x : vagas){
            lista.add(x.getVaga());
        }
        return lista;
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
        return Causa.valorDe(causa1);
    }

    public void setCausa1 (Causa causa1) {
        if(causa1 != null)
            this.causa1 = causa1.getCode();
    }

    public Causa getCausa2() {
        if (causa2 != null)
            return Causa.valorDe(causa2);
        else
            return null;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public MiniCurriculo getMiniCurriculo() {
        return miniCurriculo;
    }

    public void setMiniCurriculo(MiniCurriculo miniCurriculo) {
        this.miniCurriculo = miniCurriculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public Set<VagaVoluntario> getVagas() {
        return vagas;
    }

    public void setVagas(Set<VagaVoluntario> vagas) {
        this.vagas = vagas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
