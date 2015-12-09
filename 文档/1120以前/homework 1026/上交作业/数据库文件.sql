/*
Navicat MySQL Data Transfer

Source Server         : TEST
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : express_system

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-10-26 23:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) DEFAULT NULL,
  `account_amount` decimal(10,2) DEFAULT NULL,
  `account_startdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for bitransport
-- ----------------------------
DROP TABLE IF EXISTS `bitransport`;
CREATE TABLE `bitransport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_department_id_fk` int(11) DEFAULT NULL,
  `end_department_id_fk` int(11) DEFAULT NULL,
  `BItransport_start_datetime` datetime DEFAULT NULL,
  `BItransport_end_datetime` datetime DEFAULT NULL,
  `vehicle_id_fk` int(11) DEFAULT NULL,
  `driver_id_fk` int(11) DEFAULT NULL,
  `BItransport_fare` decimal(10,2) DEFAULT NULL,
  `BItransport_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `start_department_id_fk` (`start_department_id_fk`),
  KEY `end_department_id_fk` (`end_department_id_fk`),
  KEY `vehicle_id_fk` (`vehicle_id_fk`),
  KEY `driver_id_fk` (`driver_id_fk`),
  CONSTRAINT `bitransport_ibfk_1` FOREIGN KEY (`start_department_id_fk`) REFERENCES `department` (`id`),
  CONSTRAINT `bitransport_ibfk_2` FOREIGN KEY (`end_department_id_fk`) REFERENCES `department` (`id`),
  CONSTRAINT `bitransport_ibfk_3` FOREIGN KEY (`vehicle_id_fk`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `bitransport_ibfk_4` FOREIGN KEY (`driver_id_fk`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bitransport
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_datetime` datetime DEFAULT NULL,
  `collection_amount` decimal(10,2) DEFAULT NULL,
  `payee_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arrive_time` datetime DEFAULT NULL,
  `courier_user_id_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courier_user_id_fk` (`courier_user_id_fk`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`courier_user_id_fk`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delivery
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) DEFAULT NULL,
  `department_location` varchar(200) DEFAULT NULL,
  `department_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '北京公司总部', '北京市西城区西直门北大街100号', '财务管理部门');
INSERT INTO `department` VALUES ('2', '南京仙林营业厅', '南京市栖霞区仙林街道仙林大道168号', '营业厅');
INSERT INTO `department` VALUES ('3', '南京中转中心', '南京市雨花台区玉兰路8号', '中转中心');
INSERT INTO `department` VALUES ('4', '北京中转中心', '北京市天安门大道259号', '中转中心');
INSERT INTO `department` VALUES ('5', '北京西直门营业厅', '北京市西城区西直门北大街55号', '营业厅');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(50) DEFAULT NULL,
  `driver_birthday` datetime DEFAULT NULL,
  `driver_idcard` varchar(50) DEFAULT NULL,
  `driver_phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) DEFAULT NULL,
  `goods_num` int(11) DEFAULT NULL,
  `goods_weight` decimal(11,2) DEFAULT NULL,
  `goods_length` int(11) DEFAULT NULL,
  `goods_width` int(11) DEFAULT NULL,
  `goods_height` int(11) DEFAULT NULL,
  `goods_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for ibtransport
-- ----------------------------
DROP TABLE IF EXISTS `ibtransport`;
CREATE TABLE `ibtransport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_department_id_fk` int(11) DEFAULT NULL,
  `end_department_id_fk` int(11) DEFAULT NULL,
  `IBtransport_start_datetime` datetime DEFAULT NULL,
  `IBtransport_end_datetime` datetime DEFAULT NULL,
  `vehicle_id_fk` int(11) DEFAULT NULL,
  `driver_id_fk` int(11) DEFAULT NULL,
  `IBtransport_fare` decimal(10,2) DEFAULT NULL,
  `IBtransport_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `start_department_id_fk` (`start_department_id_fk`),
  KEY `end_department_id_fk` (`end_department_id_fk`),
  KEY `vehicle_id_fk` (`vehicle_id_fk`),
  KEY `driver_id_fk` (`driver_id_fk`),
  CONSTRAINT `ibtransport_ibfk_1` FOREIGN KEY (`start_department_id_fk`) REFERENCES `department` (`id`),
  CONSTRAINT `ibtransport_ibfk_2` FOREIGN KEY (`end_department_id_fk`) REFERENCES `department` (`id`),
  CONSTRAINT `ibtransport_ibfk_3` FOREIGN KEY (`vehicle_id_fk`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `ibtransport_ibfk_4` FOREIGN KEY (`driver_id_fk`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ibtransport
-- ----------------------------

-- ----------------------------
-- Table structure for iitransport
-- ----------------------------
DROP TABLE IF EXISTS `iitransport`;
CREATE TABLE `iitransport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_department_id_fk` int(11) DEFAULT NULL,
  `end_department_id_fk` int(11) DEFAULT NULL,
  `IItransport_start_datetime` datetime DEFAULT NULL,
  `IItransport_end_datetime` datetime DEFAULT NULL,
  `IItransport_type` varchar(50) DEFAULT NULL,
  `IItransport_fare` decimal(10,2) DEFAULT NULL,
  `IItransport_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `start_department_id_fk` (`start_department_id_fk`),
  KEY `end_department_id_fk` (`end_department_id_fk`),
  CONSTRAINT `iitransport_ibfk_1` FOREIGN KEY (`start_department_id_fk`) REFERENCES `department` (`id`),
  CONSTRAINT `iitransport_ibfk_2` FOREIGN KEY (`end_department_id_fk`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iitransport
-- ----------------------------

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) DEFAULT NULL,
  `job_salary` decimal(11,2) DEFAULT NULL,
  `job_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '总经理', '8000.00', null);
INSERT INTO `job` VALUES ('2', '快递员', '800.00', null);
INSERT INTO `job` VALUES ('3', '营业厅业务员', '1500.00', null);
INSERT INTO `job` VALUES ('4', '中转中心业务员', '1800.00', null);
INSERT INTO `job` VALUES ('5', '仓库管理人员', '1200.00', null);
INSERT INTO `job` VALUES ('6', '财务人员', '2000.00', null);
INSERT INTO `job` VALUES ('7', '系统管理员', '5000.00', null);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_barcode` varchar(50) DEFAULT NULL,
  `post_id_fk` int(11) DEFAULT NULL,
  `collection_id_fk` int(11) DEFAULT NULL,
  `BItransport_id_fk` int(11) DEFAULT NULL,
  `IItransport_id_fk` int(11) DEFAULT NULL,
  `IBtransport_id_fk` int(11) DEFAULT NULL,
  `start_stock_id_fk` int(11) DEFAULT NULL,
  `end_stock_id_fk` int(11) DEFAULT NULL,
  `delivery_id_fk` int(11) DEFAULT NULL,
  `recipient_id_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id_fk` (`post_id_fk`),
  KEY `collection_id_fk` (`collection_id_fk`),
  KEY `BItransport_id_fk` (`BItransport_id_fk`),
  KEY `IItransport_id_fk` (`IItransport_id_fk`),
  KEY `IBtransport_id_fk` (`IBtransport_id_fk`),
  KEY `start_stock_id_fk` (`start_stock_id_fk`),
  KEY `end_stock_id_fk` (`end_stock_id_fk`),
  KEY `delivery_id_fk` (`delivery_id_fk`),
  KEY `recipient_id_fk` (`recipient_id_fk`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`post_id_fk`) REFERENCES `post` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`collection_id_fk`) REFERENCES `collection` (`id`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`BItransport_id_fk`) REFERENCES `bitransport` (`id`),
  CONSTRAINT `order_ibfk_4` FOREIGN KEY (`IItransport_id_fk`) REFERENCES `iitransport` (`id`),
  CONSTRAINT `order_ibfk_5` FOREIGN KEY (`IBtransport_id_fk`) REFERENCES `ibtransport` (`id`),
  CONSTRAINT `order_ibfk_6` FOREIGN KEY (`start_stock_id_fk`) REFERENCES `stock` (`id`),
  CONSTRAINT `order_ibfk_7` FOREIGN KEY (`end_stock_id_fk`) REFERENCES `stock` (`id`),
  CONSTRAINT `order_ibfk_8` FOREIGN KEY (`delivery_id_fk`) REFERENCES `delivery` (`id`),
  CONSTRAINT `order_ibfk_9` FOREIGN KEY (`recipient_id_fk`) REFERENCES `recipient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_datetime` datetime DEFAULT NULL,
  `payment_amount` decimal(10,2) DEFAULT NULL,
  `payer_name` varchar(50) DEFAULT NULL,
  `payment_type` varchar(50) DEFAULT NULL,
  `payment_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id_fk` int(11) DEFAULT NULL,
  `receiver_id_fk` int(11) DEFAULT NULL,
  `goods_id_fk` int(11) DEFAULT NULL,
  `post_type` varchar(50) DEFAULT NULL,
  `packingexpense` decimal(2,2) DEFAULT NULL,
  `packing_username` varchar(50) DEFAULT NULL,
  `post_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_id_fk` (`sender_id_fk`),
  KEY `receiver_id_fk` (`receiver_id_fk`),
  KEY `goods_id_fk` (`goods_id_fk`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`sender_id_fk`) REFERENCES `sender` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`receiver_id_fk`) REFERENCES `receiver` (`id`),
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`goods_id_fk`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for receiver
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver_name` varchar(50) DEFAULT NULL,
  `receiver_address` varchar(50) DEFAULT NULL,
  `receiver_phone` varchar(50) DEFAULT NULL,
  `receiver_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receiver
-- ----------------------------

-- ----------------------------
-- Table structure for recipient
-- ----------------------------
DROP TABLE IF EXISTS `recipient`;
CREATE TABLE `recipient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipient_name` varchar(50) DEFAULT NULL,
  `recipient_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipient
-- ----------------------------

-- ----------------------------
-- Table structure for sender
-- ----------------------------
DROP TABLE IF EXISTS `sender`;
CREATE TABLE `sender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_address` varchar(50) DEFAULT NULL,
  `sender_phone` varchar(50) DEFAULT NULL,
  `sender_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sender
-- ----------------------------

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id_fk` int(11) DEFAULT NULL,
  `area_num` smallint(6) DEFAULT NULL,
  `row_num` smallint(6) DEFAULT NULL,
  `shelf_num` smallint(6) DEFAULT NULL,
  `position_num` smallint(6) DEFAULT NULL,
  `stockin_datetime` datetime DEFAULT NULL,
  `stockout_datetime` datetime DEFAULT NULL,
  `stock_isEmpty` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_id_fk` (`department_id_fk`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`department_id_fk`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `job_id_fk` int(11) DEFAULT NULL,
  `department_id_fk` int(11) DEFAULT NULL,
  `user_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `job_id_fk` (`job_id_fk`),
  KEY `department_id_fk` (`department_id_fk`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`job_id_fk`) REFERENCES `job` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`department_id_fk`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '吴磊', 'wl', '1', '1', null);
INSERT INTO `user` VALUES ('2', '赵启忠', 'zqz', '2', '2', null);
INSERT INTO `user` VALUES ('3', '吕宗同', 'lzt', '2', '2', null);
INSERT INTO `user` VALUES ('4', '刘可哲', 'lkz', '3', '2', null);
INSERT INTO `user` VALUES ('5', '岳强', 'lq', '3', '2', null);
INSERT INTO `user` VALUES ('6', null, null, null, null, null);

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `license_plate_number` varchar(50) DEFAULT NULL,
  `usetime` datetime DEFAULT NULL,
  `vehicle_info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
