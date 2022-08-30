CREATE TABLE shopping.shopping (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
   rua VARCHAR(30) NOT NULL,
   bairro VARCHAR(30) NOT NULL,
   estado VARCHAR(30) NOT NULL,
   versao INT DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

INSERT INTO shopping.shopping(NOME, rua, bairro, estado)
VALUES('plaza', 'rua 1', 'nit', 'niteroi');

