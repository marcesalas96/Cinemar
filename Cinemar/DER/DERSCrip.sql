-- MySQL Script generated by MySQL Workbench
-- sáb 25 jun 2022 13:01:13
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`administracion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`administracion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`administracion` (
  `administracion` VARCHAR(25) NOT NULL,
  `id_admin` INT NOT NULL,
  PRIMARY KEY (`administracion`, `id_admin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `correo` VARCHAR(25) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `cliente_id_usuario` INT NOT NULL,
  `administracion_administracion` VARCHAR(25) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`correo`, `cliente_id_usuario`, `administracion_administracion`, `id_usuario`),
  INDEX `fk_Usuario_administracion1_idx` (`administracion_administracion` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_administracion1`
    FOREIGN KEY (`administracion_administracion`)
    REFERENCES `mydb`.`administracion` (`administracion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cartelera`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cartelera` ;

CREATE TABLE IF NOT EXISTS `mydb`.`cartelera` (
  `pelicula_id` INT NOT NULL,
  `pelicula_sala_idsala` INT NOT NULL,
  `pelicula_sala_cartelera_sala` INT NOT NULL,
  `pelicula_sala_reserva_idsala` INT NOT NULL,
  `id_cartelera` INT NULL,
  PRIMARY KEY (`pelicula_id`, `pelicula_sala_idsala`, `pelicula_sala_cartelera_sala`, `pelicula_sala_reserva_idsala`, `id_cartelera`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reserva` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reserva` (
  `id_cartelera` INT NOT NULL,
  `horario` INT NOT NULL,
  `butaca` VARCHAR(45) NOT NULL,
  `pelicula` VARCHAR(45) NOT NULL,
  `cartelera_pelicula_titulo` INT NOT NULL,
  `cartelera_pelicula_sala_idsala` INT NOT NULL,
  `cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  `id_reserva` INT NOT NULL,
  PRIMARY KEY (`id_cartelera`, `cartelera_pelicula_titulo`, `cartelera_pelicula_sala_idsala`, `cartelera_pelicula_sala_cartelera_sala`, `cartelera_pelicula_sala_reserva_idsala`, `id_reserva`),
  INDEX `fk_reserva_cartelera1_idx` (`cartelera_pelicula_titulo` ASC, `cartelera_pelicula_sala_idsala` ASC, `cartelera_pelicula_sala_cartelera_sala` ASC, `cartelera_pelicula_sala_reserva_idsala` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_cartelera1`
    FOREIGN KEY (`cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala`)
    REFERENCES `mydb`.`cartelera` (`pelicula_id` , `pelicula_sala_idsala` , `pelicula_sala_cartelera_sala` , `pelicula_sala_reserva_idsala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cliente` ;

CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `id_usuario` INT NOT NULL,
  `tarjeta` TINYINT NOT NULL,
  `reserva_idsala` INT NOT NULL,
  `Usuario_correo` VARCHAR(25) NOT NULL,
  `Usuario_cliente_id_usuario` INT NOT NULL,
  `reserva_id_cartelera` INT NOT NULL,
  `reserva_cartelera_pelicula_titulo` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_idsala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  `reserva_id_reserva` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `reserva_idsala`, `Usuario_correo`, `Usuario_cliente_id_usuario`),
  INDEX `fk_cliente_Usuario1_idx` (`Usuario_correo` ASC, `Usuario_cliente_id_usuario` ASC) VISIBLE,
  INDEX `fk_cliente_reserva1_idx` (`reserva_id_cartelera` ASC, `reserva_cartelera_pelicula_titulo` ASC, `reserva_cartelera_pelicula_sala_idsala` ASC, `reserva_cartelera_pelicula_sala_cartelera_sala` ASC, `reserva_cartelera_pelicula_sala_reserva_idsala` ASC, `reserva_id_reserva` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_Usuario1`
    FOREIGN KEY (`Usuario_correo` , `Usuario_cliente_id_usuario`)
    REFERENCES `mydb`.`Usuario` (`correo` , `cliente_id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_reserva1`
    FOREIGN KEY (`reserva_id_cartelera` , `reserva_cartelera_pelicula_titulo` , `reserva_cartelera_pelicula_sala_idsala` , `reserva_cartelera_pelicula_sala_cartelera_sala` , `reserva_cartelera_pelicula_sala_reserva_idsala` , `reserva_id_reserva`)
    REFERENCES `mydb`.`reserva` (`id_cartelera` , `cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala` , `id_reserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pelicula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pelicula` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pelicula` (
  `titulo` VARCHAR(50) NOT NULL,
  `productor` VARCHAR(45) NOT NULL,
  `duracion` INT NOT NULL,
  `idioma` VARCHAR(45) NOT NULL,
  `horariosEmision` VARCHAR(45) NOT NULL,
  `tipodepelicula` VARCHAR(45) NOT NULL,
  `cartelera_pelicula_titulo` INT NOT NULL,
  `cartelera_pelicula_sala_idsala` INT NOT NULL,
  `cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  `pelicula_id` INT NOT NULL,
  PRIMARY KEY (`titulo`, `cartelera_pelicula_titulo`, `cartelera_pelicula_sala_idsala`, `cartelera_pelicula_sala_cartelera_sala`, `cartelera_pelicula_sala_reserva_idsala`, `pelicula_id`),
  INDEX `fk_pelicula_cartelera1_idx` (`cartelera_pelicula_titulo` ASC, `cartelera_pelicula_sala_idsala` ASC, `cartelera_pelicula_sala_cartelera_sala` ASC, `cartelera_pelicula_sala_reserva_idsala` ASC) VISIBLE,
  CONSTRAINT `fk_pelicula_cartelera1`
    FOREIGN KEY (`cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala`)
    REFERENCES `mydb`.`cartelera` (`pelicula_id` , `pelicula_sala_idsala` , `pelicula_sala_cartelera_sala` , `pelicula_sala_reserva_idsala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sala` ;

CREATE TABLE IF NOT EXISTS `mydb`.`sala` (
  `idsala` INT NOT NULL,
  `pelicula` VARCHAR(45) NOT NULL,
  `butaca` INT NOT NULL,
  `cartelera_sala` INT NOT NULL,
  `reserva_idsala` INT NOT NULL,
  `pelicula_id` INT NOT NULL,
  `pelicula_cartelera_sala` INT NOT NULL,
  `pelicula_cartelera_pelicula_titulo` INT NOT NULL,
  `pelicula_cartelera_pelicula_sala_idsala` INT NOT NULL,
  `pelicula_cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `pelicula_cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  PRIMARY KEY (`idsala`, `cartelera_sala`, `reserva_idsala`),
  INDEX `fk_sala_pelicula1_idx` (`pelicula_id` ASC, `pelicula_cartelera_sala` ASC, `pelicula_cartelera_pelicula_titulo` ASC, `pelicula_cartelera_pelicula_sala_idsala` ASC, `pelicula_cartelera_pelicula_sala_cartelera_sala` ASC, `pelicula_cartelera_pelicula_sala_reserva_idsala` ASC) VISIBLE,
  CONSTRAINT `fk_sala_pelicula1`
    FOREIGN KEY (`pelicula_id` , `pelicula_cartelera_pelicula_titulo` , `pelicula_cartelera_pelicula_sala_idsala` , `pelicula_cartelera_pelicula_sala_cartelera_sala` , `pelicula_cartelera_pelicula_sala_reserva_idsala`)
    REFERENCES `mydb`.`pelicula` (`titulo` , `cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`descuento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`descuento` ;

CREATE TABLE IF NOT EXISTS `mydb`.`descuento` (
  `fecha` DATE NOT NULL,
  `reserva_cartelera_sala` INT NOT NULL,
  `reserva_cartelera_sala1` INT NOT NULL,
  `id_descuentos` INT NOT NULL,
  `reserva_id_cartelera` INT NOT NULL,
  `reserva_cartelera_pelicula_titulo` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_idsala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  `reserva_id_reserva` INT NOT NULL,
  PRIMARY KEY (`fecha`, `id_descuentos`),
  INDEX `fk_descuento_reserva1_idx` (`reserva_id_cartelera` ASC, `reserva_cartelera_pelicula_titulo` ASC, `reserva_cartelera_pelicula_sala_idsala` ASC, `reserva_cartelera_pelicula_sala_cartelera_sala` ASC, `reserva_cartelera_pelicula_sala_reserva_idsala` ASC, `reserva_id_reserva` ASC) VISIBLE,
  CONSTRAINT `fk_descuento_reserva1`
    FOREIGN KEY (`reserva_id_cartelera` , `reserva_cartelera_pelicula_titulo` , `reserva_cartelera_pelicula_sala_idsala` , `reserva_cartelera_pelicula_sala_cartelera_sala` , `reserva_cartelera_pelicula_sala_reserva_idsala` , `reserva_id_reserva`)
    REFERENCES `mydb`.`reserva` (`id_cartelera` , `cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala` , `id_reserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`butaca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`butaca` ;

CREATE TABLE IF NOT EXISTS `mydb`.`butaca` (
  `idbutaca` INT NOT NULL,
  `sala_idsala` INT NOT NULL,
  `sala_cartelera_sala` INT NOT NULL,
  `sala_reserva_idsala` INT NOT NULL,
  PRIMARY KEY (`idbutaca`),
  INDEX `fk_butaca_sala1_idx` (`sala_idsala` ASC, `sala_cartelera_sala` ASC, `sala_reserva_idsala` ASC) VISIBLE,
  CONSTRAINT `fk_butaca_sala1`
    FOREIGN KEY (`sala_idsala` , `sala_cartelera_sala` , `sala_reserva_idsala`)
    REFERENCES `mydb`.`sala` (`idsala` , `cartelera_sala` , `reserva_idsala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`historial`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`historial` ;

CREATE TABLE IF NOT EXISTS `mydb`.`historial` (
  `id_historial` INT NOT NULL,
  `cant_reserva` INT NOT NULL,
  `cliente_id_usuario` INT NOT NULL,
  `cliente_reserva_idsala` INT NOT NULL,
  `cliente_Usuario_correo` VARCHAR(25) NOT NULL,
  `cliente_Usuario_cliente_id_usuario` INT NOT NULL,
  `descuento_fecha` DATE NOT NULL,
  `descuento_id_descuentos` INT NOT NULL,
  `reserva_id_cartelera` INT NOT NULL,
  `reserva_cartelera_pelicula_titulo` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_idsala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_cartelera_sala` INT NOT NULL,
  `reserva_cartelera_pelicula_sala_reserva_idsala` INT NOT NULL,
  `reserva_id_reserva` INT NOT NULL,
  PRIMARY KEY (`id_historial`),
  INDEX `fk_historial_cliente1_idx` (`cliente_id_usuario` ASC, `cliente_reserva_idsala` ASC, `cliente_Usuario_correo` ASC, `cliente_Usuario_cliente_id_usuario` ASC) VISIBLE,
  INDEX `fk_historial_descuento1_idx` (`descuento_fecha` ASC, `descuento_id_descuentos` ASC) VISIBLE,
  INDEX `fk_historial_reserva1_idx` (`reserva_id_cartelera` ASC, `reserva_cartelera_pelicula_titulo` ASC, `reserva_cartelera_pelicula_sala_idsala` ASC, `reserva_cartelera_pelicula_sala_cartelera_sala` ASC, `reserva_cartelera_pelicula_sala_reserva_idsala` ASC, `reserva_id_reserva` ASC) VISIBLE,
  CONSTRAINT `fk_historial_cliente1`
    FOREIGN KEY (`cliente_id_usuario` , `cliente_reserva_idsala` , `cliente_Usuario_correo` , `cliente_Usuario_cliente_id_usuario`)
    REFERENCES `mydb`.`cliente` (`id_usuario` , `reserva_idsala` , `Usuario_correo` , `Usuario_cliente_id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historial_descuento1`
    FOREIGN KEY (`descuento_fecha` , `descuento_id_descuentos`)
    REFERENCES `mydb`.`descuento` (`fecha` , `id_descuentos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historial_reserva1`
    FOREIGN KEY (`reserva_id_cartelera` , `reserva_cartelera_pelicula_titulo` , `reserva_cartelera_pelicula_sala_idsala` , `reserva_cartelera_pelicula_sala_cartelera_sala` , `reserva_cartelera_pelicula_sala_reserva_idsala` , `reserva_id_reserva`)
    REFERENCES `mydb`.`reserva` (`id_cartelera` , `cartelera_pelicula_titulo` , `cartelera_pelicula_sala_idsala` , `cartelera_pelicula_sala_cartelera_sala` , `cartelera_pelicula_sala_reserva_idsala` , `id_reserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
