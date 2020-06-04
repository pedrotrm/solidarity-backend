package com.solidarity.api.resources;


import com.solidarity.api.dto.ExperienciaDTO;
import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.services.MiniCurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "voluntarios/minicurriculos/{id}")
public class MiniCurriculoResource {

        @Autowired
        private MiniCurriculoService miniCurriculoService;

        @GetMapping
        public ResponseEntity<MiniCurriculo> findById(@PathVariable("id") Long id){
            MiniCurriculo obj = miniCurriculoService.getById(id);
            return ResponseEntity.ok().body(obj);
        }

        @RequestMapping(value = "/experiencias", method = RequestMethod.POST)
        public ResponseEntity<Void> insertExperiencia(@PathVariable("id") Long id,
                                                      @Valid @RequestBody Experiencia experiencia){
            miniCurriculoService.createExperiencia(experiencia, id);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(experiencia.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        @RequestMapping(value = "experiencias/{experienciaId}",method = RequestMethod.PUT)
        public ResponseEntity<Void> updateExperiencia(@PathVariable("experienciaId") Long experienciaId,
                                                      @Valid @RequestBody ExperienciaDTO objDto){

            Experiencia obj = miniCurriculoService.fromExperienciaDTO(objDto);
            obj.setId(experienciaId);
            miniCurriculoService.updateExperiencia(obj);
            return ResponseEntity.noContent().build();
        }




}
