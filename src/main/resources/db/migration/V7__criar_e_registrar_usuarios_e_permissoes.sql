
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

-- Insert de Usuarios ja existentes no banco de dados.

INSERT INTO tb12_usuario(tb12_nome, tb12_email, tb12_senha) VALUES
('Pedro Thiago','pedrotrm@outlook.com.br', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Sabrine Carvalho', 'sabrine@gmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Pastoral da Criança', 'pastoral@gmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Queridos Velhinos', 'velhinhosqueridos@hotmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Artemis', 'mulherdecasa@gmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Nordestinos Valentes', 'nordestinos@hotmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Meu querido vovo', 'vovoquerido@gmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6'),
('Fome Zero', 'fomezero@hotmail.com', '$2a$10$1Yml3uvbLFCxMRr013NqF.qNCBkv1oW90pT8J0QkqSeIxKeCLtJm6');

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
('ROLE_DELETAR_MINICURRICULO');

