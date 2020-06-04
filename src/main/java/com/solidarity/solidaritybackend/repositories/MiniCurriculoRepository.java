package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.Formacao;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.model.Projeto;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MiniCurriculoRepository {

    Optional <MiniCurriculo> findMinicurriculo(Long id);

    Optional <Experiencia> findByExperienciaIdAndMinicurriculoId(Long experienciaId, Long miniCurriculoId);

    Optional <Formacao> findByFormacaoIdAndMinicurriculoId(Long formacaoId, Long miniCurriculoId);

    Optional <Projeto> findByProjetoIdAndMinicurriculoId(Long projetoId, Long miniCurriculoId);

    void saveExperiencia(Experiencia experiencia);

    void updateExperienciaById(Experiencia experiencia);

    void deleteExperienciaById(Experiencia experiencia);

    void saveProjeto(Projeto projeto);

    void updateProjetoById(Projeto projeto);

    void deleteProjetobyId(Projeto projeto);

    void saveFormacao(Formacao formacao);

    void updateFormacaoById(Formacao formacao);

    void deleteFormacaoById(Formacao formacao);


}
