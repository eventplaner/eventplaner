SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `eventplaning` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `eventplaning` ;

-- -----------------------------------------------------
-- Table `eventplaning`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventplaning`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `telnumber` VARCHAR(45) NULL,
  `passkey` VARCHAR(200) NULL,
  `active` BIT NOT NULL,
  `createdate` DATETIME NOT NULL,
  `changedate` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eventplaning`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventplaning`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `description` TEXT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NULL,
  `position_latitude` VARCHAR(100) NULL,
  `position_longitude` VARCHAR(100) NULL,
  `createdate` DATETIME NOT NULL,
  `changedate` DATETIME NOT NULL,
  `createuser_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`createuser_id` ASC),
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`createuser_id`)
    REFERENCES `eventplaning`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eventplaning`.`participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventplaning`.`participant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` BIT NULL,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_participant_user_idx` (`user_id` ASC),
  INDEX `fk_participant_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_participant_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `eventplaning`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participant_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventplaning`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
