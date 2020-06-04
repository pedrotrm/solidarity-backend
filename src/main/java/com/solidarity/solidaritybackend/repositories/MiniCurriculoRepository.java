package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.Formacao;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.model.Projeto;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MiniCurriculoRepository {

    Optional <MiniCurriculo> findMinicurriculo(Long id);

    Experiencia findByExperienciaId(Long experienciaId);

    Formacao findByFormacaoId(Long formacaoId);

    Projeto findByProjetoId(Long projetoId);

    void saveMiniCurriculo(MiniCurriculo miniCurriculo);

    void updateMiniCurriculo(MiniCurriculo miniCurriculo);

    void deleteMiniCurriculo(MiniCurriculo miniCurriculo);

    void saveExperiencia(Experiencia experiencia);

    void updateExperiencia(Experiencia experiencia);

    void deleteExperiencia(Experiencia experiencia);

    void saveProjeto(Projeto projeto);

    void updateProjeto(Projeto projeto);

    void deleteProjeto(Projeto projeto);

    void saveFormacao(Formacao formacao);

    void updateFormacao(Formacao formacao);

    void deleteFormacao(Formacao formacao);


}
