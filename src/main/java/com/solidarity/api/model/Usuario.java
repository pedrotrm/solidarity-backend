package com.solidarity.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb12_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb12_id")
    private Long id;

    @Column(name = "tb12_nome")
    private String nome;

    @Column(name = "tb12_email")
    private String email;

    @Column(name = "tb12_senha")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb14_usuario_permissao", joinColumns = @JoinColumn(name = "fktb14tb12_cod_usuario")
            , inverseJoinColumns = @JoinColumn(name = "fktb14tb13_cod_permissao"))
    private List<Permissao> permissoes;


    public Usuario() {
    }

    public Usuario(Long id,String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
