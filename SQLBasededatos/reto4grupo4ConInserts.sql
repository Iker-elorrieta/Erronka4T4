-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2023 a las 08:44:04
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto4grupo4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacen`
--

CREATE TABLE `almacen` (
  `CodProducto` int(11) NOT NULL,
  `CodClinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `almacen`
--

INSERT INTO `almacen` (`CodProducto`, `CodClinica`) VALUES
(1, 1),
(1, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

CREATE TABLE `animal` (
  `IdAnimal` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Especie` varchar(20) NOT NULL,
  `Sexo` varchar(20) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) VALUES
(1, 'Agallas', 5, 'Perro', 'H', '45894650J'),
(2, 'Garfield', 12, 'Gato', 'H', '45894650J'),
(3, 'pez', 1, 'pez', 'h', '12345678D'),
(4, 'loro', 2, 'loro', 'm', '87654321A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellidos` varchar(40) NOT NULL,
  `Contraseña` varchar(40) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `CantidadMascotas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `CantidadMascotas`) VALUES
('12345678D', 'a', 'b', '987654321', 'Praza Rafael, 05, 9º B', 1),
('45894650J', 'Sergio', 'Galera Frias', '1234567890', 'Travesía Gamboa, 618, 4º C', 2),
('87654321A', 'b', 'a', '123456789', 'Avinguda Peres, 0, 38º 5º', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinica`
--

CREATE TABLE `clinica` (
  `CodClinica` int(11) NOT NULL,
  `Ubicacion` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clinica`
--

INSERT INTO `clinica` (`CodClinica`, `Ubicacion`) VALUES
(1, 'Madrid'),
(2, 'Barcelona'),
(3, 'Bilbao');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

CREATE TABLE `consulta` (
  `CodConsulta` int(11) NOT NULL,
  `Precio` float NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `IdAnimal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) VALUES
(1, 5, '2023-04-24', '10:55:36', '61556765W', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `NumeroCuenta` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`NumeroCuenta`, `DNI`) VALUES
(1, '12345678D'),
(2, '45894650J'),
(3, '87654321A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellidos` varchar(40) NOT NULL,
  `Contraseña` varchar(40) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Salario` int(11) NOT NULL,
  `Antiguedad` int(11) NOT NULL,
  `Especializacion` varchar(30) NOT NULL,
  `CodClinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) VALUES
('05491864Q', 'Hilario', 'Quintana', '1', 'Avenida Cotto, 747, 2º E', 1300, 4, 'Limpieza', 1),
('13240462Y', 'Francisco-Jose ', 'Campo', '21', 'Praza Girón, 3, 4º', 1500, 2, 'Ventas', 3),
('22761890D', 'Ander', 'De la Iglesia Perex', '2', 'Praza Mario, 6, 13º F', 2000, 6, 'Admin', 2),
('22761891X', 'Gorka', 'De la Iglesia Perex', '3', 'Carrer Marcos, 0, 6º A', 2000, 7, 'Admin', 3),
('25688765L', 'Constantin ', 'Solis', '4', 'Avinguda Peres, 0, 38º 5º', 1700, 2, 'Perros', 2),
('25881680X', 'Erica ', 'Antunez', '5', 'Travesía Gamboa, 618, 4º C', 1500, 7, 'Ventas', 1),
('31130846R', 'Erik', ' de La Cruz', '6', 'Ruela Rascón, 66, 0º', 1700, 6, 'Gatos', 3),
('34321343F', 'Eduardo', 'Manos Tijeras', '7', 'Avinguda Toro, 0, Bajo 1º', 2000, 10, 'Admin', 1),
('41896850G', 'Itziar ', 'Valverde', '8', 'Praza Rafael, 05, 9º B', 1700, 1, 'Gatos', 1),
('43992652T', 'Mireia ', 'Novoa', '9', 'Calle Ruvalcaba, 9, 49º B', 1700, 3, 'Peces', 2),
('47044061E', 'Olivia ', 'Gilabert', '10', 'Passeig Gonzalo, 68, 92º 8º', 1300, 3, 'Limpieza', 3),
('58096482T', 'Severino ', 'Navarro', '11', 'Ronda Gastélum, 93, Bajos', 1700, 5, 'Perros', 3),
('59773519S', 'Nazaret ', 'Castro', '12', 'Passeig Morales, 3, 88º E', 1500, 4, 'Ventas', 2),
('61556765W', 'Sandra Maria ', 'Alcaide', '13', 'Carrer Laura, 373, Bajos', 1300, 5, 'Limpieza', 2),
('64585000B', 'Amaya ', 'Marin', '14', 'Travessera Ponce, 523, Bajo 6º', 1700, 2, 'Peces', 1),
('67437514L', 'Robert ', 'Guirado', '15', 'Rúa Cano, 734, 1º C', 1700, 6, 'Peces', 3),
('68488382V', 'Miquel ', 'Alba', '16', 'Ruela Aina, 8, 9º E', 1700, 6, 'Loros', 3),
('72904384L', 'Consuelo ', 'Rio', '17', 'Travessera Vega, 37, 60º 9º', 1700, 11, 'Loros', 1),
('74014233A', 'Roger ', 'San-Martin', '18', 'Travessera Millán, 5, 67º E', 1700, 9, 'Loros', 2),
('94565675W', 'Helena ', 'Freire', '19', 'Passeig Arnau, 75, 57º 4º', 1700, 8, 'Perros', 1),
('97275748P', 'Julia ', 'Alba', '20', 'Avinguda Cortés, 0, 7º 6º', 1700, 7, 'Gatos', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestion`
--

CREATE TABLE `gestion` (
  `CodGestion` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `CodProducto` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gestion`
--

INSERT INTO `gestion` (`CodGestion`, `Fecha`, `Hora`, `Cantidad`, `CodProducto`, `DNI`) VALUES
(1, '2023-04-24', '10:55:36', 3, 1, '22761891X');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `CodPedido` int(11) NOT NULL,
  `PrecioTotal` float NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `CantidadProductos` int(11) NOT NULL,
  `CodProducto` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) VALUES
(1, 10, '2023-04-26', '08:38:42', 2, 2, '45894650J');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CodProducto` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Stock` int(11) NOT NULL,
  `Precio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CodProducto`, `Nombre`, `Stock`, `Precio`) VALUES
(1, 'Correa', 34, 2),
(2, 'Pienso para perros', 15, 5),
(3, 'Pienso para gatos', 3, 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD KEY `fk_CodProducto_almacen` (`CodProducto`),
  ADD KEY `fk_CodClinica_almacen` (`CodClinica`);

--
-- Indices de la tabla `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`IdAnimal`),
  ADD KEY `fk_DNI_Animal` (`DNI`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `clinica`
--
ALTER TABLE `clinica`
  ADD PRIMARY KEY (`CodClinica`);

--
-- Indices de la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`CodConsulta`),
  ADD KEY `fk_DNI_Consulta` (`DNI`),
  ADD KEY `fk_IdAnimal_Consulta` (`IdAnimal`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`NumeroCuenta`),
  ADD KEY `fk_DNI_Cliente` (`DNI`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`DNI`),
  ADD KEY `fk_CodClinica_empleado` (`CodClinica`);

--
-- Indices de la tabla `gestion`
--
ALTER TABLE `gestion`
  ADD PRIMARY KEY (`CodGestion`),
  ADD KEY `fk_CodProducto_Gestion` (`CodProducto`),
  ADD KEY `fk_DNI_Gestion` (`DNI`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`CodPedido`),
  ADD KEY `fk_CodProducto_Pedido` (`CodProducto`),
  ADD KEY `fk_DNI_Pedido` (`DNI`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`CodProducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `animal`
--
ALTER TABLE `animal`
  MODIFY `IdAnimal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `consulta`
--
ALTER TABLE `consulta`
  MODIFY `CodConsulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `NumeroCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gestion`
--
ALTER TABLE `gestion`
  MODIFY `CodGestion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `CodPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `CodProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD CONSTRAINT `fk_CodClinica_almacen` FOREIGN KEY (`CodClinica`) REFERENCES `clinica` (`CodClinica`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CodProducto_almacen` FOREIGN KEY (`CodProducto`) REFERENCES `productos` (`CodProducto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `fk_DNI_Animal` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `fk_DNI_Consulta` FOREIGN KEY (`DNI`) REFERENCES `empleado` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_IdAnimal_Consulta` FOREIGN KEY (`IdAnimal`) REFERENCES `animal` (`IdAnimal`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_DNI_Cliente` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_CodClinica_empleado` FOREIGN KEY (`CodClinica`) REFERENCES `clinica` (`CodClinica`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gestion`
--
ALTER TABLE `gestion`
  ADD CONSTRAINT `fk_CodProducto_Gestion` FOREIGN KEY (`CodProducto`) REFERENCES `productos` (`CodProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DNI_Gestion` FOREIGN KEY (`DNI`) REFERENCES `empleado` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_CodProducto_Pedido` FOREIGN KEY (`CodProducto`) REFERENCES `productos` (`CodProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DNI_Pedido` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
