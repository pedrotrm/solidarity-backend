package com.solidarity.api.builders;

import com.solidarity.api.model.Permissao;
import com.solidarity.api.model.Usuario;

import java.util.List;

public final class UsuarioBuilder {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<Permissao> permissoes;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder anUsuario() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder withSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder withPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setPermissoes(permissoes);
        return usuario;
    }
}
