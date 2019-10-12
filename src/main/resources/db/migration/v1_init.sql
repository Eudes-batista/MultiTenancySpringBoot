create table if not exists product(
    id integer(11) not null AUTO_INCREMENT,
    name varchar(50) not null,
    price double(15,2) not null,
    currenty_inventory integer(5) not null default 0,
    primary key(id)
);