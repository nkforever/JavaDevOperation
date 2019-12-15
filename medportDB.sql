-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medport
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `address_table`
--

DROP TABLE IF EXISTS `address_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_table` (
  `address_id` varchar(45) NOT NULL,
  `street_num` varchar(45) DEFAULT NULL,
  `apt_num` varchar(45) DEFAULT NULL,
  `street_name` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `lastupdate` varchar(10) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_table`
--

LOCK TABLES `address_table` WRITE;
/*!40000 ALTER TABLE `address_table` DISABLE KEYS */;
INSERT INTO `address_table` VALUES ('02330584','1234',' ','pfw driv','fort wayne','IN','45803','2019-10-27',NULL),('11111111','123',' ','THIS SHOW THE UPDATE','Area 51','AZ','11111','2019-12-14','secretary'),('11198701','',' ','','','','','2019-12-02',NULL),('11353940','',' ','','','','','2019-12-02',NULL),('12345678','1234',' ','THIS IS AN UPDATE','Fort Wayne','IN','46806','2019-12-14','Admin'),('13234256','222',' ','test2 street','two city','IN','12345',NULL,NULL),('17098115','123',' ','Doctor St','Doctor City','IN','11111','10/20/2019',NULL),('17275009','',' ','','','','','2019-12-02',NULL),('17490585','',' ','','','','','2019-12-02',NULL),('18033454','123',' ','Nurse St','Nurse City','IN','22222','10/20/2019',NULL),('19038114','123',' ','Finance St','Finance City','IN','33333','10/20/2019',NULL),('19064113','123',' ','Secretary St','Secretary City','IN','46803','10/20/2019',NULL),('21454256','333',' ','test3 street','three city','IN','12345',NULL,NULL),('22222222','444',' ','NEW TEST','New City','IN','12345','2019-12-02','doctor'),('30739068','22444',' ','main street','Anew road','IN','45682','2019-10-29',NULL),('36360158',' 123',' ',' usa st',' usa cityq','IN',' 11111','2019-10-26',NULL),('57435430','9901',' ','Spooner St','Cohaog','RI','11223','2019-10-31',NULL),('63652561','555',' ','fifth street','fifth city','IN','12345',NULL,NULL),('65556466','2001',' ','no name','Fort Wayne','IN','46803','2019-10-30',NULL),('74407544','1234',' ','ONE TWO THREE','FORT WAYNE','AK','','2019-12-14',NULL),('99999999','123',' ','Admin St','Admin City','IN','46803','2019-12-05','admin');
/*!40000 ALTER TABLE `address_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_list`
--

DROP TABLE IF EXISTS `doctor_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_list` (
  `dr_id` varchar(45) NOT NULL,
  `dr_firstname` varchar(45) DEFAULT NULL,
  `doctor_lastname` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`dr_id`),
  UNIQUE KEY `dr_id_UNIQUE` (`dr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_list`
--

LOCK TABLES `doctor_list` WRITE;
/*!40000 ALTER TABLE `doctor_list` DISABLE KEYS */;
INSERT INTO `doctor_list` VALUES ('11223344','V.','Inukollu',1),('12233455','Jeni','Lynn',1),('12345678','Nyi','Nyi',1),('17098111','Vinny','Vinny',1),('33445562','Dale','Dale',1);
/*!40000 ALTER TABLE `doctor_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_info`
--

DROP TABLE IF EXISTS `employee_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_info` (
  `employee_id` varchar(45) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `mid_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `DOB` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `ssnArea` varchar(45) NOT NULL,
  `ssnGroup` varchar(45) NOT NULL,
  `ssnSerial` varchar(45) NOT NULL,
  `last_update` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `userAdmin` int(11) DEFAULT NULL,
  `addEditPatient` int(11) DEFAULT NULL,
  `viewPatient` int(11) DEFAULT NULL,
  `ownProfile` int(11) DEFAULT NULL,
  `viewBill` int(11) DEFAULT NULL,
  `processPayment` int(11) DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `ee_id_UNIQUE` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_info`
--

LOCK TABLES `employee_info` WRITE;
/*!40000 ALTER TABLE `employee_info` DISABLE KEYS */;
INSERT INTO `employee_info` VALUES ('12233455','nurse','Jane','J','Doe','Female',NULL,'Jane.Doe@gmail.com','(111) 222-3333','Nurse','111','33','3333',NULL,'admin',0,1,1,1,0,0,0),('12345678','nyi','Nyi','N','NAING','Male','12/30/1999','whoUseAol@LOL.com','(123) 456-7890',' ','123','45','6789','2019-12-14','Admin',1,0,0,1,0,0,1),('17098111','doctor','John','A','Who','Male',NULL,'John.Who@gmail.com','(333) 444-5555','Doctor','222','44','5555',NULL,'admin',0,1,1,1,0,0,1),('19038115','secretary','Mary','K','Pennyworth','Female',NULL,'mary.pennyworth@gmail.com','(444) 555-6666','Secretary','333','66','4444',NULL,'admin',0,1,1,1,1,1,1),('33445562','finance','Finance','FN','Administrator','Female','12/02/2011','finance@finance.com','2604471111','Finance','111','11','1111',NULL,NULL,0,0,1,1,1,1,1),('99999999','admin','Admin','A','Admin','Male','12/01/2012','admin.A@gmail.com','(999) 999-9999','Admin','999','99','9999','2019-12-05','admin',1,0,0,1,0,0,1);
/*!40000 ALTER TABLE `employee_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_table`
--

DROP TABLE IF EXISTS `employee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_table` (
  `employee_id` varchar(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `lastupdate` varchar(45) NOT NULL DEFAULT 'Current_Timestamp',
  `changeby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `personalnumber_UNIQUE` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_table`
--

LOCK TABLES `employee_table` WRITE;
/*!40000 ALTER TABLE `employee_table` DISABLE KEYS */;
INSERT INTO `employee_table` VALUES ('12345678','nyi','root','09/15/2019','admin'),('17098115','Doctor','root','09/15/2019','admin'),('18033454','Nurse','root','09/15/2019','admin'),('19038114','Secretary','root','09/15/2019','admin'),('33445562','finance','root','09/15/2019','admin'),('99999999','admin','Admin','09/15/2019','admin');
/*!40000 ALTER TABLE `employee_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_table`
--

DROP TABLE IF EXISTS `id_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `id_table` (
  `count` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`count`),
  UNIQUE KEY `idnew_table_UNIQUE` (`id`),
  UNIQUE KEY `count_UNIQUE` (`count`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_table`
--

LOCK TABLES `id_table` WRITE;
/*!40000 ALTER TABLE `id_table` DISABLE KEYS */;
INSERT INTO `id_table` VALUES (1,'11111111'),(21,'12345678'),(22,'17098115'),(23,'18033454'),(2,'19038114'),(7,'33445562'),(25,'74407544'),(24,'99999999');
/*!40000 ALTER TABLE `id_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_table`
--

DROP TABLE IF EXISTS `invoice_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_table` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `invoice` int(10) NOT NULL,
  `previous_bal` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `debit` double DEFAULT '0',
  `last_update` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `index_UNIQUE` (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_table`
--

LOCK TABLES `invoice_table` WRITE;
/*!40000 ALTER TABLE `invoice_table` DISABLE KEYS */;
INSERT INTO `invoice_table` VALUES (11,'11111111',11170318,0,0,0,0,'2019-12-14 00:00:00','Secretary');
/*!40000 ALTER TABLE `invoice_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_history`
--

DROP TABLE IF EXISTS `patient_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_history` (
  `line` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(45) NOT NULL,
  `invoice` varchar(45) NOT NULL,
  `check_in_date` varchar(45) DEFAULT NULL,
  `check_out_date` varchar(45) DEFAULT NULL,
  `doctor` varchar(45) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  `upper_bp` int(11) DEFAULT NULL,
  `lower_bp` int(11) DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `visit_reason` varchar(100) DEFAULT NULL,
  `treatment_type` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `last_update` varchar(20) DEFAULT NULL,
  `change_by` varchar(20) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`line`),
  UNIQUE KEY `line_UNIQUE` (`line`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_history`
--

LOCK TABLES `patient_history` WRITE;
/*!40000 ALTER TABLE `patient_history` DISABLE KEYS */;
INSERT INTO `patient_history` VALUES (55,'11111111','11170318','12/11/2019','12/11/2019','Dr. Nyi Nyi','ER 104',11,11,11,'a must required','100030 Surgery: Spine','','2019-12-11','secretary',12000);
/*!40000 ALTER TABLE `patient_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_table`
--

DROP TABLE IF EXISTS `patient_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_table` (
  `patient_id` varchar(20) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `mid_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `DOB` varchar(20) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `primaryDoctor` varchar(45) DEFAULT NULL,
  `ssnArea` varchar(10) DEFAULT NULL,
  `ssnGroup` varchar(10) DEFAULT NULL,
  `ssnSerial` varchar(10) DEFAULT NULL,
  `phone_num` varchar(45) DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  `last_update` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `patient_id_UNIQUE` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_table`
--

LOCK TABLES `patient_table` WRITE;
/*!40000 ALTER TABLE `patient_table` DISABLE KEYS */;
INSERT INTO `patient_table` VALUES ('11111111','TEST','SUBJECT','ONE','01/01/2010','Female','Dr. FrankinStein','111','22','1234','(260) 999-9999',0,'2019-12-14','secretary'),('22222222','TESTINT','NEW','PATIENT','04/04/2020','Female','Dr. Hello','333','44','5555','(   )    -    ',0,'2019-12-02','doctor'),('74407544','THIS','IS','NEW','11/11/2001','Male',' ','111','22','3333','(   )    -    ',1,'2019-12-14',NULL);
/*!40000 ALTER TABLE `patient_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_record_table`
--

DROP TABLE IF EXISTS `payment_record_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_record_table` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `invoice` int(11) NOT NULL,
  `cc_number` varchar(45) NOT NULL,
  `cc_amount` double DEFAULT '0',
  `check_number` varchar(45) NOT NULL,
  `check_amount` double DEFAULT NULL,
  `check_routine` varchar(45) DEFAULT NULL,
  `check_account` varchar(45) DEFAULT NULL,
  `cash_amount` double DEFAULT NULL,
  `date_process` datetime DEFAULT NULL,
  `process_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `index_UNIQUE` (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_record_table`
--

LOCK TABLES `payment_record_table` WRITE;
/*!40000 ALTER TABLE `payment_record_table` DISABLE KEYS */;
INSERT INTO `payment_record_table` VALUES (6,11170318,'',0,'',0,'','',10000,'2019-12-11 00:00:00','secretary'),(7,11170318,'',0,'',0,'','',2000,'2019-12-14 00:00:00','Secretary');
/*!40000 ALTER TABLE `payment_record_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_table`
--

DROP TABLE IF EXISTS `prescription_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_table` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `cost_per_um` double NOT NULL DEFAULT '1',
  `last_update` datetime NOT NULL,
  PRIMARY KEY (`prescription_id`),
  UNIQUE KEY `idprescription_table_UNIQUE` (`prescription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_table`
--

LOCK TABLES `prescription_table` WRITE;
/*!40000 ALTER TABLE `prescription_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_list`
--

DROP TABLE IF EXISTS `room_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_list` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `room_num` int(11) NOT NULL,
  `occupy` tinyint(4) NOT NULL DEFAULT '0',
  `last_update` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_list`
--

LOCK TABLES `room_list` WRITE;
/*!40000 ALTER TABLE `room_list` DISABLE KEYS */;
INSERT INTO `room_list` VALUES (425,'ER',103,0,NULL,NULL),(426,'ER',104,0,NULL,NULL),(427,'ER',104,0,NULL,NULL),(428,'ICU',105,0,NULL,NULL),(430,'ICU',105,0,NULL,NULL),(432,'ICU',101,0,NULL,NULL),(433,'ER',101,0,NULL,NULL),(434,'ER',102,0,NULL,NULL),(436,'ER',110,0,NULL,NULL),(437,'ER',111,0,NULL,NULL),(438,'ICU',102,0,NULL,NULL),(439,'General',101,0,NULL,NULL),(440,'General',102,0,NULL,NULL),(441,'General',103,0,NULL,NULL),(442,'General',105,0,NULL,NULL),(446,'General',104,0,NULL,NULL),(447,'ER',1000,0,NULL,NULL),(448,'TEST',1234,0,NULL,NULL);
/*!40000 ALTER TABLE `room_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_table`
--

DROP TABLE IF EXISTS `salary_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary_table` (
  `employee_id` int(11) NOT NULL,
  `hour` double DEFAULT '1',
  `ot` double DEFAULT NULL,
  `holiday` double DEFAULT NULL,
  `vacation` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `401k` double DEFAULT NULL,
  `healthcare` double DEFAULT NULL,
  `dental` double DEFAULT NULL,
  `vision` double DEFAULT NULL,
  `fed_tax` double DEFAULT NULL,
  `state_tax` double DEFAULT NULL,
  `payout` double DEFAULT NULL,
  `datepay` date DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_table`
--

LOCK TABLES `salary_table` WRITE;
/*!40000 ALTER TABLE `salary_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment_list`
--

DROP TABLE IF EXISTS `treatment_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment_list` (
  `treatment_id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  `cost` double DEFAULT '1',
  `last_update` varchar(10) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`treatment_id`),
  UNIQUE KEY `treatment_id_UNIQUE` (`treatment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_list`
--

LOCK TABLES `treatment_list` WRITE;
/*!40000 ALTER TABLE `treatment_list` DISABLE KEYS */;
INSERT INTO `treatment_list` VALUES (100000,'-',1,0,NULL,NULL),(100020,'Surgrey: Brain',1,50000,'10/24/2019',1),(100030,'Surgery: Spine',1,12000,'10/24/2019',1),(100040,'Surgery: Knee',1,5000,'10/22/2019',1),(200200,'Lab: Blood Basic Test',1,800,'10/22/2019',1),(200210,'Lab: Urine Test',1,120,'10/22/2019',1),(200220,'Lab: Stool Test',1,250,'10/22/2019',1),(200230,'Lab: Xray',1,570,'10/22/2019',1),(200240,'Lab: Ultra Sound',1,350,'10/22/2019',1),(300100,'Lab: CT Scan',1,1200,'10/22/2019',1),(300200,'General: IV',1,350,'10/22/2019',1),(300300,'General: Pain Reliever',1,60,'10/22/2019',1),(300400,'General: First Aid',1,40,'10/22/2019',1),(400100,'General: Anti-bliotic',1,60,'10/22/2019',1),(400200,'General: Asprin',1,40,'10/22/2019',1),(400210,'General: Wound Dressing',1,55,'10/22/2019',1),(400310,'General: Wax Removal',1,50,'10/22/2019',1),(400320,'General: Consultation',1,200,'10/22/2019',1),(500010,'General: Diagnosis',1,200,'10/22/2019',1),(500020,'Lab: Allergry Test',1,490,'10/22/2019',1),(500030,'Lab: Blood Micro Test',1,1860,'10/22/2019',1),(500040,'Dummy Testing',1,50000,'10/22/2019',1);
/*!40000 ALTER TABLE `treatment_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment_record_table`
--

DROP TABLE IF EXISTS `treatment_record_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment_record_table` (
  `invoice_id` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `care_id` int(11) NOT NULL,
  `quantiry` int(11) NOT NULL DEFAULT '1',
  `cost` double DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  KEY `invoice_idx` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_record_table`
--

LOCK TABLES `treatment_record_table` WRITE;
/*!40000 ALTER TABLE `treatment_record_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment_record_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-15 16:39:05
