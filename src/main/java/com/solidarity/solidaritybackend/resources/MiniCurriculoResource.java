package com.solidarity.solidaritybackend.resources;


import com.solidarity.solidaritybackend.dto.ExperienciaDTO;
import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.services.MiniCurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "voluntarios/{id}/minicurriculos")
public class MiniCurriculoResource {

        @Autowired
        private MiniCurriculoService miniCurriculoService;

        @GetMapping
        public ResponseEntity<MiniCurriculo> findById(@PathVariable("id") Long id){
            MiniCurriculo obj = miniCurriculoService.getById(id);
            return ResponseEntity.ok().body(obj);
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
