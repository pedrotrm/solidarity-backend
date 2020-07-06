    ALTER TABLE tb09_experiencia
                ALTER COLUMN tb09_data_entrada TYPE date,
                ALTER COLUMN tb09_data_saida TYPE date;

    ALTER TABLE tb10_formacao
        ALTER COLUMN tb10_data_inicio TYPE date,
        ALTER COLUMN tb10_data_fim TYPE date;


    INSERT INTO tb09_experiencia (tb09_atribuicoes, tb09_data_entrada, tb09_data_saida, tb09_nome_empresa, curriculo_id) VALUES
    ('Progamador em uma pequena startup - Stack Java e Javacript', '2018-03-16', '2019-09-27', 'Desenvolvedor Back-End',1),
    ('Estagiario na area de Automação na Vale S.A', '2019-10-03', null, 'Vale S.A.',1);

    INSERT INTO tb10_formacao (tb10_data_fim, tb10_data_inicio, tb10_nome_curso, tb10_nome_instituicao, fktb10tb08_curriculo_id) VALUES
    ('2012-03-10','2014-11-30','Ensino Médio Integrado','IFMA',1),
    ('2016-03-15',null,'Engenharia da Cumputação','Universidade Ceuma',1);

    INSERT INTO tb11_projeto (tb11_descricao, tb11_nome_projeto, fktb11tb08_curriculo_id) VALUES
    ('Projeto de desenvolvimento de uma plataforma para ajudar Ongs a encontrar trabalho volunatario', 'Projeto Solidarity',1);





