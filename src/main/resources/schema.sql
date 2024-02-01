create table if not exists users (
    id serial primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(50) not null
);