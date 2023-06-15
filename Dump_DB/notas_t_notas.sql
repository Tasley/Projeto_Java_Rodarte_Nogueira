-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: notas
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
-- Table structure for table `t_notas`
--

DROP TABLE IF EXISTS `t_notas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_notas` (
  `identificacao` bigint NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nota_trimestre_dois` float DEFAULT NULL,
  `nota_trimestre_tres` float DEFAULT NULL,
  `nota_trimestre_um` float DEFAULT NULL,
  `sexo` char(1) NOT NULL,
  PRIMARY KEY (`identificacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_notas`
--

LOCK TABLES `t_notas` WRITE;
/*!40000 ALTER TABLE `t_notas` DISABLE KEYS */;
INSERT INTO `t_notas` VALUES (121314,'1990-07-06','Joana Aguiar',20,15,25,'F'),(123456,'1995-08-04','Maria da Silva',30,30,15,'F'),(151617,'1997-08-06','Antônio Fernandes',30,32,30,'M'),(181920,'1993-05-25','Priscila Mendes',35,35,30,'F'),(212223,'1990-09-23','Leonardo Martins',13,15,10,'M'),(242526,'1989-06-12','Janaina Gomes',18,25,21,'F'),(272829,'1993-03-23','Nelson Garcia',28,25,18,'M'),(303132,'1995-09-30','Fernanda Lopes',34,31,23,'F'),(333435,'1998-05-01','Gabriel Pereira',22,30,14,'M'),(363738,'1994-11-22','Larissa Diniz',25,29,20,'F'),(394041,'1999-07-11','Rodolfo Maia',35,34,30,'M'),(424344,'1990-08-14','Letícia Rodrigues',29,33,27,'F'),(454647,'1994-12-03','Marina Azevedo',29,23,11,'F'),(484950,'1993-09-04','Ivan Nunes',29,27,25,'M'),(515253,'1996-04-19','Eliza Lage',32,34,29,'F'),(545556,'1994-09-17','Otavio Mendonça',22,27,19,'M'),(575859,'1997-10-25','Silvana Andrade',35,35,30,'F'),(789101,'1991-04-05','José dos Santos',20,25,20,'M');
/*!40000 ALTER TABLE `t_notas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-15  9:42:37
