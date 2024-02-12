Database: managment
-- ------------------------------------------------------
-- Server version	8.0.35



CREATE TABLE `test` (
  `testid` int NOT NULL AUTO_INCREMENT,
  `tehnologija` varchar(25) NOT NULL,
  `pitanje` varchar(255) NOT NULL,
  `odgovori` text NOT NULL,
  `tacan` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`testid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



INSERT INTO `test` (`testid`, `tehnologija`, `pitanje`, `odgovori`, `tacan`) VALUES (16,'JAVA','q1','a1q1,a2q1','a2q1'),(17,'JAVA','q2','a1q2,a2q2','a1q2'),(18,'HTML','q1','a1q1,a2q1','a1q1'),(19,'HTML','q2','a1q2,a2q2','a2q2');



CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `userfname` varchar(20) DEFAULT NULL,
  `userlname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `unique_username` (`username`)



INSERT INTO `user` (`userid`, `userfname`, `userlname`, `username`, `password`, `admin`) VALUES (4,'a','a','a','a',1),(25,'u','u','u','u',0);


