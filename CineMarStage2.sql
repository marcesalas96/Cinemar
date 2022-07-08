create database cinemar_F character set utf8;
USE cinemar_F;

create table Usuario(
idUsuario int not null primary key,
nombre varchar(45) not null,
apellido varchar(45) not null,
edad int not null,
correo varchar(45) not null,
contraseña varchar(18) not null
);

create table Administracion(
idAdminstracion int not null primary key,
Usuario_idUsuario int not null,
foreign key(Usuario_idUsuario)references Usuario(idUsuario)
);

create table Descuento(
idDescuento int not null primary key,
porcentajeDesc int not null,
dia varchar(9) not null
);

create table Reservas(
idReservas int not null primary key,
horario time not null,
Descuento_idDescuento int not null,
foreign key(Descuento_idDescuento)references Descuento(idDescuento)
);

create table Historial(
idHistorial int not null primary key,
cant_reserva int not null,
Reservas_idReservas int not null,
Descuento_idDescuento int not null,
foreign key(Reservas_idReservas) references Reservas(idReservas),
foreign key(Descuento_idDescuento)references Descuento(idDescuento)
);
 
 create table Cliente(
 idCliente int auto_increment not null primary key,
 tarjeta tinyint not null,
 Usuario_IdUsuario int not null,
 Reservas_idReservas int not null,
 Historial_idHistorial int not null,
 foreign key(Usuario_idUsuario)references Usuario(idUsuario),
 foreign key(Reservas_idReservas)references Reservas(idReservas),
 foreign key(Historial_idHistorial)references Historial(idHistorial)
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
id int not null auto_increment primary key,
asiento int,
nro_Sala int,
ocupado boolean,
foreign key(nro_Sala) references Sala(nro_Sala)
);