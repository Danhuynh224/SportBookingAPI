-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: booking_app
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` bigint NOT NULL AUTO_INCREMENT,
  `booking_date` date NOT NULL,
  `end_time` time(6) NOT NULL,
  `start_time` time(6) NOT NULL,
  `status` enum('CANCELLED','CONFIRMED','PENDING') NOT NULL,
  `total_price` decimal(38,2) NOT NULL,
  `sub_facility_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FK3wtlhxrey5p0aql92611a350w` (`sub_facility_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FK3wtlhxrey5p0aql92611a350w` FOREIGN KEY (`sub_facility_id`) REFERENCES `sub_facility` (`sub_facility_id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2025-03-01','10:00:00.000000','08:00:00.000000','PENDING',100000.00,1,3),(2,'2025-03-02','11:00:00.000000','09:00:00.000000','CONFIRMED',300000.00,3,4),(3,'2025-03-03','16:00:00.000000','14:00:00.000000','CANCELLED',400000.00,4,3),(4,'2025-03-04','19:00:00.000000','17:00:00.000000','CONFIRMED',500000.00,5,4),(5,'2025-03-05','12:00:00.000000','10:00:00.000000','CONFIRMED',260000.00,6,3),(6,'2025-03-06','15:00:00.000000','13:00:00.000000','CONFIRMED',220000.00,7,4),(7,'2025-03-07','18:00:00.000000','16:00:00.000000','PENDING',280000.00,8,3),(8,'2025-03-07','15:00:00.000000','14:00:00.000000','CONFIRMED',150000.00,18,4),(9,'2025-03-07','19:00:00.000000','17:00:00.000000','CONFIRMED',300000.00,19,3),(10,'2025-03-07','18:00:00.000000','17:00:00.000000','CONFIRMED',150000.00,20,4);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_type`
--

