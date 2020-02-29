package com.solidarity.solidarity_backend.resources;


import com.solidarity.solidarity_backend.model.Voluntario;
import com.solidarity.solidarity_backend.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/voluntarios")
public class VoluntarioResource {

    @Autowired
    private VoluntarioService service;

    @RequestMapping
    public ResponseEntity<List<Voluntario>> findAll(){

        List<Voluntario> list = service.findAll();

        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Voluntario> findById(@PathVariable Long id){
        Voluntario obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }



}
