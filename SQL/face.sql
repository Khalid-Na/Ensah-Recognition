-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 26, 2021 at 10:33 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recognition`
--

-- --------------------------------------------------------

--
-- Table structure for table `face`
--

DROP TABLE IF EXISTS `face`;
CREATE TABLE IF NOT EXISTS `face` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(10) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `face`
--

INSERT INTO `face` (`id`, `code`, `first_name`, `last_name`, `age`) VALUES
(8, 1, 'Nazi', 'Khalid', 21),
(9, 1, 'Nazi', 'Khalid', 21),
(10, 1, 'Nazi', 'Khalid', 21),
(11, 1, 'Nazi', 'Khalid', 21),
(12, 1, 'Nazi', 'Khalid', 21),
(13, 1, 'Nazi', 'Khalid', 21),
(14, 1, 'Nazi', 'Khalid', 21),
(15, 1, 'Nazi', 'Khalid', 21),

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
