package com.solidarity.api.builders;

import com.solidarity.api.model.Cidade;
import com.solidarity.api.model.Endereco;

public final class EnderecoBuilder {
    private Long id;
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Cidade cidade;

    private EnderecoBuilder() {
    }

    public static EnderecoBuilder anEndereco() {
        return new EnderecoBuilder();
    }

    public EnderecoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EnderecoBuilder withLogadouro(String logadouro) {
        this.logadouro = logadouro;
        return this;
    }

    public EnderecoBuilder withNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilder withComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoBuilder withBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoBuilder withCep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoBuilder withCidade(Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco build() {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setLogadouro(logadouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        return endereco;
    }
}
