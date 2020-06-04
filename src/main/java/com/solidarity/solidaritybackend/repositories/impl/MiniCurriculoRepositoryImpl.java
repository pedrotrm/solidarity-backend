package com.solidarity.solidaritybackend.repositories.impl;

import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.Formacao;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.model.Projeto;
import com.solidarity.solidaritybackend.repositories.MiniCurriculoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class MiniCurriculoRepositoryImpl implements MiniCurriculoRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<MiniCurriculo> findMinicurriculo(Long id) {
        return Optional.of(entityManager.find(MiniCurriculo.class, id));
    }

    @Override
    public Optional<Experiencia> findByExperienciaIdAndMinicurriculoId(Long experienciaId, Long miniCurriculoId) {
        return Optional.of(entityManager.createQuery("select exp from tb09_experiencia exp " +
                "where exp.id = ?1 and exp.curriculo.id = ?2", Experiencia.class)
                .setParameter(1, experienciaId)
                .setParameter(2, miniCurriculoId)
                .getSingleResult());
    }

    @Override
    public Optional<Formacao> findByFormacaoIdAndMinicurriculoId(Long formacaoId, Long miniCurriculoId) {
        return Optional.of(entityManager.createQuery("select formacao from tb10_formacao formacao " +
                "where formacao.id = ?1 and formacao.curriculo.id = ?2", Formacao.class)
                .setParameter(1, formacaoId)
                .setParameter(2, miniCurriculoId)
                .getSingleResult());
    }

    @Override
    public Optional<Projeto> findByProjetoIdAndMinicurriculoId(Long projetoId, Long miniCurriculoId) {
        return Optional.of(entityManager.createQuery("select proj from tb11_projeto proj " +
                "where proj.id = ?1 and proj.curriculo.id = ?2", Projeto.class)
                .setParameter(1, projetoId)
                .setParameter(2, miniCurriculoId)
                .getSingleResult());
    }

    @Override
    public void saveExperiencia(Experiencia experiencia) {
        entityManager.persist(experiencia);
    }

    @Override
    public void updateExperienciaById(Experiencia experiencia) {
        entityManager.merge(experiencia);
    }

    @Override
    public void deleteExperienciaById(Experiencia experiencia) {
        entityManager.remove(experiencia);
    }

    @Override
    public void saveProjeto(Projeto projeto) {
        entityManager.persist(projeto);
    }

    @Override
    public void updateProjetoById(Projeto projeto) {
        entityManager.merge(projeto);
    }

    @Override
    public void deleteProjetobyId(Projeto projeto) {
        entityManager.remove(projeto);
    }

    @Override
    public void saveFormacao(Formacao formacao) {
        entityManager.persist(formacao);
    }

    @Override
    public void updateFormacaoById(Formacao formacao) {
        entityManager.merge(formacao);
    }

    @Override
    public void deleteFormacaoById(Formacao formacao) {
        entityManager.remove(formacao);
    }

}
