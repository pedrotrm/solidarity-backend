package com.solidarity.solidarity_backend.DTO;

import com.solidarity.solidarity_backend.model.Voluntario;
import com.solidarity.solidarity_backend.model.enums.Causa;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VoluntarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5 ,max = 120, message = "O tamanho deve ser entre 5 e 200 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email invalido")
    private String email;

    @NotNull(message = "Preenchimento obrigatório")
    private Causa causa1;

    @NotNull(message = "Preenchimento obrigatório")
    private Causa causa2;

    public VoluntarioDTO() {
    }

    public VoluntarioDTO(Voluntario obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        causa1 = obj.getCausa1();
        causa2 = obj.getCausa2();
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
}
