package com.solidarity.api.dto;



import com.solidarity.api.services.validation.EntidadeInsert;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EntidadeInsert
public class EntidadeNewDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max= 200, message = "O tamanho deve ser entre 5 e 200 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email invalido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=8, message = "A senha deve conter no minimo 8 caracteres")
    private String senha;

    @CNPJ(message = "CNPJ invalido")
    private String cnpj;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer causa1;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer causa2;

    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;

    @Length(min=8, max =8, message = "O CEP deve conter 8 digitos")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;

    private String telefone2;
    private String telefone3;


    @NotEmpty(message = "Preenchimento obrigatório")
    private String descricao;

    private Long cidadeId;

    public EntidadeNewDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCausa1() {
        return causa1;
    }

    public void setCausa1(Integer causa1) {
        this.causa1 = causa1;
    }

    public Integer getCausa2() {
        return causa2;
    }

    public void setCausa2(Integer causa2) {
        this.causa2 = causa2;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    

}
