package com.solidarity.api.services;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.model.enums.Habilidade;
import com.solidarity.api.model.enums.TipoVaga;
import com.solidarity.api.repositories.EnderecoRepository;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.services.exception.ObjectNotFoundException;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class VagaService {

    private VagaRepository repository;
    private EnderecoRepository enderecoRepository;

    public VagaService(VagaRepository repository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<Vaga> findAll() {
        Optional<List<Vaga>> obj =  repository.findAll();
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado ! Tipo: Vagas"));
    }

    public Vaga findById(Long id)  {
        Optional<Vaga> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado Id: " + id + " Tipo: " + Vaga.class.getName()));
    }

    public List<Vaga> findByCausa(Integer causa){
        Optional<List<Vaga>> obj = repository.findByCausa(causa);
        return obj.orElseThrow(() ->  new ObjectNotFoundException(
                "Objeto não encontrado! Tipo Causa: " + Causa.valorDe(causa)));
    }

    public List<Vaga> findByHabilidade(Integer habilidade){
        Optional<List<Vaga>> obj = repository.findByHabilidade(habilidade);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Tipo Habilidade: "+ Habilidade.valorDe(habilidade)));
    }

    public List<Vaga> findByTipo(Integer tipo){
        Optional<List<Vaga>> obj = repository.findByTipoVaga(tipo);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! TipoVaga: "+ TipoVaga.valorDe(tipo)));
    }

    public List<Vaga> findByNome(String nome){
        Optional<List<Vaga>> obj =  repository.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Tipo Nome: "+ nome));
    }

}
