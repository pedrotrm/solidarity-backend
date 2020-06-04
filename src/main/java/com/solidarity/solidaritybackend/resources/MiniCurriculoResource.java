package com.solidarity.solidaritybackend.resources;


import com.solidarity.solidaritybackend.model.Experiencia;
import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.services.MiniCurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping(value = "experiencias/{experienciaId}")
        public ResponseEntity<Experiencia> updateExperiencia(@PathVariable("id") Long minicurriculoId,
                                                             @PathVariable("experienciaId") Long experienciaId){

                    Experiencia obj = miniCurriculoService.getExperienciaById(experienciaId, minicurriculoId);

            return ResponseEntity.ok().body(obj);
        }




}
