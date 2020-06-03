package com.solidarity.solidaritybackend.resources;


import com.solidarity.solidaritybackend.model.MiniCurriculo;
import com.solidarity.solidaritybackend.services.MiniCurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "voluntarios/{id}")
public class MiniCurriculoResource {

        @Autowired
        private MiniCurriculoService miniCurriculoService;

        @GetMapping("/minicurriculos")
        public ResponseEntity<MiniCurriculo> findById(@PathVariable("id") Long id){
            MiniCurriculo obj = miniCurriculoService.getById(id);
            return ResponseEntity.ok().body(obj);
        }




}
