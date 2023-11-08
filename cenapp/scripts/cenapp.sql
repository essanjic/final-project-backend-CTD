-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cenapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cenapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cenapp` DEFAULT CHARACTER SET utf8 ;
USE `cenapp` ;

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
-- Table `cenapp`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`category` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `description` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `url` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb3;


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
    INDEX `fk_city_country1_idx` (`country_id` ASC) VISIBLE,
    CONSTRAINT `FKrpd7j1p7yxr784adkx4pyepba`
    FOREIGN KEY (`country_id`)
    REFERENCES `cenapp`.`country` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`colaborator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`colaborator` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `name` VARCHAR(45) NOT NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `enabled` TINYINT NOT NULL,
    `role_id` INT NOT NULL,
    `admin_id` INT NOT NULL,
    `city_id` INT NOT NULL,
    `image_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`, `city_id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_colaborator_admin1_idx` (`admin_id` ASC) VISIBLE,
    INDEX `fk_colaborator_city1_idx` (`city_id` ASC) VISIBLE,
    INDEX `fk_colaborator_image1_idx` (`image_id` ASC) VISIBLE)
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
-- Table `cenapp`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`restaurant` (
                                                     `id` INT UNSIGNED NOT NULL,
                                                     `name` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `short_description` VARCHAR(120) NOT NULL,
    `address` VARCHAR(200) NOT NULL,
    `zone/street` VARCHAR(100) NULL DEFAULT NULL,
    `rating` INT NULL DEFAULT NULL,
    `admin_id` INT NOT NULL,
    `day_disponibility` JSON NOT NULL,
    `parking` TINYINT NOT NULL,
    `live_music` TINYINT NULL DEFAULT NULL,
    `events` TINYINT NULL DEFAULT NULL,
    `terraza` TINYINT NULL DEFAULT NULL,
    `active` BIT(1) NOT NULL,
    `area` VARCHAR(255) NULL DEFAULT NULL,
    `average_score` DOUBLE NULL DEFAULT NULL,
    `latitude` VARCHAR(255) NULL DEFAULT NULL,
    `longitude` VARCHAR(255) NULL DEFAULT NULL,
    `cancellation_policies` VARCHAR(255) NULL DEFAULT NULL,
    `hse_policies` VARCHAR(255) NULL DEFAULT NULL,
    `site_policies` VARCHAR(255) NULL DEFAULT NULL,
    `category_id` INT NULL DEFAULT NULL,
    `city_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_restaurant_admin1_idx` (`admin_id` ASC) VISIBLE,
    INDEX `FK8jj0ghjhpw9xiue5pkqxb67s7` (`category_id` ASC) VISIBLE,
    INDEX `FKl968d8d7966yymvsxtdsni1vw` (`city_id` ASC) VISIBLE,
    CONSTRAINT `FK8jj0ghjhpw9xiue5pkqxb67s7`
    FOREIGN KEY (`category_id`)
    REFERENCES `cenapp`.`category` (`id`),
    CONSTRAINT `FKl968d8d7966yymvsxtdsni1vw`
    FOREIGN KEY (`city_id`)
    REFERENCES `cenapp`.`city` (`id`))
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
    `restaurant_id` INT UNSIGNED NULL DEFAULT NULL,
    `url` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_image_restaurant1_idx` (`restaurant_id` ASC) VISIBLE,
    CONSTRAINT `FK1sx05gapcfuchki4jyoup6wrv`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `cenapp`.`restaurant` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`province_state_municipality`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`province_state_municipality` (
                                                                      `id` INT NOT NULL,
                                                                      `name` VARCHAR(100) NULL DEFAULT NULL,
    `country_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_province_country_idx` (`country_id` ASC) VISIBLE)
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
-- Table `cenapp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`user` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(45) NOT NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `enabled` TINYINT NOT NULL,
    `city_id` INT NULL DEFAULT NULL,
    `role_id` INT NULL DEFAULT NULL,
    `image_id` INT UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `fk_user_city1_idx` (`city_id` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_user_image1_idx` (`image_id` ASC) VISIBLE,
    CONSTRAINT `FK29eqyw0gxw5r4f1ommy11nd9i`
    FOREIGN KEY (`city_id`)
    REFERENCES `cenapp`.`city` (`id`),
    CONSTRAINT `FK9hpx11qlu8cqhrkjn0yor93h`
    FOREIGN KEY (`image_id`)
    REFERENCES `cenapp`.`image` (`id`),
    CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy`
    FOREIGN KEY (`role_id`)
    REFERENCES `cenapp`.`role` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
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
                                                      `check_in_date` DATE NULL DEFAULT NULL,
                                                      `checkout_date` DATE NULL DEFAULT NULL,
                                                      `comments` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `user_id`),
    UNIQUE INDEX `idfood_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_reservation_restaurant1_idx` (`restaurant_id` ASC) VISIBLE,
    INDEX `fk_reservation_food1_idx` (`food_id` ASC) VISIBLE,
    INDEX `fk_reservation_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKbna4xjm3tqln2j6kda3fl2yiy`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `cenapp`.`restaurant` (`id`),
    CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg`
    FOREIGN KEY (`user_id`)
    REFERENCES `cenapp`.`user` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_es_trad_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cenapp`.`spec`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`spec` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `icon` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


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
-- Table `cenapp`.`table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`table` (
                                                `id` INT UNSIGNED NOT NULL,
                                                `name` VARCHAR(45) NULL DEFAULT NULL,
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
-- Table `cenapp`.`table_spec`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cenapp`.`table_spec` (
                                                     `id_table` INT NOT NULL,
                                                     `id_spec` INT NOT NULL,
                                                     INDEX `FK2887b3gmnnutm5td028t2n5ex` (`id_spec` ASC) VISIBLE,
    CONSTRAINT `FK2887b3gmnnutm5td028t2n5ex`
    FOREIGN KEY (`id_spec`)
    REFERENCES `cenapp`.`spec` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
