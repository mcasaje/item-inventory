# --- !Ups

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
-- Dumping data for table item-inventory-db.app_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `username`, `password_salt`, `password_hash`) VALUES
  (5, 'test', '343421064681527428843826896969', 'NW0ixKJc98ijGVjWxdDM+43aBOnkR3ddgX299pirK4v80pJBtwAzLfTuZ0eLDOwG');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.field: ~5 rows (approximately)
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` (`id`, `name`, `item_type_id`, `username`) VALUES
  (37, 'Headquarters', 14, 'test'),
  (38, 'Website', 14, 'test'),
  (44, 'Author', 16, 'test'),
  (45, 'Publisher', 16, 'test'),
  (46, 'Year', 16, 'test');
/*!40000 ALTER TABLE `field` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.item: ~10 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id`, `name`, `item_type_id`, `username`) VALUES
  (30, 'Samsung', 14, 'test'),
  (31, 'Apple', 14, 'test'),
  (32, 'Foxconn', 14, 'test'),
  (33, 'HP', 14, 'test'),
  (34, 'IBM', 14, 'test'),
  (35, 'Amazon', 14, 'test'),
  (36, 'Microsoft', 14, 'test'),
  (37, 'Sony', 14, 'test'),
  (38, 'Google', 14, 'test'),
  (39, 'Ensemble', 14, 'test'),
  (43, 'Clean Code: A Handbook of Agile Software Craftsmanship', 16, 'test'),
  (44, 'Object-Oriented Design Heuristics', 16, 'test'),
  (45, 'Design Patterns: Elements of Reusable Object-Oriente?d Software', 16, 'test'),
  (46, 'Refactoring: Improving the Design of Existing Code', 16, 'test'),
  (47, 'The Pragmatic Programmer: From Journeyman to Master', 16, 'test'),
  (48, 'Code Complete (2nd Edition)', 16, 'test'),
  (49, 'Accelerated C++: Practical Programming by Example', 16, 'test'),
  (50, 'Cracking the Coding Interview: 150 Programming Questions and Solutions', 16, 'test'),
  (51, 'The 7 Habits of Highly Effective People: Powerful Lessons in Personal Change', 16, 'test'),
  (52, 'Getting Things Done: The Art of Stress-Free Productivity', 16, 'test'),
  (53, 'The 80/20 Principle: The Secret to Achieving More with Less', 16, 'test'),
  (54, 'Your Brain At Work', 16, 'test'),
  (55, 'Psycho-Cybernetics: Updated and Revised', 16, 'test'),
  (56, 'Team of Teams: New Rules of Engagement for a Complex World', 16, 'test'),
  (57, 'Think and Grow Rich', 16, 'test');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.item_field: ~20 rows (approximately)
/*!40000 ALTER TABLE `item_field` DISABLE KEYS */;
INSERT INTO `item_field` (`id`, `item_id`, `field_id`, `username`, `field_val`) VALUES
  (129, 30, 37, 'test', 'Suwon, South Korea'),
  (130, 30, 38, 'test', 'http://www.samsung.com/'),
  (132, 31, 37, 'test', 'Cupertino, CA, USA'),
  (133, 31, 38, 'test', 'http://www.apple.com/'),
  (135, 32, 37, 'test', 'New Taipei, Taiwan'),
  (136, 32, 38, 'test', 'http://www.foxconn.com/'),
  (138, 33, 37, 'test', 'Palo Alto, CA, USA'),
  (139, 33, 38, 'test', 'http://www.hp.com/'),
  (141, 34, 37, 'test', 'Armonk, NY, USA'),
  (142, 34, 38, 'test', 'http://www.ibm.com/'),
  (144, 35, 37, 'test', 'Seattle, WA, USA'),
  (145, 35, 38, 'test', 'http://www.amazon.com'),
  (147, 36, 37, 'test', 'Redmond, WA, USA'),
  (148, 36, 38, 'test', 'http://www.microsoft.com'),
  (150, 37, 37, 'test', 'Tokyo, Japan'),
  (151, 37, 38, 'test', 'http://www.sony.net/'),
  (153, 38, 37, 'test', 'Mountain View, CA, USA'),
  (154, 38, 38, 'test', 'http://www.google.com'),
  (156, 39, 37, 'test', 'Richmond, BC, Canada'),
  (157, 39, 38, 'test', 'http://www.ensemble.com'),
  (170, 43, 44, 'test', 'Robert C. Martin'),
  (171, 43, 45, 'test', 'Prentice Hall'),
  (172, 43, 46, 'test', '2008'),
  (173, 44, 44, 'test', 'Arthur J. Riel'),
  (174, 44, 45, 'test', 'Addison-Wesley Professional'),
  (175, 44, 46, 'test', '1996'),
  (176, 45, 44, 'test', 'Erich Gamma, Richard Helm, Ralph Johnson'),
  (177, 45, 45, 'test', 'Addison-Wesley Professional'),
  (178, 45, 46, 'test', '1994'),
  (179, 46, 44, 'test', 'Martin Fowler, Kent Beck, John Brant'),
  (180, 46, 45, 'test', 'Addison-Wesley Professional'),
  (181, 46, 46, 'test', '1999'),
  (182, 47, 44, 'test', 'Andrew Hunt, David Thomas'),
  (183, 47, 45, 'test', 'Addison-Wesley Professional'),
  (184, 47, 46, 'test', '1999'),
  (185, 48, 44, 'test', 'Steve McConnell'),
  (186, 48, 45, 'test', 'Microsoft Press'),
  (187, 48, 46, 'test', '2004'),
  (188, 49, 44, 'test', 'Andrew Koenig, Barbara E. Moo'),
  (189, 49, 45, 'test', 'Addison-Wesley Professional'),
  (190, 49, 46, 'test', '2000'),
  (191, 50, 44, 'test', 'Gayle Laakmann McDowell'),
  (192, 50, 45, 'test', 'CareerCup'),
  (193, 50, 46, 'test', '2011'),
  (194, 51, 44, 'test', 'Stephen R. Covey'),
  (195, 51, 45, 'test', 'Simon & Schuster'),
  (196, 51, 46, 'test', '2004'),
  (197, 52, 44, 'test', 'David Allen'),
  (198, 52, 45, 'test', 'Penguin Books'),
  (199, 52, 46, 'test', '2002'),
  (200, 53, 44, 'test', 'Richard Koch'),
  (201, 53, 45, 'test', 'Crown Business'),
  (202, 53, 46, 'test', '1999'),
  (203, 54, 44, 'test', 'David Rock'),
  (204, 54, 45, 'test', 'Harper Business'),
  (205, 54, 46, 'test', '2009'),
  (206, 55, 44, 'test', 'Maxwell Maltz, Dan Kennedy'),
  (207, 55, 45, 'test', 'Amazon'),
  (208, 55, 46, 'test', '2006'),
  (209, 56, 44, 'test', 'General Stanley McChrystal, Tantum Collins, David Silverman, Chris Fussell'),
  (210, 56, 45, 'test', 'Amazon'),
  (211, 56, 46, 'test', '2015'),
  (212, 57, 44, 'test', 'Napoleon Hill'),
  (213, 57, 45, 'test', 'Amazon'),
  (214, 57, 46, 'test', '2014');