DROP TABLE IF EXISTS `facility_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility_type` (
  `facility_type_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`facility_type_id`),
  UNIQUE KEY `UKd2o6ftcro2k532kvg8rp5k6qc` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_type`
--

LOCK TABLES `facility_type` WRITE;
/*!40000 ALTER TABLE `facility_type` DISABLE KEYS */;
INSERT INTO `facility_type` VALUES (4,'Bóng đá'),(3,'Bóng rổ'),(1,'Cầu lông'),(5,'Pickleball'),(2,'Tennis');
/*!40000 ALTER TABLE `facility_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) NOT NULL,
  `payment_date` date NOT NULL,
  `payment_method` enum('BANK_TRANSFER','CASH','CREDIT_CARD') NOT NULL,
  `status` enum('COMPLETED','FAILED','PENDING') NOT NULL,
  `booking_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `UKku02qy6369hn9uhy3n7jk9v6e` (`booking_id`),
  KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`),
  CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKqewrl4xrv9eiad6eab3aoja65` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,200000.00,'2025-03-01','CREDIT_CARD','COMPLETED',1,3),(2,300000.00,'2025-03-02','CASH','PENDING',2,4),(3,400000.00,'2025-03-03','BANK_TRANSFER','COMPLETED',3,3),(4,260000.00,'2025-03-05','BANK_TRANSFER','COMPLETED',5,4),(5,220000.00,'2025-03-06','CREDIT_CARD','COMPLETED',6,3),(6,280000.00,'2025-03-07','BANK_TRANSFER','PENDING',7,4);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` bigint NOT NULL AUTO_INCREMENT,
  `comment` text,
  `rating` int NOT NULL,
  `facility_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FKo92wms916mkn29yylbw6pr8x3` (`facility_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKo92wms916mkn29yylbw6pr8x3` FOREIGN KEY (`facility_id`) REFERENCES `sports_facility` (`sports_facility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'Sân rất đẹp và thoải mái!',5,1,3),(2,'Dịch vụ tốt nhưng giá hơi cao.',4,2,4),(3,'Sân ổn nhưng cần bảo trì thêm.',3,3,3),(4,'Rất hài lòng với trải nghiệm ở đây!',5,4,4),(5,'Pickleball ở đây chơi rất thích!',5,5,3),(6,'Không gian rộng rãi, rất phù hợp!',4,1,4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_facility`
--

DROP TABLE IF EXISTS `sports_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports_facility` (
  `sports_facility_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`sports_facility_id`),
  UNIQUE KEY `UKarrw3pocsyvgqa100yex2adcw` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_facility`
--

LOCK TABLES `sports_facility` WRITE;
/*!40000 ALTER TABLE `sports_facility` DISABLE KEYS */;
INSERT INTO `sports_facility` VALUES (1,'HCM, 1110 Phạm Văn Đồng P.Linh Đông Tp, Thủ Đức, Hồ Chí Minh 70000, Việt Nam','2025-02-25 10:00:00.000000','1742649804675_2019-03-24.jpg',10.84841524925496,106.74130928229619,'Tennis Cây Lộc Vừng'),(2,'158 Đ. số 17, Phường Linh Trung, Thủ Đức, Hồ Chí Minh 71308, Việt Nam','2025-02-25 11:00:00.000000','1742629916736_2023-09-24.jpg',10.864870010122146,106.78818257940658,'Sân cầu lông Aurora Sport'),(3,'10 Đ. Số 3, Phước Long A, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629938268_2024-05-02.jpg',10.835224103049228,106.76414735242275,'Galaxy badminton'),(4,'40 Đ. Số 11, Trường Thọ, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629962983_2024-08-07.jpg',10.8448260987901,106.75305479475074,'Sân cầu lông B-ZONE 11'),(5,'1 Đ. Võ Văn Ngân, Linh Chiểu, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629982790_2019-03-24.jpg',10.850662559501243,106.77313336962,'Sân bóng ĐH SPKT TP HCM'),(6,'127 Lê Văn Chí, Phường Linh Trung, Thủ Đức, Hồ Chí Minh 700000, Việt Nam','2025-03-22 21:53:04.409332','1742655184429_2024-01-12.jpg',10.86038480159787,106.77723479562422,'Sân Cầu Lông SHB Badminton');
/*!40000 ALTER TABLE `sports_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_facility`
--

DROP TABLE IF EXISTS `sub_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_facility` (
  `sub_facility_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `status` enum('AVAILABLE','ISBOOKED') NOT NULL,
  `facility_type_id` bigint NOT NULL,
  `sports_facility_id` bigint NOT NULL,
  PRIMARY KEY (`sub_facility_id`),
  KEY `FKai7iex0l8jvguxphunermsbh3` (`facility_type_id`),
  KEY `FKaxytax7fgqmlu6lj7lrwvbjhd` (`sports_facility_id`),
  CONSTRAINT `FKai7iex0l8jvguxphunermsbh3` FOREIGN KEY (`facility_type_id`) REFERENCES `facility_type` (`facility_type_id`),
  CONSTRAINT `FKaxytax7fgqmlu6lj7lrwvbjhd` FOREIGN KEY (`sports_facility_id`) REFERENCES `sports_facility` (`sports_facility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_facility`
--

LOCK TABLES `sub_facility` WRITE;
/*!40000 ALTER TABLE `sub_facility` DISABLE KEYS */;
INSERT INTO `sub_facility` VALUES (1,'Sân 1','AVAILABLE',2,1),(2,'Sân 2','AVAILABLE',2,1),(3,'Sân 3','ISBOOKED',2,1),(4,'Sân 1','AVAILABLE',1,2),(5,'Sân 2','ISBOOKED',1,2),(6,'Sân 3','ISBOOKED',1,2),(7,'Sân 4','ISBOOKED',1,2),(8,'Sân 5','AVAILABLE',1,2),(9,'Sân 1','AVAILABLE',1,3),(10,'Sân 2','AVAILABLE',1,3),(11,'Sân 3','AVAILABLE',1,3),(12,'Sân 4','AVAILABLE',1,3),(13,'Sân 1','AVAILABLE',1,4),(14,'Sân 2','AVAILABLE',1,4),(15,'Sân 3','AVAILABLE',1,4),(16,'Sân 4','AVAILABLE',1,4),(17,'Sân 5','AVAILABLE',1,4),(18,'Sân 1','AVAILABLE',4,5),(19,'Sân 2','AVAILABLE',4,5),(20,'Sân 3','AVAILABLE',4,5),(21,'Sân 4','AVAILABLE',4,5),(22,'Sân 5','AVAILABLE',4,5);
/*!40000 ALTER TABLE `sub_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_slot` (
  `time_slot_id` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(38,2) NOT NULL,
  `time_range` enum('DAYTIME','EVENING','MORNING','WEEKEND') NOT NULL,
  `facility_type_id` bigint NOT NULL,
  `sub_facility_id` bigint NOT NULL,
  PRIMARY KEY (`time_slot_id`),
  KEY `FKnm4t9mgp25i0ebrxc7btot272` (`facility_type_id`),
  KEY `FK5lm41n678bkuhcst95matgkm1` (`sub_facility_id`),
  CONSTRAINT `FK5lm41n678bkuhcst95matgkm1` FOREIGN KEY (`sub_facility_id`) REFERENCES `sub_facility` (`sub_facility_id`),
  CONSTRAINT `FKnm4t9mgp25i0ebrxc7btot272` FOREIGN KEY (`facility_type_id`) REFERENCES `facility_type` (`facility_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_slot`
--

LOCK TABLES `time_slot` WRITE;
/*!40000 ALTER TABLE `time_slot` DISABLE KEYS */;
INSERT INTO `time_slot` VALUES (1,10.00,'MORNING',1,1),(2,15.00,'DAYTIME',1,1),(3,20.00,'EVENING',1,1),(4,25.00,'WEEKEND',1,1);
/*!40000 ALTER TABLE `time_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `role` enum('OWNER','USER') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK589idila9li6a4arw1t8ht1gx` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a@example.com','Huỳnh Việt Đan','123','0987654321','OWNER'),(2,'b@example.com','Nguyễn Phan Minh Trí','123','0977123456','OWNER'),(3,'c@example.com','Nguyễn Hồng Sơn','123','0965234789','USER'),(4,'d@example.com','Huỳnh Tuấn Kiệt','123','0956347891','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-24 13:24:34
