package com.solidarity.solidarity_backend.config;

import com.solidarity.solidarity_backend.model.*;
import com.solidarity.solidarity_backend.model.enums.Causa;
import com.solidarity.solidarity_backend.model.enums.Habilidade;
import com.solidarity.solidarity_backend.model.enums.TipoVaga;
import com.solidarity.solidarity_backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;

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
    private VagaRepository vagaRepository;

    @Autowired
    private VagaVoluntarioRepository vagaVoluntarioRepository;


    
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

        Endereco end4 = new Endereco(null, "Rua da Alegria", "79", null, "Alto do Bode", "65370000", c3);

        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));

        Entidade ent1 = new Entidade(null, "manases@outlook.com.br", "Manases","30033079000118", "ONG de combate as drogas",  end1, Causa.COMBATE_AS_DROGAS,Causa.CIDADANIA, 249);

        Entidade ent2 = new Entidade(null, "planmundial@outlook.com", "Plan", "30033079000122", "Ong para auxilio crianças carentes", end2, Causa.CRIANCAS, Causa.EDUCACAO, 140);

       ent1.getTelefones().add("32275654");
       ent2.getTelefones().addAll(Arrays.asList("32456565", "9899714515"));

        entidadeRepository.saveAll(Arrays.asList(ent1,ent2));

        Voluntario v1 = new Voluntario(null, "Pedro", "pedrotrm@outlook.com.br", Causa.EDUCACAO, Causa.TERINAMENTO_PROFISSIONAL,end3);

        v1.getTelefones().addAll(Arrays.asList("98988003545", "36542169"));

        MiniCurriculo m1 = new MiniCurriculo(null, "Progamador Java", v1);

        Experiencia ep1 = new Experiencia(null,"Sintech Solucoes", sdf.parse("03/02/2017"), sdf.parse("14/08/2018"), "Estagiario", m1);

        Formacao f1 = new Formacao(null, "Ceuma", "Engenharia da Computação", sdf.parse("10/03/2016"), sdf.parse("30/11/2020"), m1);

        Projeto p1 = new Projeto(null, "Solidarity", "Plataforma para voluntariados", m1);

        Voluntario v2 = new Voluntario(null, "Maria Eduarda",  "mariaeduarda@gmail.com", Causa.COMBATE_POBREZA, Causa.MULHERES, end4);

        v2.getTelefones().addAll(Arrays.asList("98984715817", "36542250"));

        MiniCurriculo m2 = new MiniCurriculo(null, "Vendendora", v2);

        Formacao f2 = new Formacao(null, "Cema", "Ensino médio", sdf.parse("03/02/2014"), sdf.parse("29/11/2016"), m2);

        miniCurriculoRepository.saveAll(Arrays.asList(m1,m2));

        m1.getExperiencias().add(ep1);
        m1.getFormacoes().add(f1);
        m2.getFormacoes().add(f2);
        m1.getProjetos().add(p1);
        v1.setMiniCurriculo(m1);
        v2.setMiniCurriculo(m2);

        voluntarioRepository.saveAll(Arrays.asList(v1,v2));


        Vaga vaga1 = new Vaga(null, "Progamador", "Desenvolver um sistema WEB", Causa.CRIANCAS, Causa.EDUCACAO, Habilidade.COMPUTADORES_TECNOLOGIA,null, ent2);

        Vaga vaga2 = new Vaga(null, "Cozinheira", "Fazer comida",Causa.COMBATE_AS_DROGAS,Causa.CIDADANIA, Habilidade.COZINHA,end1,ent1);

        vagaRepository.saveAll(Arrays.asList(vaga1, vaga2));


        VagaVoluntario vagav1 = new VagaVoluntario(vaga1, v1, sdf.parse("10/10/2020"),null, TipoVaga.PONTUAIS,5);

        VagaVoluntario vagav2 = new VagaVoluntario(vaga1, v2, sdf.parse("10/10/2020"),null, TipoVaga.PONTUAIS,4);

        VagaVoluntario vagav3 = new VagaVoluntario(vaga2, v2, sdf.parse("10/10/2020"),sdf.parse("12/11/2020"), TipoVaga.RECORRENTE,2);

        vagaVoluntarioRepository.saveAll(Arrays.asList(vagav1,vagav2,vagav3));




    }
}
