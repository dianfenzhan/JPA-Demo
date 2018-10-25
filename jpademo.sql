/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : jpademo

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-25 10:21:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for senyi_deptment
-- ----------------------------
DROP TABLE IF EXISTS `senyi_deptment`;
CREATE TABLE `senyi_deptment` (
  `dep_id` int(11) NOT NULL,
  `dep_name` varchar(50) DEFAULT NULL,
  `dept_code` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of senyi_deptment
-- ----------------------------
INSERT INTO `senyi_deptment` VALUES ('3', '3', '003', '3');
INSERT INTO `senyi_deptment` VALUES ('4', '4', '004', '4');
INSERT INTO `senyi_deptment` VALUES ('5', '5', '005', '5');
INSERT INTO `senyi_deptment` VALUES ('6', '6', '006', '6');
INSERT INTO `senyi_deptment` VALUES ('7', '7', '007', '7');
INSERT INTO `senyi_deptment` VALUES ('8', '8', '008', '8');
INSERT INTO `senyi_deptment` VALUES ('9', '9', '009', '9');
INSERT INTO `senyi_deptment` VALUES ('10', '部11门10100101我们', null, null);
INSERT INTO `senyi_deptment` VALUES ('11', '11', '011', '11');
INSERT INTO `senyi_deptment` VALUES ('12', '12', '012', '12');
INSERT INTO `senyi_deptment` VALUES ('13', '13', '013', '13');
INSERT INTO `senyi_deptment` VALUES ('14', '14', '014', '14');
INSERT INTO `senyi_deptment` VALUES ('15', '15', '015', '15');
INSERT INTO `senyi_deptment` VALUES ('16', '16', '016', '16');
INSERT INTO `senyi_deptment` VALUES ('17', '17', '017', '17');
INSERT INTO `senyi_deptment` VALUES ('18', '18', '017', '18');
INSERT INTO `senyi_deptment` VALUES ('19', '19', '019', '19');
INSERT INTO `senyi_deptment` VALUES ('20', '20', '020', 'note20');

-- ----------------------------
-- Table structure for senyi_role
-- ----------------------------
DROP TABLE IF EXISTS `senyi_role`;
CREATE TABLE `senyi_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_code` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of senyi_role
-- ----------------------------
INSERT INTO `senyi_role` VALUES ('1', '1', '1', null);
INSERT INTO `senyi_role` VALUES ('2', '2', '2', null);
INSERT INTO `senyi_role` VALUES ('3', '3', '3', null);
INSERT INTO `senyi_role` VALUES ('4', '4', '4', null);
INSERT INTO `senyi_role` VALUES ('5', '5', '5', null);
INSERT INTO `senyi_role` VALUES ('6', '6', '6', null);
INSERT INTO `senyi_role` VALUES ('7', '7', '7', null);
INSERT INTO `senyi_role` VALUES ('8', '8', '8', null);
INSERT INTO `senyi_role` VALUES ('9', '9', '9', null);
INSERT INTO `senyi_role` VALUES ('10', '10', '10', null);
INSERT INTO `senyi_role` VALUES ('11', '11', '11', null);
INSERT INTO `senyi_role` VALUES ('12', '12', '12', null);
INSERT INTO `senyi_role` VALUES ('13', '13', '13', null);
INSERT INTO `senyi_role` VALUES ('14', '14', '14', null);

-- ----------------------------
-- Table structure for senyi_user
-- ----------------------------
DROP TABLE IF EXISTS `senyi_user`;
CREATE TABLE `senyi_user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of senyi_user
-- ----------------------------
INSERT INTO `senyi_user` VALUES ('1', '1', '11', '1', '1', '11');
INSERT INTO `senyi_user` VALUES ('2', '1', '12', '1', '2', '12');
INSERT INTO `senyi_user` VALUES ('3', '1', '13', '1', '3', '13');
INSERT INTO `senyi_user` VALUES ('4', '11', '14', '1', '4', '14');
INSERT INTO `senyi_user` VALUES ('5', '11', '5', '1', '5', '15');
INSERT INTO `senyi_user` VALUES ('6', '2', '6', '1', '6', '16');
INSERT INTO `senyi_user` VALUES ('7', '1', '7', '1', '7', '17');
INSERT INTO `senyi_user` VALUES ('8', '1', '8', '1', '8', '18');
INSERT INTO `senyi_user` VALUES ('9', '1', '9', '2', '9', '19');
INSERT INTO `senyi_user` VALUES ('10', '1', '10', '2', '10', '20');
