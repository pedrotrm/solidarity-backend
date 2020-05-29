package com.solidarity.solidaritybackend.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VagaVoluntarioPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VagaVoluntarioPK)) return false;
        VagaVoluntarioPK that = (VagaVoluntarioPK) o;
        return Objects.equals(getVaga(), that.getVaga()) &&
                Objects.equals(getVoluntario(), that.getVoluntario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVaga(), getVoluntario());
    }
}
