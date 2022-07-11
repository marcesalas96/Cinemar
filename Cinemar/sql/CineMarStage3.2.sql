create database cinemar_Final character set utf8;
USE cinemar_Final;

create table Usuario(
correo varchar(45) not null primary key,
nombre varchar(45) not null,
apellido varchar(45) not null,
edad int not null,
contraseña varchar(18) not null,
admi boolean
);

create table Descuento(
idDescuento int not null primary key,
porcentaje1 double,
procentaje2 double,
porcentaje3 double,
precioPeli double
);
create table Cliente(
 nombreUsuario varchar(20) not null primary key,
 tarjeta tinyint not null,
 correo varchar(90) not null,
 foreign key(correo)references Usuario(correo)
 );
create table Reservas(
idReservas int not null auto_increment primary key,
fechaReserva varchar(45) not null,
horaReserva varchar(45) not null,
horaPelicula time not null,
fechaPelicula varchar(45) not null,
nroSala int not null,
posButaca int not null,
nombrePelicula varchar(50) not null,
precioTotal double not null,
nombreUsuario varchar(90) not null
);


 
   create table Sala(
nro_Sala int not null primary key,
capacidad int
);
    
   create table Pelicula(
id int not null primary key,
titulo varchar(90),
productor varchar(50),
duracion time,
idioma varchar(30),
horario time,
genero varchar(90),
tipo enum('2D','3D'),
foreign key (id) references Sala(nro_Sala)
);
    
    
create table Butaca(
pos int,
nro_Sala int,
ocupado boolean,
primary key(nro_Sala, pos),
foreign key(nro_Sala) references Sala(nro_Sala)
);


 select*from Reservas inner join Cliente on Cliente.idCliente=Cliente.idCliente;