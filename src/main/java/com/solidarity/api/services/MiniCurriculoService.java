package com.solidarity.api.services;


import com.solidarity.api.dto.ExperienciaDTO;
import com.solidarity.api.model.Experiencia;
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

    public Experiencia fromExperienciaDTO(ExperienciaDTO objDto){
        return new Experiencia(objDto.getId(), objDto.getNomeEmpresa(),objDto.getDataEntrada(),objDto.getDataSaida(),objDto.getAtribuicoes());
    }

    private void updateDataExperiencia(Experiencia newObj, Experiencia obj){
        newObj.setNomeEmpresa(obj.getNomeEmpresa());
        newObj.setDataEntrada(obj.getDataEntrada());
        newObj.setDataSaida(obj.getDataSaida());
        newObj.setAtribuicoes(obj.getAtribuicoes());
    }

}
