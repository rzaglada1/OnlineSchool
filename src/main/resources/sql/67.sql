create database homework67;


create table homework67.address
(
    id bigint auto_increment,
    street varchar(255),
    num_house varchar(6),
    app varchar(6),
    primary key (id)
);

create table homework67.customers
(
    id bigint auto_increment,
    name varchar(256),
    id_address bigint not null,
    primary key (id),
    constraint address_fk foreign key (id_address) references address (id)
);

create table homework67.methods
(
    id bigint auto_increment,
    payment_method varchar(256),
    primary key(id)
);

create table homework67.statuses
(
    id bigint auto_increment,
    order_status varchar(256),
    primary key(id)
);

create table homework67.pays_statuses
(
    id bigint auto_increment,
    id_method bigint not null,
    id_status bigint not null,
    primary key(id),
    foreign key (id_method) references methods (id),
    foreign key (id_status) references statuses (id)
);

create table homework67.products
(
    id bigint auto_increment,
    name varchar(256),
    primary key(id)
);


create table homework67.orders_history
(
    id bigint auto_increment,
    price bigint,
    id_customer bigint not null,
    id_pays_statuses bigint not null,
    primary key (id),
    foreign key (id_customer) references customers (id),
    foreign key (id_pays_statuses) references pays_statuses (id)
);


create table homework67.orders_products
(
    id bigint auto_increment,
    id_order bigint not null,
    id_product bigint not null,
    primary key(id),
    foreign key (id_order) references orders_history (id),
    foreign key (id_product) references products (id)
);

