package com.solidarity.api.services;

import com.solidarity.api.dto.VoluntarioDTO;
import com.solidarity.api.dto.VoluntarioNewDTO;
import com.solidarity.api.model.*;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.repositories.CidadeRepository;
import com.solidarity.api.repositories.EnderecoRepository;
import com.solidarity.api.repositories.VagaRepository;
import com.solidarity.api.repositories.VoluntarioRepository;
import com.solidarity.api.services.exception.DataIntegrityException;
import com.solidarity.api.services.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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

@Service
public class VoluntarioService {


    private final VoluntarioRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;
    private final VagaRepository vagaRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final S3Service s3Service;

    public VoluntarioService(VoluntarioRepository repository,
                             EnderecoRepository enderecoRepository,
                             CidadeRepository cidadeRepository,
                             VagaRepository vagaRepository,
                             PasswordEncoder passwordEncoder,
                             EmailService emailService,
                             S3Service s3Service) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
        this.vagaRepository = vagaRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.s3Service = s3Service;
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
        if (obj.getEndereco() != null)
            enderecoRepository.save(obj.getEndereco());
        emailService.enviarEmailConfirmacaoVoluntario(obj);
        return obj;
    }

    @Transactional
    public Voluntario update(Voluntario obj) {
        repository.save(obj);
        if (obj.getEndereco() != null)
            enderecoRepository.save(obj.getEndereco());
        return obj;
    }

    @Transactional
    public void participarVaga(Vaga vaga, Voluntario voluntario) {
        VagaVoluntario vagaVoluntario = new VagaVoluntario(vaga, voluntario, vaga.getDataInicio(), vaga.getDataFim(), vaga.getTipoVaga(), vaga.getQuantidade());
        vagaRepository.participarVaga(vagaVoluntario);
    }

    @Transactional
    public void dessistirVaga(Long voluntarioId, Long vagaId) {
        VagaVoluntario vagaVoluntario = findVagaVoluntarioById(voluntarioId, vagaId);
        vagaRepository.desistirVaga(vagaVoluntario);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente com vagas associoadas");
        }
    }

    public Page<Voluntario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Voluntario updateFromDTO(VoluntarioDTO objDto) {
        Voluntario voluntario = findById(objDto.getId());
        if (objDto.getLogadouro() != null ||
                objDto.getCidadeId() != null) {
            uptadeEnderecoFromDto(objDto, voluntario);
        }
        if (objDto.getNome() != null) {
            voluntario.setNome(objDto.getNome());
            voluntario.getUsuario().setNome(objDto.getNome());
        }
        if (objDto.getEmail() != null) {
            voluntario.setEmail(objDto.getEmail());
            voluntario.getUsuario().setEmail(objDto.getEmail());
        }
        if (objDto.getCausa1() != null) {
            voluntario.setCausa1(objDto.getCausa1());
        }
        if (objDto.getCausa2() != null) {
            voluntario.setCausa2(objDto.getCausa2());
        }
        if (objDto.getTelefone1() != null){
            voluntario.getTelefones().add(objDto.getTelefone1());
        }
        if (objDto.getTelefone2() != null){
            voluntario.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null){
            voluntario.getTelefones().add(objDto.getTelefone3());
        }
        return voluntario;
    }

    public Voluntario fromDTO(VoluntarioNewDTO objDto) {
        Usuario usuario = new Usuario(null, objDto.getNome(), objDto.getEmail(), passwordEncoder.encode(objDto.getSenha()));
        usuario.setPermissoes(getPermissoesVoluntario());
        Voluntario voluntario = new Voluntario();
        voluntario.setNome(objDto.getNome());
        voluntario.setEmail(objDto.getEmail());
        voluntario.setUsuario(usuario);
        voluntario.setCausa1(Causa.valorDe(objDto.getCausa1()));
        if (objDto.getLogadouro() != null ||
                objDto.getCidadeId() != null) {
            Endereco endereco = createEnderecoFromDto(objDto);
            voluntario.setEndereco(endereco);
        }
        if (objDto.getCausa2() != null)
            voluntario.setCausa2(Causa.valorDe(objDto.getCausa2()));
        MiniCurriculo m = new MiniCurriculo(null, objDto.getDescricao(), voluntario);
        voluntario.setMiniCurriculo(m);
        voluntario.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            voluntario.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            voluntario.getTelefones().add(objDto.getTelefone3());
        }
        return voluntario;
    }

    public URI uploadFotoPerfil(MultipartFile multipartFile, Long voluntarioId) {
        URI uri = s3Service.uploadFile(multipartFile);
        Voluntario voluntario = findById(voluntarioId);
        voluntario.setFotoPerfil(uri.toString());
        repository.save(voluntario);
        return uri;
    }

    private Endereco createEnderecoFromDto(VoluntarioNewDTO objDto) {
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

    private Endereco uptadeEnderecoFromDto(VoluntarioDTO objDto, Voluntario voluntario) {
        Cidade cidade = cidadeRepository.findById(objDto.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + objDto.getCidadeId() + ", Tipo: " + Cidade.class.getName()));
        if (voluntario.getEndereco() != null){
            voluntario.getEndereco().setLogadouro(objDto.getLogadouro());
            voluntario.getEndereco().setCidade(cidade);
            if (objDto.getBairro() != null)
                voluntario.getEndereco().setBairro(objDto.getBairro());
            if (objDto.getCep() != null)
                voluntario.getEndereco().setCep(objDto.getCep());
            if (objDto.getNumero() != null)
                voluntario.getEndereco().setNumero(objDto.getNumero());
            if (objDto.getComplemento() != null)
                voluntario.getEndereco().setComplemento(objDto.getComplemento());
            return voluntario.getEndereco();
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
            voluntario.setEndereco(endereco);
            return endereco;
        }
    }

    private VagaVoluntario findVagaVoluntarioById(Long voluntarioId, Long vagaId) {
        Optional<VagaVoluntario> obj = vagaRepository.findVagaVoluntarioByVoluntarioId(voluntarioId, vagaId);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Voluntatrio não esta participando da vaga! Id: " + voluntarioId + ", Tipo: " + VagaVoluntario.class.getName()));
    }

    private List<Permissao> getPermissoesVoluntario() {
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(new Permissao(1L));
        permissoes.add(new Permissao(2L));
        permissoes.add(new Permissao(5L));
        permissoes.add(new Permissao(6L));
        permissoes.add(new Permissao(9L));
        permissoes.add(new Permissao(12L));
        permissoes.add(new Permissao(13L));
        permissoes.add(new Permissao(14L));
        permissoes.add(new Permissao(15L));
        permissoes.add(new Permissao(16L));
        permissoes.add(new Permissao(17L));
        return permissoes;
    }

}
