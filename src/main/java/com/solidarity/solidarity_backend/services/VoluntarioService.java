package com.solidarity.solidarity_backend.services;

import com.solidarity.solidarity_backend.model.Voluntario;
import com.solidarity.solidarity_backend.repositories.VoluntarioRepository;
import com.solidarity.solidarity_backend.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository repository;

    public List<Voluntario> findAll() {
        return repository.findAll();
    }

    public Voluntario findById(Long id) {
        Optional<Voluntario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
