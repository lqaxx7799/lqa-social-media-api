-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 12:51 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lqa_social_media_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `email` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `username` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `gender` varchar(16) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `bio` text COLLATE utf8_vietnamese_ci NOT NULL,
  `profile_picture_url` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `phone_number` varchar(16) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `email`, `password`, `username`, `name`, `date_of_birth`, `gender`, `bio`, `profile_picture_url`, `phone_number`) VALUES
(1, 'qanh@gmail.com', '7ce0359f12857f2a90c7de465f40a95f01cb5da9', 'qanhqanh', 'Lam Quoc Anh', '1999-11-07 00:00:00', 'male', 'This is Qanh\'s account', NULL, '0123456789'),
(2, 'anna@gmail.com', '7ce0359f12857f2a90c7de465f40a95f01cb5da9', 'annaanna', 'Anna Bell', '1999-10-07 00:00:00', 'female', 'This is Anna\'s account', NULL, '0123456789'),
(3, 'joseph@gmail.com', '7ce0359f12857f2a90c7de465f40a95f01cb5da9', 'josephjoseph', 'Jo Seph', '1999-06-07 00:00:00', 'male', 'This is Jo Seph\'s account', NULL, '0123456789');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `content` text COLLATE utf8_vietnamese_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `is_deleted` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `thumbnail_url` text COLLATE utf8_vietnamese_ci NOT NULL,
  `caption` text COLLATE utf8_vietnamese_ci NOT NULL,
  `account_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `is_deleted` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `thumbnail_url`, `caption`, `account_id`, `created_time`, `is_deleted`) VALUES
(1, 'https://www.awakenthegreatnesswithin.com/wp-content/uploads/2018/08/Nature-Quotes-1.jpg', 'Beautiful', 2, '2021-05-05 17:03:17', b'0'),
(2, 'https://jssors8.azureedge.net/demos/image-slider/img/faded-monaco-scenery-evening-dark-picjumbo-com-image.jpg', 'Another one', 2, '2021-05-14 17:03:17', b'0'),
(3, 'https://lokeshdhakar.com/media/posts/roadtrip-2004/trip01.jpg', 'Bridge', 3, '2021-05-13 17:04:48', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `reaction`
--

CREATE TABLE `reaction` (
  `account_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_following`
--

CREATE TABLE `user_following` (
  `account_id` int(11) NOT NULL,
  `follow_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `user_following`
--

INSERT INTO `user_following` (`account_id`, `follow_id`, `created_time`) VALUES
(1, 2, '2021-05-14 17:05:19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `reaction`
--
ALTER TABLE `reaction`
  ADD PRIMARY KEY (`account_id`,`post_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `user_following`
--
ALTER TABLE `user_following`
  ADD PRIMARY KEY (`account_id`,`follow_id`),
  ADD KEY `follow_id` (`follow_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `reaction`
--
ALTER TABLE `reaction`
  ADD CONSTRAINT `reaction_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `reaction_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Constraints for table `user_following`
--
ALTER TABLE `user_following`
  ADD CONSTRAINT `user_following_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `user_following_ibfk_2` FOREIGN KEY (`follow_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
