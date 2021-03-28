package com.solidarity.api.builders;

import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Usuario;
import com.solidarity.api.model.enums.Causa;

import java.util.HashSet;
import java.util.Set;

public final class EntidadeBuilder {
    private Long id;
    private String email;
    private String nome;
    private String cnpj;
    private String descricao;
    private Integer causa1;
    private Integer causa2;
    private String fotoPerfil;
    private Integer numeroBeneficiarios;
    private Usuario usuario;
    private Set<String> telefones = new HashSet<>();

    private EntidadeBuilder() {
    }

    public static EntidadeBuilder anEntidade() {
        return new EntidadeBuilder();
    }

    public EntidadeBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EntidadeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EntidadeBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EntidadeBuilder withCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public EntidadeBuilder withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public EntidadeBuilder withCausa1(Integer causa1) {
        this.causa1 = causa1;
        return this;
    }

    public EntidadeBuilder withCausa2(Integer causa2) {
        this.causa2 = causa2;
        return this;
    }

    public EntidadeBuilder withFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
        return this;
    }

    public EntidadeBuilder withNumeroBeneficiarios(Integer numeroBeneficiarios) {
        this.numeroBeneficiarios = numeroBeneficiarios;
        return this;
    }

    public EntidadeBuilder withUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public EntidadeBuilder withTelefones(Set<String> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Entidade build() {
        Entidade entidade = new Entidade();
        entidade.setId(id);
        entidade.setEmail(email);
        entidade.setNome(nome);
        entidade.setCnpj(cnpj);
        entidade.setDescricao(descricao);
        if (causa1 != null)
            entidade.setCausa1(Causa.valorDe(causa1));
        else
            entidade.setCausa1(null);
        if (causa2 != null)
            entidade.setCausa2(Causa.valorDe(causa2));
        else
            entidade.setCausa2(null);
        entidade.setFotoPerfil(fotoPerfil);
        entidade.setNumeroBeneficiarios(numeroBeneficiarios);
        entidade.setUsuario(usuario);
        entidade.setTelefones(telefones);
        return entidade;
    }
}
