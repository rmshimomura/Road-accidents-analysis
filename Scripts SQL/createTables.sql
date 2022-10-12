CREATE TABLE Rodovia(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Rodovia PRIMARY KEY (UF, nomeRodovia)
);

CREATE TABLE Veiculo(
    nomeVeiculo VARCHAR(50) NOT NULL,

    CONSTRAINT PK_Veiculo PRIMARY KEY (nomeVeiculo)
);

CREATE TABLE TipoDeCasualidade(
    nomeCasualidade VARCHAR(50) NOT NULL,

    CONSTRAINT PK_TipoDeCasualidade PRIMARY KEY (nomeCasualidade)
);

CREATE TABLE TrechoRodovia(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,

    icc FLOAT NOT NULL,
    icp FLOAT NOT NULL,
    icm FLOAT NOT NULL,

    CONSTRAINT PK_TrechoRodovia PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao),
    CONSTRAINT FK_TrechoRodovia_Rodovia FOREIGN KEY (UF, nomeRodovia) REFERENCES Rodovia(UF, nomeRodovia)
);

CREATE TABLE AcidenteSemCasualidades(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,
    dataAcidente DATE NOT NULL,
    horario TIME NOT NULL,
    kmDoAcidente FLOAT NOT NULL,
    sentido VARCHAR(10) NOT NULL,
    tipoDeAcidente VARCHAR(50) NOT NULL,

    CONSTRAINT PK_AcidenteSemCasualidades PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente), 
    CONSTRAINT FK_AcidenteSemCasualidades_TrechoRodovia FOREIGN KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao) REFERENCES TrechoRodovia(UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao)
);

CREATE TABLE AcidenteComCasualidades(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,
    dataAcidente DATE NOT NULL,
    horario TIME NOT NULL,
    kmDoAcidente FLOAT NOT NULL,
    sentido VARCHAR(10) NOT NULL,
    tipoDeAcidente VARCHAR(50) NOT NULL,

    CONSTRAINT PK_AcidenteComCasualidades PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente),
    CONSTRAINT FK_AcidenteComCasualidades_TrechoRodovia FOREIGN KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao) REFERENCES TrechoRodovia(UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao)
);

CREATE TABLE VeiculosNoAcidenteSemCasualidades(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,
    dataAcidente DATE NOT NULL,
    horario TIME NOT NULL,
    kmDoAcidente FLOAT NOT NULL,
    nomeVeiculo VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_VeiculosNoAcidenteSemCasualidades PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeVeiculo),
    CONSTRAINT FK_VeiculosNoAcidenteSemCasualidades_AcidenteSemCasualidades FOREIGN KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente) REFERENCES AcidenteSemCasualidades(UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente),
    CONSTRAINT FK_VeiculosNoAcidenteSemCasualidades_Veiculo FOREIGN KEY (nomeVeiculo) REFERENCES Veiculo(nomeVeiculo)
);    
  


CREATE TABLE VeiculosNoAcidenteComCasualidades(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,
    dataAcidente DATE NOT NULL,
    horario TIME NOT NULL,
    kmDoAcidente FLOAT NOT NULL,
    nomeVeiculo VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_VeiculosNoAcidenteComCasualidades PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeVeiculo),
    CONSTRAINT FK_VeiculosNoAcidenteComCasualidades_AcidenteComCasualidades FOREIGN KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente) REFERENCES AcidenteComCasualidades(UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente),
    CONSTRAINT FK_VeiculosNoAcidenteComCasualidades_Veiculo FOREIGN KEY (nomeVeiculo) REFERENCES Veiculo(nomeVeiculo)
);


CREATE TABLE AcidenteComCasualidadeEnvolveTipoCasualidade(
    UF VARCHAR(2) NOT NULL,
    nomeRodovia VARCHAR(50) NOT NULL,
    kmInicial FLOAT NOT NULL,
    kmFinal FLOAT NOT NULL,
    dataAvaliacao DATE NOT NULL,
    dataAcidente DATE NOT NULL,
    horario TIME NOT NULL,
    kmDoAcidente FLOAT NOT NULL,
    nomeCasualidade VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT PK_AcidenteComCasualidadeEnvolveTipoDeCasualidade PRIMARY KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade),
    CONSTRAINT FK_AcidenteComCasualidadeEnvolveTipoDeCasualidade_AcidenteComCasualidades FOREIGN KEY (UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente) REFERENCES AcidenteComCasualidades(UF, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente),
    CONSTRAINT FK_AcidenteComCasualidadeEnvolveTipoDeCasualidade_TipoDeCasualidade FOREIGN KEY (nomeCasualidade) REFERENCES TipoDeCasualidade(nomeCasualidade)
);

