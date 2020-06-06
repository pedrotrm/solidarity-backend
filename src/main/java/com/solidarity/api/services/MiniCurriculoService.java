package com.solidarity.api.services;


import com.solidarity.api.dto.ExperienciaDTO;
import com.solidarity.api.dto.FormacaoDTO;
import com.solidarity.api.dto.ProjetoDTO;
import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.Formacao;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.model.Projeto;
import com.solidarity.api.repositories.MiniCurriculoRepository;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MiniCurriculoService {

    private MiniCurriculoRepository repository;

    public MiniCurriculoService(MiniCurriculoRepository repository) {
        this.repository = repository;
    }

    public MiniCurriculo getById(Long id){
        Optional<MiniCurriculo> obj = repository.findMinicurriculo(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MiniCurriculo.class.getName()));
    }

    @Transactional
    public void createExperiencia(Experiencia obj, Long curriculoId){
        obj.setId(null);
        obj.setCurriculo(getById(curriculoId));
        repository.saveExperiencia(obj);
    }

    @Transactional
    public void updateExperiencia(Experiencia obj){
        Experiencia newObj = repository.findByExperienciaId(obj.getId());
        updateDataExperiencia(newObj, obj);
        repository.updateExperiencia(newObj);
    }

    @Transactional
    public void deleteExperiencia(Long id){
        Experiencia obj = repository.findByExperienciaId(id);
        repository.deleteExperiencia(obj);
    }

    @Transactional
    public void createFormacao(Formacao obj, Long curriculoId){
        obj.setId(null);
        obj.setCurriculo(getById(curriculoId));
        repository.saveFormacao(obj);
    }

    @Transactional
    public void updateFormacao(Formacao obj){
        Formacao newObj = repository.findByFormacaoId(obj.getId());
        updateDataFormacao(newObj, obj);
        repository.updateFormacao(newObj);
    }

    @Transactional
    public void deleteFormacao(Long id){
        Formacao obj = repository.findByFormacaoId(id);
        repository.deleteFormacao(obj);
    }

    @Transactional
    public void createProjeto(Projeto obj,Long curriculoId){
        obj.setId(null);
        obj.setCurriculo(getById(curriculoId));
        repository.saveProjeto(obj);
    }

    @Transactional
    public void updateProjeto(Projeto obj){
        Projeto newObj = repository.findByProjetoId(obj.getId());
        updateDataProjeto(newObj, obj);
        repository.updateProjeto(newObj);
    }

    public void deleteProjeto(Long id){
        Projeto obj = repository.findByProjetoId(id);
        repository.deleteProjeto(obj);
    }

    public Experiencia fromExperienciaDTO(ExperienciaDTO objDto){
        return new Experiencia(objDto.getId(), objDto.getNomeEmpresa(),objDto.getDataEntrada(),objDto.getDataSaida(),objDto.getAtribuicoes());
    }

    public Formacao fromFormacaoDTO(FormacaoDTO objDto){
        return new Formacao(objDto.getId(), objDto.getNomeCurso(), objDto.getNomeInstituicao(), objDto.getDataInicio(), objDto.getDataFim());
    }

    public Projeto fromProjetoDTO(ProjetoDTO objDto){
        return new Projeto(objDto.getId(), objDto.getNomeProjeto(), objDto.getDescricao());
    }

    private void updateDataExperiencia(Experiencia newObj, Experiencia obj){
        newObj.setNomeEmpresa(obj.getNomeEmpresa());
        newObj.setDataEntrada(obj.getDataEntrada());
        newObj.setDataSaida(obj.getDataSaida());
        newObj.setAtribuicoes(obj.getAtribuicoes());
    }

    private void updateDataFormacao(Formacao newObj, Formacao obj){
        newObj.setNomeCurso(obj.getNomeCurso());
        newObj.setNomeInstituicao(obj.getNomeInstituicao());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDataFim(obj.getDataFim());
    }

    private void updateDataProjeto(Projeto newObj, Projeto obj){
        newObj.setNomeProjeto(obj.getNomeProjeto());
        newObj.setDescricao(obj.getDescricao());
    }
}
