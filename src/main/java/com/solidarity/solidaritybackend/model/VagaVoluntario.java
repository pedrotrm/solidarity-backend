package com.solidarity.solidaritybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solidarity.solidaritybackend.model.enums.TipoVaga;
import jdk.jfr.Timespan;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity(name = "tb04_vaga_voluntario")
public class VagaVoluntario implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    @Column(name = "tb04_id")
    private VagaVoluntarioPK id = new VagaVoluntarioPK();

    @Column(name = "tb04_data_inicio", columnDefinition = "TIMESTAMP")
    private Instant dataInicio;

    @Column(name = "tb04_data_fim", columnDefinition = "TIMESTAMP")
    private Instant dataFim;

    @Column(name = "tb04_tipo_vaga")
    private TipoVaga tipoVaga;

    @Column(name = "tb04_quantidade")
    private Integer quantidade;

    public VagaVoluntario() {
    }

    public VagaVoluntario(Vaga vaga, Voluntario voluntario,Instant dataInicio, Instant dataFim, TipoVaga tipoVaga, Integer quantidade) {
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

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public void setDataFim(Instant dataFim) {
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
