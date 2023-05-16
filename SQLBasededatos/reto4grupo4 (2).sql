-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2023 a las 09:17:57
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
-- Estructura de tabla para la tabla `adopcion`
--

CREATE TABLE `adopcion` (
  `CodAdopcion` int(11) NOT NULL,
  `PrecioTotal` float NOT NULL,
  `Fecha` date NOT NULL,
  `CodMascota` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `adopcion`
--

INSERT INTO `adopcion` (`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) VALUES
(1, 35, '2023-04-24', 3, '87654321A'),
(2, 5, '2023-04-24', 4, '45894650J'),
(3, 15, '2023-05-03', 2, '87654321A');

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
(2, 1),
(2, 3),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(5, 1),
(6, 3),
(7, 3),
(8, 3),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 3),
(16, 3),
(17, 3),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 2),
(25, 2),
(26, 2),
(27, 2),
(28, 2),
(29, 2),
(30, 3),
(31, 3),
(32, 3),
(33, 3),
(34, 3),
(35, 3),
(36, 3),
(37, 3),
(38, 1),
(39, 1),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 1),
(47, 1),
(48, 1),
(49, 1),
(50, 1),
(51, 1),
(52, 2),
(53, 2),
(54, 2),
(55, 1),
(56, 1),
(57, 3),
(58, 1),
(59, 3),
(60, 1),
(61, 1),
(62, 1),
(63, 1),
(64, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(69, 1),
(70, 3),
(71, 3),
(72, 3);

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
  `DNI` varchar(9) NOT NULL,
  `URL_IMG` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`, `URL_IMG`) VALUES
(1, 'art', 3, 'Perros', 'H', '45894650J', 'imgReto2/animales/imgPerros/1.jpg'),
(2, 'trui', 2, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/2.jpg'),
(3, 'lig', 5, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/3.jpg'),
(4, 'ztt', 1, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/4.jpg'),
(5, 'zytu', 4, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/5.jpg'),
(6, 'pinu', 6, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/6.jpg'),
(7, 'qera', 2, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/7.jpg'),
(8, 'berto', 3, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/8.jpg'),
(9, 'verca', 5, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/9.jpg'),
(10, 'bulma', 1, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/10.jpg'),
(11, 'iods', 4, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/11.jpg'),
(12, 'fsdf', 6, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/12.jpg'),
(13, 'bserty', 2, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/13.jpg'),
(14, 'jsru', 3, 'Perros', 'M', '12345678D', 'imgReto2/animales/imgPerros/14.jpg'),
(15, 'jsdyu', 5, 'Perros', 'H', '12345678D', 'imgReto2/animales/imgPerros/15.jpg'),
(16, 'ayhsy', 1, 'Perros', 'M', '87654321A', 'imgReto2/animales/imgPerros/16.jpg'),
(17, 'aayaw', 4, 'Perros', 'H', '87654321A', 'imgReto2/animales/imgPerros/17.jpg'),
(18, 'nombre1', 2, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/1.jpg'),
(19, 'nombre2', 3, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/2.jpg'),
(20, 'nombre3', 1, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/3.jpg'),
(21, 'nombre4', 4, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/4.jpg'),
(22, 'nombre5', 2, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/5.jpg'),
(23, 'nombre6', 3, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/6.jpg'),
(24, 'nombre7', 1, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/7.jpg'),
(25, 'nombre8', 4, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/8.jpg'),
(26, 'nombre9', 2, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/9.jpg'),
(27, 'nombre10', 3, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/10.jpg'),
(28, 'nombre11', 1, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/11.jpg'),
(29, 'nombre12', 4, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/12.jpg'),
(30, 'nombre13', 2, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/13.jpg'),
(31, 'nombre14', 3, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/14.jpg'),
(32, 'nombre15', 1, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/15.jpg'),
(33, 'nombre16', 4, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/16.jpg'),
(34, 'nombre17', 2, 'Gatos', 'H', '87654321A', 'imgReto2/animales/imgGatos/17.jpg'),
(35, 'nombre18', 3, 'Gatos', 'M', '87654321A', 'imgReto2/animales/imgGatos/18.jpg'),
(36, 'nombre19', 3, 'Gatos', 'M', '45894650J', 'imgReto2/animales/imgGatos/19.jpg'),
(37, 'nombre20', 3, 'Gatos', 'M', '45894650J', 'imgReto2/animales/imgGatos/20.jpg'),
(38, 'Max', 3, 'Loros', 'H', '45894650J', 'imgReto2/animales/imgLoros/1.jpg'),
(39, 'Lola', 2, 'Loros', 'M', '45894650J', 'imgReto2/animales/imgLoros/2.jpg'),
(40, 'Pepe', 1, 'Loros', 'H', '45894650J', 'imgReto2/animales/imgLoros/3.jpg'),
(41, 'Rufus', 4, 'Loros', 'M', '45894650J', 'imgReto2/animales/imgLoros/4.jpg'),
(42, 'Paco', 2, 'Loros', 'H', '45894650J', 'imgReto2/animales/imgLoros/5.jpg'),
(43, 'Luna', 1, 'Loros', 'M', '45894650J', 'imgReto2/animales/imgLoros/6.jpg'),
(44, 'Nacho', 3, 'Loros', 'H', '45894650J', 'imgReto2/animales/imgLoros/7.jpg'),
(45, 'Loli', 2, 'Loros', 'M', '45894650J', 'imgReto2/animales/imgLoros/8.jpg'),
(46, 'Rocky', 1, 'Loros', 'H', '45894650J', 'imgReto2/animales/imgLoros/9.jpg'),
(47, 'Nemo', 1, 'Pez', 'H', '45894650J', 'imgReto2/animales/imgPez/1.jpg'),
(48, 'Dory', 2, 'Pez', 'M', '12345678D', 'imgReto2/animales/imgPez/2.jpg'),
(49, 'Bubbles', 1, 'Pez', 'H', '12345678D', 'imgReto2/animales/imgPez/3.jpg'),
(50, 'Flounder', 3, 'Pez', 'M', '12345678D', 'imgReto2/animales/imgPez/4.jpg'),
(51, 'Gill', 2, 'Pez', 'H', '12345678D', 'imgReto2/animales/imgPez/5.jpg'),
(52, 'Sebastian', 1, 'Pez', 'H', '12345678D', 'imgReto2/animales/imgPez/6.jpg'),
(53, 'Jacques', 1, 'Pez', 'H', '12345678D', 'imgReto2/animales/imgPez/7.jpg'),
(54, 'Darla', 2, 'Pez', 'M', '12345678D', 'imgReto2/animales/imgPez/8.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellidos` varchar(40) NOT NULL,
  `Contraseña` varchar(40) NOT NULL,
  `Direccion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) VALUES
('12345678D', 'a', 'b', '987654321', 'Praza Rafael, 05, 9º B'),
('45894650J', 'Sergio', 'Galera Frias', '1234567890', 'Travesía Gamboa, 618, 4º C'),
('87654321A', 'b', 'a', '123456789', 'Avinguda Peres, 0, 38º 5º');

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
(1, 5, '2023-04-24', '10:55:36', '61556765W', 4),
(2, 19, '2023-04-24', '10:55:36', '47044061E', 2),
(3, 15, '2023-05-03', '08:39:04', '64585000B', 1);

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
('22761890D', 'Ander', 'De la Iglesia Perex', '2', 'Praza Mario, 6, 13º F', 2400, 6, 'Admin', 2),
('22761891X', 'Gorka', 'De la Iglesia Perex', '3', 'Carrer Marcos, 0, 6º A', 2000, 7, 'Admin', 3),
('25688765L', 'Constantin ', 'Solis', '4', 'Avinguda Peres, 0, 38º 5º', 1700, 2, 'Perros', 2),
('25881680X', 'Erica ', 'Antunez', '5', 'Travesía Gamboa, 618, 4º C', 1500, 7, 'Ventas', 1),
('31130846R', 'Erik', ' de La Cruz', '6', 'Ruela Rascón, 66, 0º', 1700, 6, 'Gatos', 3),
('34321343F', 'Eduardo', 'Manos Tijeras', '7', 'Avinguda Toro, 0, Bajo 1º', 2000, 10, 'Admin', 1),
('41896850G', 'Itziar ', 'Valverde', '8', 'Praza Rafael, 05, 9º B', 1700, 1, 'Gatos', 1),
('43992652T', 'Mireia ', 'Novoa', '9', 'Calle Ruvalcaba, 9, 49º B', 1700, 3, 'Pez', 2),
('47044061E', 'Olivia ', 'Gilabert', '10', 'Passeig Gonzalo, 68, 92º 8º', 1300, 3, 'Limpieza', 3),
('58096482T', 'Severino ', 'Navarro', '11', 'Ronda Gastélum, 93, Bajos', 1700, 5, 'Perros', 3),
('59773519S', 'Nazaret ', 'Castro', '12', 'Passeig Morales, 3, 88º E', 1500, 4, 'Ventas', 2),
('61556765W', 'Sandra Maria ', 'Alcaide', '13', 'Carrer Laura, 373, Bajos', 1300, 5, 'Limpieza', 2),
('64585000B', 'Amaya ', 'Marin', '14', 'Travessera Ponce, 523, Bajo 6º', 1700, 2, 'Pez', 1),
('67437514L', 'Robert ', 'Guirado', '15', 'Rúa Cano, 734, 1º C', 1700, 6, 'Pez', 3),
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
  `Cantidad` int(11) NOT NULL,
  `CodProducto` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gestion`
--

INSERT INTO `gestion` (`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) VALUES
(1, '2023-04-24', 3, 1, '22761891X'),
(2, '2023-04-24', 3, 3, '64585000B'),
(3, '2023-05-03', 4, 2, '68488382V');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestionanimal`
--

CREATE TABLE `gestionanimal` (
  `CodGestionAnimal` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `CodMascota` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gestionanimal`
--

INSERT INTO `gestionanimal` (`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) VALUES
(1, '2023-04-24', 3, 3, '68488382V');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `mascotaconmascitas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `mascotaconmascitas` (
`IdAnimal` int(11)
,`Nombre` varchar(20)
,`Cantidad de consultas` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `CodMascota` int(11) NOT NULL,
  `Especie` varchar(20) NOT NULL,
  `Stock` int(11) NOT NULL,
  `Precio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascotas`
--

INSERT INTO `mascotas` (`CodMascota`, `Especie`, `Stock`, `Precio`) VALUES
(1, 'Perro', 15, 26),
(2, 'Gato', 19, 15),
(3, 'Loro', 5, 35),
(4, 'Pez', 50, 5);

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
(1, 10, '2023-04-26', '10:55:36', 2, 2, '45894650J'),
(2, 35, '2023-04-24', '10:55:36', 2, 1, '45894650J'),
(3, 15, '2023-05-03', '08:39:04', 5, 1, '87654321A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CodProducto` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Stock` int(11) NOT NULL,
  `Precio` float NOT NULL,
  `URL_IMG` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CodProducto`, `Nombre`, `Stock`, `Precio`, `URL_IMG`) VALUES
(1, 'cama', 34, 2, 'imgReto2/productos/productosPerro/1.jpg'),
(2, 'cama', 34, 2, 'imgReto2/productos/productosPerro/2.jpg'),
(3, 'cama', 34, 2, 'imgReto2/productos/productosPerro/3.jpg'),
(4, 'cuenco', 34, 2, 'imgReto2/productos/productosPerro/4.jpg'),
(5, 'cuenco', 34, 2, 'imgReto2/productos/productosPerro/5.jpg'),
(6, 'collar', 34, 2, 'imgReto2/productos/productosPerro/6.jpg'),
(7, 'collar', 34, 2, 'imgReto2/productos/productosPerro/19.jpg'),
(8, 'correa', 34, 2, 'imgReto2/productos/productosPerro/7.jpg'),
(9, 'correa', 34, 2, 'imgReto2/productos/productosPerro/8.jpg'),
(10, 'correa', 34, 2, 'imgReto2/productos/productosPerro/9.jpg'),
(11, 'arnes', 34, 2, 'imgReto2/productos/productosPerro/10.jpg'),
(12, 'arnes', 34, 2, 'imgReto2/productos/productosPerro/11.jpg'),
(13, 'arnes', 34, 2, 'imgReto2/productos/productosPerro/12.jpg'),
(14, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/13.jpg'),
(15, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/14.jpg'),
(16, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/15.jpg'),
(17, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/16.jpg'),
(18, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/17.jpg'),
(19, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/18.jpg'),
(20, 'juguete', 34, 2, 'imgReto2/productos/productosPerro/20.jpg'),
(21, 'jaula', 34, 2, 'imgReto2/productos/ProductosLoro/1.jpg'),
(22, 'jaula', 34, 2, 'imgReto2/productos/ProductosLoro/2.jpg'),
(23, 'jaula', 34, 2, 'imgReto2/productos/ProductosLoro/3.jpg'),
(24, 'jaula', 34, 2, 'imgReto2/productos/ProductosLoro/4.jpg'),
(25, 'jaula', 34, 2, 'imgReto2/productos/ProductosHamster/1.jpg'),
(26, 'jaula', 34, 2, 'imgReto2/productos/ProductosHamster/2.jpg'),
(27, 'jaula', 34, 2, 'imgReto2/productos/ProductosHamster/3.jpg'),
(28, 'jaula', 34, 2, 'imgReto2/productos/ProductosHamster/4.jpg'),
(29, 'jaula', 34, 2, 'imgReto2/productos/ProductosHamster/5.jpg'),
(30, 'cama', 34, 2, 'imgReto2/productos/ProductosGato/1.jpg'),
(31, 'cama', 34, 2, 'imgReto2/productos/ProductosGato/2.jpg'),
(32, 'cama', 34, 2, 'imgReto2/productos/ProductosGato/3.jpg'),
(33, 'juguete', 34, 2, 'imgReto2/productos/ProductosGato/4.jpg'),
(34, 'juguete', 34, 2, 'imgReto2/productos/ProductosGato/5.jpg'),
(35, 'cama', 34, 2, 'imgReto2/productos/ProductosGato/6.jpg'),
(36, 'cama', 34, 2, 'imgReto2/productos/ProductosGato/7.jpg'),
(37, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/1.jpg'),
(38, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/2.jpg'),
(39, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/3.jpg'),
(40, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/4.jpg'),
(41, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/5.jpg'),
(42, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/6.jpg'),
(43, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/7.jpg'),
(44, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/8.jpg'),
(45, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/9.jpg'),
(46, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/10.jpg'),
(47, 'comidaGato', 34, 2, 'imgReto2/productos/ProductosGato/ComidaGato/11.jpg'),
(48, 'comidaHamster', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/1.jpg'),
(49, 'comidaHamster', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/2.jpg'),
(50, 'ComidaHasmter', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/3.jpg'),
(51, 'ComidaHasmter', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/4.jpg'),
(52, 'ComidaHasmter', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/5.jpg'),
(53, 'ComidaHasmter', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/6.jpg'),
(54, 'ComidaHasmter', 34, 2, 'imgReto2/productos/ProductosHamster/ComidaHasmter/7.jpg'),
(55, 'ComidaLoro', 34, 2, 'imgReto2/productos/ProductosLoro/ComidaLoro/1.jpg'),
(56, 'ComidaLoro', 34, 2, 'imgReto2/productos/ProductosLoro/ComidaLoro/2.jpg'),
(57, 'ComidaLoro', 34, 2, 'imgReto2/productos/ProductosLoro/ComidaLoro/3.jpg'),
(58, 'ComidaLoro', 34, 2, 'imgReto2/productos/ProductosLoro/ComidaLoro/4.jpg'),
(59, 'ComidaLoro', 34, 2, 'imgReto2/productos/ProductosLoro/ComidaLoro/5.jpg'),
(60, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/1.jpg'),
(61, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/2.jpg'),
(62, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/3.jpg'),
(63, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/4.jpg'),
(64, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/5.jpg'),
(65, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/6.jpg'),
(66, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/7.jpg'),
(67, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/8.jpg'),
(68, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/9.jpg'),
(69, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/10.jpg'),
(70, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/11.jpg'),
(71, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/12.jpg'),
(72, 'ComidaPerro', 34, 2, 'imgReto2/productos/productosPerro/comidaPerro/13.jpg');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `productosconpocostock`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `productosconpocostock` (
`CodProducto` int(11)
,`Nombre` varchar(20)
,`Stock` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `productospedidos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `productospedidos` (
`CodProducto` int(11)
,`Nombre` varchar(20)
,`Cantidad de pedidos` bigint(21)
,`Cantidad de veces solicitadas` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiene`
--

CREATE TABLE `tiene` (
  `CodMascota` int(11) NOT NULL,
  `CodClinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiene`
--

INSERT INTO `tiene` (`CodMascota`, `CodClinica`) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(4, 2),
(4, 3);

-- --------------------------------------------------------

--
-- Estructura para la vista `mascotaconmascitas`
--
DROP TABLE IF EXISTS `mascotaconmascitas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mascotaconmascitas`  AS SELECT `a`.`IdAnimal` AS `IdAnimal`, `a`.`Nombre` AS `Nombre`, count(`c`.`CodConsulta`) AS `Cantidad de consultas` FROM (`consulta` `c` join `animal` `a` on(`c`.`IdAnimal` = `a`.`IdAnimal`)) GROUP BY `a`.`IdAnimal`, `a`.`Nombre` HAVING count(`c`.`CodConsulta`) >= 11  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `productosconpocostock`
--
DROP TABLE IF EXISTS `productosconpocostock`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `productosconpocostock`  AS SELECT `productos`.`CodProducto` AS `CodProducto`, `productos`.`Nombre` AS `Nombre`, `productos`.`Stock` AS `Stock` FROM `productos` WHERE `productos`.`Stock` <= 33  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `productospedidos`
--
DROP TABLE IF EXISTS `productospedidos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `productospedidos`  AS SELECT `p`.`CodProducto` AS `CodProducto`, `p`.`Nombre` AS `Nombre`, count(`pe`.`CodPedido`) AS `Cantidad de pedidos`, sum(`pe`.`CantidadProductos`) AS `Cantidad de veces solicitadas` FROM (`productos` `p` join `pedidos` `pe` on(`p`.`CodProducto` = `pe`.`CodProducto`)) GROUP BY `p`.`CodProducto`, `p`.`Nombre` HAVING count(`pe`.`CodPedido`) >= 11  ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adopcion`
--
ALTER TABLE `adopcion`
  ADD PRIMARY KEY (`CodAdopcion`),
  ADD KEY `fk_CodMascota_Adopcion` (`CodMascota`),
  ADD KEY `fk_DNI_Adopcion` (`DNI`);

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
-- Indices de la tabla `gestionanimal`
--
ALTER TABLE `gestionanimal`
  ADD PRIMARY KEY (`CodGestionAnimal`),
  ADD KEY `fk_CodMascota_GestionAnimal` (`CodMascota`),
  ADD KEY `fk_DNI_GestionAnimal` (`DNI`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`CodMascota`);

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
-- Indices de la tabla `tiene`
--
ALTER TABLE `tiene`
  ADD KEY `fk_CodMascota_Tiene` (`CodMascota`),
  ADD KEY `fk_CodClinica_Tiene` (`CodClinica`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adopcion`
--
ALTER TABLE `adopcion`
  MODIFY `CodAdopcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `animal`
--
ALTER TABLE `animal`
  MODIFY `IdAnimal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `consulta`
--
ALTER TABLE `consulta`
  MODIFY `CodConsulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `NumeroCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gestion`
--
ALTER TABLE `gestion`
  MODIFY `CodGestion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gestionanimal`
--
ALTER TABLE `gestionanimal`
  MODIFY `CodGestionAnimal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `CodMascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `CodPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `CodProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `adopcion`
--
ALTER TABLE `adopcion`
  ADD CONSTRAINT `fk_CodMascota_Adopcion` FOREIGN KEY (`CodMascota`) REFERENCES `mascotas` (`CodMascota`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DNI_Adopcion` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Filtros para la tabla `gestionanimal`
--
ALTER TABLE `gestionanimal`
  ADD CONSTRAINT `fk_CodMascota_GestionAnimal` FOREIGN KEY (`CodMascota`) REFERENCES `mascotas` (`CodMascota`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DNI_GestionAnimal` FOREIGN KEY (`DNI`) REFERENCES `empleado` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_CodProducto_Pedido` FOREIGN KEY (`CodProducto`) REFERENCES `productos` (`CodProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DNI_Pedido` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tiene`
--
ALTER TABLE `tiene`
  ADD CONSTRAINT `fk_CodClinica_Tiene` FOREIGN KEY (`CodClinica`) REFERENCES `clinica` (`CodClinica`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CodMascota_Tiene` FOREIGN KEY (`CodMascota`) REFERENCES `mascotas` (`CodMascota`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
