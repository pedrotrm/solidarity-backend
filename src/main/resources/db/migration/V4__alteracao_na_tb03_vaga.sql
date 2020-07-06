ALTER TABLE tb03_vaga
ADD COLUMN tb03_data_inicio TIMESTAMP NULL,
ADD COLUMN tb03_data_fim TIMESTAMP NULL,
ADD COLUMN tb04_quantidade INT4 NULL,
ADD COLUMN tb04_tipo_vaga INT4 NULL;

UPDATE tb03_vaga
SET tb03_data_inicio = '2020-06-09 00:00:00'
WHERE tb03_id = 1;

UPDATE tb03_vaga
SET tb03_data_inicio = '2020-06-09 00:00:00';
