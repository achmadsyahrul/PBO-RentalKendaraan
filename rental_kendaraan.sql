-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2020 at 05:25 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_kendaraan`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `NIK` varchar(50) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `no_hp` varchar(50) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `NIK`, `nama`, `no_hp`, `alamat`) VALUES
(1, '37140000', 'Achmad Syahrul', '085543212322', 'Yogyakarta'),
(2, '34710000', 'Bariq Jauhar', '08123432123', 'Bantul'),
(3, '37410000', 'Maudy Ayunda', '0876464646', 'Gunung Kidul'),
(4, '31740000', 'Sukardi', '085678763210', 'Bantul');

-- --------------------------------------------------------

--
-- Table structure for table `kendaraan`
--

CREATE TABLE `kendaraan` (
  `id` int(11) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `plat` varchar(50) NOT NULL,
  `warna` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kendaraan`
--

INSERT INTO `kendaraan` (`id`, `merk`, `plat`, `warna`, `status`) VALUES
(1, 'Avanza', 'AB 123 BA', 'Silver', 1),
(2, 'Supra X', 'AB 3240 AF', 'Hitam', 1),
(3, 'Vario', 'AB 2132 BB', 'Merah', 0),
(4, 'Jazz', 'AB 7455 JZ', 'Putih', 1),
(6, 'Supra X', 'AB 2230 FA', 'Hitam', 0),
(7, 'Jazz', 'AB 1559 IO', 'Hitam', 1),
(8, 'Brio', 'AB 5654 SS', 'Hitam', 0);

-- --------------------------------------------------------

--
-- Table structure for table `menyewa`
--

CREATE TABLE `menyewa` (
  `id` int(11) NOT NULL,
  `NIK` varchar(50) NOT NULL,
  `id_kend` int(11) DEFAULT NULL,
  `tgl_sewa` datetime NOT NULL,
  `lama` int(11) NOT NULL,
  `tgl_kembali` datetime DEFAULT NULL,
  `tarif` int(11) DEFAULT NULL,
  `denda` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menyewa`
--

INSERT INTO `menyewa` (`id`, `NIK`, `id_kend`, `tgl_sewa`, `lama`, `tgl_kembali`, `tarif`, `denda`, `total`) VALUES
(3, '37410000', 3, '2020-05-12 09:00:00', 2, '2020-05-14 07:00:00', 150000, 0, 150000),
(4, '34710000', 6, '2020-05-12 10:00:00', 1, '2020-05-13 11:10:00', 150000, 12500, 162500),
(5, '37140000', 5, '2020-05-14 10:15:00', 1, '2020-05-15 10:30:00', 150000, 0, 150000),
(6, '31740000', 1, '2020-05-14 10:10:00', 2, '2020-05-16 12:00:00', 500000, 41666, 541666),
(7, '37410000', 3, '2020-05-15 09:25:00', 1, NULL, 150000, NULL, NULL),
(8, '37140000', 8, '2020-05-15 10:05:00', 2, NULL, 550000, NULL, NULL),
(9, '34710000', 6, '2020-05-16 09:00:00', 1, NULL, 150000, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `merk`
--

CREATE TABLE `merk` (
  `id` int(11) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `tarif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merk`
--

INSERT INTO `merk` (`id`, `merk`, `kategori`, `tarif`) VALUES
(1, 'Avanza', 'Mobil', 500000),
(2, 'Vario', 'Motor', 150000),
(3, 'Supra X', 'Motor', 150000),
(4, 'Jazz', 'Mobil', 600000),
(5, 'Brio', 'Mobil', 550000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `NIK` (`NIK`);

--
-- Indexes for table `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `plat` (`plat`),
  ADD KEY `merk` (`merk`);

--
-- Indexes for table `menyewa`
--
ALTER TABLE `menyewa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nik` (`NIK`),
  ADD KEY `id_kend` (`id_kend`);

--
-- Indexes for table `merk`
--
ALTER TABLE `merk`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `merk` (`merk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `kendaraan`
--
ALTER TABLE `kendaraan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `menyewa`
--
ALTER TABLE `menyewa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `merk`
--
ALTER TABLE `merk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD CONSTRAINT `merk` FOREIGN KEY (`merk`) REFERENCES `merk` (`merk`);

--
-- Constraints for table `menyewa`
--
ALTER TABLE `menyewa`
  ADD CONSTRAINT `id_kend` FOREIGN KEY (`id_kend`) REFERENCES `kendaraan` (`id`),
  ADD CONSTRAINT `nik` FOREIGN KEY (`NIK`) REFERENCES `customer` (`NIK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
