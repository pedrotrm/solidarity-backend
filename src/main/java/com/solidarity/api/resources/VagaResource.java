package com.solidarity.api.resources;


import com.solidarity.api.model.Vaga;
import com.solidarity.api.services.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/vagas")
public class VagaResource {

    private VagaService service;

    public VagaResource(VagaService service) {
        this.service = service;
    }

    @RequestMapping
    public ResponseEntity<List<Vaga>> findAll(){

        List<Vaga> list = service.findAll();

        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vaga> findById(@PathVariable Long id){
        Vaga obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }



}
