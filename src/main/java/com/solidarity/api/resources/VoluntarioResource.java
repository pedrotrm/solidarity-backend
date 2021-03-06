package com.solidarity.api.resources;


import com.solidarity.api.dto.VoluntarioDTO;
import com.solidarity.api.dto.VoluntarioNewDTO;
import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.Voluntario;
import com.solidarity.api.services.VoluntarioService;
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
@RequestMapping(value ="/voluntarios")
public class VoluntarioResource {

    private VoluntarioService service;

    public VoluntarioResource(VoluntarioService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_VOLUNTARIO')")
    public ResponseEntity<Voluntario> findById(@PathVariable Long id){
        Voluntario obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Void> insert(@Valid @RequestBody VoluntarioNewDTO objDto) {
        Voluntario obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{voluntarioId}/foto")
    @PreAuthorize("hasAnyAuthority('ROLE_ALTERAR_VOLUNTARIO')")
    public ResponseEntity<Void> uploadFotoPerfil(@PathVariable Long voluntarioId, @RequestParam(name = "file") MultipartFile multipartFile) {
        URI uri = service.uploadFotoPerfil(multipartFile, voluntarioId);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ALTERAR_VOLUNTARIO')")
    public ResponseEntity<Void> update(@Valid @RequestBody VoluntarioDTO objDto, @PathVariable Long id) {
        objDto.setId(id);
        Voluntario obj = service.updateFromDTO(objDto);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    // TODO: Testa resto desses endpoints endpoints

    @DeleteMapping(value="/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_DELETAR_VOLUNTARIO')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{voluntarioId}/vagas/{vagaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_PARTICIPAR_VAGA')")
    public ResponseEntity<Void> participarVaga(@PathVariable("voluntarioId") Long voluntarioId,@PathVariable("vagaId") Long vagaId){
        Vaga vaga = service.findVagaById(vagaId);
        Voluntario voluntario = service.findById(voluntarioId);
        service.participarVaga(vaga, voluntario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{voluntarioId}/vagas/{vagaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_DESISTIR_VAGA')")
    public ResponseEntity<Void> dessistirVaga(@PathVariable("voluntarioId") Long voluntarioId,@PathVariable("vagaId") Long vagaId){
        service.dessistirVaga(voluntarioId, vagaId);
        return ResponseEntity.noContent().build();
    }

   @GetMapping
   @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_VOLUNTARIO')")
    public ResponseEntity<Set<VoluntarioDTO>> findAll() {
        List<Voluntario> list = service.findAll();
        Set<VoluntarioDTO> listDto = list.stream()
                .map(VoluntarioDTO::new)
                .collect(Collectors.toSet());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_VOLUNTARIO')")
    public ResponseEntity<Page<VoluntarioDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Voluntario> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<VoluntarioDTO> listDto = list.map(VoluntarioDTO::new);
        return ResponseEntity.ok().body(listDto);
    }


}
