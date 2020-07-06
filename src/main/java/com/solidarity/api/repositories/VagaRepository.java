package com.solidarity.api.repositories;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.VagaVoluntario;
import com.solidarity.api.model.Voluntario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VagaRepository  {

    Optional <List<Vaga>> findAll();

    Optional<Vaga> findById(Long id);

    Optional <List<Vaga>> findByCausa(Integer causa);

    Optional <List<Vaga>> findByHabilidade(Integer habilidade);

    Optional <List<Vaga>> findByTipoVaga(Integer tipo);

    Optional <List<Vaga>> findByNome(String busca);

    void createVaga(Vaga vaga);

    void updateVaga(Vaga vaga);

    void deleteVaga(Vaga vaga);

    void participarVaga(VagaVoluntario vagaVoluntario);

    void desistirVaga(VagaVoluntario vagaVoluntario);





}
