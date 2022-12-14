CREATE TABLE Rodovia(
    id_rodovia SERIAL NOT NULL,
    UF VARCHAR(2) NOT NULL,
    nome_rodovia VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Rodovia PRIMARY KEY (id_rodovia),
    CONSTRAINT UK_Rodovia UNIQUE (UF, nome_rodovia)
);

CREATE TABLE Veiculo(
    id_veiculo SERIAL NOT NULL,
    nome_veiculo VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Veiculo PRIMARY KEY (id_veiculo),
    CONSTRAINT UK_Veiculo UNIQUE (nome_veiculo)
);

CREATE TABLE Tipo_casualidade(
    id_tipo_casualidade SERIAL NOT NULL,
    nome_casualidade VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Tipo_casualidade PRIMARY KEY (id_tipo_casualidade),
    CONSTRAINT UK_Tipo_casualidade UNIQUE (nome_casualidade)
);

CREATE TABLE Trecho(
    id_trecho SERIAL NOT NULL,
    id_rodovia SERIAL NOT NULL,
    km_inicial FLOAT NOT NULL,
    km_final FLOAT NOT NULL,
    data_avaliacao DATE NOT NULL,

    icc FLOAT NOT NULL,
    icp FLOAT NOT NULL,
    icm FLOAT NOT NULL,

    CONSTRAINT PK_Trecho PRIMARY KEY (id_trecho),
    CONSTRAINT FK_Trecho_Rodovia FOREIGN KEY (id_rodovia) REFERENCES Rodovia(id_rodovia),
    CONSTRAINT UK_Trecho UNIQUE (id_rodovia, km_inicial, km_final, data_avaliacao)
);

CREATE TABLE Acidente_sc(

    id_acidente_sc SERIAL NOT NULL,
    id_trecho SERIAL NOT NULL,
    data_acidente DATE NOT NULL,
    horario TIME NOT NULL,
    km_acidente FLOAT NOT NULL,
    sentido VARCHAR(30) NOT NULL,
    tipo_acidente VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Acidente_sc PRIMARY KEY (id_acidente_sc),
    CONSTRAINT FK_Acidente_sc_Trecho FOREIGN KEY (id_trecho) REFERENCES Trecho(id_trecho),
    CONSTRAINT UK_Acidente_sc UNIQUE (id_trecho, data_acidente, horario, km_acidente, sentido, tipo_acidente)
);

CREATE TABLE Acidente_cc(

    id_acidente_cc SERIAL NOT NULL,
    id_trecho SERIAL NOT NULL,
    data_acidente DATE NOT NULL,
    horario TIME NOT NULL,
    km_acidente FLOAT NOT NULL,
    sentido VARCHAR(30) NOT NULL,
    tipo_acidente VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Acidente_cc PRIMARY KEY (id_acidente_cc),
    CONSTRAINT FK_Acidente_cc_Trecho FOREIGN KEY (id_trecho) REFERENCES Trecho(id_trecho),
    CONSTRAINT UK_Acidente_cc UNIQUE (id_trecho, data_acidente, horario, km_acidente, sentido, tipo_acidente)
);

CREATE TABLE Veiculo_Acidente_sc(
    id_acidente_sc SERIAL NOT NULL,
    id_veiculo SERIAL NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_Veiculo_Acidente_sc PRIMARY KEY (id_acidente_sc, id_veiculo),
    CONSTRAINT FK_Veiculo_Acidente_sc_Acidente_sc FOREIGN KEY (id_acidente_sc) REFERENCES Acidente_sc(id_acidente_sc),
    CONSTRAINT FK_Veiculo_Acidente_sc_Veiculo FOREIGN KEY (id_veiculo) REFERENCES Veiculo(id_veiculo)
);    
  


CREATE TABLE Veiculo_acidente_cc(
    id_acidente_cc SERIAL NOT NULL,
    id_veiculo SERIAL NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_Veiculo_acidente_cc PRIMARY KEY (id_acidente_cc, id_veiculo),
    CONSTRAINT FK_Veiculo_acidente_cc_Acidente_cc FOREIGN KEY (id_acidente_cc) REFERENCES Acidente_cc(id_acidente_cc),
    CONSTRAINT FK_Veiculo_acidente_cc_Veiculo FOREIGN KEY (id_veiculo) REFERENCES Veiculo(id_veiculo)

);


CREATE TABLE Casualidade_acidente(  
    id_acidente_cc SERIAL NOT NULL,
    id_tipo_casualidade SERIAL NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_Casualidade_acidente PRIMARY KEY (id_acidente_cc, id_tipo_casualidade),
    CONSTRAINT FK_Casualidade_acidente_Acidente_cc FOREIGN KEY (id_acidente_cc) REFERENCES Acidente_cc(id_acidente_cc),
    CONSTRAINT FK_Casualidade_acidente_Tipo_casualidade FOREIGN KEY (id_tipo_casualidade) REFERENCES Tipo_casualidade(id_tipo_casualidade)

);

CREATE TABLE Log_Cargas(
    id_log_cargas SERIAL NOT NULL,

    nome_arquivo VARCHAR(50) NOT NULL,
    tipo_arquivo VARCHAR(50) NOT NULL,
    tuplas_carregadas INT NOT NULL,

    horario_carga TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_Log_Cargas PRIMARY KEY (id_log_cargas),
    CONSTRAINT CK_Tuplas_Carregadas CHECK (tuplas_carregadas >= 0)


);
