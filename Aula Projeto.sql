CREATE TABLE Cidade(
	idCidade INTEGER IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Nome VARCHAR(32) NOT NULL,
	Descricao VARCHAR(32)
);

INSERT INTO Cidade
	(nome, descricao)
VALUES
	(?,?)
