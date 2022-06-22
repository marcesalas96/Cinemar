
use cinemar;
create table usuario(
id_usuario int auto_increment primary key,
correo varchar(25) not null,
contraseña varchar(20) not null,
nombre varchar(25) not null,
apellido varchar(25) not null,
edad int not null,
tipo enum('user','admin')
);
create table cliente(
tarjeta boolean,
id_usuario int,
foreign key(id_usuario) references usuario(id_usuario)
);
create table administrador(
admin varchar(25) not null,
id_usuario int,
foreign key(id_usuario) references usuario(id_usuario)
);

