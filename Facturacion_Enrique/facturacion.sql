-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-07-2016 a las 17:32:32
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `facturacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `NIF_Cliente` varchar(9) CHARACTER SET latin1 NOT NULL,
  `Nombre_Cliente` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Direccion_Cliente` varchar(100) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`NIF_Cliente`, `Nombre_Cliente`, `Direccion_Cliente`) VALUES
('10111213N', 'Fernando', 'Ayuda'),
('11111111N', 'Fernando', 'Ayuda!!!'),
('2222222', 'Fernando ', 'No Ayudas!!!');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `Num_Factura` int(11) NOT NULL,
  `Fecha_Factura` date NOT NULL,
  `IDR_Cliente` varchar(9) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`Num_Factura`, `Fecha_Factura`, `IDR_Cliente`) VALUES
(1, '2016-08-04', '10111213N'),
(2, '2016-07-20', '10111213N'),
(4, '2016-07-20', '10111213N'),
(5, '2016-07-21', '10111213N'),
(6, '2016-07-21', '11111111N'),
(7, '2016-07-21', '10111213N'),
(8, '2016-07-21', '11111111N'),
(9, '2016-07-21', '11111111N'),
(10, '2016-07-21', '11111111N'),
(11, '2016-07-21', '10111213N'),
(12, '2016-07-21', '10111213N'),
(13, '2016-07-21', '10111213N'),
(14, '2016-07-21', '11111111N'),
(15, '2016-07-21', '11111111N'),
(16, '2016-07-21', '10111213N'),
(17, '2016-07-21', '10111213N'),
(18, '2016-07-21', '10111213N'),
(19, '2016-07-21', '10111213N'),
(20, '2016-07-21', '10111213N'),
(21, '2016-07-21', '10111213N'),
(22, '2016-07-21', '10111213N'),
(23, '2016-07-21', '10111213N'),
(24, '2016-07-21', '10111213N'),
(25, '2016-07-21', '10111213N'),
(26, '2016-07-21', '10111213N'),
(27, '2016-07-21', '10111213N'),
(28, '2016-07-21', '10111213N'),
(29, '2016-07-21', '10111213N'),
(30, '2016-07-21', '10111213N'),
(31, '2016-07-21', '10111213N'),
(32, '2016-07-21', '10111213N'),
(33, '2016-07-21', '10111213N'),
(34, '2016-07-21', '10111213N'),
(35, '2016-07-21', '10111213N'),
(36, '2016-07-21', '10111213N'),
(37, '2016-07-21', '10111213N'),
(38, '2016-07-22', '10111213N'),
(39, '2016-07-22', '10111213N'),
(40, '2016-07-22', '10111213N'),
(41, '2016-07-22', '10111213N'),
(42, '2016-07-22', '10111213N'),
(43, '2016-07-22', '10111213N'),
(44, '2016-07-22', '10111213N'),
(45, '2016-07-22', '11111111N');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea`
--

CREATE TABLE `linea` (
  `ID_Linea` int(11) NOT NULL,
  `Cantidad_Linea` int(11) NOT NULL,
  `Precio_Linea` double NOT NULL,
  `IDR_Factura` int(11) DEFAULT NULL,
  `IDR_Producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID_Producto` int(11) NOT NULL,
  `Nombre_Producto` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `Precio_Producto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID_Producto`, `Nombre_Producto`, `Precio_Producto`) VALUES
(1, 'silla', 20),
(2, 'mesa', 200),
(3, 'Ordenador', 500),
(4, 'Pantallas', 120),
(5, 'Raton', 15);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`NIF_Cliente`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`Num_Factura`),
  ADD KEY `idr_cliente` (`IDR_Cliente`);

--
-- Indices de la tabla `linea`
--
ALTER TABLE `linea`
  ADD PRIMARY KEY (`ID_Linea`),
  ADD KEY `idr_factura` (`IDR_Factura`),
  ADD KEY `idr_producto` (`IDR_Producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID_Producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `Num_Factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT de la tabla `linea`
--
ALTER TABLE `linea`
  MODIFY `ID_Linea` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID_Producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`idr_cliente`) REFERENCES `cliente` (`nif_cliente`);

--
-- Filtros para la tabla `linea`
--
ALTER TABLE `linea`
  ADD CONSTRAINT `linea_ibfk_1` FOREIGN KEY (`idr_factura`) REFERENCES `factura` (`num_factura`),
  ADD CONSTRAINT `linea_ibfk_2` FOREIGN KEY (`idr_producto`) REFERENCES `producto` (`id_producto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
