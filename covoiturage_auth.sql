-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 07 mai 2025 à 14:08
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `covoiturage_auth`
--

-- --------------------------------------------------------

--
-- Structure de la table `admins`
--

CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personnes`
--

CREATE TABLE `personnes` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_telephone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` tinyint(4) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personnes`
--

INSERT INTO `personnes` (`id`, `email`, `genre`, `nom`, `numero_telephone`, `password`, `prenom`, `role`, `username`) VALUES
(1, 'aitbenalihoucine18@gmail.com', 'Homme', 'ben', '0660348473', '$2a$10$d9PXZmAFUpWxiaf43n9N.eUTiLdOp8f3G.csscZo6m4cksD.Xiwiq', 'houcine', 0, 'houcine1'),
(2, 'admin@admin.com', 'Homme', 'admin', '0660348474', '$2a$10$BJLMS//GufN5SVouwfwqfeCnPU3kLEYcue2lIUmRl1UusEKeFIudC', 'Admin', 0, 'admin1'),
(52, 'john@example.com', 'Homme', 'Doe', '0600000000', '$2a$10$7eR2Rv6O1puEFY3hsAUgL.VX3mfJBcNO1V62301Z17lzFjtyvjy2W', 'John', 0, 'johndoe');

-- --------------------------------------------------------

--
-- Structure de la table `personnes_seq`
--

CREATE TABLE `personnes_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personnes_seq`
--

INSERT INTO `personnes_seq` (`next_val`) VALUES
(151);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK47bvqemyk6vlm0w7crc3opdd4` (`email`);

--
-- Index pour la table `personnes`
--
ALTER TABLE `personnes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbj8f297l8wgvcwscfgcyctda3` (`email`),
  ADD UNIQUE KEY `UK155lxhq29609hacsemd4lh1hk` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
