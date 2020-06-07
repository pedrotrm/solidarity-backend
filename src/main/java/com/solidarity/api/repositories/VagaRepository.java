package com.solidarity.api.repositories;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.VagaVoluntario;
import com.solidarity.api.model.Voluntario;

import java.util.List;
import java.util.Optional;

public interface VagaRepository  {

    List<Vaga> findAll();

    Optional<Vaga> findById(Long id);

    List<Vaga> findByCausa(Integer causa);

    List<Vaga> findByHabilidade(Integer habilidade);

    List<Vaga> findByTipoVaga(Integer tipo);

    List<Vaga> findByNome(String busca);

    List<VagaVoluntario> listarVagasDoVoluntarioById (Long voluntarioId);

    List<VagaVoluntario> listarVoluntariosDaVagaById (Long vagaId);

    void createVaga(Vaga vaga);

    void updateVaga(Vaga vaga);

    void deleteVaga(Vaga vaga);

    void participarVaga(Voluntario voluntario, Vaga vaga);

    void desistirVaga(Voluntario voluntario, Vaga vaga);





}
