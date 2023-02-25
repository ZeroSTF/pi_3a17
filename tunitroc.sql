-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2023 at 03:49 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tunitroc`
--

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL,
  `contenu` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `id_post` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `dislikes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `echange`
--

CREATE TABLE `echange` (
  `id` int(11) NOT NULL,
  `id_panier` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_d` date NOT NULL,
  `date_f` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `description`, `date_d`, `date_f`) VALUES
(1, 'eventname', 'eventdescription', '2023-02-20', '2023-02-28');

-- --------------------------------------------------------

--
-- Table structure for table `fidelite`
--

CREATE TABLE `fidelite` (
  `id` int(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `produit_s` int(11) NOT NULL,
  `produit_r` int(11) NOT NULL,
  `transporteurB` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `contenu` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `id_user` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `dislikes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `photo` varchar(500) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `id_userS` int(11) NOT NULL,
  `id_userR` int(11) NOT NULL,
  `cause` varchar(255) NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporteur`
--

CREATE TABLE `transporteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `num_tel` int(11) NOT NULL,
  `photo` mediumblob NOT NULL,
  `id_echange` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `num_tel` int(11) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `valeur_fidelite` int(11) NOT NULL,
  `role` tinyint(1) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `pwd`, `nom`, `prenom`, `photo`, `num_tel`, `ville`, `valeur_fidelite`, `role`, `salt`, `token`, `etat`) VALUES
(5, 'fjazpodjlkazj', 'BsIolDsyzVQ3thxJu7vg+nf/OuIA3/yDcs6DhwoBi0o=', 'efjlkafjazl', 'fealkfjalk', '', 51591222, 'Bizerte', 0, 0, 's&���', NULL, 'INACTIF'),
(6, 'riadh.chnitir@esprit.tn', '7AlBN0/gG3xhLGdiFaF9yj2YYsnZm+XHWrIzyTbr+DE=', 'chnitir', 'riadh', '', 51591222, 'Bizerte', 0, 0, '����&��', NULL, 'INACTIF'),
(7, 'hedi.kramti@esprit.tn', 'MP9MbiZRKcTcaSXyMuG9Xr6Q96yqNxbiWHFRgozE5jE=', 'kramti', 'hedi', '', 51591222, 'Beja', 0, 1, 'VP�����', NULL, 'ACTIF'),
(8, 'khayri@esprit.tn', '1k0AS+qnhtSr3AN2so9dkSivipWW8sDrRa45jzCBfpQ=', 'bouzazi', 'khayri', '', 51591222, 'Gafsa', 0, 1, '03�o|', NULL, 'ACTIF');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `post_com` (`id_post`),
  ADD KEY `user_com` (`id_user`);

--
-- Indexes for table `echange`
--
ALTER TABLE `echange`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fidelite`
--
ALTER TABLE `fidelite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_fidelite` (`id_user`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `produitS_user` (`produit_s`),
  ADD KEY `produitR_user` (`produit_r`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_post` (`id_user`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_produit` (`id_user`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userS_reclamation` (`id_userS`),
  ADD KEY `userR` (`id_userR`);

--
-- Indexes for table `transporteur`
--
ALTER TABLE `transporteur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `echange_transporteur` (`id_echange`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `echange`
--
ALTER TABLE `echange`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fidelite`
--
ALTER TABLE `fidelite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transporteur`
--
ALTER TABLE `transporteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `post_com` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `user_com` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `fidelite`
--
ALTER TABLE `fidelite`
  ADD CONSTRAINT `user_fidelite` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `produitR_user` FOREIGN KEY (`produit_r`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `produitS_user` FOREIGN KEY (`produit_s`) REFERENCES `produit` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `user_post` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `user_produit` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `userR` FOREIGN KEY (`id_userR`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `userS_reclamation` FOREIGN KEY (`id_userS`) REFERENCES `user` (`id`);

--
-- Constraints for table `transporteur`
--
ALTER TABLE `transporteur`
  ADD CONSTRAINT `echange_transporteur` FOREIGN KEY (`id_echange`) REFERENCES `echange` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
