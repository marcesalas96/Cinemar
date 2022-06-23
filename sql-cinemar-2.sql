create database cinemar_;
use cinemar_;
create table formato_p(
 id_formato int auto_increment primary key,
 nombre_formato varchar(2),
 precio_formato double
 );

create table pelicula (
id_pelicula int auto_increment primary key ,
titulo_pelicula varchar (30) ,
duracion_pelicula time , 
categoria_pelicula varchar (10), 
sinopsis_pelicula varchar(150),
id_formato int, 
foreign key (id_formato)references formato_p (id_formato)
);

create table tipo_usuario(
id_tipo_usuario int auto_increment primary key,
nombre_tipo varchar(10),
rol varchar (40)
);

create table usuario(
id_usuario int auto_increment primary key,
nombre_usuario varchar(15),
apellido_usuario varchar(30),
email_usuario varchar(20),
telefono_usuario int,
contrasenia_usuario varchar(8),
id_tipo_usuario int,
foreign key (id_tipo_usuario) references tipo_usuario (id_tipo_usuario)
);

create table descuentos(
id_desc int auto_increment primary key,
dia_desc varchar (10),
porcentaje_desc int
);

create table reserva (
id_reserva int auto_increment primary key,
fecha_reserva date, 
total double, 
id_desc int,
id_pelicula int,
id_usuario int, 
foreign key (id_desc) references descuentos(id_desc),
foreign key (id_usuario) references usuario(id_usuario),
foreign key (id_pelicula) references pelicula (id_pelicula)
);

create table sala(
id_sala  int primary key,
nombre_sala varchar (10),
capacidad_sala int,
id_pelicula int,
descripcion varchar(50),
horario time,
dia varchar(10),
foreign key (id_pelicula)references pelicula (id_pelicula)
);

create table butaca(
id_butaca int primary key,
fila_butaca char , columna int,
id_sala int , foreign key (id_sala) references sala(id_sala)
);






