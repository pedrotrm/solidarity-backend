package com.solidarity.api.repositories.impl;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.VagaVoluntario;
import com.solidarity.api.model.Voluntario;
import com.solidarity.api.model.enums.TipoVaga;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;



@Repository
public class VagaRepositoryImpl implements VagaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Vaga>> findAll() {
        List<Vaga> list = entityManager.createQuery("select v from tb03_vaga v", Vaga.class)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    @Override
    public Optional<Vaga> findById(Long id) {
        return (entityManager.createQuery("select v from tb03_vaga v " +
                "where v.id = ?1", Vaga.class)
                .setParameter(1, id)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst());
    }

    @Override
    public Optional<List<Vaga>> findByCausa(Integer causa) {
         List<Vaga> list = entityManager.createQuery("select v from tb03_vaga v " +
                "where v.causa1 = ?1 or v.causa2 = ?1", Vaga.class)
                .setParameter(1, causa)
                .getResultList();
         return list.isEmpty() ? Optional.empty() :Optional.of(list);
    }

    @Override
    public Optional<List<Vaga>> findByHabilidade(Integer habilidade) {
        List<Vaga> list = entityManager.createQuery("select v from tb03_vaga v " +
                "where v.habilidade = ?1", Vaga.class)
                .setParameter(1, habilidade)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    @Override
    public Optional<List<Vaga>> findByTipoVaga(Integer tipo) {
        List<Vaga> list = entityManager.createQuery("select v from tb03_vaga v " +
                "where v.tipoVaga = ?1 ", Vaga.class)
                .setParameter(1, tipo)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    @Override
    public Optional<List<Vaga>> findByNome(String busca) {
        List<Vaga> list =  entityManager.createQuery("select v from tb03_vaga v " +
                "where v.nome like :nome", Vaga.class)
                .setParameter("nome","%"+busca+"%")
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    @Override
    public void createVaga(Vaga vaga) {
        entityManager.persist(vaga);
    }

    @Override
    public void updateVaga(Vaga vaga) {
        entityManager.merge(vaga);
    }

    @Override
    public void deleteVaga(Vaga vaga) {
        entityManager.remove(vaga);
    }

    @Override
    public void participarVaga(VagaVoluntario vagaVoluntario) {
        entityManager.persist(vagaVoluntario);
    }

    @Override
    public void desistirVaga(VagaVoluntario vagaVoluntario) {
        entityManager.remove(vagaVoluntario);
    }
}
