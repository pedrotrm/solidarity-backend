package com.solidarity.solidaritybackend.services;

import com.solidarity.solidaritybackend.model.Entidade;
import com.solidarity.solidaritybackend.repositories.EntidadeRepository;
import com.solidarity.solidaritybackend.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadeService {

    @Autowired
    private EntidadeRepository repository;

    public List<Entidade> findAll() {
        return repository.findAll();
    }

    public Entidade findById(Long id) {
        Optional<Entidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Entidade.class.getName()));
    }

}
