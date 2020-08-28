package com.solidarity.api.services;

import com.solidarity.api.dto.VoluntarioDTO;
import com.solidarity.api.dto.VoluntarioNewDTO;
import com.solidarity.api.model.*;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.repositories.EnderecoRepository;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.repositories.VoluntarioRepository;
import com.solidarity.api.services.exception.DataIntegrityException;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {


    private final VoluntarioRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final VagaRepository vagaRepository;

    public VoluntarioService(VoluntarioRepository repository, EnderecoRepository enderecoRepository, VagaRepository vagaRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.vagaRepository = vagaRepository;
    }

    public List<Voluntario> findAll() {
        return repository.findAll();
    }

    public Voluntario findById(Long id) {
        Optional<Voluntario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Voluntario.class.getName()));
    }

    public Vaga findVagaById(Long id){
        Optional<Vaga> obj = vagaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Vaga.class.getName()));
    }

    @Transactional
    public Voluntario insert(Voluntario obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.save(obj.getEndereco());
        return obj;
    }

    @Transactional
    public Voluntario update(Voluntario obj) {
        Voluntario newObj = findById(obj.getId());
        updateData(newObj, obj);
        repository.save(newObj);
        enderecoRepository.save(newObj.getEndereco());
        return newObj;
    }

    @Transactional
    public void participarVaga(Vaga vaga, Voluntario voluntario){
        VagaVoluntario vagaVoluntario = new VagaVoluntario(vaga, voluntario, vaga.getDataInicio(), vaga.getDataFim(), vaga.getTipoVaga(), vaga.getQuantidade());
        vagaRepository.participarVaga(vagaVoluntario);
    }

    @Transactional
    public void dessistirVaga(Long voluntarioId, Long vagaId){
        VagaVoluntario vagaVoluntario = findVagaVoluntarioById(voluntarioId, vagaId);
        vagaRepository.desistirVaga(vagaVoluntario);
    }



    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente com vagas associoadas");
        }
    }

    public Page<Voluntario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

   public Voluntario fromDTO(VoluntarioDTO objDto) {
       Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
       Endereco end = new Endereco(null, objDto.getLogadouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cid);
        return new Voluntario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCausa1(), objDto.getCausa2(),end);
    }

    public Voluntario fromDTO(VoluntarioNewDTO objDto){
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogadouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cid);
        Voluntario v = new Voluntario(null, objDto.getNome(), objDto.getEmail(), Causa.valorDe(objDto.getCausa1()),Causa.valorDe(objDto.getCausa2()),end);
        MiniCurriculo m = new MiniCurriculo(null, objDto.getDescricao(),v);
        v.setMiniCurriculo(m);
        v.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            v.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            v.getTelefones().add(objDto.getTelefone3());
        }
        return v;
    }

    private void updateData(Voluntario newObj, Voluntario obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setCausa1(obj.getCausa1());
        newObj.setCausa2(obj.getCausa2());
        newObj.setEndereco(obj.getEndereco());
    }

    private VagaVoluntario findVagaVoluntarioById(Long voluntarioId, Long vagaId){
        Optional<VagaVoluntario> obj = vagaRepository.findVagaVoluntarioByVoluntarioId(voluntarioId, vagaId);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Volunatrio não esta participando da vaga! Id: " + voluntarioId + ", Tipo: " + VagaVoluntario.class.getName()));
    }

}
