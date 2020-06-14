package com.solidarity.api.resources;


import com.solidarity.api.model.Vaga;
import com.solidarity.api.services.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Implementa paginação nesses recursos

@RestController
@RequestMapping(value ="/vagas")
public class VagaResource {

    private VagaService service;

    public VagaResource(VagaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> findAll(){
        List<Vaga> list = service.findAll();
        return  ResponseEntity.ok().body(list);
    }

    // Corrigir o Tratamento de exeção desse metodo

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vaga> findById(@PathVariable Long id){
        Vaga obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/causas/{causaId}")
    public ResponseEntity<List<Vaga>> findByCausa(@PathVariable Integer causaId){
        List<Vaga> list = service.findByCausa(causaId);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "habilidades/{habilidadeId}")
    public ResponseEntity<List<Vaga>> findByHabilidade(@PathVariable Integer habilidadeId){
        List<Vaga> list = service.findByHabilidade(habilidadeId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "tipos/{tipoId}")
    public ResponseEntity<List<Vaga>> findByTipo(@PathVariable Integer tipoId){
        List<Vaga> list = service.findByTipo(tipoId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "nomes/{nome}")
    public ResponseEntity<List<Vaga>> findByNome(@PathVariable String nome){
        List<Vaga> list = service.findByNome(nome);
        return ResponseEntity.ok().body(list);
    }



}
