-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: booking_app
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id_post` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `summary` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `sports_facility_id` bigint NOT NULL,
  PRIMARY KEY (`id_post`),
  KEY `FKppmd3kauyk9d61fpo3i4mfwra` (`sports_facility_id`),
  CONSTRAINT `FKppmd3kauyk9d61fpo3i4mfwra` FOREIGN KEY (`sports_facility_id`) REFERENCES `sports_facility` (`sports_facility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Giải cầu lông toàn quốc 2024 diễn ra tại nhà thi đấu Phú Thọ, TP. Hồ Chí Minh, với sự tham gia của hơn 200 tay vợt đến từ khắp các tỉnh thành. \n Ngay từ những ngày thi đấu đầu tiên, khán giả đã được chứng kiến những màn rượt đuổi điểm số nghẹt thở, những pha đập cầu sấm sét và những chiến thuật thi đấu tinh tế. \n Đáng chú ý là sự góp mặt của các tuyển thủ quốc gia như Nguyễn Tiến Minh và Vũ Thị Trang, những người đã làm nên tên tuổi của cầu lông Việt Nam trên đấu trường quốc tế.\n \n Trận chung kết đơn nam là màn so tài kịch tính giữa hai tài năng trẻ. Với lối chơi tấn công mạnh mẽ và khả năng di chuyển linh hoạt, trận đấu diễn ra căng thẳng qua từng set đấu. \n Mỗi cú đánh đều thể hiện sự khéo léo và chính xác, mang lại cảm xúc mãnh liệt cho người hâm mộ. \n Chung cuộc, nhà vô địch mới đã lộ diện, mở ra một thế hệ vận động viên trẻ đầy triển vọng cho tương lai của cầu lông Việt Nam.','Nhà thi đấu Phú Thọ chứng kiến những trận đấu kịch tính giữa các tay vợt hàng đầu.','Giải Cầu Lông Toàn Quốc 2024 - Những Trận Cầu Đỉnh Cao',2),(2,'Giải bóng đá vô địch quốc gia 2024 đã khép lại với trận chung kết đầy cảm xúc giữa hai đội bóng hàng đầu. \n Hàng vạn khán giả đổ về sân vận động Mỹ Đình để chứng kiến những ngôi sao sáng nhất làng bóng đá Việt Nam tranh tài. \n Ngay từ những phút đầu tiên, cả hai đội đều thể hiện lối chơi quyết liệt và không ngại va chạm. \n\n Phút thứ 15, đội chủ nhà có bàn thắng mở tỷ số sau một pha phối hợp đẹp mắt ở trung lộ. Tuy nhiên, niềm vui không kéo dài lâu khi đội khách gỡ hòa ngay trong hiệp 1 từ một cú sút xa đầy uy lực. \n Hiệp 2 diễn ra vô cùng kịch tính khi cả hai đội đều đẩy cao đội hình, tạo ra hàng loạt cơ hội nguy hiểm. \n\n Bước ngoặt xảy ra ở phút 85 khi tiền đạo chủ lực của đội chủ nhà có pha solo ngoạn mục qua hai hậu vệ, dứt điểm hiểm hóc ấn định chiến thắng 2-1. \n Niềm vui vỡ òa khi tiếng còi mãn cuộc vang lên, đánh dấu một mùa giải đầy cảm xúc và hứa hẹn nhiều điều bất ngờ cho mùa giải năm sau.','Sân vận động Quốc gia Mỹ Đình bùng nổ trong trận chung kết nghẹt thở.','Chung Kết Giải Bóng Đá Vô Địch Quốc Gia 2024 - Những Pha Bóng Nghẹt Thở',1),(3,'Giải Tennis Mở Rộng 2024 được tổ chức tại Trung tâm thể thao Đà Nẵng đã thu hút sự chú ý của hàng ngàn khán giả yêu mến môn thể thao quý tộc này. \n Ngay từ những vòng đấu đầu tiên, khán giả đã được chứng kiến những trận đấu kịch tính, nơi từng đường bóng đều là sự kết hợp hoàn hảo giữa kỹ thuật, sức mạnh và bản lĩnh thi đấu. \n\n Trận chung kết đơn nam là cuộc đối đầu giữa hai tay vợt hàng đầu Việt Nam. Set đầu tiên diễn ra vô cùng cân bằng khi cả hai đều giữ chắc game giao bóng của mình, buộc trận đấu phải giải quyết bằng loạt tie-break căng thẳng. \n Set thứ hai chứng kiến sự bứt phá mạnh mẽ khi một trong hai tay vợt liên tục giành break, tạo ra khoảng cách an toàn để giành chiến thắng chung cuộc. \n\n Không chỉ là nơi phô diễn kỹ thuật, giải đấu năm nay còn là cơ hội để các tay vợt trẻ cọ xát, học hỏi và khẳng định bản thân trên đấu trường quốc gia.','Trung tâm thể thao Đà Nẵng chào đón những tay vợt xuất sắc nhất Việt Nam.','Giải Tennis Mở Rộng 2024 - Những Pha Bóng Nghệ Thuật',4),(4,'Giải bóng chuyền nữ vô địch quốc gia năm nay chứng kiến sự cạnh tranh quyết liệt giữa các đội bóng mạnh nhất cả nước. \n Trận chung kết là màn so tài kịch tính giữa đội bóng nữ TP. Hồ Chí Minh và đội bóng nữ Vĩnh Long. \n\n Ngay từ những pha bóng đầu tiên, cả hai đội đã mang đến cho khán giả những màn rượt đuổi điểm số đầy căng thẳng. \n Đội TP. Hồ Chí Minh nổi bật với những cú đập uy lực từ tuyến ngoài, trong khi đội Vĩnh Long lại ghi điểm nhờ những pha bám chắn chắc chắn cùng lối chơi đồng đội gắn kết. \n\n Trận đấu kéo dài tới 5 set đầy nghẹt thở, trong đó set cuối cùng kết thúc với tỷ số 16-14, khép lại một giải đấu đáng nhớ. \n Đây không chỉ là sân chơi để các đội bóng tranh tài mà còn là nơi phát hiện những tài năng mới cho đội tuyển quốc gia trong tương lai.','Những pha đập bóng uy lực và những cuộc rượt đuổi điểm số nghẹt thở.','Giải Bóng Chuyền Nữ Vô Địch Quốc Gia - Sự Trỗi Dậy Của Thế Hệ Mới',3);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price` (
  `priceid` bigint NOT NULL AUTO_INCREMENT,
  `day_time` decimal(38,2) NOT NULL,
  `early_time` decimal(38,2) NOT NULL,
  `night_time` decimal(38,2) NOT NULL,
  `week_time` decimal(38,2) NOT NULL,
  `facility_id` bigint NOT NULL,
  `type_id` bigint NOT NULL,
  PRIMARY KEY (`priceid`),
  KEY `FKgp8rdy7v8o8f6fr41v6fyojaa` (`facility_id`),
  KEY `FKgh9nqtu6kce816vurkha355q2` (`type_id`),
  CONSTRAINT `FKgh9nqtu6kce816vurkha355q2` FOREIGN KEY (`type_id`) REFERENCES `facility_type` (`facility_type_id`),
  CONSTRAINT `FKgp8rdy7v8o8f6fr41v6fyojaa` FOREIGN KEY (`facility_id`) REFERENCES `sports_facility` (`sports_facility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` VALUES (1,50000.00,100000.00,100000.00,100000.00,1,1),(2,50000.00,100000.00,100000.00,100000.00,1,2);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
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
  `detail` longtext,
  PRIMARY KEY (`sports_facility_id`),
  UNIQUE KEY `UKarrw3pocsyvgqa100yex2adcw` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_facility`
--

LOCK TABLES `sports_facility` WRITE;
/*!40000 ALTER TABLE `sports_facility` DISABLE KEYS */;
INSERT INTO `sports_facility` VALUES (1,'HCM, 1110 Phạm Văn Đồng P.Linh Đông Tp, Thủ Đức, Hồ Chí Minh 70000, Việt Nam','2025-02-25 10:00:00.000000','1742628987207_2024-05-09.jpg',10.84841524925496,106.74130928229619,'Tennis Cây Lộc Vừng','Sân tennis rộng rãi với mặt sân chất lượng cao, phù hợp cho cả người chơi chuyên nghiệp và giải trí. Có hệ thống chiếu sáng ban đêm, chỗ ngồi cho khán giả và khu vực nghỉ ngơi. Dịch vụ cho thuê vợt và bóng tại chỗ.'),(2,'158 Đ. số 17, Phường Linh Trung, Thủ Đức, Hồ Chí Minh 71308, Việt Nam','2025-02-25 11:00:00.000000','1742629916736_2023-09-24.jpg',10.864870010122146,106.78818257940658,'Sân cầu lông Aurora Sport','Không gian thoáng mát, trang bị sàn gỗ chuyên dụng và hệ thống chiếu sáng đạt chuẩn. Phù hợp cho các buổi luyện tập và thi đấu. Có khu vực giữ xe miễn phí và căng tin phục vụ đồ uống.'),(3,'10 Đ. Số 3, Phước Long A, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629938268_2024-05-02.jpg',10.835224103049228,106.76414735242275,'Galaxy badminton','Sân cầu lông hiện đại với mặt sân đạt tiêu chuẩn thi đấu. Phòng thay đồ sạch sẽ, dịch vụ cho thuê dụng cụ và chỗ ngồi cho khán giả. Có máy lạnh và wifi miễn phí.'),(4,'40 Đ. Số 11, Trường Thọ, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629962983_2024-08-07.jpg',10.8448260987901,106.75305479475074,'Sân cầu lông B-ZONE 11','Sân cầu lông với hệ thống đèn LED tiết kiệm năng lượng, không gian rộng rãi, sạch sẽ. Có khu vực tập thể lực, bãi giữ xe rộng rãi và căng tin phục vụ đồ ăn nhẹ.'),(5,'1 Đ. Võ Văn Ngân, Linh Chiểu, Thủ Đức, Hồ Chí Minh, Việt Nam','2025-02-25 12:00:00.000000','1742629982790_2019-03-24.jpg',10.850662559501243,106.77313336962,'Sân bóng ĐH SPKT TP HCM','Sân bóng đá cỏ nhân tạo với diện tích tiêu chuẩn, phù hợp cho các trận đấu giao hữu và giải đấu sinh viên. Có khán đài nhỏ, khu vực nghỉ ngơi, và dịch vụ cho thuê đồng phục.'),(6,'127 Lê Văn Chí, Phường Linh Trung, Thủ Đức, Hồ Chí Minh 700000, Việt Nam','2025-03-22 21:53:04.409332','1742655184429_2024-01-12.jpg',10.86038480159787,106.77723479562422,'Sân Cầu Lông SHB Badminton','Sân cầu lông rộng rãi, sạch sẽ, phù hợp cho việc tập luyện và thi đấu. Hệ thống chiếu sáng hiện đại, có dịch vụ cho thuê vợt và giày cầu lông. Bãi giữ xe an toàn, miễn phí.'),(7,'270 Bưng Ông Thoàn, Tăng Nhơn Phú B, Thủ Đức, Hồ Chí Minh 70000, Việt Nam','2025-03-24 13:43:19.945239','1742798599992_2024-08-23.jpg',10.83004159504669,106.78527352152858,'Sân cầu lông Tám Khỏe Badminton','Sân cầu lông với không gian thoáng mát, mặt sàn chất lượng cao. Phù hợp cho các buổi tập luyện nhóm và thi đấu. Có phòng thay đồ, khu vực nghỉ ngơi, và chỗ giữ xe miễn phí.');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_facility`
--

LOCK TABLES `sub_facility` WRITE;
/*!40000 ALTER TABLE `sub_facility` DISABLE KEYS */;
INSERT INTO `sub_facility` VALUES (1,'Sân 1','AVAILABLE',2,1),(2,'Sân 2','AVAILABLE',2,1),(3,'Sân 3','ISBOOKED',2,1),(4,'Sân 1','AVAILABLE',1,2),(5,'Sân 2','ISBOOKED',1,2),(6,'Sân 3','ISBOOKED',1,2),(7,'Sân 4','ISBOOKED',1,2),(8,'Sân 5','AVAILABLE',1,2),(9,'Sân 1','AVAILABLE',1,3),(10,'Sân 2','AVAILABLE',1,3),(11,'Sân 3','AVAILABLE',1,3),(12,'Sân 4','AVAILABLE',1,3),(13,'Sân 1','AVAILABLE',1,4),(14,'Sân 2','AVAILABLE',1,4),(15,'Sân 3','AVAILABLE',1,4),(16,'Sân 4','AVAILABLE',1,4),(17,'Sân 5','AVAILABLE',1,4),(18,'Sân 1','AVAILABLE',4,5),(19,'Sân 2','AVAILABLE',4,5),(20,'Sân 3','AVAILABLE',4,5),(21,'Sân 4','AVAILABLE',4,5),(22,'Sân 5','AVAILABLE',4,5),(23,'Sân 4','AVAILABLE',2,1),(24,'Sân 5','AVAILABLE',2,1),(25,'Sân 6','AVAILABLE',1,1),(26,'Sân A','AVAILABLE',1,2);
/*!40000 ALTER TABLE `sub_facility` ENABLE KEYS */;
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

-- Dump completed on 2025-03-26 17:12:17
