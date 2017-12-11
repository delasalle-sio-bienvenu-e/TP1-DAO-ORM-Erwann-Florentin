-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 11 Décembre 2017 à 09:06
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `daoormjooq`
--
CREATE DATABASE IF NOT EXISTS `daoormjooq` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `daoormjooq`;
-- --------------------------------------------------------

--
-- Structure de la table `prix_manege`
--

DROP TABLE IF EXISTS `prix_manege` CASCADE;
CREATE TABLE IF NOT EXISTS `prix_manege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_manege` int(11) NOT NULL,
  `prix` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`,`id_manege`),
  KEY `FK_prix_manege` (`id_manege`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Structure de la table `parc_attractions`
--

DROP TABLE IF EXISTS `parc_attractions` CASCADE;
CREATE TABLE IF NOT EXISTS `parc_attractions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_societe` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `taille` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_societe`),
  KEY `FK_societe` (`id_societe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `manege`
--

DROP TABLE IF EXISTS `manege` CASCADE;
CREATE TABLE IF NOT EXISTS `manege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `nombrePlaces` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

DROP TABLE IF EXISTS `societe` CASCADE;
CREATE TABLE IF NOT EXISTS `societe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `parc_attractions`
--
ALTER TABLE `parc_attractions`
  ADD CONSTRAINT `FK_societe` FOREIGN KEY (`id_societe`) REFERENCES `societe` (`id`) ON DELETE CASCADE;

--
-- Index pour la table `prix_manege`
--
ALTER TABLE `prix_manege`
  ADD CONSTRAINT `FK_prix_manege` FOREIGN KEY (`id_manege`) REFERENCES `manege` (`id`) ON DELETE CASCADE;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
