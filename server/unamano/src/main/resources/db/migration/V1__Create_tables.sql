CREATE DATABASE IF NOT EXISTS `db_app` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_app`;

-- Volcando estructura para tabla db_app.address
CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `country` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `number` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `state` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `street` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;


CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `profile_picture` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `role` enum('ROLE_CLIENT','ROLE_WORKER') COLLATE latin1_spanish_ci DEFAULT NULL,
  `username` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `addresses_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7jfb6hl517os3r490klee35h7` (`addresses_id`),
  CONSTRAINT `FK53252plvatm5ofj7qscphscd` FOREIGN KEY (`addresses_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando estructura para tabla db_app.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `currency_type` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `date_updated` datetime(6) DEFAULT NULL,
  `task_description` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `status` enum('PUBLISHED','INPROGRESS','COMPLETED','REMOVED') COLLATE latin1_spanish_ci DEFAULT NULL,
  `task_date` datetime(6) DEFAULT NULL,
  `task_title` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dnap2f853jjqy5huf0apr628a` (`address_id`),
  KEY `FKcg8xvetvn1ja3e480i894nfnq` (`id_client`),
  CONSTRAINT `FKcg8xvetvn1ja3e480i894nfnq` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  CONSTRAINT `FKcv9u0m0fk3k2vojoj8g828rfv` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;




-- Volcando estructura para tabla db_app.occupations
CREATE TABLE IF NOT EXISTS `occupations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `occupation_name` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando estructura para tabla db_app.task_occupation
CREATE TABLE IF NOT EXISTS `task_occupation` (
  `task_id` bigint NOT NULL,
  `occupation_id` bigint NOT NULL,
  KEY `FKk0k49ec02h2irnqqgv4smuvln` (`occupation_id`),
  KEY `FK6usamj3wbmaipesgo8igon3xc` (`task_id`),
  CONSTRAINT `FK6usamj3wbmaipesgo8igon3xc` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKk0k49ec02h2irnqqgv4smuvln` FOREIGN KEY (`occupation_id`) REFERENCES `occupations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;


-- Volcando estructura para tabla db_app.ratings
CREATE TABLE IF NOT EXISTS `ratings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgd4laqdp3puv1bxgmh62tj8hn` (`user_id`),
  CONSTRAINT `FKgd4laqdp3puv1bxgmh62tj8hn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- La exportación de datos fue deseleccionada.


