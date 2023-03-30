create table atendimento(
    id bigint not null auto_increment unique,
    paciente varchar(255) not null,
    data varchar(255) not null,
    horario varchar(40) not null,
    descricao varchar(255) not null,
    especialidade varchar(255) not null,
    primary key(id)
);