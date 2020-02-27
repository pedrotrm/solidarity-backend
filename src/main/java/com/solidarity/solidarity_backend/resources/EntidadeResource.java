package com.solidarity.solidarity_backend.resources;


import com.solidarity.solidarity_backend.model.Entidade;
import com.solidarity.solidarity_backend.services.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/entidades")
public class EntidadeResource {

    @Autowired
    private EntidadeService service;

    @RequestMapping
    public ResponseEntity<List<Entidade>> findAll(){

        List<Entidade> list = service.findAll();

        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entidade> findById(@PathVariable Long id){
        Entidade obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }



}
