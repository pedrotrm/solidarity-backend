package com.solidarity.api.services;

import com.solidarity.api.dto.EntidadeDTO;
import com.solidarity.api.dto.EntidadeNewDTO;
import com.solidarity.api.model.*;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.repositories.EnderecoRepository;
import com.solidarity.api.repositories.EntidadeRepository;
import com.solidarity.api.services.exception.DataIntegrityException;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EntidadeService {

    @Autowired
    private EntidadeRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Entidade> findAll() {
        return repository.findAll();
    }

    public Entidade findById(Long id) {
        Optional<Entidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Entidade.class.getName()));
    }

    @Transactional
    public Entidade insert(Entidade obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.save(obj.getEndereco());
        return obj;
    }


    @Transactional
    public Entidade update(Entidade obj) {
        Entidade newObj = findById(obj.getId());
        updateData(newObj, obj);
        repository.save(newObj);
        enderecoRepository.save(newObj.getEndereco());
        return newObj;
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


    public Page<Entidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }


    public Entidade fromDTO(EntidadeDTO objDto) {
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogadouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cid);
        Entidade entidade = new Entidade(objDto.getId(),objDto.getNome(), objDto.getEmail(),objDto.getCnpj(),Causa.valorDe(objDto.getCausa1()),Causa.valorDe(objDto.getCausa2()), objDto.getDescricao(), end);
        return entidade;
    }

    public Entidade fromDTO(EntidadeNewDTO objDto){
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogadouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cid);
        Entidade entidade = new Entidade(null ,objDto.getNome(), objDto.getEmail(),objDto.getCnpj(),Causa.valorDe(objDto.getCausa1()),Causa.valorDe(objDto.getCausa2()), objDto.getDescricao(), end);
        entidade.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            entidade.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            entidade.getTelefones().add(objDto.getTelefone3());
        }
        return entidade;
    }

    private  void updateData(Entidade newObj, Entidade obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setCnpj(obj.getCnpj());
        newObj.setCausa1(obj.getCausa1());
        newObj.setCausa2(obj.getCausa2());
        newObj.setEndereco(obj.getEndereco());
    }

}
