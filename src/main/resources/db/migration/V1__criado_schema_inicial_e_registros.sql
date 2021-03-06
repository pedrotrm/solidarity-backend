
-- Implementação da tabela estado

CREATE TABLE public.tb05_estado
(
    tb05_id   int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb05_nome varchar(100) NULL,
    CONSTRAINT tb05_estado_pkey PRIMARY KEY (tb05_id)
);

-- Implementação da tabela cidade

 CREATE TABLE public.tb06_cidade
(
    tb06_id              int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb06_nome            varchar(100) NULL,
    fktb06tb05_estado_id int8         NULL,
    CONSTRAINT tb06_cidade_pkey PRIMARY KEY (tb06_id),
    CONSTRAINT fknq6vuffgwaqjwotlvpio37p9c FOREIGN KEY (fktb06tb05_estado_id) REFERENCES tb05_estado (tb05_id)
);

-- Implementação da tabela endereco

 CREATE TABLE public.tb07_endereco
(
    tb07_id              int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb07_bairro          varchar(100) NULL,
    tb07_cep             char(8) NULL,
    tb07_complemento     varchar(100) NULL,
    tb07_logadouro       varchar(100) NULL,
    tb07_numero          varchar(10) NULL,
    fktb07tb06_cidade_id int8         NULL,
    CONSTRAINT tb07_endereco_pkey PRIMARY KEY (tb07_id),
    CONSTRAINT fk4ssxjq3p6s61qgcuibchs4ea FOREIGN KEY (fktb07tb06_cidade_id) REFERENCES tb06_cidade (tb06_id)
);

-- Implementação da tabela voluntario

 CREATE TABLE public.tb01_voluntario
(
    tb01_id                int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb01_causa1            int4         NULL,
    tb01_causa2            int4         NULL,
    tb01_email             varchar(50) NULL,
    tb01_nome              varchar(100) NULL,
    fktb01tb07_endereco_id int8         NULL,
    tb01_foto_perfil_url VARCHAR(255)   NULL,
    CONSTRAINT tb01_voluntario_pkey PRIMARY KEY (tb01_id),
    CONSTRAINT uk_laah7hngacxoyp2k4n0v5hat8 UNIQUE (tb01_email),
    CONSTRAINT fkcmsafrwdctruywml0huj21ik0 FOREIGN KEY (fktb01tb07_endereco_id) REFERENCES tb07_endereco (tb07_id)
);

-- Implementação da tabela voluntario telefones

CREATE TABLE public.tb01_voluntario_telefones
(
    tb01_voluntario_tb01_id int8        NOT NULL,
    telefones               varchar(30) NULL,
    CONSTRAINT fkiavlofr547n1n0027cm12qqe3 FOREIGN KEY (tb01_voluntario_tb01_id) REFERENCES tb01_voluntario (tb01_id)
);

-- Implementação da tabela entidade

 CREATE TABLE public.tb02_entidade
(
    tb02_id                   int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb02_causa1               int4         NULL,
    tb02_causa2               int4         NULL,
    tb02_cnpj                 char(18)     NULL,
    tb02_descricao            varchar(255) NULL,
    tb02_email                varchar(50) NULL,
    tb02_nome                 varchar(100) NULL,
    tb02_numero_beneficiarios int4         NULL,
    fktb02tb07_endereco_id    int8         NULL,
    tb02_foto_perfil_url      varchar(255) NULL,
    CONSTRAINT tb02_entidade_pkey PRIMARY KEY (tb02_id),
    CONSTRAINT uk_fv8k6wcyt37bkgudsaxsgmykm UNIQUE (tb02_email),
    CONSTRAINT uk_kp9oedueivjrx0re4xuupyesg UNIQUE (tb02_cnpj),
    CONSTRAINT fk1f0m8dc05od0l52pbwajqd82t FOREIGN KEY (fktb02tb07_endereco_id) REFERENCES tb07_endereco (tb07_id)
);

-- Implementação da tabela enntidade telefones

 CREATE TABLE public.tb02_entidade_telefones
