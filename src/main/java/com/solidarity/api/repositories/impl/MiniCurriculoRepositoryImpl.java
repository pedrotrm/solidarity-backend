package com.solidarity.api.repositories.impl;

import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.Formacao;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.model.Projeto;
import com.solidarity.api.repositories.MiniCurriculoRepository;
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
    public Experiencia findByExperienciaId(Long experienciaId) {
        return entityManager.createQuery("select exp from tb09_experiencia exp " +
                "where exp.id = ?1 ", Experiencia.class)
                .setParameter(1, experienciaId)
                .getSingleResult();
    }

    @Override
    public Formacao findByFormacaoId(Long formacaoId) {
        return entityManager.createQuery("select formacao from tb10_formacao formacao " +
                "where formacao.id = ?1 ", Formacao.class)
                .setParameter(1, formacaoId)
                .getSingleResult();
    }

    @Override
    public Projeto findByProjetoId(Long projetoId) {
        return entityManager.createQuery("select proj from tb11_projeto proj " +
                "where proj.id = ?1 ", Projeto.class)
                .setParameter(1, projetoId)
                .getSingleResult();
    }

    @Override
    public void saveMiniCurriculo(MiniCurriculo miniCurriculo) {
        entityManager.persist(miniCurriculo);
    }

    @Override
    public void updateMiniCurriculo(MiniCurriculo miniCurriculo) {
        entityManager.merge(miniCurriculo);
    }

    @Override
    public void deleteMiniCurriculo(MiniCurriculo miniCurriculo) {
        entityManager.remove(miniCurriculo);
    }

    @Override
    public void saveExperiencia(Experiencia experiencia) {
        entityManager.persist(experiencia);
    }

    @Override
    public void updateExperiencia(Experiencia experiencia) {
        entityManager.merge(experiencia);
    }

    @Override
    public void deleteExperiencia(Experiencia experiencia) {
        entityManager.remove(experiencia);
    }

    @Override
    public void saveProjeto(Projeto projeto) {
        entityManager.persist(projeto);
    }

    @Override
    public void updateProjeto(Projeto projeto) {
        entityManager.merge(projeto);
    }

    @Override
    public void deleteProjeto(Projeto projeto) {
        entityManager.remove(projeto);
    }

    @Override
    public void saveFormacao(Formacao formacao) {
        entityManager.persist(formacao);
    }

    @Override
    public void updateFormacao(Formacao formacao) {
        entityManager.merge(formacao);
    }

    @Override
    public void deleteFormacao(Formacao formacao) {
        entityManager.remove(formacao);
    }

}
