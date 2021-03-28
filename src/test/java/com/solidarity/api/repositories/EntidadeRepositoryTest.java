package com.solidarity.api.repositories;


import com.solidarity.api.model.Entidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.solidarity.api.builders.EntidadeBuilder.anEntidade;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Testes para EntidadeRepository")
class EntidadeRepositoryTest {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Test
    @DisplayName("Salver entidade com sucesso")
    void save_Entidade_ComSucesso() {
        Entidade entidadeParaSalvar = criarEntidade();
        Entidade entidadeSalva = entidadeRepository.save(entidadeParaSalvar);

        assertThat(entidadeSalva)
                .isNotNull();
        assertThat(entidadeSalva.getId())
                .isNotNull();
        assertThat(entidadeSalva.getEmail())
                .isEqualTo(entidadeParaSalvar.getEmail());
        assertThat(entidadeSalva.getNome())
                .isEqualTo(entidadeParaSalvar.getNome());
    }

    private Entidade criarEntidade(){
        return anEntidade()
                .withId(null)
                .withNome("Pastoral da Criança")
                .withEmail("contato@pastoral.com.br")
                .build();

    }
}