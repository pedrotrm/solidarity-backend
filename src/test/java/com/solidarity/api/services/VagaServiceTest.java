package com.solidarity.api.services;

import com.solidarity.api.model.*;
import com.solidarity.api.model.enums.Causa;
import com.solidarity.api.repositories.VagaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class VagaServiceTest {
    @Mock
    VagaRepository repository;
    @InjectMocks
    VagaService vagaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(null);

        List<Vaga> result = vagaService.findAll();
        Assertions.assertEquals(Arrays.<Vaga>asList(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null))), result);
    }

    @Test
    void testFindById() {
        when(repository.findById(anyLong())).thenReturn(null);

        Vaga result = vagaService.findById(Long.valueOf(1));
        Assertions.assertEquals(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null)), result);
    }

    @Test
    void testFindByCausa() {
        when(repository.findByCausa(anyInt())).thenReturn(null);

        List<Vaga> result = vagaService.findByCausa(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Vaga>asList(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null))), result);
    }

    @Test
    void testFindByHabilidade() {
        when(repository.findByHabilidade(anyInt())).thenReturn(null);

        List<Vaga> result = vagaService.findByHabilidade(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Vaga>asList(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null))), result);
    }

    @Test
    void testFindByTipo() {
        when(repository.findByTipoVaga(anyInt())).thenReturn(null);

        List<Vaga> result = vagaService.findByTipo(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Vaga>asList(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null))), result);
    }

    @Test
    void testFindByNome() {
        when(repository.findByNome(anyString())).thenReturn(null);

        List<Vaga> result = vagaService.findByNome("nome");
        Assertions.assertEquals(Arrays.<Vaga>asList(new Vaga(Long.valueOf(1), "nome", "descricao", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, Month.MARCH, 27, 16, 16, 4).toInstant(ZoneOffset.UTC), Integer.valueOf(0), Integer.valueOf(0), new Endereco(Long.valueOf(1), "logadouro", "numero", "complemento", "bairro", "cep", new Cidade(Long.valueOf(1), "nome", new Estado(Long.valueOf(1), "nome"))), new Entidade(null, null, null, null, Causa.TREINAMENTO_PROFISSIONAL, Causa.TREINAMENTO_PROFISSIONAL, null, null, null))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme