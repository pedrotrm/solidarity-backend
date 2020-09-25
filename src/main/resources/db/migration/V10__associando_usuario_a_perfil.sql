-- Associando Voluntarios a Usuario

UPDATE public.tb01_voluntario
SET fkqg01qg12_cod_usuario=1
WHERE tb01_id=1;
UPDATE public.tb01_voluntario
SET fkqg01qg12_cod_usuario=2
WHERE tb01_id=2;

-- Associando Entidade a Usuario

UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=3
WHERE tb02_id=1;
UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=4
WHERE tb02_id=2;
UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=5
WHERE tb02_id=3;
UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=6
WHERE tb02_id=4;
UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=7
WHERE tb02_id=5;
UPDATE public.tb02_entidade
SET fktb02tb12_cod_usuario=8
WHERE tb02_id=6;