create table teste(
	id integer,
    descricao text,
    constraint pk Primary Key(id)
);

INSERT INTO teste(id, descricao)
VALUES (1, "Rio de Janaeio"),
	   (2, "Sao Paulo");