CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`idUsuario`));

CREATE TABLE IF NOT EXISTS `mydb`.`Administracion` (
  `idAdministracion` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idAdministracion`),
  INDEX `fk_Administracion_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Administracion_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`));
    
    CREATE TABLE IF NOT EXISTS `mydb`.`Cartelera` (
  `idCartelera` INT NOT NULL,
  PRIMARY KEY (`idCartelera`));

CREATE TABLE IF NOT EXISTS `mydb`.`Descuento` (
  `idDescuento` INT NOT NULL,
  `porcentajeDescuento` int not null,
  `fechas` INT(7) NOT NULL,
  PRIMARY KEY (`idDescuento`));
  
  CREATE TABLE IF NOT EXISTS `mydb`.`Reservas` (
  `idReservas` INT NOT NULL,
  `horario` TIME NOT NULL,
  `Cartelera_idCartelera` INT NOT NULL,
  `Descuento_idDescuento` INT NOT NULL,
  PRIMARY KEY (`idReservas`),
  INDEX `fk_Reservas_Cartelera1_idx` (`Cartelera_idCartelera` ASC) VISIBLE,
  INDEX `fk_Reservas_Descuento1_idx` (`Descuento_idDescuento` ASC) VISIBLE,
  CONSTRAINT `fk_Reservas_Cartelera1`
    FOREIGN KEY (`Cartelera_idCartelera`)
    REFERENCES `mydb`.`Cartelera` (`idCartelera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservas_Descuento1`
    FOREIGN KEY (`Descuento_idDescuento`)
    REFERENCES `mydb`.`Descuento` (`idDescuento`));
    
    CREATE TABLE IF NOT EXISTS `mydb`.`Historial` (
  `idHistorial` INT NOT NULL,
  `cant_reserva` INT NOT NULL,
  `Reservas_idReservas` INT NOT NULL,
  `Descuento_idDescuento` INT NOT NULL,
  PRIMARY KEY (`idHistorial`),
  INDEX `fk_Historial_Reservas1_idx` (`Reservas_idReservas` ASC) VISIBLE,
  INDEX `fk_Historial_Descuento1_idx` (`Descuento_idDescuento` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Reservas1`
    FOREIGN KEY (`Reservas_idReservas`)
    REFERENCES `mydb`.`Reservas` (`idReservas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Descuento1`
    FOREIGN KEY (`Descuento_idDescuento`)
    REFERENCES `mydb`.`Descuento` (`idDescuento`));
    
    CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `tarjeta` TINYINT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Reservas_idReservas` INT NOT NULL,
  `Historial_idHistorial` INT NOT NULL,
  PRIMARY KEY (`idcliente`, `Usuario_idUsuario`),
  INDEX `fk_Cliente_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  INDEX `fk_Cliente_Reservas1_idx` (`Reservas_idReservas` ASC) VISIBLE,
  INDEX `fk_Cliente_Historial1_idx` (`Historial_idHistorial` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Reservas1`
    FOREIGN KEY (`Reservas_idReservas`)
    REFERENCES `mydb`.`Reservas` (`idReservas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Historial1`
    FOREIGN KEY (`Historial_idHistorial`)
    REFERENCES `mydb`.`Historial` (`idHistorial`));
    
    CREATE TABLE IF NOT EXISTS `mydb`.`Pelicula` (
  `idPelicula` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `productor` VARCHAR(45) NOT NULL,
  `duracion` TIME NOT NULL,
  `idioma` VARCHAR(45) NOT NULL,
  `horarios` TIME NOT NULL,
   `genero` varchar(45) not null,
  `tipo` enum('2D', '3D') NOT NULL,
  `Cartelera_idCartelera` INT NOT NULL,
  PRIMARY KEY (`idPelicula`),
  INDEX `fk_Pelicula_Cartelera1_idx` (`Cartelera_idCartelera` ASC) VISIBLE,
  CONSTRAINT `fk_Pelicula_Cartelera1`
    FOREIGN KEY (`Cartelera_idCartelera`)
    REFERENCES `mydb`.`Cartelera` (`idCartelera`));
    
    CREATE TABLE IF NOT EXISTS `mydb`.`Salas` (
  `idSalas` INT NOT NULL,
  `Pelicula_idPelicula` INT NOT NULL,
  PRIMARY KEY (`idSalas`),
  INDEX `fk_Salas_Pelicula1_idx` (`Pelicula_idPelicula` ASC) VISIBLE,
  CONSTRAINT `fk_Salas_Pelicula1`
    FOREIGN KEY (`Pelicula_idPelicula`)
    REFERENCES `mydb`.`Pelicula` (`idPelicula`));
    
    
CREATE TABLE IF NOT EXISTS `mydb`.`Butaca` (
  `idButaca` INT NOT NULL,
  `cantButacas` INT NOT NULL,
  `Salas_idSalas` INT NOT NULL,
  PRIMARY KEY (`idButaca`),
  INDEX `fk_Butaca_Salas1_idx` (`Salas_idSalas` ASC) VISIBLE,
  CONSTRAINT `fk_Butaca_Salas1`
    FOREIGN KEY (`Salas_idSalas`)
    REFERENCES `mydb`.`Salas` (`idSalas`));
    
        insert into cartelera (idCartelera) values
    (1),
    (2);
    select*from cartelera;
     insert into pelicula (idPelicula, titulo, productor, duracion, idioma, horarios, genero, tipo, Cartelera_idCartelera) values
    (1, "Avatar", "James Cameron", "02:40:00", "ingles", "23:00:00", "accion", "2D", 1),
    (2, "Vengadores:Endgame", "Anthony y Joe Russo", "03:02:00", "subtitulada", "17:00:00", "accion", "3D", 2);
    
   select*from pelicula;
   
   insert into usuario (idUsuario, nombre, apellido, edad, correo, contraseña) values
(1, "marcelo", "Salas", "26", "marcelo_capo@gmail.com", "bocamipassion"),
(2, "Daniel", "Calle", "24", "dani2600@gmail.com", "always24"),
(3, "Jesica", "Martinez", "29", "jesiyes@gmail.com", "sweetchild29"),
(4, "Jorge", "Reales", "29", "jorgefast@gmail.com", "taxiboy30"),
(5, "Martin", "Fradejas", "18", "martin_acdc@gmail.com", "livinon18");
    
 select*from usuario;

insert into administracion(idAdministracion, Usuario_idUsuario)values
(1, 3);

select*from administracion;

insert into descuento (idDescuento, porcentajeDescuento, fechas) values
(1, "20", "1"),
(2, "15", "2"),
(3, "10", "5");

select*from descuento;

insert into reservas(idReservas, horario, Cartelera_idCartelera, Descuento_idDescuento) values
(1, "23:00:00", 1, 2 ),
(2, "23:00:00", 1, 1),
(3, "17:00:00", 2, 1),
(4, "17:00:00", 2, 3);

select*from reservas;

insert into historial(idHistorial, cant_reserva, Reservas_idReservas, Descuento_idDescuento)values
(1, "8", 1, 2 ),
(2, "10", 2, 1),
(3, "12", 3, 1),
(4, "23", 4, 3);

select*from historial;

insert into cliente(idcliente, tarjeta, Usuario_idUsuario, Reservas_idReservas, Historial_idHistorial) values
(1, "1", 1, 2, 2 ),
(2, "0", 2, 3, 3),
(3, "1", 4, 4, 4 );

select*from cliente;

insert into salas(idSalas, Pelicula_idPelicula) values
(1, "2"),
(2, "1");

select*from salas;

insert into butaca(idButaca, cantButacas, Salas_idSalas) values
(1, "32", 1),
(2, "45", 2);

select*from butaca;

#quien es el admin?
select nombre, apellido, correo from usuario right join administracion on usuario.idUsuario=administracion.Usuario_idUsuario;

#si existe tal usuario?
select*from usuario where nombre="Juan";

#mostrar la cartelera disponible
select titulo, duracion, horarios from pelicula left join cartelera on pelicula.Cartelera_idCartelera=cartelera.idCartelera;

#ver tipo de descuento
select porcentajeDescuento from descuento;

#historial del cliente
select idcliente, Usuario_idUsuario from cliente inner join historial on cliente.Historial_idHistorial=historial.idHistorial;
select nombre, apellido, idcliente from usuario left join cliente on usuario.idUsuario=cliente.Usuario_idUsuario;

#cant de gente que asistio(revision)
select count(Cartelera_idCartelera) as total from reservas;


