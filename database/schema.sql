-- -----------------------------------------------------
-- Schema match_pool
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `match_pool` DEFAULT CHARACTER SET utf8 ;
USE `match_pool` ;

-- -----------------------------------------------------
-- Table `match_pool`.`team`
-- -----------------------------------------------------
CREATE TABLE `match_pool`.`team` (
	`id` INTEGER auto_increment NOT NULL,
	`name` varchar(100) NOT NULL,
	CONSTRAINT `team_PK` PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci
AUTO_INCREMENT=1;


-- -----------------------------------------------------
-- Table `match_pool`.`match_info`
-- -----------------------------------------------------
CREATE TABLE `match_pool`.match_info (
	`id` INTEGER auto_increment NOT NULL,
	`home_team` INTEGER NOT NULL,
	`away_team` INTEGER NOT NULL,
	`home_score` INTEGER NOT NULL,
	`away_score` INTEGER NOT NULL,
	`start_time` DATETIME NOT NULL,
	`end_time` DATETIME NULL,
	status varchar(100) NOT NULL,
	CONSTRAINT match_PK PRIMARY KEY (`id`),
  CONSTRAINT `match_home_FK` FOREIGN KEY (`home_team`) REFERENCES `match_pool`.`team`(`id`),
  CONSTRAINT `match_away_FK` FOREIGN KEY (`away_team`) REFERENCES `match_pool`.`team`(`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

