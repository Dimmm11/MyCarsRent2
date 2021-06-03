CREATE DATABASE  IF NOT EXISTS `cars_rent_test` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 */;
USE `cars_rent_test`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cars_rent
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `allcars`
--

DROP TABLE IF EXISTS `allcars`;
/*!50001 DROP VIEW IF EXISTS `allcars`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `allcars` AS SELECT 
 1 AS `id`,
 1 AS `marque`,
 1 AS `car_class`,
 1 AS `model`,
 1 AS `price`,
 1 AS `car_status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `broken_cars`
--

DROP TABLE IF EXISTS `broken_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `broken_cars` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `car_id` int NOT NULL,
  `client_id` int NOT NULL,
  `penalty` decimal(10,2) NOT NULL DEFAULT '0.00',
  `comment` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_car_idx` (`car_id`),
  KEY `fk_client_idx` (`client_id`),
  KEY `fk_bk_order` (`order_id`),
  CONSTRAINT `fk_bk_car` FOREIGN KEY (`car_id`) REFERENCES `orders` (`car_id`),
  CONSTRAINT `fk_bk_client` FOREIGN KEY (`client_id`) REFERENCES `orders` (`client_id`),
  CONSTRAINT `fk_bk_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broken_cars`
--

LOCK TABLES `broken_cars` WRITE;
/*!40000 ALTER TABLE `broken_cars` DISABLE KEYS */;
/*!40000 ALTER TABLE `broken_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marque` varchar(45) NOT NULL,
  `car_class` enum('econom','middle','business') NOT NULL,
  `model` varchar(45) NOT NULL,
  `price` decimal(15,2) NOT NULL DEFAULT '0.00',
  `car_status` enum('FREE','ORDERED') NOT NULL DEFAULT 'FREE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` (marque, car_class, model, price, car_status)
VALUES
('Suzuki','econom','Swift',38.00,'ORDERED'),
('Skoda','econom','Fabia',39.00,'FREE'),
('Toyota','middle','Corolla',40.00,'ORDERED');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(155) NOT NULL,
  `passport` varchar(45) NOT NULL,
  `role_id` int NOT NULL DEFAULT '1',
  `status` enum('ACTIVE','BANNED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `passport_UNIQUE` (`passport`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_role_idx` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=552 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients`(name, password, passport, role_id, status)
VALUES
('Dima','$2a$10$xeu92kHNJLZZZyyneU.G6OenjuUj43TFFnv8cu.85epBMrgxf7ssK','AQ112233',3,'ACTIVE'),
('Vasya','$2a$10$xeu92kHNJLZZZyyneU.G6OenjuUj43TFFnv8cu.85epBMrgxf7ssK','QWeett',2,'ACTIVE'),
('Olya','$2a$10$xeu92kHNJLZZZyyneU.G6OenjuUj43TFFnv8cu.85epBMrgxf7ssK','Af2123',1,'ACTIVE');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deleted_clients`
--

DROP TABLE IF EXISTS `deleted_clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deleted_clients` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `passport` varchar(45) NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `finished_orders`
--

DROP TABLE IF EXISTS `finished_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finished_orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `client_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fo_client_idx` (`client_id`),
  CONSTRAINT `fk_clients_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finished_orders`
--

LOCK TABLES `finished_orders` WRITE;
/*!40000 ALTER TABLE `finished_orders` DISABLE KEYS */;
INSERT INTO `finished_orders` VALUES (6,59,8),(7,58,26);
/*!40000 ALTER TABLE `finished_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `car_id` int NOT NULL,
  `driver` enum('YES','NO') NOT NULL DEFAULT 'NO',
  `confirmed` enum('ON CHECK','CONFIRMED','REJECTED') NOT NULL DEFAULT 'ON CHECK',
  `term` int NOT NULL DEFAULT '1',
  `rent_cost` decimal(10,2) DEFAULT '0.00',
  `penalty` decimal(10,2) DEFAULT '0.00',
  `total_cost` decimal(10,2) DEFAULT '0.00',
  `comment` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_client_idx` (`client_id`),
  KEY `fk_car_idx` (`car_id`),
  CONSTRAINT `fk_car` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_client` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (client_id, car_id, driver, confirmed, term, rent_cost, penalty, total_cost, comment)
VALUES
(3,1,'YES','CONFIRMED',2,80.00,50.00,130.00,'поцарапано крыло'),
(3,3,'YES','CONFIRMED',10,700.00,50.00,750.00,'broken mirror');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` enum('admin','manager','client','guest') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (3,'admin'),(2,'manager'),(1,'client'),(0,'guest');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `allcars`
--

/*!50001 DROP VIEW IF EXISTS `allcars`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `allcars` AS select `cars`.`id` AS `id`,`cars`.`marque` AS `marque`,`cars`.`car_class` AS `car_class`,`cars`.`model` AS `model`,`cars`.`price` AS `price`,`cars`.`car_status` AS `car_status` from `cars` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-03 17:44:08
