CREATE DATABASE  IF NOT EXISTS `musicon_2021` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `musicon_2021`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: musicon_2021
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    `mail` varchar(45) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `location` varchar(45) DEFAULT NULL,
    `picture` varchar(255) DEFAULT NULL,
    `date_Register` DATETIME DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*INSERT INTO `users` VALUES (1,'Eduardo','LIS',1,1),(2,'Antonio','LCC',2,2);*/
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `artists` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    `id_user` int(11) DEFAULT NULL,
    `followers` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
/*INSERT INTO `artists` VALUES (1,'Eduardo','LIS',1,1),(2,'Antonio','LCC',2,2);*/
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `songs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `id_artist` int(11) DEFAULT NULL,
  `id_album` int(11) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `reproductions` int(11) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `rating` float(11) DEFAULT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
/*INSERT INTO `songs` VALUES (1,'Eduardo','LIS',1,1),(2,'Antonio','LCC',2,2);*/
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `id_artist` int(11) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `url_cover` varchar(255) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `rating` float(11) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
/*INSERT INTO `albums` VALUES (1,'Lenovo Linux'),(2,'HP Windows')*/;
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlists` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    `id_user` int(11) DEFAULT NULL,
    `duration` int DEFAULT NULL,
    `location` varchar(45) DEFAULT NULL,
    `url_cover` varchar(255) DEFAULT NULL,
    `genre` varchar(45) DEFAULT NULL,
    `rating` float(11) DEFAULT NULL,   

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
/*INSERT INTO `playlists` VALUES (1,'Eduardo Profesor',10)*/;
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `podcasts`
--

DROP TABLE IF EXISTS `podcasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `podcasts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `id_artist` int(11) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `reproductions` int(11) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `rating` float(11) DEFAULT NULL,
  `file_name` varchar(45) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podcasts`
--

LOCK TABLES `podcasts` WRITE;
/*!40000 ALTER TABLE `podcasts` DISABLE KEYS */;
/*INSERT INTO `podcasts` VALUES (1,1,2),(2,1,3)*/;
/*!40000 ALTER TABLE `podcasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followers_artist`
--

DROP TABLE IF EXISTS `followers_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `followers_artist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_artist` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followers_artist`
--

LOCK TABLES `followers_artist` WRITE;
/*!40000 ALTER TABLE `followers_artist` DISABLE KEYS */;
/*INSERT INTO `followers_artist` VALUES (1,'eduardo@uady.mx','hola','abc'),(2,'antonio@uady.mx','hola','def')*/;
/*!40000 ALTER TABLE `followers_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists_podcasts`
--

DROP TABLE IF EXISTS `playlists_podcasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlists_podcasts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_podcasts` int(11) DEFAULT NULL,
  `id_playlist` int(11) DEFAULT NULL,
  `order_podcast` int(11) DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists_podcasts`
--

LOCK TABLES `playlists_podcasts` WRITE;
/*!40000 ALTER TABLE `playlists_podcasts` DISABLE KEYS */;
/*INSERT INTO `playlists_podcasts` VALUES (1,'eduardo@uady.mx','hola','abc'),(2,'antonio@uady.mx','hola','def')*/;
/*!40000 ALTER TABLE `playlists_podcasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists_songs`
--

DROP TABLE IF EXISTS `playlists_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlists_songs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_song` int(11) DEFAULT NULL,
  `id_playlist` int(11) DEFAULT NULL,
  `order_song` int(11) DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists_songs`
--

LOCK TABLES `playlists_songs` WRITE;
/*!40000 ALTER TABLE `playlists_songs` DISABLE KEYS */;
/*INSERT INTO `playlists_songs` VALUES (1,'eduardo@uady.mx','hola','abc'),(2,'antonio@uady.mx','hola','def')*/;
/*!40000 ALTER TABLE `playlists_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `podcast_plays`
--

DROP TABLE IF EXISTS `podcast_plays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `podcast_plays` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_podcast` int(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podcast_plays`
--

LOCK TABLES `podcast_plays` WRITE;
/*!40000 ALTER TABLE `podcast_plays` DISABLE KEYS */;
/*INSERT INTO `podcast_plays` VALUES (1,'eduardo@uady.mx','hola','abc'),(2,'antonio@uady.mx','hola','def')*/;
/*!40000 ALTER TABLE `podcast_plays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_plays`
--

DROP TABLE IF EXISTS `song_plays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `song_plays` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_song` int(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_plays`
--

LOCK TABLES `song_plays` WRITE;
/*!40000 ALTER TABLE `song_plays` DISABLE KEYS */;
/*INSERT INTO `song_plays` VALUES (1,'eduardo@uady.mx','hola','abc'),(2,'antonio@uady.mx','hola','def')*/;
/*!40000 ALTER TABLE `song_plays` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-03 17:00:22
