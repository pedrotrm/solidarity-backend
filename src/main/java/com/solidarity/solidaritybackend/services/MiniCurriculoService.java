package com.solidarity.solidaritybackend.services;


import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.repositories.MiniCurriculoRepository;
import com.solidarity.solidaritybackend.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Experiencia getExperienciaById(Long experienciaId, Long miniCurriculoId){
        Optional<Experiencia> obj = repository.findByExperienciaIdAndMinicurriculoId(experienciaId, miniCurriculoId);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + experienciaId + ", Tipo: " + Experiencia.class.getName()));
    }
}
