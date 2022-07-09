create database cinemar_F character set utf8;
USE cinemar_F;

create table Usuario(
idUsuario int not null primary key auto_increment,
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
idReservas int not null primary key auto_increment,
fechaReserva varchar(45) not null,
horaReserva varchar(45) not null,
horaPelicula time not null,
fechaPelicula varchar(45) not null,
nroSala int not null,
posButaca int not null,
nombrePelicula varchar(50) not null,
nombreCliente varchar(45) not null,
apellidoCliente varchar(45) not null,
precioTotal double not null,

idHistorial int not null,
foreign key (idHistorial) references Historial(idHistorial)
);

create table Historial(
idHistorial int not null primary key auto_increment,
Reservas_idReservas int not null,
Descuento_idDescuento int not null,
foreign key(Reservas_idReservas) references Reservas(idReservas),
foreign key(Descuento_idDescuento)references Descuento(idDescuento)
);
 
 create table Cliente(
 idCliente int auto_increment not null primary key,
 tarjeta tinyint not null,
 Usuario_IdUsuario int not null,
 foreign key(Usuario_idUsuario)references Usuario(idUsuario),
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