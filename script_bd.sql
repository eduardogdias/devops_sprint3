CREATE TABLE TB_PATIO (
	id_patio INT IDENTITY(1,1) PRIMARY KEY,
    nome_patio VARCHAR(255) NOT NULL,
    endereco_patio VARCHAR(255) NOT NULL
);
 
CREATE TABLE TB_ZONA (
	id_zona INT IDENTITY(1,1) PRIMARY KEY,
    tipo_zona VARCHAR(255) NOT NULL,
    qtd_vaga_zona INT NOT NULL,
    fk_patio INT NOT NULL,
    CONSTRAINT fk_zona_patio FOREIGN KEY (fk_patio) REFERENCES TB_PATIO (id_patio) ON DELETE CASCADE
);
 
CREATE TABLE TB_MOTO (
	id_moto INT IDENTITY(1,1) PRIMARY KEY,
    placa_moto VARCHAR(7),
    chassi_moto VARCHAR(17),
    marca_moto VARCHAR(255),
    modelo_moto VARCHAR(20) NOT NULL
);
 
CREATE TABLE TB_SENSOR (
	id_sensor INT IDENTITY(1,1) PRIMARY KEY,
    localizacao_sensor VARCHAR(50) NOT NULL,
    data_sensor DATE NOT NULL,
    hora_sensor TIME NOT NULL
);
 
CREATE TABLE TB_HISTORICO (
	id_hist INT IDENTITY(1,1) PRIMARY KEY,
    posicao_hist INT NOT NULL,
    fk_moto INT NOT NULL,
    fk_zona INT NOT NULL,
    fk_sensor INT NOT NULL,
    CONSTRAINT fk_hist_moto FOREIGN KEY (fk_moto) REFERENCES TB_MOTO (id_moto),
    CONSTRAINT fk_hist_zona FOREIGN KEY (fk_zona) REFERENCES TB_ZONA (id_zona),
    CONSTRAINT fk_hist_sensor FOREIGN KEY (fk_sensor) REFERENCES TB_SENSOR (id_sensor)
);

CREATE TABLE tb_usuario (
	id INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);


-- Inserindo Registros
INSERT INTO TB_PATIO (nome_patio, endereco_patio) VALUES ('Mottu Space 3', 'Av. Butantan, 552');
 
INSERT INTO TB_ZONA (tipo_zona, qtd_vaga_zona, fk_patio) VALUES ('Reparo', 10, 1);
 
INSERT INTO TB_MOTO (placa_moto, chassi_moto, marca_moto, modelo_moto) VALUES ('1234567', '12345678901234567', 'Honda', 'Pop');
 
INSERT INTO TB_SENSOR (localizacao_sensor, data_sensor, hora_sensor) VALUES ('Entrada principal', '2025-05-23', '22:30:00');

INSERT INTO TB_HISTORICO (posicao_hist, fk_moto, fk_zona, fk_sensor) VALUES (2, 1, 1, 1);

INSERT INTO tb_usuario (email, senha, role) VALUES ('admin@mottu.com', '{noop}123456', 'ADMIN');
INSERT INTO tb_usuario (email, senha, role) VALUES ('user@mottu.com', '{noop}123456', 'USER');
