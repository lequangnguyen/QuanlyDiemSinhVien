-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema quanlydiem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quanlydiem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quanlydiem` DEFAULT CHARACTER SET utf8 ;
USE `quanlydiem` ;

-- -----------------------------------------------------
-- Table `quanlydiem`.`monhoc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`monhoc` (
  `mamonhoc` VARCHAR(20) NOT NULL,
  `tenmonhoc` VARCHAR(100) NOT NULL,
  `id_mon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`mamonhoc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quanlydiem`.`khoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`khoa` (
  `makhoa` VARCHAR(20) NOT NULL,
  `tenkhoa` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`makhoa`),
  UNIQUE INDEX `tenkhoa_UNIQUE` (`tenkhoa` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quanlydiem`.`lophoc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`lophoc` (
  `malop` VARCHAR(20) NOT NULL,
  `tenlop` VARCHAR(100) NOT NULL,
  `makhoa` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`malop`),
  INDEX `fk_lophoc_tenkhoa_idx` (`makhoa` ASC),
  UNIQUE INDEX `tenlop_UNIQUE` (`tenlop` ASC),
  CONSTRAINT `fk_lophoc_tenkhoa`
    FOREIGN KEY (`makhoa`)
    REFERENCES `quanlydiem`.`khoa` (`makhoa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quanlydiem`.`sinhvien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`sinhvien` (
  `masinhvien` VARCHAR(20) NOT NULL,
  `tensinhvien` VARCHAR(100) NOT NULL,
  `malop` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`masinhvien`),
  INDEX `fk_sinhvien_malop_idx` (`malop` ASC),
  CONSTRAINT `fk_sinhvien_malop`
    FOREIGN KEY (`malop`)
    REFERENCES `quanlydiem`.`lophoc` (`malop`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quanlydiem`.`bangdiem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`bangdiem` (
  `masinhvien` VARCHAR(20) NOT NULL,
  `mamonhoc` VARCHAR(20) NOT NULL,
  `diemkiemtra1` FLOAT NULL DEFAULT NULL,
  `diemkiemtra2` FLOAT NULL DEFAULT NULL,
  `diemthi` FLOAT NULL DEFAULT NULL,
  `diemtongket` FLOAT NULL DEFAULT NULL,
  `xeploai` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`masinhvien`, `mamonhoc`),
  INDEX `fk_bangdiem_mamonhoc_idx` (`mamonhoc` ASC),
  CONSTRAINT `fk_bangdiem_mamonhoc`
    FOREIGN KEY (`mamonhoc`)
    REFERENCES `quanlydiem`.`monhoc` (`mamonhoc`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_bangdiem_masinhvien`
    FOREIGN KEY (`masinhvien`)
    REFERENCES `quanlydiem`.`sinhvien` (`masinhvien`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quanlydiem`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlydiem`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