(
    tb02_entidade_tb02_id int8         NOT NULL,
    telefones             varchar(30) NULL,
    CONSTRAINT fk999wfbmx89a4c5phqft2ah0fk FOREIGN KEY (tb02_entidade_tb02_id) REFERENCES tb02_entidade (tb02_id)
);

-- Implementação da tabela vaga

CREATE TABLE public.tb03_vaga
(
    tb03_id                     int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb03_causa1                 int4         NULL,
    tb03_causa2                 int4         NULL,
    tb03_descricao              text         NULL,
    tb03_habilidade             int4         NULL,
    tb03_nome                   varchar(100) NULL,
    fktb03tb07_endereco_vaga_id int8         NULL,
    fktb03tb02_entidade_id      int8         NULL,
    tb03_data_inicio            date         NULL,
    tb03_data_fim               date         NULL,
    tb03_foto_perfil_url        VARCHAR(255) NULL,
    tb03_quantidade             int4         NULL,
    tb03_tipo_vaga              int4         NULL,
    CONSTRAINT tb03_vaga_pkey PRIMARY KEY (tb03_id),
    CONSTRAINT fkjru91ayanpgyqexopiaiyj0ry FOREIGN KEY (fktb03tb02_entidade_id) REFERENCES tb02_entidade (tb02_id),
    CONSTRAINT fknmrxmc6asp7ofih3cd7m6x825 FOREIGN KEY (fktb03tb07_endereco_vaga_id) REFERENCES tb07_endereco (tb07_id)
);

-- Implementação da tabela vaga voluntario

CREATE TABLE public.tb04_vaga_voluntario
(
    tb04_data_fim      date NULL,
    tb04_data_inicio   date NULL,
    tb04_quantidade    int4      NULL,
    tb04_tipo_vaga     int4      NULL,
    fktb04tb03_vaga_id int8      NOT NULL,
    voluntario_id      int8      NOT NULL,
    CONSTRAINT tb04_vaga_voluntario_pkey PRIMARY KEY (fktb04tb03_vaga_id, voluntario_id),
    CONSTRAINT fkcm3k3e205yg66oorg1v4ixhp2 FOREIGN KEY (fktb04tb03_vaga_id) REFERENCES tb03_vaga (tb03_id),
    CONSTRAINT fkfhsfk7physu8ypu60rswiysyc FOREIGN KEY (voluntario_id) REFERENCES tb01_voluntario (tb01_id)
);

-- Implementação da tabela mini-curriculo

CREATE TABLE public.tb08_mini_curriculo
(
    fktb08tb01_voluntario_id int8         NOT NULL,
    tb08_descricao           varchar(255) NULL,
    CONSTRAINT tb08_mini_curriculo_pkey PRIMARY KEY (fktb08tb01_voluntario_id),
    CONSTRAINT fkny1w07g9icnkfvjimyy9ogefd FOREIGN KEY (fktb08tb01_voluntario_id) REFERENCES tb01_voluntario (tb01_id)
);

-- Implementação da tabela experiencias

CREATE TABLE public.tb09_experiencia
(
    tb09_id           int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb09_atribuicoes  varchar(255) NULL,
    tb09_data_entrada date    NULL,
    tb09_data_saida   date    NULL,
    tb09_nome_empresa varchar(100) NULL,
    curriculo_id      int8         NULL,
    CONSTRAINT tb09_experiencia_pkey PRIMARY KEY (tb09_id),
    CONSTRAINT fk1diksjs5r250vn44y6t5ot3bh FOREIGN KEY (curriculo_id) REFERENCES tb08_mini_curriculo (fktb08tb01_voluntario_id)
);

-- Implementação da tabela formacao

CREATE TABLE public.tb10_formacao
(
    tb10_id                 int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb10_data_fim           timestamp    NULL,
    tb10_data_inicio        timestamp    NULL,
    tb10_nome_curso         varchar(100) NULL,
    tb10_nome_instituicao   varchar(100) NULL,
    fktb10tb08_curriculo_id int8         NULL,
    CONSTRAINT tb10_formacao_pkey PRIMARY KEY (tb10_id),
    CONSTRAINT fk8wsnn26gy8hglfrh0b1vej0xl FOREIGN KEY (fktb10tb08_curriculo_id) REFERENCES tb08_mini_curriculo (fktb08tb01_voluntario_id)
);

