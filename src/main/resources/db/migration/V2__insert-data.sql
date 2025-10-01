-- Inserindo Patio
INSERT INTO TB_PATIO (nome_patio, endereco_patio) VALUES ('Mottu Space 3', 'Av. Butantan, 552');
 
-- Inserindo Zona
INSERT INTO TB_ZONA (tipo_zona, qtd_vaga_zona, fk_patio) VALUES ('Reparo', 10, 1);
 
-- Inserindo Moto
INSERT INTO TB_MOTO (placa_moto, chassi_moto, marca_moto, modelo_moto) VALUES ('1234567', '12345678901234567', 'Honda', 'Pop');
 
-- Inserindo Sensor
INSERT INTO TB_SENSOR (localizacao_sensor, data_sensor, hora_sensor) VALUES ('Entrada principal', '2025-05-23', '22:30:00');

-- Inserindo Historico
INSERT INTO TB_HISTORICO (posicao_hist, fk_moto, fk_zona, fk_sensor) VALUES (2, 1, 1, 1);