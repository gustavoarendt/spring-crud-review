create table appointment(
    id bigint not null auto_increment,
    id_medic bigint not null,
    id_patient bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_appointment_id_medic foreign key(id_medic) references medic(id),
    constraint fk_appointment_id_patient foreign key(id_patient) references patient(id)
);