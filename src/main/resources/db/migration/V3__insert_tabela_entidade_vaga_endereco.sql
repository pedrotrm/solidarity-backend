INSERT INTO tb07_endereco (tb07_id,tb07_logadouro,tb07_numero,tb07_bairro,tb07_cep,tb07_complemento,fktb07tb06_cidade_id) VALUES
(5, 'Rua do feijão', '324', 'Vinhas', '65003455',null, 1),
(8, 'Rua da Alegria', '720 Q6', 'Cohab', '65400234','Loja 67', 1),
(6, 'Av Ricardo Almeida', '560', 'Monte Castelo', '65400257',null, 1),
(7, 'Rua da Vilinha', '123', 'Bequimão', '65476240', null, 1);

INSERT INTO tb02_entidade (tb02_id,tb02_causa1, tb02_causa2, tb02_cnpj, tb02_descricao, tb02_email, tb02_nome, tb02_numero_beneficiarios, fktb02tb07_endereco_id)  VALUES
(3, 15, 6, '55.811.717/0001-05','Ong para Mulheres Vitima de Violencia', 'mulherdecasa@gmail.com', 'Artemis', 5000,5),
(4, 5, 10, '54.744.061/0001-92','Ong combate discriminação', 'nordestinos@hotmail.com', 'Nordestinos Valentes', 279, 8),
(5, 12, 13, '25.323.721/0001-63','Ong para Idosos com Leucemia', 'vovoquerido@gmail.com', 'Meu querido vovo', 678,6),
(6, 2, 20, '06.334.594/0001-53','Ong Combate a probreza', 'fomezero@hotmail.com', 'Fome Zero', 320, 6);

INSERT INTO tb03_vaga (tb03_id,tb03_causa1, tb03_causa2, tb03_descricao, tb03_habilidade, tb03_nome, fktb03tb07_endereco_vaga_id, fktb03tb02_entidade_id) VALUES
(1, 15, 6, 'Procuramos um voluntário criativo que saiba tratar imagens e padronizá-las, criando identidade de marca para divulgação da Orientavida em mídias sociais!',10,'Webdesigner',5,3),
(2, 5,10, 'Disponibilidade de orientar os atendidos nas técnicas de pedreiro assentador e azulejista, com o apoio de funcionários da ONG.',12, 'Pedreiro assentador e azulejista',8,4),
(3, 12, 13, 'Precisamos de um/uma fisioterapeuta para trabalhar com os idosos do Hotelzinho',12, 'Fisioterapeuta da terceira idade',6,5);


INSERT INTO tb04_vaga_voluntario (tb04_data_fim, tb04_data_inicio, tb04_quantidade, tb04_tipo_vaga, fktb04tb03_vaga_id, voluntario_id) VALUES
(null,'09/06/2020',2,1,1,1),
('02/03/2021','10/06/2020', 6,1,3,2);





