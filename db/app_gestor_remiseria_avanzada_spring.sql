-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2025 a las 19:51:48
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `app_gestor_remiseria_avanzada_spring`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auto`
--

CREATE TABLE `auto` (
  `id` bigint(20) NOT NULL,
  `disponible` bit(1) NOT NULL,
  `kilometraje` double NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auto`
--

INSERT INTO `auto` (`id`, `disponible`, `kilometraje`, `marca`, `modelo`) VALUES
(1, b'0', 20105, 'Toyota', 'Corolla'),
(2, b'1', 50000, 'Fiat', 'Cronos'),
(3, b'1', 45000, 'VolksWagen', 'Logan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chofer`
--

CREATE TABLE `chofer` (
  `id` bigint(20) NOT NULL,
  `auto_propio` bit(1) NOT NULL,
  `celular` bigint(20) DEFAULT NULL,
  `disponible` bit(1) NOT NULL,
  `dni` bigint(20) DEFAULT NULL,
  `eliminado` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `auto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `chofer`
--

INSERT INTO `chofer` (`id`, `auto_propio`, `celular`, `disponible`, `dni`, `eliminado`, `nombre`, `auto_id`) VALUES
(1, b'0', 123123123, b'1', 123123, b'1', 'choferSinAuto', NULL),
(2, b'1', 123451234454, b'0', 44351653, b'0', 'choferConAuto', NULL),
(3, b'0', 123123123, b'1', 678678, b'0', 'choferSinAuto', 1),
(4, b'1', 123123, b'1', 123123, b'1', 'te borro', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `password`, `username`) VALUES
(2, '$2a$10$j3Ow2YucXLYyVYlq8cU6SuLkxHBIu0ObDWuJPQ4wE5UCk6HXsiI86', 'admin'),
(3, '$2a$10$Hth1OxRP3yCqwwxrgvrl1Ozb50a94XtvhByfzP/O47JnBvC6cPjp.', 'mariano'),
(7, '$2a$10$qKnYX5nlhlY6iw4RWKD8vOCDCC5zY5R/g2FSKNZdfqwV9sNVekrhO', 'invitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `id` bigint(20) NOT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `estado_viaje` enum('ENCURSO','FINALIZADO') DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `kilometros` double NOT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `valor_km` double NOT NULL,
  `chofer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `viaje`
--

INSERT INTO `viaje` (`id`, `destino`, `estado_viaje`, `fecha`, `kilometros`, `origen`, `valor_km`, `chofer_id`) VALUES
(10, 'Calle 123', 'FINALIZADO', '2025-08-06 14:49:00.000000', 10, 'Calle 123', 850, 2),
(11, 'Calle 456', 'FINALIZADO', '2025-08-06 14:50:00.000000', 12, 'Calle 456', 850, 3),
(12, 'Calle 789', 'ENCURSO', '2025-08-06 14:50:00.000000', 14, 'Calle 789', 850, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `chofer`
--
ALTER TABLE `chofer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK3kqy4w7fuam6f6wm88xp69nb2` (`auto_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK34e3r4ak38sa952qtwpebphro` (`chofer_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auto`
--
ALTER TABLE `auto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `chofer`
--
ALTER TABLE `chofer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `chofer`
--
ALTER TABLE `chofer`
  ADD CONSTRAINT `FKpr85e6w77t34x2usw73wcwgk` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `FK34e3r4ak38sa952qtwpebphro` FOREIGN KEY (`chofer_id`) REFERENCES `chofer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
