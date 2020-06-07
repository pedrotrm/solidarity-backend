package com.solidarity.api.repositories.impl;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.VagaVoluntario;
import com.solidarity.api.model.Voluntario;
import com.solidarity.api.repositories.VagaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class VagaRepositoryImpl implements VagaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vaga> findAll() {
        return entityManager.createQuery("select v from tb03_vaga v", Vaga.class)
                .getResultList();
    }

    @Override
    public Optional<Vaga> findById(Long id) {
        return Optional.of(entityManager.createQuery("select v from tb03_vaga v " +
                "where v.id = ?1", Vaga.class)
                .getSingleResult());
    }

    @Override
    public List<Vaga> findByCausa(Integer causa) {
        return entityManager.createQuery("select v from tb03_vaga v " +
                "where v.causa1 = ?1 or v.causa2 = ?1", Vaga.class)
                .setParameter(1, causa)
                .getResultList();
    }

    @Override
    public List<Vaga> findByHabilidade(Integer habilidade) {
        return entityManager.createQuery("select v from tb03_vaga v " +
                "where v.habilidade = ?1", Vaga.class)
                .setParameter(1, habilidade)
                .getResultList();
    }

    @Override
    public List<Vaga> findByTipoVaga(Integer tipo) {
        return entityManager.createQuery("select v from tb03_vaga v, tb04_vaga_voluntario vv " +
                "where vv.tipoVaga = ?1", Vaga.class)
                .setParameter(1, tipo)
                .getResultList();
    }

    @Override
    public List<Vaga> findByNome(String busca) {
        return entityManager.createQuery("select v from tb03_vaga v " +
                "where v.nome like :nome", Vaga.class)
                .setParameter("nome", busca)
                .getResultList();
    }

    @Override
    public List<VagaVoluntario> listarVagasDoVoluntarioById(Long voluntarioId) {
        return null;
    }

    @Override
    public List<VagaVoluntario> listarVoluntariosDaVagaById(Long vagaId) {
        return null;
    }

    @Override
    public void createVaga(Vaga vaga) {

    }

    @Override
    public void updateVaga(Vaga vaga) {

    }

    @Override
    public void deleteVaga(Vaga vaga) {

    }

    @Override
    public void participarVaga(Voluntario voluntario, Vaga vaga) {

    }

    @Override
    public void desistirVaga(Voluntario voluntario, Vaga vaga) {

    }
}
