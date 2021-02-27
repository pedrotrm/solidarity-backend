package com.solidarity.api.dto;

import com.solidarity.api.model.Voluntario;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.services.validation.VoluntarioUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Optional;

@VoluntarioUpdate
public class VoluntarioDTO implements Serializable {

    private Long id;

    @Length(min = 5 ,max = 120, message = "O tamanho deve ser entre 5 e 200 caracteres")
    private String nome;

    @Email(message = "Email invalido")
    private String email;

    private Causa causa1;
    private Causa causa2;
    private String fotoPerfil;
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;

    public VoluntarioDTO() {
    }
    /*
        Contrutor para criar o DTO apartir de um Voluntario
        tratando os valores nulos.
     */
    public VoluntarioDTO(Voluntario voluntario) {
        this.id = voluntario.getId();
        this.nome = voluntario.getNome();
        this.email = voluntario.getEmail();
        this.causa1 = voluntario.getCausa1();
        if (voluntario.getCausa2() != null)
            this.causa2 = voluntario.getCausa2();
        if (voluntario.getFotoPerfil() != null)
            this.fotoPerfil = voluntario.getFotoPerfil();
        if (voluntario.getEndereco().getLogadouro() != null)
            this.logadouro = voluntario.getEndereco().getLogadouro();
        if (voluntario.getEndereco().getNumero() != null)
            this.numero = voluntario.getEndereco().getNumero();
        if (voluntario.getEndereco().getComplemento() != null)
            this.complemento = voluntario.getEndereco().getComplemento();
        if (voluntario.getEndereco().getBairro() != null)
            this.bairro = voluntario.getEndereco().getBairro();
        if (voluntario.getEndereco().getCep() != null)
            this.cep = voluntario.getEndereco().getCep();
        if (!voluntario.getTelefones().isEmpty() && voluntario.getTelefones() != null) {
            voluntario.getTelefones().stream()
                    .findFirst()
                    .ifPresent(this::setTelefone1);
        }
        if (voluntario.getEndereco().getCidade() != null)
            this.cidadeId = voluntario.getEndereco().getCidade().getId();
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
        return causa1;
    }

    public void setCausa1(Causa causa1) {
        this.causa1 = causa1;
    }

    public Causa getCausa2() {
        return causa2;
    }

    public void setCausa2(Causa causa2) {
        this.causa2 = causa2;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
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

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
