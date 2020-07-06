package com.solidarity.api.repositories;

import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.Formacao;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.model.Projeto;

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
