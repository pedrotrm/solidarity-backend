package com.solidarity.api.resources;


import com.solidarity.api.dto.EntidadeDTO;
import com.solidarity.api.dto.EntidadeNewDTO;
import com.solidarity.api.dto.VagaDTO;
import com.solidarity.api.dto.VoluntarioDTO;
import com.solidarity.api.model.Entidade;
import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.VagaVoluntario;
import com.solidarity.api.services.EntidadeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/entidades")
public class EntidadeResource {

    private EntidadeService service;

    public EntidadeResource(EntidadeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_ENTIDADE')")
    public ResponseEntity<Entidade> findById(@PathVariable Long id){
        Entidade obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Void> insert(@Valid @RequestBody EntidadeNewDTO objDto) {
        Entidade obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{entidadeId}/foto")
    @PreAuthorize("hasAnyAuthority('ROLE_ALTERAR_ENTIDADE')")
    public ResponseEntity<Void> uploadFotoPerfil(@PathVariable Long entidadeId, @RequestParam(name = "file") MultipartFile multipartFile) {
        URI uri = service.uploadFotoPerfil(multipartFile, entidadeId);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ALTERAR_ENTIDADE')")
    public ResponseEntity<Void> update(@Valid @RequestBody EntidadeDTO objDto, @PathVariable Long id) {
        Entidade obj = service.fromDTO(objDto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_DELETAR_ENTIDADE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_ENTIDADE')")
    public ResponseEntity<List<EntidadeDTO>> findAll() {
        List<Entidade> list = service.findAll();
        List<EntidadeDTO> listDto = list.stream()
                .map(EntidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/vagas")
    @PreAuthorize("hasAnyAuthority('ROLE_CADASTRAR_VAGA')")
    public ResponseEntity<Void> createVaga( @RequestBody VagaDTO objDto){
        Vaga vaga = service.fromVagaDTO(objDto);
        service.createVaga(vaga);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vaga.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/vagas/{vagaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ALTERAR_VAGA')")
    public ResponseEntity<Void> updateVaga(@PathVariable Long vagaId, @RequestBody VagaDTO objDto){
        Vaga vaga = service.fromVagaDTO(objDto);
        vaga.setId(vagaId);
        service.updateVaga(vaga);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{entidadeId}/vagas")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_VAGAVOLUNTARIO')")
    public ResponseEntity<Set<VagaDTO>> findAllVagas(@PathVariable Long entidadeId){
        Set<VagaDTO> result = service.findAllVagas(entidadeId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/vagas/{vagaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_VAGAVOLUNTARIO')")
    public ResponseEntity<Set<VoluntarioDTO>> listVagaVoluntario(@PathVariable Long vagaId){
        Set<VoluntarioDTO> voluntarios = service.findVagaVoluntarios(vagaId);
        return ResponseEntity.ok().body(voluntarios);
    }


    @GetMapping(value="/page")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_ENTIDADE')")
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