-- Implementação da tabela projetos


CREATE TABLE public.tb11_projeto
(
    tb11_id                 int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb11_descricao          varchar(255) NULL,
    tb11_nome_projeto       varchar(100) NULL,
    fktb11tb08_curriculo_id int8         NULL,
    CONSTRAINT tb11_projeto_pkey PRIMARY KEY (tb11_id),
    CONSTRAINT fkp0g4vjrxats69lms2yvp92ftx FOREIGN KEY (fktb11tb08_curriculo_id) REFERENCES tb08_mini_curriculo (fktb08tb01_voluntario_id)
);


-- Implementação da tabela de usuario

CREATE TABLE public.tb12_usuario
(
    tb12_id INT8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb12_nome VARCHAR(50) NOT NULL,
    tb12_email VARCHAR(50) NOT NULL,
    tb12_senha VARCHAR(150) NOT NULL,

    CONSTRAINT tb12_usuario_pkey PRIMARY KEY (tb12_id)
);

-- Implementação da tabela de permissao

CREATE TABLE public.tb13_permissao
(
    tb13_id INT8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tb13_descricao VARCHAR(50) NOT NULL,
    CONSTRAINT tb13_permissao_pkey PRIMARY KEY (tb13_id)
);

-- Implementação da tabela de de relacionamento

CREATE TABLE public.tb14_usuario_permissao
(
    fktb14tb12_cod_usuario INT8 NOT NULL,
    fktb14tb13_cod_permissao INT8 NOT NULL,
    CONSTRAINT tb14_usuario_permissao_pkey PRIMARY KEY (fktb14tb12_cod_usuario, fktb14tb13_cod_permissao),
    CONSTRAINT fktb14tb12_cod_usuario_pkey FOREIGN KEY (fktb14tb12_cod_usuario) REFERENCES tb12_usuario (tb12_id),
    CONSTRAINT fktb14tb13_cod_permissao_pkey FOREIGN KEY (fktb14tb13_cod_permissao) REFERENCES tb13_permissao (tb13_id)
);

-- Adicionado fk na Tabela Voluntario

ALTER TABLE tb01_voluntario
    ADD COLUMN fkqg01qg12_cod_usuario INT8,
    ADD CONSTRAINT fkqg01qg12_cod_usuario_pkey FOREIGN KEY (fkqg01qg12_cod_usuario) REFERENCES tb12_usuario (tb12_id);


-- Adicionado fk na Tabela Entidade

ALTER TABLE tb02_entidade
    ADD COLUMN fktb02tb12_cod_usuario INT8,
    ADD CONSTRAINT fktb02tb12_cod_usuario_pkey FOREIGN KEY (fktb02tb12_cod_usuario) REFERENCES tb12_usuario (tb12_id);

-- Insert de Permissoes no banco de dados.

INSERT INTO tb13_permissao (tb13_descricao) VALUES
('ROLE_ALTERAR_VOLUNTARIO'),
('ROLE_DELETAR_VOLUNTARIO'),
('ROLE_CADASTRAR_VAGA'),
('ROLE_ALTERAR_VAGA'),
('ROLE_PARTICIPAR_VAGA'),
('ROLE_DESISTIR_VAGA'),
('ROLE_ALTERAR_ENTIDADE'),
('ROLE_DELETAR_ENTIDADE'),
('ROLE_CADASTRAR_CIDADE'),
('ROLE_ALTERAR_CIDADE'),
('ROLE_DELETAR_CIDADE'),
('ROLE_ALTERAR_MINICURRICULO'),
('ROLE_DELETAR_MINICURRICULO'),
('ROLE_PESQUISAR_VOLUNTARIO'),
('ROLE_PESQUISAR_VAGAVOLUNTARIO'),
('ROLE_PESQUISAR_ENTIDADE'),
('ROLE_PESQUISAR_CIDADE');
