create table atendimento(
    id bigint not null auto_increment unique,
    data varchar(255) not null,
    horario varchar(40) not null,
    descricao varchar(255) not null,
    paciente_id bigint not null,
    especialidade varchar(255) not null,
    primary key(id),
    foreign key(paciente_id) references pacientes(id)
);