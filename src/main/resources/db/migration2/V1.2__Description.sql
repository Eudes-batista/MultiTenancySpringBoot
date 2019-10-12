create table if not exists product(
    id integer(11) not null AUTO_INCREMENT,
    name varchar(50) not null,
    price double(15,2) not null,
    current_inventory integer(5) not null default 0,
    primary key(id)
);
create table if not exists movement_stock(
    document varchar(5) not null,
    date date not null,
    quantity int(11) not null,
    type varchar(11) not null,
    code_product int(11) not null,
    primary key(document)
);
