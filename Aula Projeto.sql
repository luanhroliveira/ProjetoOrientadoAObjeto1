CREATE TABLE Cidade(
	idCidade INTEGER IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Nome VARCHAR(32) NOT NULL,
	Descricao VARCHAR(32)
);

INSERT INTO Cidade
	(nome, descricao)
VALUES
	(?,?)

SELECT 
	c.idCidade as 'C�d. Cidade',
	c.nome as 'Nome',
	c.descricao as 'Descri��o'
FROM
	CIDADE c
WHERE idCidade = ?

DELETE FROM
	Cidade
WHERE
	idCidade = ?