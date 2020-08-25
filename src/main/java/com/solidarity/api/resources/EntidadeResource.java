package com.solidarity.api.resources;


import com.solidarity.api.dto.EntidadeDTO;
import com.solidarity.api.dto.EntidadeNewDTO;
import com.solidarity.api.dto.VagaDTO;
import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Vaga;
import com.solidarity.api.services.EntidadeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/entidades")
public class EntidadeResource {

    private EntidadeService service;

    public EntidadeResource(EntidadeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entidade> findById(@PathVariable Long id){
        Entidade obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody EntidadeNewDTO objDto) {
        Entidade obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody EntidadeDTO objDto, @PathVariable Long id) {
        Entidade obj = service.fromDTO(objDto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EntidadeDTO>> findAll() {
        List<Entidade> list = service.findAll();
        List<EntidadeDTO> listDto = list.stream()
                .map(EntidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/vagas")
    public ResponseEntity<Void> createVaga( @RequestBody VagaDTO objDto){
        Vaga vaga = service.fromVagaDTO(objDto);
        service.createVaga(vaga);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vaga.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/vagas/{vagaId}")
    public ResponseEntity<Void> updateVaga(@PathVariable Long vagaId, @RequestBody VagaDTO objDto){
        Vaga vaga = service.fromVagaDTO(objDto);
        vaga.setId(vagaId);
        service.updateVaga(vaga);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value="/page")
    public ResponseEntity<Page<EntidadeDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Entidade> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<EntidadeDTO> listDto = list.map(EntidadeDTO::new);
        return ResponseEntity.ok().body(listDto);
    }


}
