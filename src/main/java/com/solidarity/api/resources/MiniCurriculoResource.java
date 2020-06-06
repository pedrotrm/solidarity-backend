package com.solidarity.api.resources;


import com.solidarity.api.dto.ExperienciaDTO;
import com.solidarity.api.dto.FormacaoDTO;
import com.solidarity.api.dto.ProjetoDTO;
import com.solidarity.api.model.Experiencia;
import com.solidarity.api.model.Formacao;
import com.solidarity.api.model.MiniCurriculo;
import com.solidarity.api.model.Projeto;
import com.solidarity.api.services.MiniCurriculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "voluntarios/minicurriculos/{id}")
public class MiniCurriculoResource {

    private MiniCurriculoService miniCurriculoService;

    public MiniCurriculoResource(MiniCurriculoService miniCurriculoService) {
    this.miniCurriculoService = miniCurriculoService;
     }

    @GetMapping
    public ResponseEntity<MiniCurriculo> findById(@PathVariable("id") Long id){
        MiniCurriculo obj = miniCurriculoService.getById(id);
            return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/experiencias", method = RequestMethod.POST)
    public ResponseEntity<Void> insertExperiencia(@PathVariable("id") Long id,
                                                  @Valid @RequestBody Experiencia obj){
        miniCurriculoService.createExperiencia(obj, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/experiencias/{experienciaId}",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateExperiencia(@PathVariable("experienciaId") Long experienciaId,
                                                  @Valid @RequestBody ExperienciaDTO objDto){

        Experiencia obj = miniCurriculoService.fromExperienciaDTO(objDto);
        obj.setId(experienciaId);
        miniCurriculoService.updateExperiencia(obj);
            return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/experiencias/{experienciaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteExperiencia(@PathVariable("experienciaId") Long experienciaId){
        miniCurriculoService.deleteExperiencia(experienciaId);
            return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/formacoes", method = RequestMethod.POST)
    public ResponseEntity<Void> insertFormacao(@PathVariable("id") Long curriculoId,
                                               @Valid @RequestBody Formacao obj ){
        miniCurriculoService.createFormacao(obj, curriculoId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/formacoes/{formacaoId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateFormacao(@PathVariable("formacaoId") Long formacaoId,
                                               @Valid @RequestBody FormacaoDTO objDto){
        Formacao obj = miniCurriculoService.fromFormacaoDTO(objDto);
        obj.setId(formacaoId);
        miniCurriculoService.updateFormacao(obj);
            return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/formacoes/{formacaoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFormacao(@PathVariable("formacaoId") Long formacaoId){
        miniCurriculoService.deleteFormacao(formacaoId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/projetos", method = RequestMethod.POST)
    public ResponseEntity<Void> insertProjeto(@PathVariable("id") Long curriculoId,
                                               @Valid @RequestBody Projeto obj ){
        miniCurriculoService.createProjeto(obj, curriculoId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/projetos/{projetoId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProjeto(@PathVariable("projetoId") Long projetoId,
                                               @Valid @RequestBody ProjetoDTO objDto){
        Projeto obj = miniCurriculoService.fromProjetoDTO(objDto);
        obj.setId(projetoId);
        miniCurriculoService.updateProjeto(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/projetos/{projetoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjeto(@PathVariable("projetoId") Long formacaoId){
        miniCurriculoService.deleteProjeto(formacaoId);
        return ResponseEntity.noContent().build();
    }

}
