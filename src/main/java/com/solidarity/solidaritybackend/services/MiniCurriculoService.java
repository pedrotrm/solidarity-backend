package com.solidarity.solidaritybackend.services;


import com.solidarity.solidaritybackend.dto.ExperienciaDTO;
import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.repositories.MiniCurriculoRepository;
import com.solidarity.solidaritybackend.services.exception.ObjectNotFoundException;
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
