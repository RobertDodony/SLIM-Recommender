DROP DATABASE  IF EXISTS `bookshop`;

CREATE DATABASE  IF NOT EXISTS `bookshop`;
USE `bookshop`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `email` varchar(50) NOT NULL,
  `purchases` varchar(50000) DEFAULT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(100),
  `author` varchar(50) NOT NULL,
  `year` int(4),
  `page_number` int(4),
  `price1` int(6) NOT NULL,
  `price2` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

ALTER TABLE `books` CHANGE `title` `title` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;
ALTER TABLE `books` CHANGE `subtitle` `subtitle` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci;
INSERT INTO `users`
VALUES
(NULL, 'firstName0', 'lastName0', 'password', 'email0@example.com', '1,2,3','USER'),
(NULL, 'firstName1', 'lastName1', 'password', 'email1@example.com', '4,7','USER'),
(NULL, 'firstName2', 'lastName2', 'password', 'email2@example.com', '8,9','USER'),
(NULL, 'firstName3', 'lastName3', 'password', 'email3@example.com', '13,15','USER'),
(NULL, 'firstName4', 'lastName4', 'password', 'email4@example.com', '16,17,19','USER'),
(NULL, 'firstName5', 'lastName5', 'password', 'email5@example.com', '20,22,23','USER'),
(NULL, 'firstName6', 'lastName6', 'password', 'email6@example.com', '25,27,28,29','USER'),
(NULL, 'firstName7', 'lastName7', 'password', 'email7@example.com', '0,1,13,14','USER'),
(NULL, 'firstName8', 'lastName8', 'password', 'email8@example.com', '5,6,7,26,27','USER'),
(NULL, 'firstName9', 'lastName9', 'password', 'email9@example.com', '9,11,14,15','USER'),
(NULL, 'firstName10', 'lastName10', 'password', 'email10@example.com', '8,9,10,21,23','USER'),
(NULL, 'firstName11', 'lastName11', 'password', 'email11@example.com', '0,3,12,14','USER'),
(NULL, 'firstName12', 'lastName12', 'password', 'email12@example.com', '8,11,12,13,15','USER'),
(NULL, 'firstName13', 'lastName13', 'password', 'email13@example.com', '16,18,26,27,29','USER'),
(NULL, 'firstName14', 'lastName14', 'password', 'email14@example.com', '17,18,19,21,23','USER'),
(NULL, 'firstName15', 'lastName15', 'password', 'email15@example.com', '16,17,20,21,22','USER'),
(NULL, 'firstName16', 'lastName16', 'password', 'email16@example.com', '10,11,20,23','USER'),
(NULL, 'firstName17', 'lastName17', 'password', 'email17@example.com', '22,23,24,25,28','USER'),
(NULL, 'firstName18', 'lastName18', 'password', 'email18@example.com', '4,6,26,27,28','USER'),
(NULL, 'firstName19', 'lastName19', 'password', 'email19@example.com', '20,21,25,28,29','USER'),
(NULL, 'firstName20', 'lastName20', 'password', 'email20@example.com', '17,19,25,26,27','USER'),
(NULL, 'firstName21', 'lastName21', 'password', 'email21@example.com', '5,6,22,24,29','USER'),
(NULL, 'firstName22', 'lastName22', 'password', 'email22@example.com', '16,17,21,23,26,27,28,29','USER'),
(NULL, 'firstName23', 'lastName23', 'password', 'email23@example.com', '9,10,16,18,19,20,21,24','USER'),
(NULL, 'firstName24', 'lastName24', 'password', 'email24@example.com', '1,2,8,9,12,13,14','USER'),
(NULL, 'firstName25', 'lastName25', 'password', 'email25@example.com', '4,5,7,10,25,27','USER'),
(NULL, 'firstName26', 'lastName26', 'password', 'email26@example.com', '13,15,16,17,18','USER'),
(NULL, 'firstName27', 'lastName27', 'password', 'email27@example.com', '4,7,20,21,23,24','USER'),
(NULL, 'firstName28', 'lastName28', 'password', 'email28@example.com', '4,5,6,12,14,15','USER'),
(NULL, 'firstName29', 'lastName29', 'password', 'email29@example.com', '10,11,22,24,25,26,27,28','USER');

INSERT INTO `books`
VALUES
(NULL, 'A Perdide árvája', 'nincs', 'author0',1924,496,4931,3875),
(NULL, 'Praotorianus', 'nincs', 'author1',1909,394,4658,3466),
(NULL, 'Mulandó üresség', 'nincs', 'author2',1920,316,2587,2971),
(NULL, 'Dűne', 'nincs', 'author3',1928,438,2508,1218),
(NULL, 'Önerőből', 'nincs', 'author4',2016,393,4394,2507),
(NULL, 'Keto', 'nincs', 'author5',1993,302,2970,3583),
(NULL, 'Újra én', 'nincs', 'author6',1923,317,2512,1209),
(NULL, 'Élni jöttem', 'nincs', 'author7',2013,279,2556,3196),
(NULL, 'A nagy történelem', 'nincs', 'author8',1993,457,2211,2467),
(NULL, 'Micsoda népek', 'nincs', 'author9',1906,283,1526,3428),
(NULL, 'Alkony', 'nincs', 'author10',1995,232,4955,3010),
(NULL, 'Trianon', 'nincs', 'author11',2018,324,3665,3693),
(NULL, 'Vaják - Tündevér', 'nincs', 'author12',1969,285,2811,3056),
(NULL, 'Hobbit', 'nincs', 'author13',1965,419,3824,2791),
(NULL, 'A kiválasztott', 'nincs', 'author14',1925,312,4908,1723),
(NULL, 'Eragon - Örökség', 'nincs', 'author15',1965,326,2203,1596),
(NULL, 'Most élsz', 'nincs', 'author16',1964,275,1564,1666),
(NULL, 'A stáb', 'nincs', 'author17',2013,223,3239,1119),
(NULL, 'Maradj közel', 'nincs', 'author18',1941,338,2636,2532),
(NULL, 'Hallgass meg!', 'nincs', 'author19',1968,463,2220,1511),
(NULL, 'Michael Jordan - A levegő Ura', 'nincs', 'author20',1983,472,3425,1278),
(NULL, 'Ferkovics József', 'nincs', 'author21',2002,416,4341,1226),
(NULL, 'Élet', 'nincs', 'author22',2003,294,3332,1667),
(NULL, 'Hivatásom prizmája', 'nincs', 'author23',1906,228,1759,3996),
(NULL, 'Visszadobtak', 'nincs', 'author24',1935,280,3457,3099),
(NULL, 'A nő', 'nincs', 'author25',1905,230,4461,3064),
(NULL, 'A férfi', 'nincs', 'author26',1902,221,3267,3062),
(NULL, 'A kiút', 'nincs', 'author27',2009,453,3816,1331),
(NULL, 'A képlet', 'nincs', 'author28',1970,482,3442,3976),
(NULL, 'A fájdalom arcai', 'nincs', 'author29',1945,286,3745,2565);