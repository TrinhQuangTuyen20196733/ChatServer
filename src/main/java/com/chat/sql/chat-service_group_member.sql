-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chat-service
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_member` (
  `group_member_id` int NOT NULL AUTO_INCREMENT,
  `join_time` datetime(6) DEFAULT NULL,
  `last_seen` datetime(6) DEFAULT NULL,
  `left_time` datetime(6) DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  `conversation_id` int DEFAULT NULL,
  PRIMARY KEY (`group_member_id`),
  KEY `FKqbnnx666uxh38dff4uanvgw88` (`contact_id`),
  KEY `FKh9ojh95jsbqa9k94mk9sjs64k` (`conversation_id`),
  CONSTRAINT `FKh9ojh95jsbqa9k94mk9sjs64k` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`),
  CONSTRAINT `FKqbnnx666uxh38dff4uanvgw88` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'2023-11-14 20:37:03.000000',NULL,NULL,1,2),(2,'2023-11-14 20:37:03.000000',NULL,NULL,2,2),(3,'2023-11-14 20:37:05.000000',NULL,NULL,1,1),(5,'2023-11-14 20:37:04.000000',NULL,NULL,3,1),(6,'2023-11-14 20:37:02.000000',NULL,NULL,1,3),(10,'2023-11-14 20:37:04.000000',NULL,NULL,2,1),(11,'2023-11-14 20:37:02.000000',NULL,NULL,3,3),(12,'2023-11-14 20:37:01.000000',NULL,NULL,1,6),(13,'2023-11-14 20:37:00.000000',NULL,NULL,5,6),(14,'2023-11-11 01:20:22.246000',NULL,NULL,1,7),(15,'2023-11-11 01:20:56.099000',NULL,NULL,2,7);
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-14 20:40:57
