package com.solidarity.api.services;


import com.solidarity.api.dto.ExperienciaDTO;
import com.solidarity.api.dto.FormacaoDTO;
import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.Formacao;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.repositories.MiniCurriculoRepository;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MiniCurriculoService {

    @Autowired
    private MiniCurriculoRepository repository;

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
    public void updateFormacao(Formacao obj){
        Formacao newObj = repository.findByFormacaoId(obj.getId());
        updateDataFormacao(newObj, obj);
        repository.updateFormacao(newObj);
    }

    @Transactional
    public void createFormacao(Formacao obj, Long curriculoId){
        obj.setId(null);
        obj.setCurriculo(getById(curriculoId));
        repository.saveFormacao(obj);
    }



    public Experiencia fromExperienciaDTO(ExperienciaDTO objDto){
        return new Experiencia(objDto.getId(), objDto.getNomeEmpresa(),objDto.getDataEntrada(),objDto.getDataSaida(),objDto.getAtribuicoes());
    }

    public Formacao fromFormacaoDTO(FormacaoDTO objDto){
        return new Formacao(objDto.getId(), objDto.getNomeCurso(), objDto.getNomeInstituicao(), objDto.getDataInicio(), objDto.getDataFim());
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
}
