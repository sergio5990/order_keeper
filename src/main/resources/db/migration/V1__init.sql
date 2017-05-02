DROP DATABASE IF EXISTS order_keeper_db;
CREATE DATABASE order_keeper_db;
create table company
(
  id int not null auto_increment
    primary key,
  name varchar(128) not null,
  address varchar(128) not null,
  phone varchar(128) not null,
  contact_user int not null,
  constraint company_id_uindex
  unique (id),
  constraint company_name_uindex
  unique (name)
)
;

create index company_user_id_fk
  on company (contact_user)
;

create table `order`
(
  id int not null auto_increment
    primary key,
  creator_id int not null,
  creation_date timestamp default CURRENT_TIMESTAMP not null,
  constraint order_id_uindex
  unique (id),
  constraint order_company_id_fk
  foreign key (creator_id) references order_keeper_db.company (id)
    on update cascade on delete cascade
)
;

create index order_company_id_fk
  on `order` (creator_id)
;

create table product
(
  id int not null auto_increment
    primary key,
  name varchar(128) not null,
  description text null,
  price float not null,
  quantity int not null,
  constraint product_id_uindex
  unique (id),
  constraint product_name_uindex
  unique (name)
)
;

create table product_ship_type
(
  id int not null auto_increment
    primary key,
  product_id int not null,
  ship_type_id int not null,
  constraint product_ship_type_id_uindex
  unique (id),
  constraint product_ship_type_product_id_fk
  foreign key (product_id) references order_keeper_db.product (id)
    on update cascade on delete cascade
)
;

create index product_ship_type_product_id_fk
  on product_ship_type (product_id)
;

create index product_ship_type_ship_type_id_fk
  on product_ship_type (ship_type_id)
;

create table products_in_order
(
  id int not null auto_increment
    primary key,
  order_id int not null,
  product_id int not null,
  ship_type_id int null,
  product_price float not null,
  product_quantity int not null,
  ship_price float null,
  constraint products_in_order_id_uindex
  unique (id),
  constraint products_in_order_order_id_fk
  foreign key (order_id) references order_keeper_db.`order` (id)
    on update cascade on delete cascade,
  constraint products_in_order_product_id_fk
  foreign key (product_id) references order_keeper_db.product (id)
    on update cascade on delete cascade
)
;

create index products_in_order_order_id_fk
  on products_in_order (order_id)
;

create index products_in_order_product_id_fk
  on products_in_order (product_id)
;

create index products_in_order_ship_type_id_fk
  on products_in_order (ship_type_id)
;

create table ship_type
(
  id int not null auto_increment
    primary key,
  name varchar(128) not null,
  cost float not null,
  constraint ship_type_id_uindex
  unique (id),
  constraint ship_type_name_uindex
  unique (name)
)
;

alter table product_ship_type
  add constraint product_ship_type_ship_type_id_fk
foreign key (ship_type_id) references order_keeper_db.ship_type (id)
  on update cascade on delete cascade
;

alter table products_in_order
  add constraint products_in_order_ship_type_id_fk
foreign key (ship_type_id) references order_keeper_db.ship_type (id)
  on update cascade on delete cascade
;

create table user
(
  id int not null auto_increment
    primary key,
  username varchar(128) not null,
  name varchar(128) not null,
  surname varchar(128) not null,
  email varchar(128) not null,
  password varchar(128) not null,
  constraint user_id_uindex
  unique (id),
  constraint user_username_uindex
  unique (username)
)
;

alter table company
  add constraint company_user_id_fk
foreign key (contact_user) references order_keeper_db.user (id)
  on update cascade on delete cascade
;

create table user_token
(
  id int not null auto_increment
    primary key,
  token varchar(128) not null,
  user_id int not null,
  expiration_date timestamp default CURRENT_TIMESTAMP not null,
  constraint user_token_id_uindex
  unique (id),
  constraint user_token_token_uindex
  unique (token),
  constraint user_token_user_id_uindex
  unique (user_id),
  constraint user_token_user_id_fk
  foreign key (user_id) references order_keeper_db.user (id)
    on update cascade on delete cascade
)
;

