package com.solidarity.solidarity_backend.services;

import com.solidarity.solidarity_backend.model.Entidade;
import com.solidarity.solidarity_backend.model.Vaga;
import com.solidarity.solidarity_backend.repositories.VagaRepository;
import com.solidarity.solidarity_backend.services.exception.ObjectNotFoundException;
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
