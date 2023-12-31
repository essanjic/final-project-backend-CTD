-- MySQL Script generated by MySQL Workbench
-- Mon Nov  6 18:11:13 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cenapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cenapp
-- -----------------------------------------------------
CREATE SCHEMA `cenapp` DEFAULT CHARACTER SET utf8;
CREATE DATABASE `cenapp` DEFAULT CHARACTER SET utf8 ;
USE `cenapp` ;

-- -----------------------------------------------------
-- Table `cenapp`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`country` (
  `id` INT NOT NULL,
  `name` VARCHAR(70) NOT NULL,
  `abbreviation` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`city` (
  `id` INT NOT NULL,
  `name` VARCHAR(70) NOT NULL,
  `abbreviation` VARCHAR(3) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_country1_idx` (`country_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `city_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `image_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_city1_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_admin_image1_idx` (`image_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`restaurant` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `short_description` VARCHAR(120) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `zone/street` VARCHAR(100) NULL,
  `rating` INT NULL,
  `admin_id` INT NOT NULL,
  `day_disponibility` DATE NOT NULL,
  `parking` TINYINT NOT NULL,
  `live_music` TINYTEXT NULL,
  `events` TINYTEXT NULL,
  `terraza` TINYTEXT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_restaurant_admin1_idx` (`admin_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`image` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `url_image` VARCHAR(255) NOT NULL,
  `restaurant_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_image_restaurant1_idx` (`restaurant_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `image` VARCHAR(255) NULL,
  `city_id` INT NULL,
  `role_id` INT NULL,
  `image_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_city1_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_image1_idx` (`image_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`table` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `type` INT NOT NULL,
  `cuantity` INT NOT NULL,
  `restaurant_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_food_restaurant1_idx` (`restaurant_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`suggest_kitchen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`suggest_kitchen` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `short_description` VARCHAR(120) NOT NULL,
  `food_id` INT UNSIGNED NOT NULL,
  `restaurant_id` INT UNSIGNED NOT NULL,
  `image_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `food_id`),
  UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_category_food_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_suggest_kitchen_restaurant1_idx` (`restaurant_id` ASC) VISIBLE,
  INDEX `fk_suggest_kitchen_image1_idx` (`image_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`favorite` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `food_id` INT UNSIGNED NOT NULL,
  `suggest_kitchen_id` INT UNSIGNED NOT NULL,
  `user_id` INT NOT NULL,
  `restaurant_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_favorite_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_favorite_suggest_kitchen1_idx` (`suggest_kitchen_id` ASC) VISIBLE,
  INDEX `fk_favorite_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_favorite_restaurant1_idx` (`restaurant_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`reservation` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `restaurant_id` INT UNSIGNED NOT NULL,
  `food_id` INT UNSIGNED NOT NULL,
  `check_in` DATE NOT NULL,
  `arrival_time` TIME NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_reservation_restaurant1_idx` (`restaurant_id` ASC) VISIBLE,
  INDEX `fk_reservation_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_reservation_user1_idx` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_es_trad_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
