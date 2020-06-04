package com.solidarity.api.services;

import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Vaga;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    public List<Vaga> findAll() {
        return repository.findAll();
    }

    public Vaga findById(Long id) {
        Optional<Vaga> obj = repository.findById(id);
        return obj.orElseThrow(() ->  new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Entidade.class.getName()));
    }

}
