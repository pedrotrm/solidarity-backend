package com.solidarity.api.services;

import com.solidarity.api.dto.EntidadeDTO;
import com.solidarity.api.dto.EntidadeNewDTO;
import com.solidarity.api.dto.VagaDTO;
import com.solidarity.api.dto.VoluntarioDTO;
import com.solidarity.api.model.*;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.repositories.CidadeRepository;
import com.solidarity.api.repositories.EnderecoRepository;
import com.solidarity.api.repositories.EntidadeRepository;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.services.exception.DataIntegrityException;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntidadeService {

    private final EntidadeRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final VagaRepository vagaRepository;
    private final CidadeRepository cidadeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final S3Service s3Service;

    public EntidadeService(EntidadeRepository repository,
                           EnderecoRepository enderecoRepository,
                           VagaRepository vagaRepository,
                           CidadeRepository cidadeRepository,
                           PasswordEncoder passwordEncoder,
                           EmailService emailService,
                           S3Service s3Service) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.vagaRepository = vagaRepository;
        this.cidadeRepository = cidadeRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.s3Service = s3Service;
    }

    public List<Entidade> findAll() {
        return repository.findAll();
    }

    public Entidade findById(Long id) {
        Optional<Entidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Entidade.class.getName()));
    }

    public Vaga findVagaById(Long id){
        Optional<Vaga> obj = vagaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Vaga.class.getName()));
    }

    public Set<VoluntarioDTO> findVagaVoluntarios(Long vagaId) {
        Vaga vaga = findVagaById(vagaId);
        return vaga.getVagas().stream()
                .map(x -> new VoluntarioDTO(x.getVoluntario()))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<VagaDTO> findAllVagas(Long entidadeId){
        List<Vaga> obj = vagaRepository.findAllVagasByEntidadeId(entidadeId).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Tipo: " + Vaga.class.getName()));
        return obj.stream()
                .map(VagaDTO::new)
                .collect(Collectors.toSet());
    }

    @Transactional
    public Entidade insert(Entidade obj) {
        obj.setId(null);
        obj = repository.save(obj);
        if (obj.getEndereco() != null)
            enderecoRepository.save(obj.getEndereco());
        emailService.enviarEmailConfirmacaoEntidade(obj);
        return obj;
    }

    @Transactional
    public Entidade update(Entidade obj) {
        repository.save(obj);
        if (obj.getEndereco() != null)
            enderecoRepository.save(obj.getEndereco());
        return obj;
    }

    @Transactional
    public void createVaga(Vaga vaga){
        vaga.setId(null);
        vagaRepository.createVaga(vaga);
        enderecoRepository.save(vaga.getEnderecoVaga());
    }

    @Transactional
    public void updateVaga(Vaga obj){
        Vaga newObj = findVagaById(obj.getId());
        Endereco endereco = enderecoRepository.findById(newObj.getEnderecoVaga().getId()).orElseThrow();
        updateVaga(newObj, obj, endereco);
        vagaRepository.updateVaga(newObj);
        enderecoRepository.save(endereco);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        }
        catch (Exception e) {
            throw new DataIntegrityException("Não é possível excluir um cliente com vagas associoadas");
        }
    }

    public URI uploadFotoPerfil(MultipartFile multipartFile, Long entidadeId){
        URI uri = s3Service.uploadFile(multipartFile);
        Entidade entidade = findById(entidadeId);
        entidade.setFotoPerfil(uri.toString());
        repository.save(entidade);
        return uri;
    }

    public Page<Entidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Entidade fromDTO(EntidadeNewDTO objDto) {
        Usuario usuario = new Usuario(null, objDto.getNome(), objDto.getEmail(), passwordEncoder.encode(objDto.getSenha()));
        usuario.setPermissoes(getPermissoesEntidade());
        Entidade entidade = new Entidade();
        entidade.setNome(objDto.getNome());
        entidade.setEmail(objDto.getEmail());
        entidade.setUsuario(usuario);
        entidade.setCausa1(Causa.valorDe(objDto.getCausa1()));
        entidade.setCausa2(Causa.valorDe(objDto.getCausa2()));
        entidade.getTelefones().add(objDto.getTelefone1());
        entidade.setDescricao(objDto.getDescricao());
        if (objDto.getLogadouro() != null ||
                objDto.getCidadeId() != null) {
            Endereco endereco = createEnderecoFromDto(objDto);
            entidade.setEndereco(endereco);
        }
        if (objDto.getCnpj() != null)
            entidade.setCnpj(objDto.getCnpj());
        if (objDto.getTelefone2() != null) {
            entidade.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            entidade.getTelefones().add(objDto.getTelefone3());
        }
        return entidade;
    }

    public Entidade updateFromDTO(EntidadeDTO objDto) {
        Entidade entidade = findById(objDto.getId());
        if (objDto.getLogadouro() != null ||
                objDto.getCidadeId() != null) {
            uptadeEnderecoFromDto(objDto, entidade);
        }
        if (objDto.getNome() != null) {
            entidade.setNome(objDto.getNome());
            entidade.getUsuario().setNome(objDto.getNome());
        }
        if (objDto.getEmail() != null) {
            entidade.setEmail(objDto.getEmail());
            entidade.getUsuario().setEmail(objDto.getEmail());
        }
        if (objDto.getCnpj() != null){
            entidade.setCnpj(objDto.getCnpj());
        }
        if (objDto.getCausa1() != null) {
            entidade.setCausa1(objDto.getCausa1());
        }
        if (objDto.getCausa2() != null) {
            entidade.setCausa2(objDto.getCausa2());
        }
        if (objDto.getCnpj() != null){
            entidade.setCnpj(objDto.getCnpj());
        }
        if (objDto.getTelefone1() != null){
            entidade.getTelefones().add(objDto.getTelefone1());
        }
        if (objDto.getTelefone2() != null){
            entidade.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null){
            entidade.getTelefones().add(objDto.getTelefone3());
        }
        if (objDto.getDescricao() != null){
            entidade.setDescricao(objDto.getDescricao());
        }
        return entidade;
    }

    private Endereco uptadeEnderecoFromDto(EntidadeDTO objDto, Entidade entidade) {
        Cidade cidade = cidadeRepository.findById(objDto.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + objDto.getCidadeId() + ", Tipo: " + Cidade.class.getName()));
        if (entidade.getEndereco() != null){
            entidade.getEndereco().setLogadouro(objDto.getLogadouro());
            entidade.getEndereco().setCidade(cidade);
            if (objDto.getBairro() != null)
                entidade.getEndereco().setBairro(objDto.getBairro());
            if (objDto.getCep() != null)
                entidade.getEndereco().setCep(objDto.getCep());
            if (objDto.getNumero() != null)
                entidade.getEndereco().setNumero(objDto.getNumero());
            if (objDto.getComplemento() != null)
                entidade.getEndereco().setComplemento(objDto.getComplemento());
            return entidade.getEndereco();
        } else {
            Endereco endereco = new Endereco();
            endereco.setLogadouro(objDto.getLogadouro());
            endereco.setCidade(cidade);
            if (objDto.getBairro() != null)
                endereco.setBairro(objDto.getBairro());
            if (objDto.getCep() != null)
                endereco.setCep(objDto.getCep());
            if (objDto.getNumero() != null)
                endereco.setNumero(objDto.getNumero());
            if (objDto.getComplemento() != null)
                endereco.setComplemento(objDto.getComplemento());
            entidade.setEndereco(endereco);
            return endereco;
        }
    }

    public Vaga fromVagaDTO(VagaDTO objDto){
        Cidade cidade = cidadeRepository.findById((objDto.getCidadeId())).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + objDto.getCidadeId() + ", Tipo: " + Cidade.class.getName()));
        Endereco endereco = new Endereco(null, objDto.getLogadouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cidade);
        Entidade entidade = findById(objDto.getEntidadeId());
        return new Vaga(null, objDto.getNome(), objDto.getDescricao(), objDto.getCausa1().getCode(), objDto.getCausa2().getCode(), objDto.getHabilidade().getCode(), objDto.getDataInicio(), objDto.getDataFim(),objDto.getTipoVaga().getCode(), objDto.getQuantidade(), endereco, entidade);
    }

    private Endereco createEnderecoFromDto(EntidadeNewDTO objDto) {
        Endereco endereco = new Endereco();
        Cidade cidade = cidadeRepository.findById(objDto.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + objDto.getCidadeId() + ", Tipo: " + Cidade.class.getName()));
        endereco.setLogadouro(objDto.getLogadouro());
        endereco.setCidade(cidade);
        if (objDto.getBairro() != null)
            endereco.setBairro(objDto.getBairro());
        if (objDto.getCep() != null)
            endereco.setCep(objDto.getCep());
        if (objDto.getNumero() != null)
            endereco.setNumero(objDto.getNumero());
        if (objDto.getComplemento() != null)
            endereco.setComplemento(objDto.getComplemento());
        return endereco;
    }

    private void updateVaga(Vaga newObj, Vaga obj, Endereco objEndereco) {
        newObj.setNome(obj.getNome());
        newObj.setDescricao(obj.getDescricao());
        newObj.setCausa1(obj.getCausa1());
        newObj.setCausa1(obj.getCausa2());
        newObj.setHabilidade(obj.getHabilidade());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDataFim(obj.getDataFim());
        newObj.setTipoVaga(obj.getTipoVaga());
        newObj.setQuantidade(obj.getQuantidade());
        objEndereco.setLogadouro(obj.getEnderecoVaga().getLogadouro());
        objEndereco.setNumero(obj.getEnderecoVaga().getNumero());
        objEndereco.setBairro(obj.getEnderecoVaga().getBairro());
        objEndereco.setCep(obj.getEnderecoVaga().getCep());
        objEndereco.setComplemento(obj.getEnderecoVaga().getComplemento());
        objEndereco.setCidade(obj.getEnderecoVaga().getCidade());
    }

    private List<Permissao> getPermissoesEntidade(){
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(new Permissao(3L));
        permissoes.add(new Permissao(4L));
        permissoes.add(new Permissao(7L));
        permissoes.add(new Permissao(8L));
        permissoes.add(new Permissao(9L));
        permissoes.add(new Permissao(10L));
        permissoes.add(new Permissao(15L));
        permissoes.add(new Permissao(16L));
        permissoes.add(new Permissao(17L));
        return permissoes;
    }
}
