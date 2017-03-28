-- MySQL dump 10.13  Distrib 5.7.11, for osx10.9 (x86_64)
--
-- Host: localhost    Database: bookManager
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bill` (
  `billid` int(8) NOT NULL AUTO_INCREMENT,
  `billtype` int(1) DEFAULT NULL,
  `billdate` date DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`billid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
INSERT INTO `Bill` VALUES (1,1,'2016-09-26','admin',60),(2,0,'2016-09-26','user1',20),(3,3,'2016-09-27','user1',20),(4,0,'2016-09-27','234',9000),(5,2,'2016-09-27','user1',20);
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Librarian`
--

DROP TABLE IF EXISTS `Librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Librarian` (
  `l_id` int(8) NOT NULL AUTO_INCREMENT,
  `l_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Librarian`
--

LOCK TABLES `Librarian` WRITE;
/*!40000 ALTER TABLE `Librarian` DISABLE KEYS */;
INSERT INTO `Librarian` VALUES (999999,'admin','admin');
/*!40000 ALTER TABLE `Librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `u_id` int(8) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `u_name` varchar(20) NOT NULL,
  `u_type` int(1) DEFAULT '0',
  `phone` int(12) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `idcard` int(18) NOT NULL,
  `money` double DEFAULT '10',
  `borrow` int(2) DEFAULT '4',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_name` (`u_name`),
  UNIQUE KEY `idcard` (`idcard`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'user','user1',1,NULL,'aa@aa.com',333,20,24),(2,'234','234',0,234,'234',234,8990,4),(3,'1','1',0,1,'1',1,10,4),(4,'121','12',0,12,'',12,10,4),(5,'123124','12314',0,12312123,'123123',2123,10,4);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approve`
--

DROP TABLE IF EXISTS `approve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approve` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `book_id` int(8) DEFAULT NULL,
  `user_id` int(8) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `book_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approve`
--

LOCK TABLES `approve` WRITE;
/*!40000 ALTER TABLE `approve` DISABLE KEYS */;
/*!40000 ALTER TABLE `approve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(8) NOT NULL,
  `authority` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `b_id` int(8) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(20) NOT NULL,
  `b_type` int(3) NOT NULL,
  `author` varchar(18) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `numbers` int(3) DEFAULT '1',
  `now_number` int(3) DEFAULT NULL,
  PRIMARY KEY (`b_id`),
  KEY `book_booktype` (`b_type`),
  CONSTRAINT `book_booktype` FOREIGN KEY (`b_type`) REFERENCES `booktype` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'C prime plus',1,'董皓',99.9,3,2),(2,'Think in Java',3,'董皓',99.9,3,3),(3,'鸟哥的Linux私房菜',4,'鸟',89,10,9),(4,'123',2,'123',123,123,120),(5,'99999123',2,'123',1231,23,23),(13,'123',1,'123',1231,23,23),(14,'c++ prime',2,'abc',50.9,3,3),(15,'c++ prime plus',2,'buzhidao',89.9,3,3),(16,'NIUBI',2,'buzhidao',48,3,3),(17,'nihao',1,'dashabi',40,3,3),(18,'lianhuanhua',1,'dasabi',40,3,3),(19,'30geshabi',1,'sabi',39,3,3),(20,'20geshabi',1,'buzhidao',20,3,3),(21,'shoudongzengjia',1,'nidaye',30,3,3),(22,'changjiang',1,'huanghe',30,3,3),(23,'wode',1,'das',59,3,3),(24,'test',1,'test',20,3,3);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booktype`
--

DROP TABLE IF EXISTS `booktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booktype` (
  `t_id` int(3) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(20) NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booktype`
--

LOCK TABLES `booktype` WRITE;
/*!40000 ALTER TABLE `booktype` DISABLE KEYS */;
INSERT INTO `booktype` VALUES (1,'C'),(2,'C++'),(3,'Java'),(4,'Linux');
/*!40000 ALTER TABLE `booktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowBooks`
--

DROP TABLE IF EXISTS `borrowBooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrowBooks` (
  `borrow_id` int(10) NOT NULL AUTO_INCREMENT,
  `borrow_user_id` int(8) DEFAULT NULL,
  `borrow_book_id` int(8) DEFAULT NULL,
  `borrow_date` date DEFAULT NULL,
  `type` int(1) DEFAULT '0',
  PRIMARY KEY (`borrow_id`),
  KEY `borrow_books` (`borrow_book_id`),
  KEY `borrow_user` (`borrow_user_id`),
  CONSTRAINT `borrow_books` FOREIGN KEY (`borrow_book_id`) REFERENCES `books` (`b_id`),
  CONSTRAINT `borrow_user` FOREIGN KEY (`borrow_user_id`) REFERENCES `Users` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowBooks`
--

LOCK TABLES `borrowBooks` WRITE;
/*!40000 ALTER TABLE `borrowBooks` DISABLE KEYS */;
INSERT INTO `borrowBooks` VALUES (10,1,1,'2016-09-03',0),(11,1,3,'2016-10-03',0),(12,1,4,'2016-10-03',0);
/*!40000 ALTER TABLE `borrowBooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expressBook`
--

DROP TABLE IF EXISTS `expressBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expressBook` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `bookid` int(8) DEFAULT NULL,
  `userid` int(8) DEFAULT NULL,
  `spot` int(11) DEFAULT NULL,
  `spotInfo` varchar(100) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expressBook`
--

LOCK TABLES `expressBook` WRITE;
/*!40000 ALTER TABLE `expressBook` DISABLE KEYS */;
INSERT INTO `expressBook` VALUES (1,2,2,1,'1',11,0),(2,2,2,2,'1',11,10),(3,2,13,2,'11',1111,10),(4,1,4,2,'1',1,10);
/*!40000 ALTER TABLE `expressBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchased`
--

DROP TABLE IF EXISTS `purchased`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchased` (
  `p_id` int(5) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(20) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchased`
--

LOCK TABLES `purchased` WRITE;
/*!40000 ALTER TABLE `purchased` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchased` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeStamps`
--

DROP TABLE IF EXISTS `timeStamps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timeStamps` (
  `userid` int(11) NOT NULL,
  `time` mediumtext,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeStamps`
--

LOCK TABLES `timeStamps` WRITE;
/*!40000 ALTER TABLE `timeStamps` DISABLE KEYS */;
/*!40000 ALTER TABLE `timeStamps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userTime`
--

DROP TABLE IF EXISTS `userTime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userTime` (
  `userid` int(11) NOT NULL,
  `time` mediumtext,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userTime`
--

LOCK TABLES `userTime` WRITE;
/*!40000 ALTER TABLE `userTime` DISABLE KEYS */;
INSERT INTO `userTime` VALUES (1,'5');
/*!40000 ALTER TABLE `userTime` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-07  9:41:10
