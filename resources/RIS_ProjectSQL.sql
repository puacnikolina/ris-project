-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Role` (
  `idRole` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `Role_idRole` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_User_Role_idx` (`Role_idRole` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`Role_idRole`)
    REFERENCES `mydb`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Artist` (
  `idArtist` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idArtist`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `rating` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `releaseDate` DATETIME NOT NULL,
  `quantity` INT NOT NULL,
  `img` LONGBLOB NOT NULL,
  `Artist_idArtist` INT NOT NULL,
  `Category_idCategory` INT NOT NULL,
  PRIMARY KEY (`idProduct`),
  INDEX `fk_Product_Artist1_idx` (`Artist_idArtist` ASC) VISIBLE,
  INDEX `fk_Product_Category1_idx` (`Category_idCategory` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Artist1`
    FOREIGN KEY (`Artist_idArtist`)
    REFERENCES `mydb`.`Artist` (`idArtist`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Product_Category1`
    FOREIGN KEY (`Category_idCategory`)
    REFERENCES `mydb`.`Category` (`idCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Song`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Song` (
  `idSong` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `duration` VARCHAR(45) NOT NULL,
  `Product_idProduct` INT NOT NULL,
  PRIMARY KEY (`idSong`),
  INDEX `fk_Song_Product1_idx` (`Product_idProduct` ASC) VISIBLE,
  CONSTRAINT `fk_Song_Product1`
    FOREIGN KEY (`Product_idProduct`)
    REFERENCES `mydb`.`Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Wishlist` (
  `idWishlist` INT NOT NULL AUTO_INCREMENT,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idWishlist`),
  INDEX `fk_Wishlist_User1_idx` (`User_idUser` ASC) VISIBLE,
  UNIQUE INDEX `User_idUser_UNIQUE` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Wishlist_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `mydb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Wishlist_has_Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Wishlist_has_Product` (
  `Wishlist_idWishlist` INT NOT NULL,
  `Product_idProduct` INT NOT NULL,
  PRIMARY KEY (`Wishlist_idWishlist`, `Product_idProduct`),
  INDEX `fk_Wishlist_has_Product_Product1_idx` (`Product_idProduct` ASC) VISIBLE,
  INDEX `fk_Wishlist_has_Product_Wishlist1_idx` (`Wishlist_idWishlist` ASC) VISIBLE,
  CONSTRAINT `fk_Wishlist_has_Product_Wishlist1`
    FOREIGN KEY (`Wishlist_idWishlist`)
    REFERENCES `mydb`.`Wishlist` (`idWishlist`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Wishlist_has_Product_Product1`
    FOREIGN KEY (`Product_idProduct`)
    REFERENCES `mydb`.`Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Orders` (
  `idOrder` INT NOT NULL AUTO_INCREMENT,
  `totalPrice` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `orderDate` DATETIME NOT NULL,
  `deliveryDate` DATETIME NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Orders_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Orders_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `mydb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Orders_has_Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Orders_has_Product` (
  `Orders_idOrder` INT NOT NULL,
  `Product_idProduct` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`Orders_idOrder`, `Product_idProduct`),
  INDEX `fk_Orders_has_Product_Product1_idx` (`Product_idProduct` ASC) VISIBLE,
  INDEX `fk_Orders_has_Product_Orders1_idx` (`Orders_idOrder` ASC) VISIBLE,
  CONSTRAINT `fk_Orders_has_Product_Orders1`
    FOREIGN KEY (`Orders_idOrder`)
    REFERENCES `mydb`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orders_has_Product_Product1`
    FOREIGN KEY (`Product_idProduct`)
    REFERENCES `mydb`.`Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
