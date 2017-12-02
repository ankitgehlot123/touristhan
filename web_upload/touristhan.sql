-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2017 at 07:05 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `touristhan`
--

-- --------------------------------------------------------

--
-- Table structure for table `camp`
--

CREATE TABLE IF NOT EXISTS `camp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` int(10) NOT NULL,
  `place` varchar(30) NOT NULL,
  `available` tinyint(1) NOT NULL,
  `tourist` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `camp`
--

INSERT INTO `camp` (`id`, `name`, `price`, `place`, `available`, `tourist`) VALUES
(2, 'Shiva Adventures', 3000, 'Jaipur', 0, ''),
(3, 'Jai Treks and camps', 1500, 'Jaipur', 0, ''),
(4, 'Rajput Camps', 4200, 'Jodhpur', 0, ''),
(5, 'Jodhpur Adventure Sports', 1300, 'Jodhpur', 0, ''),
(6, 'Hunting Camp Lodge', 2550, 'Udaipur', 0, ''),
(7, 'Lake City Adventures', 2000, 'Udaipur', 0, ''),
(8, 'Sand Dunes Camps', 1600, 'Jaisalmer', 0, ''),
(9, 'Swiss Camps and Adventures', 3800, 'Jaisalmer', 0, ''),
(10, 'Abu Mountaineering', 3325, 'Mt.Abu', 0, ''),
(11, 'Adventures in Abu', 3660, 'Mt.Abu', 0, ''),
(12, 'Baba Treck', 3995, 'Others', 0, ''),
(13, 'Bajrang Camps', 4330, 'Others', 0, ''),
(14, 'Ani Adventures', 4665, 'Others', 0, ''),
(15, 'Binny Sports and Camps', 5000, 'Others', 0, ''),
(16, 'Hims Swiss Camp stay', 5335, 'Others', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `explore`
--

CREATE TABLE IF NOT EXISTS `explore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=62 ;

--
-- Dumping data for table `explore`
--

INSERT INTO `explore` (`id`, `city`, `place`) VALUES
(2, 'jaipur', 'Abhaneri'),
(3, 'jaipur', 'Samode'),
(4, 'jaipur', 'Bairath'),
(5, 'jaipur', 'Ramgarh lake'),
(6, 'jaipur', 'Sanganer'),
(7, 'jaipur', 'Bagru'),
(8, 'jaipur', 'Dundlod fort'),
(9, 'jaipur', 'Chandlai lake'),
(10, 'jaipur', 'Madhogarh Tunga'),
(11, 'jaipur', 'Jaisinghpura Khor'),
(12, 'jodhpur', 'Narlai'),
(13, 'jodhpur', 'Jaswant Sagar Dam'),
(14, 'jodhpur', 'Jai Pol'),
(15, 'jodhpur', 'Maha Mandir Temple'),
(16, 'jodhpur', 'Ganesh Mandir'),
(17, 'jodhpur', 'RIFF festival'),
(18, 'jodhpur', 'Salawas'),
(19, 'jodhpur', 'Takhat Sagar Lake'),
(20, 'jodhpur', 'Phalodi Fort'),
(21, 'jodhpur', 'Pali'),
(22, 'Udaipur', 'Nathdwara'),
(23, 'Udaipur', 'Solar Observatory'),
(24, 'Udaipur', 'Monsoon Palace'),
(25, 'Udaipur', 'Karni Mata'),
(26, 'Udaipur', 'Duddhtalai Musical Garden'),
(27, 'Udaipur', 'Alsigarh'),
(28, 'Udaipur', 'Ubeshwar'),
(29, 'Udaipur', 'Lakhsman Sagar Hunting Lodge'),
(30, 'Udaipur', 'Nagda'),
(31, 'Udaipur', 'Tree Houses'),
(32, 'Jaisalmer', 'Parasailing'),
(33, 'Jaisalmer', 'Paramonitring'),
(34, 'Jaisalmer', 'Adventure Safari'),
(35, 'Jaisalmer', 'Dune Bashing'),
(36, 'Jaisalmer', 'Desert Camping'),
(37, 'Jaisalmer', 'Camel Safari'),
(38, 'Jaisalmer', 'Swiss Tenting'),
(39, 'Jaisalmer', 'Gadisar Lake'),
(40, 'Jaisalmer', 'Khabab fort'),
(41, 'Jaisalmer', 'Salim Singh ki Haweli'),
(42, 'Mt. Abu', 'Wildlife Santury'),
(43, 'Mt. Abu', 'Toad Rock'),
(44, 'Mt. Abu', 'Gaumukh Temple'),
(45, 'Mt. Abu', 'Bhramha Kumaris'),
(46, 'Mt. Abu', 'Camping'),
(47, 'Mt. Abu', 'Adventure Sports'),
(48, 'Mt. Abu', 'Trekking'),
(49, 'Mt. Abu', 'Rock Climbing'),
(50, 'Mt. Abu', 'Achalgarh Fort'),
(51, 'Mt. Abu', 'Sadar Bazaar'),
(52, 'Others', 'Bhangarh Fort'),
(53, 'Others', 'Kubhalgarh Fort'),
(54, 'Others', 'Badnore'),
(55, 'Others', 'Bundi'),
(56, 'Others', 'Ramathra Fort'),
(57, 'Others', 'Mihir Garh'),
(58, 'Others', 'Aman Bagh'),
(59, 'Others', 'Jawai Dam'),
(60, 'Others', 'Barmer'),
(61, 'Others', 'Mahansar');

-- --------------------------------------------------------

--
-- Table structure for table `guide`
--

CREATE TABLE IF NOT EXISTS `guide` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `phone` int(15) NOT NULL,
  `fees` int(10) NOT NULL,
  `city` varchar(30) NOT NULL,
  `language` varchar(50) NOT NULL,
  `available` tinyint(1) NOT NULL,
  `tourist_name` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `guide`
--

INSERT INTO `guide` (`id`, `name`, `phone`, `fees`, `city`, `language`, `available`, `tourist_name`, `password`) VALUES
(2, 'Ankit', 123456789, 400, 'Jaipur', 'Hindi,English,Germen,French', 0, '', 'abc'),
(3, 'Jaideep', 234567891, 550, 'Jaipur', 'Hindi,English,French', 0, '', 'xyz'),
(4, 'Himanshu', 345678912, 635, 'Jodhpur', 'Hindi,English,Dutch', 0, '', 'qwe'),
(5, 'Krishna', 456789123, 400, 'Jodhpur', 'Hindi,English,Telgu,Germen', 0, '', 'asd'),
(6, 'Anif', 567900185, 325, 'Udaipur', 'Hindi,English,', 0, '', 'rty'),
(7, 'Aarif', 679010987, 560, 'Udaipur', 'Hindi,English,Swiss,germen', 0, '', 'xcv'),
(8, 'Joseph', 790121789, 780, 'Jaisalmer', 'Hindi,English,', 0, '', 'fgh'),
(9, 'Gaurav', 901232591, 450, 'Jaisalmer', 'Hindi,English,French', 0, '', 'tyu'),
(10, 'Dikki', 101234339, 650, 'Mt. Abu', 'Hindi,English,Germen,French', 0, '', 'ssd'),
(11, 'Tinku', 698763913, 700, 'Mt. Abu', 'Hindi,English,Dutch,French', 0, '', 'awe');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` int(10) NOT NULL,
  `place` varchar(30) NOT NULL,
  `available` tinyint(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `price`, `place`, `available`, `username`) VALUES
(2, 'Hotel Shiv', 1000, 'Jaipur', 0, ''),
(3, 'Hotel Jaipur palace', 2500, 'Jaipur', 0, ''),
(4, 'Hotel grand', 5000, 'Jaipur', 0, ''),
(5, 'ITC', 7000, 'Jaipur', 0, ''),
(6, 'City Palace', 9990, 'Jaipur', 0, ''),
(7, 'Hotel Ankit', 1000, 'Jodhpur', 0, ''),
(8, 'Hotel Jodhana', 2500, 'Jodhpur', 0, ''),
(9, 'Hotel Oberoi', 5000, 'Jodhpur', 0, ''),
(10, 'Hotel Taj', 7000, 'Jodhpur', 0, ''),
(11, 'ITC', 9990, 'Jodhpur', 0, ''),
(12, 'Hotel Jaideep', 1000, 'Udaipur', 0, ''),
(13, 'Hotel Lake View', 2500, 'Udaipur', 0, ''),
(14, 'Hotel City View', 5000, 'Udaipur', 0, ''),
(15, 'Lake Palace', 7000, 'Udaipur', 0, ''),
(16, 'Hotel Grand View', 9990, 'Udaipur', 0, ''),
(17, 'Hotel Himanshu', 1000, 'Jaisalmer', 0, ''),
(18, 'Hotel Fort View', 2500, 'Jaisalmer', 0, ''),
(19, 'Hotel Grand Sand', 7000, 'Jaisalmer', 0, ''),
(20, 'Swiss Tent Camps', 5000, 'Jaisalmer', 0, ''),
(21, 'Sam Camps', 9990, 'Jaisalmer', 0, ''),
(22, 'Hotel Lake View', 1000, 'Mt. Abu', 0, ''),
(23, 'Hotel Hilltop', 2500, 'Mt. Abu', 0, ''),
(24, 'Hotel Hillton', 5000, 'Mt. Abu', 0, ''),
(25, 'Grand Abu Inn', 7000, 'Mt. Abu', 0, ''),
(26, 'Chacha Inn', 9990, 'Mt. Abu', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `markers`
--

CREATE TABLE IF NOT EXISTS `markers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `address` varchar(80) NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Dumping data for table `markers`
--

INSERT INTO `markers` (`id`, `name`, `address`, `lat`, `lng`, `type`) VALUES
(17, 'Albert Hall museum', 'Jaipur', 26.911699, 75.819504, 'Museum'),
(15, 'Jal Mahal', 'Jaipur', 26.965599, 75.859200, 'Fort'),
(16, 'Nahargarh Fort', 'Jaipur', 26.937300, 75.815498, 'Fort'),
(14, 'Jaigarh Fort', 'Jaipur', 26.985100, 75.845596, 'Fort'),
(13, 'Jantar Mantar', 'Jaipur', 26.924801, 75.824600, 'Ancient Observatory'),
(12, 'City Palace', 'Jaipur', 26.925800, 75.823700, 'Fort'),
(11, 'Hawa Mahal', 'Jaipur', 26.923901, 75.826698, 'Fort'),
(10, 'Amer Fort', 'Jaipur', 26.985500, 75.851303, 'Fort'),
(18, 'Birla Mandir', 'Jaipur', 26.892200, 75.815498, 'Temple'),
(19, 'Jawahar Circle', 'Jaipur', 26.839899, 75.800903, 'Fort'),
(20, 'Mehrangarh', 'Jodhpur', 26.297800, 73.018501, 'Fort'),
(21, 'Umaid Bhawan', 'Jodhpur', 26.281099, 73.047699, 'Fort'),
(22, 'Jaswant Thada', 'Jodhpur', 26.303699, 73.024696, 'Fort'),
(23, 'Balsamand Lake', 'Jodhpur', 26.330200, 73.019501, 'Lake'),
(24, 'Mandore Garden', 'Jodhpur', 26.352501, 73.035400, 'Garden'),
(25, 'Ghanta Ghar', 'Jodhpur', 26.295099, 73.024002, 'Clock Tower'),
(26, 'Kayalana Lake', 'Jodhpur', 26.283300, 72.966698, 'Lake'),
(27, 'Machia Biological Park', 'Jodhpur', 26.302299, 72.976402, 'Park'),
(28, 'Rao Jodha Desert Rock Park', 'Jodhpur', 26.304399, 73.016701, 'Park'),
(29, 'Kunj Bihari Temple', 'Jodhpur', 26.293501, 73.019897, 'Temple'),
(30, 'City Palace', 'Udaipur', 24.576401, 73.683502, 'Fort'),
(31, 'Lake Pichola', 'Udaipur', 24.572001, 73.679001, 'Lake'),
(32, 'Jag Mandir', 'Udaipur', 24.567600, 73.677803, 'Temple'),
(33, 'Saheliyon-ki-Bari', 'Udaipur', 24.603001, 73.685204, 'Others'),
(34, 'Fateh Sagar Lake', 'Udaipur', 24.601400, 73.674202, 'Lake'),
(35, 'Gulab Bagh and Zoo', 'Udaipur', 24.341700, 73.413399, 'Zoo'),
(36, 'Ahar Cenotaphs', 'Udaipur', 24.587500, 73.719902, 'Others'),
(37, 'Shilpgram', 'Udaipur', 24.611000, 73.661598, 'Art Gallery'),
(38, 'Vintage and Classic Car Museum', 'Udaipur', 24.575701, 73.694504, 'Museum'),
(39, 'Bagore-ki-Haveli', 'Udaipur', 24.579800, 73.682297, 'Fort'),
(40, 'Jaiselmer Fort', 'Jaiselmer', 26.912399, 70.912300, 'Fort'),
(41, 'Bada Bagh', 'Jaiselmer', 26.955400, 70.886002, 'Garden'),
(42, 'The Thar Heritage Museum', 'Jaiselmer', 26.915800, 70.911903, 'Museum'),
(43, 'Desert Cultural Centre', 'Jaiselmer', 26.910700, 70.918999, 'Centre'),
(44, 'Patwon ki?Haveli', 'Jaiselmer', 26.545799, 70.545197, 'Fort'),
(45, 'Jaisalmer War Museum', 'Jaiselmer', 26.941000, 71.026199, 'Museum'),
(46, 'Tazia Tower', 'Jaiselmer', 26.913099, 70.912903, 'Tower'),
(47, 'Desert Sand Dunes', 'Jaiselmer', 26.876400, 70.579102, 'Dunes'),
(48, 'Dilwara Temples', 'Mt.Abu', 24.609400, 72.723000, 'Temples'),
(49, 'Nakki Lake', 'Mt.Abu', 24.594500, 72.706200, 'Lake'),
(50, 'Achalgarh Fort', 'Mt.Abu', 24.615601, 72.768700, 'Fort'),
(51, 'Guru Shikhar', 'Mt.Abu', 24.665300, 72.781898, 'Mountain Peak'),
(52, 'Arbuda Devi Temple', 'Mt.Abu', 24.604700, 72.712502, 'Temple'),
(53, 'Peace Park', 'Mt.Abu', 24.631399, 72.761803, 'Park'),
(54, 'Trevor''s Tank', 'Mt.Abu', 24.620100, 72.725304, 'Wildlife'),
(55, 'Sunset Point', 'Mt.Abu', 24.599600, 72.697899, 'Point');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
