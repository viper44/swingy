drop database swingy;
create database swingy;
use swingy;
drop table if exists armor ;
drop table if exists helmet ;
drop table if exists weapon;
drop table if exists coordinates;
drop table if exists hero;


create table hero_class(
  id int not null auto_increment primary key,
  hero_type varchar(20)
);

INSERT into hero_class (hero_type)
values("SPELLHOWLER"),
      ("TREASUREHUNTER"),
      ("DARKKNIGHT");

create table hero(
  id int not null  AUTO_INCREMENT primary key,
  name varchar(14),
  damage int not null,
  defense int not null,
  hp int not null,
  level int not null,
  exp int not null,
  hero_class_id int not null DEFAULT 1,
  FOREIGN KEY (hero_class_id) references hero_class(id)
);

create table armor(
  id int not null auto_increment primary key,
  defense int not null
);

create table weapon(
  id int not null auto_increment primary key,
  damage int not null
);

create table helmet(
  id int not null auto_increment primary key,
  hp int not null
);

create table coordinates(
  id int not null auto_increment primary key,
  x int not null,
  y int not null
);

create table weapon_type(
  id int not null
)