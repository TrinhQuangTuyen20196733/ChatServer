CREATE DATABASE  IF NOT EXISTS `chat-service` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chat-service`;
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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `avatar_location` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'E:\\Project-3\\websocket\\quynh','quynh@gmail.com','Quynh','Pham','123'),(2,'E:\\Project-3\\websocket\\tuyen','tuyen@gmail.com','Tuyen','Trinh','123'),(3,'E:\\Project-3\\websocket\\minh','minh@gmail.com','Minh','Nguyen','123'),(5,'E:\\Project-3\\websocket\\doanh','doanh@gmail.com','Doanh','Tran','123'),(8,'E:\\Project-3\\websocket\\duc','duc@gmail.com','Duc','Cao','123');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `conversation_id` int NOT NULL AUTO_INCREMENT,
  `conversation_name` varchar(255) DEFAULT NULL,
  `conversation_type` tinyint DEFAULT NULL,
  PRIMARY KEY (`conversation_id`),
  CONSTRAINT `conversation_chk_1` CHECK ((`conversation_type` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'Nhóm QTM',1),(3,'Nhóm QM',0),(5,'Nhóm MT',0),(6,'Nhóm QD',0),(7,'Nhóm QTMD',1),(8,'Nhóm QT',0);
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (3,'2023-11-14 20:37:05.000000',NULL,NULL,1,1),(5,'2023-11-14 20:37:04.000000',NULL,NULL,3,1),(6,'2023-11-14 20:37:02.000000',NULL,NULL,1,3),(10,'2023-11-14 20:37:04.000000',NULL,NULL,2,1),(11,'2023-11-14 20:37:02.000000',NULL,NULL,3,3),(12,'2023-11-14 20:37:01.000000',NULL,NULL,1,6),(14,'2023-11-11 01:20:22.246000',NULL,NULL,1,7),(15,'2023-11-11 01:20:56.099000',NULL,NULL,2,7),(16,'2023-11-14 22:32:49.566000',NULL,NULL,2,7),(17,'2023-11-17 09:02:11.042000',NULL,NULL,5,7),(18,NULL,NULL,NULL,1,8),(19,NULL,NULL,NULL,2,8);
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (3,'2023-11-10 14:47:35.000000',NULL,'Chào Tuyến và Minh',0,0,1,NULL,1),(4,'2023-11-10 14:47:36.000000',NULL,'Chào Quỳnh và Minh',0,0,2,NULL,1),(5,'2023-11-10 14:47:37.000000',NULL,'Chào Quỳnh và Tuyến',0,0,3,NULL,1),(6,'2023-11-10 14:47:42.000001',NULL,'Alo, ranh khong Tuyen',0,0,1,NULL,1),(9,'2023-11-11 14:10:03.692384',NULL,'Chào Doanh, mình là Quỳnh',0,6,1,NULL,6),(10,'2023-11-11 14:10:31.350772',NULL,'Chào Quỳnh, mình là Doanh',0,1,6,NULL,6),(14,'2023-11-17 10:00:07.000410',NULL,'Alo Tuyến',0,2,1,NULL,8),(15,'2023-11-17 10:00:20.291264',NULL,'Ơi, ông bảo gì t đấy?',0,1,2,NULL,8),(16,'2023-12-02 09:26:47.482397',NULL,'xin chào',0,2,1,NULL,3),(17,'2023-12-02 09:26:52.136505',NULL,'chào bạn',0,1,2,NULL,3),(18,'2023-12-02 09:38:11.446269',NULL,'xin chào',0,2,1,NULL,1),(19,'2023-12-02 09:38:29.815591',NULL,'chào bạn tôi là 2',0,1,2,NULL,1),(20,'2023-12-02 09:38:50.790164',NULL,'tôi là 1',0,2,1,NULL,1),(21,'2023-12-02 09:42:42.320437',NULL,'xin chào tôi là 3',0,0,3,NULL,1);
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

-- Dump completed on 2023-12-09 15:36:27
