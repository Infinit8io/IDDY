-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 21 Avril 2017 à 08:10
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `iddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `title` varchar(25) CHARACTER SET latin1 NOT NULL,
  `description` text CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`id`, `title`, `description`) VALUES
(1, 'Social', 'Social '),
(2, 'Lifestyle', 'Lifestyle'),
(3, 'Try new things', 'Try new things'),
(4, 'WTF', 'What the fuck ?'),
(5, 'nsfw', 'Not safe for work');

-- --------------------------------------------------------

--
-- Structure de la table `challenges`
--

CREATE TABLE `challenges` (
  `id` int(11) NOT NULL,
  `fk_user` int(11) NOT NULL,
  `title` varchar(25) CHARACTER SET latin1 NOT NULL,
  `description` text CHARACTER SET latin1 NOT NULL,
  `fk_cat` int(11) NOT NULL,
  `fk_diff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `challenges`
--

INSERT INTO `challenges` (`id`, `fk_user`, `title`, `description`, `fk_cat`, `fk_diff`) VALUES
(1, 3, 'Challenge de test', 'Ceci est un challenge de test', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `challenges_users`
--

CREATE TABLE `challenges_users` (
  `id` int(11) NOT NULL,
  `fk_chall` int(11) NOT NULL,
  `fk_giver` int(11) NOT NULL,
  `fk_getter` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `datetime_create` datetime NOT NULL,
  `datetime_done` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `difficulties`
--

CREATE TABLE `difficulties` (
  `id` int(11) NOT NULL,
  `title` varchar(25) CHARACTER SET latin1 NOT NULL,
  `points` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `difficulties`
--

INSERT INTO `difficulties` (`id`, `title`, `points`) VALUES
(1, 'Potato', 10),
(2, 'Easy', 50),
(3, 'Normal', 100),
(4, 'Hard', 200),
(5, 'God', 500);

-- --------------------------------------------------------

--
-- Structure de la table `friendship`
--

CREATE TABLE `friendship` (
  `id` int(11) NOT NULL,
  `fk_user1` int(11) NOT NULL,
  `fk_user2` int(11) NOT NULL,
  `state` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `groups`
--

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table of user groups';

--
-- Contenu de la table `groups`
--

INSERT INTO `groups` (`id`, `name`) VALUES
(2, 'admins'),
(1, 'users');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `bio` varchar(140) CHARACTER SET latin1 NOT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `password`, `email`, `bio`, `login_name`) VALUES
(1, '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'user1@gmail.com', 'biographie frere', 'user1'),
(2, '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'user2@gmail.com', 'biographie frere', 'user2'),
(3, '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'user3@gmail.com', 'biographie frere', 'user3'),
(4, '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'user4@gmail.com', 'biographie frere', 'user4'),
(11, '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'bryan.muhmenthaler@gmail.com', 'Ceci est ma belle biographie.', 'bryan');

-- --------------------------------------------------------

--
-- Structure de la table `users_groups`
--

CREATE TABLE `users_groups` (
  `login_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `group_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table of groups to which the users belong';

--
-- Contenu de la table `users_groups`
--

INSERT INTO `users_groups` (`login_name`, `group_name`) VALUES
('user3', 'admins'),
('user4', 'admins'),
('user2', 'users'),
('user4', 'users');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `challenges`
--
ALTER TABLE `challenges`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`fk_user`,`fk_cat`,`fk_diff`),
  ADD KEY `fk_cat` (`fk_cat`),
  ADD KEY `fk_diff` (`fk_diff`);

--
-- Index pour la table `challenges_users`
--
ALTER TABLE `challenges_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_chall` (`fk_chall`,`fk_giver`,`fk_getter`),
  ADD KEY `fk_giver` (`fk_giver`),
  ADD KEY `fk_getter` (`fk_getter`);

--
-- Index pour la table `difficulties`
--
ALTER TABLE `difficulties`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `friendship`
--
ALTER TABLE `friendship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user1` (`fk_user1`,`fk_user2`),
  ADD KEY `fk_user2` (`fk_user2`);

--
-- Index pour la table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `groups_name_uk` (`name`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login_name` (`login_name`);

--
-- Index pour la table `users_groups`
--
ALTER TABLE `users_groups`
  ADD UNIQUE KEY `users_groups_uk` (`login_name`,`group_name`),
  ADD KEY `users_groups_group_name_fk` (`group_name`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `challenges`
--
ALTER TABLE `challenges`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `challenges_users`
--
ALTER TABLE `challenges_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `difficulties`
--
ALTER TABLE `difficulties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `friendship`
--
ALTER TABLE `friendship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `challenges`
--
ALTER TABLE `challenges`
  ADD CONSTRAINT `challenges_ibfk_1` FOREIGN KEY (`fk_cat`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `challenges_ibfk_2` FOREIGN KEY (`fk_diff`) REFERENCES `difficulties` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `challenges_ibfk_3` FOREIGN KEY (`fk_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `challenges_users`
--
ALTER TABLE `challenges_users`
  ADD CONSTRAINT `challenges_users_ibfk_1` FOREIGN KEY (`fk_giver`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `challenges_users_ibfk_2` FOREIGN KEY (`fk_getter`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `challenges_users_ibfk_3` FOREIGN KEY (`fk_chall`) REFERENCES `challenges` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `friendship_ibfk_1` FOREIGN KEY (`fk_user1`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `friendship_ibfk_2` FOREIGN KEY (`fk_user2`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `users_groups`
--
ALTER TABLE `users_groups`
  ADD CONSTRAINT `users_groups_group_name_fk` FOREIGN KEY (`group_name`) REFERENCES `groups` (`name`),
  ADD CONSTRAINT `users_groups_login_name_fk` FOREIGN KEY (`login_name`) REFERENCES `users` (`login_name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