/*!40000 ALTER TABLE `item_field` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.item_tag: ~28 rows (approximately)
/*!40000 ALTER TABLE `item_tag` DISABLE KEYS */;
INSERT INTO `item_tag` (`id`, `item_id`, `tag_id`, `username`) VALUES
  (44, 30, 28, 'test'),
  (46, 30, 29, 'test'),
  (45, 30, 30, 'test'),
  (48, 31, 28, 'test'),
  (49, 31, 30, 'test'),
  (47, 31, 31, 'test'),
  (50, 32, 32, 'test'),
  (52, 33, 30, 'test'),
  (53, 33, 33, 'test'),
  (51, 33, 34, 'test'),
  (54, 34, 35, 'test'),
  (55, 34, 36, 'test'),
  (57, 35, 37, 'test'),
  (56, 35, 38, 'test'),
  (60, 36, 28, 'test'),
  (61, 36, 30, 'test'),
  (59, 36, 31, 'test'),
  (58, 36, 35, 'test'),
  (62, 37, 28, 'test'),
  (63, 37, 30, 'test'),
  (67, 38, 28, 'test'),
  (70, 38, 30, 'test'),
  (66, 38, 31, 'test'),
  (65, 38, 35, 'test'),
  (69, 38, 37, 'test'),
  (64, 38, 38, 'test'),
  (68, 38, 39, 'test'),
  (71, 39, 34, 'test'),
  (75, 43, 43, 'test'),
  (76, 43, 46, 'test'),
  (77, 44, 43, 'test'),
  (78, 44, 46, 'test'),
  (79, 45, 44, 'test'),
  (80, 45, 46, 'test'),
  (81, 46, 44, 'test'),
  (82, 46, 46, 'test'),
  (83, 47, 43, 'test'),
  (84, 47, 46, 'test'),
  (85, 48, 43, 'test'),
  (86, 48, 46, 'test'),
  (88, 49, 43, 'test'),
  (87, 49, 47, 'test'),
  (90, 50, 43, 'test'),
  (89, 50, 47, 'test'),
  (92, 51, 43, 'test'),
  (91, 51, 47, 'test'),
  (94, 52, 43, 'test'),
  (93, 52, 47, 'test'),
  (96, 53, 43, 'test'),
  (95, 53, 47, 'test'),
  (97, 54, 44, 'test'),
  (98, 54, 47, 'test'),
  (99, 55, 45, 'test'),
  (100, 55, 47, 'test'),
  (101, 56, 45, 'test'),
  (102, 56, 46, 'test'),
  (103, 57, 45, 'test'),
  (104, 57, 46, 'test');
/*!40000 ALTER TABLE `item_tag` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.item_type: ~2 rows (approximately)
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` (`id`, `name`, `username`) VALUES
  (16, 'Book', 'test'),
  (14, 'Tech Company', 'test');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;

-- Dumping data for table item-inventory-db.tag: ~12 rows (approximately)
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`id`, `name`, `item_type_id`, `username`) VALUES
  (28, 'Electronic Devices', 14, 'test'),
  (29, 'Semiconductors', 14, 'test'),
  (30, 'Personal Computing', 14, 'test'),
  (31, 'Consumer Software', 14, 'test'),
  (32, 'Components', 14, 'test'),
  (33, 'Servers', 14, 'test'),
  (34, 'Consulting', 14, 'test'),
  (35, 'Computing Services', 14, 'test'),
  (36, 'Mainframes', 14, 'test'),
  (37, 'Internet Retailer', 14, 'test'),
  (38, 'App Hosting', 14, 'test'),
  (39, 'Internet Advertising', 14, 'test'),
  (43, 'Paperback', 16, 'test'),
  (44, 'Hardcover', 16, 'test'),
  (45, 'Audiobook', 16, 'test'),
  (46, 'Want', 16, 'test'),
  (47, 'Own', 16, 'test');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
