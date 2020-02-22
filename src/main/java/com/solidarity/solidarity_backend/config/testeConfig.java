package com.solidarity.solidarity_alpha.config;

import com.solidarity.solidarity_alpha.model.Cidade;
import com.solidarity.solidarity_alpha.model.Endereco;
import com.solidarity.solidarity_alpha.model.Entidade;
import com.solidarity.solidarity_alpha.model.Estado;
import com.solidarity.solidarity_alpha.model.enums.Causa;
import com.solidarity.solidarity_alpha.repositories.CidadeRepository;
import com.solidarity.solidarity_alpha.repositories.EnderecoRepository;
import com.solidarity.solidarity_alpha.repositories.EntidadeRepository;
import com.solidarity.solidarity_alpha.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class testeConfig implements CommandLineRunner {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public void run(String... args) throws Exception {

        Estado est1 = new Estado(null, "MA");

        estadoRepository.save(est1);

        Cidade c1 = new Cidade(null, "Sao Luis",est1);

        cidadeRepository.save(c1);

        Endereco end1 = new Endereco(null, "Rua Aririzal", "04", null, "Cohama", "65066260", c1);

        enderecoRepository.save(end1);

        Entidade ent1 = new Entidade(null, "manases@outlook.com.br", "Manases","30033079000118", "ONG de combate as drogas", "12345", end1, Causa.COMBATE_AS_DROGAS,Causa.CULTURA_E_ARTE);

        entidadeRepository.save(ent1);



    }
}
