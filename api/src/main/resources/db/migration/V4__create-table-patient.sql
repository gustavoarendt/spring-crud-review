create table patient(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(20) not null,
    cpf varchar(6) not null unique,
    place varchar(100) not null,
    region varchar(100) not null,
    zipcode varchar(9) not null,
    details varchar(100),
    number varchar(20),
    state char(2) not null,
    city varchar(100) not null,
    active tinyint,

    primary key(id)
);