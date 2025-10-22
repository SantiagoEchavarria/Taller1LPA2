-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando datos para la tabla libreria.libros: ~7 rows (aproximadamente)
REPLACE INTO `libros` (`id`, `anio`, `autor`, `edicion`, `editorial`, `isbn`, `titulo`, `disponible`) VALUES
	(5, '1510', 'Miguel de Cervantes', '22', 'Puzo', '978-msjddj', 'El quijote', b'0'),
	(6, '1970', 'Puzo', '7', 'Grijalbo', '977-msxccj', 'El padrino', b'0'),
	(7, '1970', 'Puzo', '7', 'Grijalbo', '978-msxccj', 'El padrino', b'0'),
	(8, '1970', 'Puzo', '7', 'Grijalbo', '979-msxccj', 'El padrino', b'0'),
	(9, '1970', 'Puzo', '7', 'Grijalbo', '980-msxccj', 'El padrino', b'0'),
	(10, '1970', 'Puzo', '7', 'Grijalbo', '981-msxccj', 'El padrino', b'0');

-- Volcando datos para la tabla libreria.prestamos: ~5 rows (aproximadamente)
REPLACE INTO `prestamos` (`id`, `fecha_devolucion`, `fecha_prestamo`, `observacion`, `libro_id`, `usuario_id`) VALUES
	(8, '2025-09-20', '2025-09-15', 'Buena Condición ', 5, 2),
	(9, '2025-09-19', '2025-09-15', 'Condición regular', 6, 3),
	(10, '2025-09-15', '2025-09-15', 'Condición regular', 7, 2),
	(11, '2025-09-15', '2025-09-15', 'Condición regular', 8, 2),
	(12, '2025-09-15', '2025-09-15', 'Condición regular', 9, 2),
	(13, '2025-09-15', '2025-09-15', 'Condición regular', 10, 2);

-- Volcando datos para la tabla libreria.roles: ~2 rows (aproximadamente)
REPLACE INTO `roles` (`id`, `nombre`, `usuarior_id`) VALUES
	(1, 'ROLE_ADMIN', 1),
	(2, 'ROLE_USER', 2);

-- Volcando datos para la tabla libreria.usuariors: ~2 rows (aproximadamente)
REPLACE INTO `usuariors` (`id`, `activo`, `clave`, `nombre`) VALUES
	(1, b'1', '$2a$10$gma3kXYe1NciEGGei.bTsu9VYFDVyyJ0n0/UDgb4/XQBVuC5QWHMu', 'admin'),
	(2, b'1', '$2a$10$2pqlFztQ5wKUybR5pMX3QOeLwJQId0l7RMpZ/nISWWHWto37Wk6Mu', 'user');

-- Volcando datos para la tabla libreria.usuarios: ~2 rows (aproximadamente)
REPLACE INTO `usuarios` (`id`, `correo_electronico`, `identificacion`, `nombre_completo`, `telefono_movil`) VALUES
	(2, 'santiagogil12@gmail.com', '1007423344', 'Santiago Echavarria', '3053382425'),
	(3, 'nnAAN@gmail.com', '1257423344', 'Ana Maria', '3053382425');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
