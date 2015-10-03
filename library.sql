-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.21 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных library
CREATE DATABASE IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;


-- Дамп структуры для таблица library.author
CREATE TABLE IF NOT EXISTS `author` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы library.author: ~3 rows (приблизительно)
DELETE FROM `author`;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` (`id`, `name`, `surname`) VALUES
	(1, 'Іван', 'Мележ'),
	(2, 'Gabriel', 'Marquez'),
	(3, '만중', '김');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;


-- Дамп структуры для таблица library.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы library.book: ~6 rows (приблизительно)
DELETE FROM `book`;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `title`, `author`, `category`, `available`) VALUES
	(1, 'Людзі на балоце', 1, 1, 2),
	(2, 'Подых навальніцы', 1, 2, 2),
	(3, 'The Autumn of the Patriarch', 2, 4, 10),
	(4, 'One Hundred Years of Solitude', 2, 4, 16),
	(5, '구운몽', 3, 2, 3),
	(6, '마당 깊은 집', 3, 2, 5);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- Дамп структуры для таблица library.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы library.category: ~5 rows (приблизительно)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`) VALUES
	(1, 'education\r\n'),
	(2, 'historical'),
	(3, 'science fiction'),
	(4, 'classical'),
	(5, 'philosophy');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- Дамп структуры для таблица library.order_book
CREATE TABLE IF NOT EXISTS `order_book` (
  `id` int(11) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  `id_reader` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы library.order_book: ~3 rows (приблизительно)
DELETE FROM `order_book`;
/*!40000 ALTER TABLE `order_book` DISABLE KEYS */;
INSERT INTO `order_book` (`id`, `id_book`, `id_reader`, `status`) VALUES
	(1, 8, 2, 0),
	(2, 9, 6, 0),
	(3, 10, 2, 0);
/*!40000 ALTER TABLE `order_book` ENABLE KEYS */;


-- Дамп структуры для таблица library.reader
CREATE TABLE IF NOT EXISTS `reader` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `banned` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы library.reader: ~3 rows (приблизительно)
DELETE FROM `reader`;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` (`id`, `name`, `surname`, `mail`, `password`, `role`, `banned`) VALUES
	(1, 'Admin', 'Admin', 'admin@gmail.com', '2e33a9b0b06aa0a01ede70995674ee23', 1, 0),
	(2, 'Olga', 'Ivanova', 'o-ivanova@gmail.com', '79e79d28906a14db7adfb5b90ad3765', 2, 0),
	(3, 'Peter', 'Frank', 'p-f@gmail.com', '9170711da32d96229baf98f9904c7fcc', 2, 0);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
