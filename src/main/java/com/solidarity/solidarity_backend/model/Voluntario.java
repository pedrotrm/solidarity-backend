package com.solidarity.solidarity_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.solidarity.solidarity_backend.model.enums.Causa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Voluntario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;

    private Integer causa1;
    private Integer causa2;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "voluntario")
    private MiniCurriculo miniCurriculo;

    @ElementCollection
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.voluntario")
    private Set<VagaVoluntario> vagas = new HashSet<>();


    public Voluntario() {
    }

    public Voluntario(Long id, String nome,String email, Causa causa1, Causa causa2, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.causa1 = causa1.getCode();
        this.causa2 = causa2.getCode();
        this.endereco = endereco;
    }

    public Voluntario(Long id, String nome,String email, Causa causa1, Causa causa2) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.causa1 = causa1.getCode();
        this.causa2 = causa2.getCode();
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
        return Causa.valorDe(causa2);
    }

    public void setCausa2 (Causa causa2) {
        if(causa2 != null)
            this.causa2 = causa2.getCode();
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
