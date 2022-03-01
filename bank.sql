-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Мар 01 2022 г., 03:05
-- Версия сервера: 8.0.24
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `bank`
--

-- --------------------------------------------------------

--
-- Структура таблицы `authorization`
--

CREATE TABLE `authorization` (
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `authorization`
--

INSERT INTO `authorization` (`login`, `password`, `role`) VALUES
('admin', '31be6050198cd7d3efd05b33d2835e05', 'admin'),
('stepan', 'd03cd50a4c4fbab12500b26520dddf5b', 'user'),
('vladislav', '53cd262e24a4b897a0b6b7a1e8d77a4a', 'user');

-- --------------------------------------------------------

--
-- Структура таблицы `transaction`
--

CREATE TABLE `transaction` (
  `to_user` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `from_user` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `sum` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `transaction`
--

INSERT INTO `transaction` (`to_user`, `from_user`, `sum`, `time`) VALUES
('stepan', 'vladislav', '100', '2022-03-01 00:23:57.513874700'),
('stepan', 'vladislav', '100', '2022-03-01 00:29:50.420899600'),
('stepan', 'vladislav', '100', '2022-03-01 00:30:27.833075100'),
('stepan', 'vladislav', '100', '2022-03-01 00:31:06.139340500'),
('stepan', 'vladislav', '100', '2022-03-01 00:31:07.276855900');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `realname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `money` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `card` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`realname`, `status`, `login`, `money`, `card`, `password`) VALUES
('Stepan', 'green', 'stepan', '103500', '4741457856897845', 'd03cd50a4c4fbab12500b26520dddf5b'),
('Vladislav', 'green', 'vladislav', '6600', '4578568978458957', '53cd262e24a4b897a0b6b7a1e8d77a4a');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `authorization`
--
ALTER TABLE `authorization`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `transaction`
--
ALTER TABLE `transaction`
  ADD KEY `to_user` (`to_user`,`from_user`),
  ADD KEY `from_user` (`from_user`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `login` (`login`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`to_user`) REFERENCES `users` (`login`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`from_user`) REFERENCES `users` (`login`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`login`) REFERENCES `authorization` (`login`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
