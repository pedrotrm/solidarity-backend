package com.solidarity.solidaritybackend.resources;


import com.solidarity.solidaritybackend.dto.EntidadeDTO;
import com.solidarity.solidaritybackend.dto.EntidadeNewDTO;
import com.solidarity.solidaritybackend.model.Entidade;
import com.solidarity.solidaritybackend.services.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EntidadeService service;



    @GetMapping(value = "/{id}")
    public ResponseEntity<Entidade> findById(@PathVariable Long id){
        Entidade obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EntidadeNewDTO objDto) {
        Entidade obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EntidadeDTO objDto, @PathVariable Long id) {
        Entidade obj = service.fromDTO(objDto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<EntidadeDTO>> findAll() {
        List<Entidade> list = service.findAll();
        List<EntidadeDTO> listDto = list.stream().map(obj -> new EntidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<EntidadeDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Entidade> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<EntidadeDTO> listDto = list.map(obj -> new EntidadeDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
