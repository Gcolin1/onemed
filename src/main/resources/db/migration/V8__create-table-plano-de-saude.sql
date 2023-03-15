create table plano_de_saude(
    id bigint not null auto_increment unique,
    nome varchar(255) not null,
    telefone varchar(40) not null,
    cnpj varchar(30) not null unique,
    coberturas varchar(255) not null
)