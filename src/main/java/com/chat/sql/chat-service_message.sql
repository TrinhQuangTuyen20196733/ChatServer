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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `creation_time` datetime(6) DEFAULT NULL,
  `media_location` varchar(255) DEFAULT NULL,
  `message_content` varchar(255) DEFAULT NULL,
  `message_type` tinyint DEFAULT NULL,
  `receiver_id` int DEFAULT NULL,
  `sender_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `conversation_id` int DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK6yskk3hxw5sklwgi25y6d5u1l` (`conversation_id`),
  CONSTRAINT `FK6yskk3hxw5sklwgi25y6d5u1l` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`),
  CONSTRAINT `message_chk_1` CHECK ((`message_type` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2023-11-10 14:47:33.000000',NULL,'Xin chào Tuyến!',0,2,1,NULL,2),(2,'2023-11-10 14:47:34.000000',NULL,'Xin chào Quỳnh',0,1,2,NULL,2),(3,'2023-11-10 14:47:35.000000',NULL,'Chào Tuyến và Minh',0,0,1,NULL,1),(4,'2023-11-10 14:47:36.000000',NULL,'Chào Quỳnh và Minh',0,0,2,NULL,1),(5,'2023-11-10 14:47:37.000000',NULL,'Chào Quỳnh và Tuyến',0,0,3,NULL,1),(6,'2023-11-10 14:47:42.000001',NULL,'Alo, ranh khong Tuyen',0,0,1,NULL,1),(9,'2023-11-11 14:10:03.692384',NULL,'Chào Doanh, mình là Quỳnh',0,6,1,NULL,6),(10,'2023-11-11 14:10:31.350772',NULL,'Chào Quỳnh, mình là Doanh',0,1,6,NULL,6),(11,'2023-11-11 14:47:23.341388',NULL,'Tuyến làm microservice xong chưa',0,2,1,NULL,2),(12,'2023-11-11 14:47:51.768511',NULL,'T làm xong rồi, có gì xíu t gửi git cho đọc hiểu',0,1,2,NULL,2),(13,'2023-11-11 14:47:55.427225',NULL,'ok nhé',0,2,1,NULL,2);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
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
