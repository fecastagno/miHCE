-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2022 a las 10:25:12
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mihce`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

CREATE TABLE `consulta` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `especialista` varchar(40) NOT NULL,
  `motivoConsulta` varchar(400) NOT NULL,
  `tratamiento` varchar(400) NOT NULL,
  `observaciones` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`id`, `id_usuario`, `especialista`, `motivoConsulta`, `tratamiento`, `observaciones`) VALUES
(2, 3, 'Otorrinolaringologo', 'Molestias en el oido izquierdo', 'Aplicar 2 gotas por la noche', 'De continuar los dolores pasados los 4 dias, volver al consultorio'),
(27, 3, 'Pediatra', 'Fiebre alta', '2 días de reposo', 'De no bajar la fiebre, acudir a urgencias'),
(30, 3, 'Dermatólogo', 'Verrugas en la piel', 'Aplicar crema 1 vez por día durante 2 semanas.\nVolver al consultorio en 1 mes', ''),
(32, 3, 'Traumatología', 'Dolor al caminar', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `farmacos`
--

CREATE TABLE `farmacos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `medicacion` varchar(50) NOT NULL,
  `dosis` varchar(50) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `farmacos`
--

INSERT INTO `farmacos` (`id`, `id_usuario`, `medicacion`, `dosis`, `fechaInicio`, `fechaFin`) VALUES
(1, 3, 'Ibuprofeno', '1 c/6hs', '2020-08-22', '2020-08-29'),
(6, 3, 'Paracetamol', '1 x día', '2020-11-10', NULL),
(7, 3, 'Betadin', '2 gotas x día, por as noches', '2020-08-12', '2020-08-26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `misdatos`
--

CREATE TABLE `misdatos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `sexo` tinyint(1) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `edad` int(11) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `obraSocial` varchar(40) NOT NULL,
  `peso` int(11) NOT NULL,
  `altura` decimal(10,2) NOT NULL,
  `grupoSanguineo` varchar(4) DEFAULT NULL,
  `fuma` tinyint(1) NOT NULL,
  `bebeAlcohol` tinyint(1) NOT NULL,
  `alergia` varchar(100) DEFAULT NULL,
  `medicacionCronica` varchar(100) DEFAULT NULL,
  `nacionalidad` varchar(40) NOT NULL,
  `ocupacion` varchar(100) NOT NULL,
  `estadoCivil` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `misdatos`
--

INSERT INTO `misdatos` (`id`, `id_usuario`, `dni`, `sexo`, `fechaNacimiento`, `edad`, `direccion`, `obraSocial`, `peso`, `altura`, `grupoSanguineo`, `fuma`, `bebeAlcohol`, `alergia`, `medicacionCronica`, `nacionalidad`, `ocupacion`, `estadoCivil`) VALUES
(1, 1, 34814663, 1, '1989-09-14', 31, 'Martinez Castro 666', 'Omint', 65, '1.72', NULL, 0, 1, 'polvillo', NULL, 'argentino', 'estudiante', 'soltero'),
(67, 3, 34814663, 1, '2000-07-16', 20, 'Av. Córdoba 2020', 'Swiss Medical', 72, '1.80', 'B-', 0, 0, NULL, NULL, 'argentino', 'estudiante', 'casado'),
(68, 27, 12121321, 1, '1990-03-01', 32, 'Corrientes 352', 'Medifé', 69, '1.85', 'A+', 1, 1, 'No', 'No', 'Español', 'Desempleado', 'Soltero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `mail` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `mail`, `password`) VALUES
(1, 'Facundo', 'Castagno', 'fec@gmail.com', 'testing'),
(2, 'ip', '68', 'ip68@shmail.com', 'WEFGWe'),
(3, 'Ezequiel', 'Dimitri', 'eze@mail.com', '1234'),
(16, 'Facundo', 'Castagno', 'awdawd@gmail.com', '123123'),
(27, 'José', 'Fernández', 'j.fernandez@gmail.com', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`) USING BTREE;

--
-- Indices de la tabla `farmacos`
--
ALTER TABLE `farmacos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario3` (`id_usuario`);

--
-- Indices de la tabla `misdatos`
--
ALTER TABLE `misdatos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consulta`
--
ALTER TABLE `consulta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `farmacos`
--
ALTER TABLE `farmacos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `misdatos`
--
ALTER TABLE `misdatos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `id_usuario2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `farmacos`
--
ALTER TABLE `farmacos`
  ADD CONSTRAINT `id_usuario3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `misdatos`
--
ALTER TABLE `misdatos`
  ADD CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
