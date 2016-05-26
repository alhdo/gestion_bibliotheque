-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 27, 2016 at 12:46 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javadb`
--
CREATE DATABASE IF NOT EXISTS `javadb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `javadb`;

-- --------------------------------------------------------

--
-- Table structure for table `adherents`
--

CREATE TABLE IF NOT EXISTS `adherents` (
  `code` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `emprunt`
--

CREATE TABLE IF NOT EXISTS `emprunt` (
  `numeroTransaction` int(11) NOT NULL,
  `adherents_code` int(11) NOT NULL,
  `examplaires_numero` int(11) NOT NULL,
  `dateEmprunt` date DEFAULT NULL,
  `dateRetour` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `examplaires`
--

CREATE TABLE IF NOT EXISTS `examplaires` (
  `numero` int(11) NOT NULL,
  `etat` varchar(45) DEFAULT NULL,
  `dateAchat` date DEFAULT NULL,
  `ISBN` varchar(100) NOT NULL,
  `dispo` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `livres`
--

CREATE TABLE IF NOT EXISTS `livres` (
  `ISBN` varchar(100) NOT NULL,
  `titre` varchar(200) DEFAULT NULL,
  `genre` varchar(200) DEFAULT NULL,
  `nbrPages` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `emprunt`
--
ALTER TABLE `emprunt`
  ADD PRIMARY KEY (`numeroTransaction`),
  ADD KEY `fk_emprunt_adherents1_idx` (`adherents_code`),
  ADD KEY `fk_emprunt_examplaires1_idx` (`examplaires_numero`);

--
-- Indexes for table `examplaires`
--
ALTER TABLE `examplaires`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `fk_examplaires_livres_idx` (`ISBN`);

--
-- Indexes for table `livres`
--
ALTER TABLE `livres`
  ADD PRIMARY KEY (`ISBN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adherents`
--
ALTER TABLE `adherents`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `emprunt`
--
ALTER TABLE `emprunt`
  MODIFY `numeroTransaction` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `examplaires`
--
ALTER TABLE `examplaires`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `fk_emprunt_adherents1` FOREIGN KEY (`adherents_code`) REFERENCES `adherents` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_emprunt_examplaires1` FOREIGN KEY (`examplaires_numero`) REFERENCES `examplaires` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `examplaires`
--
ALTER TABLE `examplaires`
  ADD CONSTRAINT `fk_examplaires_livres` FOREIGN KEY (`ISBN`) REFERENCES `livres` (`ISBN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
