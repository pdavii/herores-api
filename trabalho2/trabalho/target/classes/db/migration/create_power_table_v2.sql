create table if not exists Power (
id integer not null auto_increment,
powerName varchar(50) not null,
strongPoint varchar(100) not null,
weakPoint varchar(100) not null,
hero_id integer not null,
primary key(id),
foreign key(hero_id) references Hero(id));