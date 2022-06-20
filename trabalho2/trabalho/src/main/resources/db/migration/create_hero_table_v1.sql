create table if not exists Hero(
id integer not null auto_increment, personName varchar(50) not null, heroName varchar(100) not null, teamName varchar(100) not null, primarykey(id)
);