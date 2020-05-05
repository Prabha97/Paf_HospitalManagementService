-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2020 at 04:27 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `health-system`
--IT 18108378

-- --------------------------------------------------------

--
-- Table structure for table `authuser`
--

CREATE TABLE `authuser` (
  `id` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `user_role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authuser`
--

INSERT INTO `authuser` (`id`, `username`, `password`, `user_role`) VALUES
(1, 'janitha', '123', 'admin'),
(2, 'saumya ', '123', 'patient'),
(3, 'praba', '123', 'admin'),
(4, 'pramadya ', '1234 ', 'patient'),
(5, 'uvini ', '123', 'doctors'),
(7, 'chamathka', '123 ', 'doctors');

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `Department_ID` int(50) NOT NULL,
  `Hospital_ID` int(50) NOT NULL,
  `Department_Name` varchar(200) NOT NULL,
  `Head` varchar(200) NOT NULL,
  `Staff_Vacancies` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`Department_ID`, `Hospital_ID`, `Department_Name`, `Head`, `Staff_Vacancies`) VALUES
(1, 3, 'OPD', '2', 15),
(2, 1, 'OPD', '5', 10),
(3, 1, 'ENT', '4', 3);

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `DoctorID` int(5) NOT NULL,
  `DoctorName` varchar(100) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `MobileNo` int(10) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Specialization` varchar(250) NOT NULL,
  `HospitalName` varchar(200) NOT NULL,
  `DepartmentName` varchar(200) NOT NULL,
  `Status` varchar(15) NOT NULL DEFAULT 'Accepted'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`DoctorID`, `DoctorName`, `NIC`, `Address`, `MobileNo`, `Email`, `Specialization`, `HospitalName`, `DepartmentName`, `Status`) VALUES
(1, 'Sunil Fernando', '948575962V', 'Malabe', 774589622, 'araiana@gmail.com', 'Emergency Medicine Specialists', 'Browns Hospital', 'OPD', 'Accepted'),
(2, 'Taylor Swift', '894512635V', 'Colombo', 774518544, 'taytay@gmail.com', 'Eye Surgeon', 'Asiri Hospital', 'ENT', 'Accepted'),
(3, 'Harry Styles', '961254129V', 'Colombo 2', 771256322, 'harry@gmail.com', 'ENT Specialist', 'Royal Hospital', 'ENT', 'Accepted'),
(4, 'Chandler Bing', '604712599V', 'Kaduwela', 724589651, 'chandler@gmail.com', 'Eye Surgeon', 'National Hospital of Sri Lanka', 'ENT', 'Accepted'),
(5, 'Chainsmokers', '884517525V', 'Kaduwala', 774582144, 'chan@gmail.com', 'Critical Care Medicine Specialists', 'National Hospital of Sri Lanka', 'OPD', 'Accepted');

-- --------------------------------------------------------

--
-- Table structure for table `hospitals`
--

CREATE TABLE `hospitals` (
  `Hospital_ID` int(50) NOT NULL,
  `Hospital_Name` varchar(200) NOT NULL,
  `Hospital_Address` varchar(100) NOT NULL,
  `Hospital_City` varchar(100) NOT NULL,
  `Hospital_Phone` char(10) NOT NULL,
  `Hospital_Email` varchar(100) NOT NULL,
  `Hospital_Description` varchar(1000) NOT NULL,
  `Open_Hours` int(2) NOT NULL DEFAULT 24
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospitals`
--

INSERT INTO `hospitals` (`Hospital_ID`, `Hospital_Name`, `Hospital_Address`, `Hospital_City`, `Hospital_Phone`, `Hospital_Email`, `Hospital_Description`, `Open_Hours`) VALUES
(1, 'National Hospital of Sri Lanka', 'Colombo 007', 'Colombo', '0112691111', 'nationalhospital111@gmail.com', 'Srilankan National Hospital', 24),
(2, 'Royal Hospital', '652 CW.A.Silva Mawatha', 'Colombo 006', '0112586581', 'royal112@gmail.com', 'Better Services provider', 12),
(3, 'Browns Hospital', 'No. 43, Mahabage Road', 'Ragama', '0115100000', 'customercarebh@brownsgroup.com', 'You are in Safe Hands.', 24),
(4, 'Asiri Hospital', 'No. 181, Kirula Road', 'Colombo 5', '0114665500', 'info@asiri.lk', 'Asiri Medical Hospital', 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authuser`
--
ALTER TABLE `authuser`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`Department_ID`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`DoctorID`);

--
-- Indexes for table `hospitals`
--
ALTER TABLE `hospitals`
  ADD PRIMARY KEY (`Hospital_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authuser`
--
ALTER TABLE `authuser`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `Department_ID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `DoctorID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `hospitals`
--
ALTER TABLE `hospitals`
  MODIFY `Hospital_ID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
