create table teste(
	id integer,
    descricao text,
    constraint pk Primary Key(id)
);

INSERT INTO teste(id, descricao)
VALUES (1, "Santa Catarina"),
	   (2, "Acre");