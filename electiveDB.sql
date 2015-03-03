SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ElectiveDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ElectiveDB` ;

-- -----------------------------------------------------
-- Table `ElectiveDB`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`user_type` (
  `type` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ElectiveDB`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`users` (
  `id` INT NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  `surname` VARCHAR(15) NOT NULL,
  `middlename` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `user_type_type` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_user_type1_idx` (`user_type_type` ASC),
  CONSTRAINT `fk_users_user_type1`
    FOREIGN KEY (`user_type_type`)
    REFERENCES `ElectiveDB`.`user_type` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ElectiveDB`.`CoursesList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`CoursesList` (
  `name` VARCHAR(15) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`name`),
  INDEX `fk_CoursesList_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_CoursesList_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `ElectiveDB`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ElectiveDB`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`courses` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  `users_id` INT NOT NULL,
  `isArchive` TINYINT(1) NOT NULL DEFAULT false,
  `isConfirm` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id`),
  INDEX `fk_courses_users1_idx` (`users_id` ASC),
  INDEX `fk_courses_CoursesList1_idx` (`name` ASC),
  CONSTRAINT `fk_courses_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `ElectiveDB`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_courses_CoursesList1`
    FOREIGN KEY (`name`)
    REFERENCES `ElectiveDB`.`CoursesList` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ElectiveDB`.`archives`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`archives` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `courses_id` SMALLINT NOT NULL,
  `mark` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_archives_courses1_idx` (`courses_id` ASC),
  CONSTRAINT `fk_archives_courses1`
    FOREIGN KEY (`courses_id`)
    REFERENCES `ElectiveDB`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ElectiveDB`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ElectiveDB`.`login` (
  `login` VARCHAR(15) NOT NULL,
  `password` VARCHAR(12) NOT NULL,
  `users_id` INT NOT NULL,
  `isWorking` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_login_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_login_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `ElectiveDB`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
