package com.solidarity.solidarity_backend.config;

import com.solidarity.solidarity_backend.model.*;
import com.solidarity.solidarity_backend.model.enums.Causa;
import com.solidarity.solidarity_backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private MiniCurriculoRepository miniCurriculoRepository;

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private  FormacaoRepository formacaoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    
    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Estado est1 = new Estado(null, "MA");

        estadoRepository.save(est1);

        Cidade c1 = new Cidade(null, "Sao Luis",est1);

        Cidade c2 = new Cidade(null,"Raposa",est1);

        Cidade c3 = new Cidade(null, "Pindaré-Mirim", est1);

        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        Endereco end1 = new Endereco(null, "Rua Aririzal", "04", null, "Cohama", "65066260", c1);

        Endereco end2 = new Endereco(null, "Rua dos Afogados", "23", "Loja 2", "Iguaiba", "65066230",c2);

        Endereco end3 = new Endereco(null, "Rua Oscar Romero", "09", null, "Centro", "65370000", c3);

        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

        Entidade ent1 = new Entidade(null, "manases@outlook.com.br", "Manases","30033079000118", "ONG de combate as drogas", "12345", end1, Causa.COMBATE_AS_DROGAS,Causa.CIDADANIA);

        Entidade ent2 = new Entidade(null, "planmundial@outlook.com", "Plan", "30033079000122", "Ong para auxilio crianças carentes", "12345", end2, Causa.CRIANCAS, Causa.EDUCACAO);

        Set<String> tel1 = new HashSet <>();
        tel1.add("32274722");
        ent1.setTelefones(tel1);

        Set<String> tel2 = new HashSet <>();
        tel2.add("36542060");
        ent2.setTelefones(tel2);

        entidadeRepository.saveAll(Arrays.asList(ent1,ent2));

        Voluntario v1 = new Voluntario(null, "Pedro", "12345", "pedrotrm@outlook.com.br", Causa.EDUCACAO, Causa.TERINAMENTO_PROFISSIONAL,end3,null);

        Set<String> tel3 = new HashSet<>();
        tel3.add("98988003545");
        tel3.add("36542169");
        v1.setTelefones(tel3);

        voluntarioRepository.save(v1);


        MiniCurriculo m1 = new MiniCurriculo(null, "Progamador Java", v1);

        miniCurriculoRepository.save(m1);

        Experiencia ep1 = new Experiencia(null,"Sintech Solucoes", sdf.parse("03/02/2017"), sdf.parse("14/08/2018"), "Estagiario", m1);

        Formacao f1 = new Formacao(null, "Ceuma", "Engenharia da Computação", sdf.parse("10/03/2016"), sdf.parse("30/11/2020"), m1);

        Projeto p1 = new Projeto(null, "Solidarity", "Plataforma para voluntariados", m1);

        experienciaRepository.save(ep1);
        formacaoRepository.save(f1);
        projetoRepository.save(p1);

        m1.setExperiencias(Arrays.asList(ep1));
        m1.setFormacoes(Arrays.asList(f1));
        m1.setProjetos(Arrays.asList(p1));
        v1.setMiniCurriculo(m1);

        miniCurriculoRepository.save(m1);
        voluntarioRepository.save(v1);






    }
}
