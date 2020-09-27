-- Adicionando campo de foto em Voluntario
ALTER TABLE tb01_voluntario
ADD COLUMN tb01_foto_perfil_url VARCHAR(255) NULL;

-- Adicionando campo de foto em Entidade

ALTER TABLE tb02_entidade
ADD COLUMN tb02_foto_perfil_url VARCHAR(255) NULL;

-- Adicionando campo de foto em Vaga

ALTER TABLE tb03_vaga
ADD COLUMN tb03_foto_perfil_url VARCHAR(255) NULL;
