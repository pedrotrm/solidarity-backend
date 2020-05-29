package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solidarity.solidaritybackend.model.enums.TipoVaga;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class VagaVoluntario implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private VagaVoluntarioPK id = new VagaVoluntarioPK();

    private Date dataInicio;
    private Date dataFim;
    private TipoVaga tipoVaga;
    private Integer quantidade;

    public VagaVoluntario() {
    }

    public VagaVoluntario(Vaga vaga, Voluntario voluntario,Date dataInicio, Date dataFim, TipoVaga tipoVaga, Integer quantidade) {
        id.setVaga(vaga);
        id.setVoluntario(voluntario);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tipoVaga = tipoVaga;
        this.quantidade = quantidade;
    }


    @JsonIgnore
    public Vaga getVaga(){
        return id.getVaga();
    }

    public Voluntario getVoluntario(){
        return id.getVoluntario();
    }

    @JsonIgnore
    public VagaVoluntarioPK getId() {
        return id;
    }

    public void setId(VagaVoluntarioPK id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(TipoVaga tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VagaVoluntario)) return false;
        VagaVoluntario that = (VagaVoluntario) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
