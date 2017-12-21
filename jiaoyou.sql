/*
Navicat MySQL Data Transfer

Source Server         : try1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : jiaoyou

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-21 23:00:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `user_id` varchar(255) NOT NULL,
  `friend_id` varchar(255) NOT NULL,
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `useridInFriend` (`user_id`),
  KEY `friendInFriend` (`friend_id`),
  CONSTRAINT `friendInFriend` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `useridInFriend` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('38793583', '58098033', '0000000001');
INSERT INTO `friends` VALUES ('30392420', '58098033', '0000000002');
INSERT INTO `friends` VALUES ('43104944', '43104933', '0000000003');
INSERT INTO `friends` VALUES ('43104933', '43104944', '0000000004');

-- ----------------------------
-- Table structure for `huaguan_cash_out`
-- ----------------------------
DROP TABLE IF EXISTS `huaguan_cash_out`;
CREATE TABLE `huaguan_cash_out` (
  `serialnumber` int(11) NOT NULL AUTO_INCREMENT COMMENT '处理序号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `time` timestamp NULL DEFAULT NULL COMMENT '订单生成时间',
  `amount` int(11) DEFAULT NULL COMMENT '提现数量',
  `money` int(11) DEFAULT NULL COMMENT '提现金额',
  PRIMARY KEY (`serialnumber`),
  KEY `user` (`user_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of huaguan_cash_out
-- ----------------------------
INSERT INTO `huaguan_cash_out` VALUES ('1', '58098033', '2017-12-06 11:25:16', '3000', '100');
INSERT INTO `huaguan_cash_out` VALUES ('2', '58098033', '2017-12-07 15:46:54', '1000', '10');
INSERT INTO `huaguan_cash_out` VALUES ('3', '43104944', '2017-12-10 21:01:51', '2', '10');
INSERT INTO `huaguan_cash_out` VALUES ('4', '43104944', '2017-12-10 21:19:49', '1', '5');
INSERT INTO `huaguan_cash_out` VALUES ('5', '43104944', '2017-12-10 21:19:55', '1', '5');
INSERT INTO `huaguan_cash_out` VALUES ('13', '43104944', '2017-12-14 17:59:08', '1', '5');
INSERT INTO `huaguan_cash_out` VALUES ('14', '43104944', '2017-12-20 09:44:24', '1', '5');
INSERT INTO `huaguan_cash_out` VALUES ('15', '43104933', '2017-12-20 12:09:09', '1', '5');

-- ----------------------------
-- Table structure for `huaya_purchase`
-- ----------------------------
DROP TABLE IF EXISTS `huaya_purchase`;
CREATE TABLE `huaya_purchase` (
  `serialnumber` int(11) NOT NULL AUTO_INCREMENT COMMENT '处理序号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生成时间',
  `amount` int(11) DEFAULT NULL COMMENT '购买数量',
  `money` int(11) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`serialnumber`),
  KEY `userp` (`user_id`),
  CONSTRAINT `userp` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of huaya_purchase
-- ----------------------------
INSERT INTO `huaya_purchase` VALUES ('2', '89237486', '2017-12-10 21:34:44', '1000', '100');
INSERT INTO `huaya_purchase` VALUES ('3', '43104944', '2017-12-10 21:31:24', '20', '100');
INSERT INTO `huaya_purchase` VALUES ('10', '43104944', '2017-12-14 09:16:29', '5000', '25000');
INSERT INTO `huaya_purchase` VALUES ('11', '14576383', '2017-12-14 15:07:21', '4000', '20000');
INSERT INTO `huaya_purchase` VALUES ('12', '43104944', '2017-12-14 17:57:54', '2220', '11100');
INSERT INTO `huaya_purchase` VALUES ('13', '43104933', '2017-12-17 22:13:21', '100000', '500000');
INSERT INTO `huaya_purchase` VALUES ('16', '11124167', '2017-12-21 11:13:14', '1200', '6000');
INSERT INTO `huaya_purchase` VALUES ('17', '12241434', '2017-12-21 12:30:07', '2220', '11100');
INSERT INTO `huaya_purchase` VALUES ('18', '43104944', '2017-12-21 13:49:14', '1110', '5550');
INSERT INTO `huaya_purchase` VALUES ('19', '50134750', '2017-12-21 13:51:58', '111110', '555550');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_price` int(10) unsigned NOT NULL COMMENT '商品价格',
  `product_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `product_picture` varchar(255) DEFAULT 'Product/ProductPicture/20171212030838515.png' COMMENT '商品图片',
  `product_mean` varchar(255) DEFAULT NULL COMMENT '商品含义（花语）',
  `product_type` char(255) DEFAULT NULL COMMENT '商品品种',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('0', '0000000001', '向阳花', 'Product/ProductPicture/20171212030838515.png', '交个朋友吧。', 'virtual');
INSERT INTO `product` VALUES ('0', '0000000002', '含羞草', 'Product/ProductPicture/20171212030838515.png', '羞涩的拒绝。', 'virtual');
INSERT INTO `product` VALUES ('1000', '0000000003', '玫瑰花1', 'Product/ProductPicture/20171218102444949.jpg', '浓浓的爱献给最心爱的你。♡❤♡', 'actual');
INSERT INTO `product` VALUES ('100', '0000000004', '百合花', 'Product/ProductPicture/20171218102602798.jpg', '百年好合。', 'actual');
INSERT INTO `product` VALUES ('5', '0000000005', '气球', 'Product/ProductPicture/20171218102704904.jpg', '带走你的梦，我帮你实现。', 'virtual');
INSERT INTO `product` VALUES ('5', '0000000006', '爱心', 'Product/ProductPicture/20171218102805106.jpg', '我的小心心都给你。', 'virtual');
INSERT INTO `product` VALUES ('20', '0000000007', '小白兔', 'Product/ProductPicture/20171218102912331.jpg', '萌萌哒，一直可爱。', 'virtual');
INSERT INTO `product` VALUES ('500', '0000000008', '水晶球', 'Product/ProductPicture/20171218103007767.jpg', '圣洁的水晶送给纯洁的你。', 'virtual');
INSERT INTO `product` VALUES ('500', '0000000009', '魔法棒', 'Product/ProductPicture/20171218103234653.jpg', '愿你所想一切成真。', 'virtual');
INSERT INTO `product` VALUES ('1000', '0000000010', '水晶鞋', 'Product/ProductPicture/20171218103102582.jpg', '公主，希望我是你的王子。', 'virtual');
INSERT INTO `product` VALUES ('2000', '0000000011', '口红', 'Product/ProductPicture/20171218102329368.jpg', '让我吻吻你的唇', 'actual');
INSERT INTO `product` VALUES ('500', '0000000012', '辣条', 'Product/ProductPicture/20171212030838515.png', '谁还不是小仙女咋滴！请你吃辣条~', 'virtual');
INSERT INTO `product` VALUES ('200', '0000000013', '卫龙', 'image/product.png', '好吃的辣条和你一起分享呀', 'virtual');

-- ----------------------------
-- Table structure for `productorder`
-- ----------------------------
DROP TABLE IF EXISTS `productorder`;
CREATE TABLE `productorder` (
  `product_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `order_by` varchar(20) NOT NULL COMMENT '赠送者id',
  `order_to` varchar(20) NOT NULL COMMENT '被赠送者id',
  `product_id` int(10) unsigned zerofill NOT NULL COMMENT '商品id',
  `send_days` int(11) NOT NULL DEFAULT '1' COMMENT '配送天数',
  `everyday_number` int(11) NOT NULL DEFAULT '1' COMMENT '每天配送数量',
  `count_price` int(11) DEFAULT NULL COMMENT '总价',
  `sendback` int(11) NOT NULL DEFAULT '0' COMMENT '回赠情况',
  `purchase_time` timestamp NULL DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`product_order_id`),
  KEY `id` (`order_to`),
  KEY `id2` (`order_by`),
  KEY `idp` (`product_id`),
  CONSTRAINT `id` FOREIGN KEY (`order_to`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id2` FOREIGN KEY (`order_by`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idp` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productorder
-- ----------------------------
INSERT INTO `productorder` VALUES ('1', '43104944', '38793583', '0000000008', '1', '1', '100', '0', '2017-12-14 13:35:00');
INSERT INTO `productorder` VALUES ('42', '43104944', '14023912', '0000000008', '3', '1', '1500', '0', '2017-12-14 18:49:57');
INSERT INTO `productorder` VALUES ('43', '43104944', '14030947', '0000000004', '7', '3', '2100', '0', '2017-12-14 18:52:14');
INSERT INTO `productorder` VALUES ('44', '43104944', '43104933', '0000000004', '2', '3', '600', '0', '2017-12-14 19:27:23');
INSERT INTO `productorder` VALUES ('45', '43104933', '43104944', '0000000003', '1', '99', '9900', '1', '2017-12-17 22:13:50');
INSERT INTO `productorder` VALUES ('46', '43104944', '43104944', '0000000012', '4', '2', '4000', '2', '2017-12-17 23:52:03');
INSERT INTO `productorder` VALUES ('48', '43104944', '43104933', '0000000011', '1', '1', '2000', '0', '2017-12-18 14:22:11');
INSERT INTO `productorder` VALUES ('49', '19067812', '07145378', '0000000004', '1', '1', '100', '0', '2017-12-18 19:14:54');
INSERT INTO `productorder` VALUES ('50', '43104933', '07145378', '0000000004', '3', '2', '600', '0', '2017-12-20 00:42:18');
INSERT INTO `productorder` VALUES ('51', '43104933', '14023912', '0000000003', '2', '1', '200', '0', '2017-12-20 00:44:40');
INSERT INTO `productorder` VALUES ('52', '43104944', '07145378', '0000000004', '3', '1', '300', '0', '2017-12-20 08:52:55');
INSERT INTO `productorder` VALUES ('53', '43104944', '14023322', '0000000012', '1', '1', '500', '0', '2017-12-20 08:59:41');
INSERT INTO `productorder` VALUES ('54', '43104944', '09025552', '0000000010', '2', '1', '2000', '0', '2017-12-20 09:02:56');
INSERT INTO `productorder` VALUES ('56', '11124167', '43104944', '0000000009', '1', '1', '500', '0', '2017-12-21 11:13:38');
INSERT INTO `productorder` VALUES ('57', '11124167', '11595992', '0000000013', '1', '1', '200', '0', '2017-12-21 12:00:00');
INSERT INTO `productorder` VALUES ('58', '12241434', '12302548', '0000000013', '1', '1', '200', '0', '2017-12-21 12:30:25');
INSERT INTO `productorder` VALUES ('59', '50134750', '14023322', '0000000013', '5', '1', '1000', '0', '2017-12-21 13:53:35');

-- ----------------------------
-- Table structure for `send_info`
-- ----------------------------
DROP TABLE IF EXISTS `send_info`;
CREATE TABLE `send_info` (
  `send_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配送号id',
  `product_order_id` int(10) unsigned zerofill NOT NULL COMMENT '订单号',
  `send_state` varchar(255) DEFAULT '未派送' COMMENT '配送状态',
  `send_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '配送时间',
  `sendman` varchar(11) DEFAULT '无' COMMENT '配送者',
  `message` varchar(255) DEFAULT '祝你天天开心。',
  PRIMARY KEY (`send_id`),
  KEY `product_order_id` (`product_order_id`),
  CONSTRAINT `poff` FOREIGN KEY (`product_order_id`) REFERENCES `productorder` (`product_order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send_info
-- ----------------------------
INSERT INTO `send_info` VALUES ('1', '0000000001', '已完成', '2017-12-21 14:02:55', 'miao', '天天开心');
INSERT INTO `send_info` VALUES ('2', '0000000042', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('3', '0000000042', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('4', '0000000042', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('5', '0000000043', '已完成', '2017-12-19 21:06:53', 'miao', '百合万岁');
INSERT INTO `send_info` VALUES ('6', '0000000043', '已完成', '2017-12-19 21:06:53', 'miao', '百合万岁');
INSERT INTO `send_info` VALUES ('7', '0000000043', '已完成', '2017-12-19 21:06:53', 'miao', '百合万岁');
INSERT INTO `send_info` VALUES ('8', '0000000043', '已完成', '2017-12-19 21:06:53', 'miao', '百合万岁');
INSERT INTO `send_info` VALUES ('9', '0000000043', '已完成', '2017-12-21 11:10:25', 'sender1', '百合万岁');
INSERT INTO `send_info` VALUES ('10', '0000000043', '已完成', '2017-12-21 11:10:25', 'sender1', '百合万岁');
INSERT INTO `send_info` VALUES ('11', '0000000043', '已完成', '2017-12-19 21:06:53', 'miao', '百合万岁');
INSERT INTO `send_info` VALUES ('12', '0000000044', '已完成', '2017-12-19 21:06:53', 'miao', '百合大发好');
INSERT INTO `send_info` VALUES ('13', '0000000044', '已完成', '2017-12-19 21:06:53', 'miao', '百合大发好');
INSERT INTO `send_info` VALUES ('14', '0000000045', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('15', '0000000046', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('16', '0000000046', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('17', '0000000046', '已完成', '2017-12-21 11:10:25', 'sender1', '1');
INSERT INTO `send_info` VALUES ('18', '0000000046', '已完成', '2017-12-19 21:06:53', 'miao', '1');
INSERT INTO `send_info` VALUES ('21', '0000000048', '已完成', '2017-12-21 11:10:25', 'sender1', '1');
INSERT INTO `send_info` VALUES ('22', '0000000049', '已完成', '2017-12-21 11:10:25', 'sender1', '1');
INSERT INTO `send_info` VALUES ('23', '0000000051', '已完成', '2017-12-21 11:10:25', '无', '第一天');
INSERT INTO `send_info` VALUES ('24', '0000000051', '未派送', '2017-12-21 00:44:40', '无', '第二天');
INSERT INTO `send_info` VALUES ('25', '0000000052', '已完成', '2017-12-21 11:10:25', '无', '是什么让你');
INSERT INTO `send_info` VALUES ('26', '0000000052', '未派送', '2017-12-21 08:52:55', '无', '编程');
INSERT INTO `send_info` VALUES ('27', '0000000052', '未派送', '2017-12-22 08:52:55', '无', '这样子');
INSERT INTO `send_info` VALUES ('28', '0000000053', '已完成', '2017-12-21 12:39:06', '无', ' ');
INSERT INTO `send_info` VALUES ('29', '0000000054', '已完成', '2017-12-21 11:10:25', '无', '2222');
INSERT INTO `send_info` VALUES ('30', '0000000054', '未派送', '2017-12-21 09:02:56', '无', '3333');
INSERT INTO `send_info` VALUES ('33', '0000000056', '未派送', '2017-12-21 11:13:38', '无', '第一次送礼物耶');
INSERT INTO `send_info` VALUES ('34', '0000000057', '未派送', '2017-12-21 12:00:00', '无', '');
INSERT INTO `send_info` VALUES ('35', '0000000058', '未派送', '2017-12-21 12:30:25', '无', '1');
INSERT INTO `send_info` VALUES ('36', '0000000059', '未派送', '2017-12-21 13:53:35', '无', '1');
INSERT INTO `send_info` VALUES ('37', '0000000059', '未派送', '2017-12-22 13:53:35', '无', '1');
INSERT INTO `send_info` VALUES ('38', '0000000059', '未派送', '2017-12-23 13:53:35', '无', '1');
INSERT INTO `send_info` VALUES ('39', '0000000059', '未派送', '2017-12-24 13:53:35', '无', '1');
INSERT INTO `send_info` VALUES ('40', '0000000059', '未派送', '2017-12-25 13:53:35', '无', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `telephone` char(30) NOT NULL COMMENT '电话号码（用户用它登录）',
  `nickname` char(50) DEFAULT '' COMMENT '昵称',
  `user_passwd` varchar(255) NOT NULL COMMENT '密码',
  `truename` varchar(255) DEFAULT '' COMMENT '真实姓名',
  `birthday` date DEFAULT '1966-06-06' COMMENT '生日',
  `avatar` varchar(255) DEFAULT 'User/UserHeadImage/20171212030838515.png' COMMENT '头像',
  `signature` varchar(255) DEFAULT '这个用户很懒，什么都没有写哦。' COMMENT '个性签名',
  `huaya` int(10) unsigned zerofill DEFAULT '0000000000',
  `huaguan` int(10) unsigned zerofill DEFAULT '0000000000',
  `sex` varchar(255) DEFAULT '男',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `role` varchar(50) NOT NULL DEFAULT 'unregistered' COMMENT '角色 管理员manager，普通用户user，未注册unregisterder',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `telephone` (`telephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('07145378', '18843121212', '18843121212', '202cb962ac59075b964b07152d234b70', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000900', '0000000000', '男', '1', 'unregistered');
INSERT INTO `user` VALUES ('09025552', '12233334444', '12233334444', '12233334444', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000002000', '0000000000', '男', '1', 'unregistered');
INSERT INTO `user` VALUES ('11124167', '12341234567', '12341234567', '63a9f0ea7bb98050796b649e85481845', '芹菜', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '																										我就是很懒，什么都没有写哦。', '0000000500', '0000000000', '男', '南方北方，某个远方。', 'user');
INSERT INTO `user` VALUES ('11484801', '18734345656', '18734345656', '6d0e9ed7c1a3a33ae3eb225bcbf7029a', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', '地址信息', 'unregistered');
INSERT INTO `user` VALUES ('11595992', '12312312345', '12312312345', '12312312345', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000200', '0000000000', '男', '1', 'unregistered');
INSERT INTO `user` VALUES ('12106440', '12233355566', '12233355566', '7f38b1ee71e7c4330cce75ac24321af4', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', '地址信息', 'unregistered');
INSERT INTO `user` VALUES ('12241434', '12345123451', '萌萌的新人小白', '0635984fc9f56728e45d35bf030c5c1d', '萌新小白', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '													这个用户很懒，什么都没有写哦。', '0000002020', '0000000000', '男', '火星', 'user');
INSERT INTO `user` VALUES ('12302548', '12222000011', '12222000011', '12222000011', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000200', '0000000000', '男', '1', 'unregistered');
INSERT INTO `user` VALUES ('14023322', '18811112222', '18811112222', '04067a3108b0879b1f0c0cc6dc598035', ' ', '2017-12-21', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000001500', '0000000000', '男', ' ', 'unregistered');
INSERT INTO `user` VALUES ('14023912', '15577778888', '15577778888', 'e64fd16885cde0245e868205470d727d', ' ', '2017-12-05', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000200', '0000000000', '男', ' ', 'unregistered');
INSERT INTO `user` VALUES ('14030947', '17798989898', '17798989898', 'fcc2a55cb74f837d277b6a9d152d07c1', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', '吉大日新', 'unregistered');
INSERT INTO `user` VALUES ('14498659', '18899991111', '18899991111', '330ae1f79d154f56c74b4b6cf10d95b4', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', ' ', 'unregistered');
INSERT INTO `user` VALUES ('14576383', '18899991112', '晴天', '8d82f3b463713918099b44e99ed90139', '晴天霹雳', '1988-04-05', 'User/UserHeadImage/20171214030650972.jpg', '																																								我就是我，是颜色不一样的烟火。。。												', '0000004000', '0000000000', '女', '吉林省长春市朝阳区', 'user');
INSERT INTO `user` VALUES ('14974887', '18876547654', '18876547654', '84f6687791bf20bdbbf08547d396e8c6', ' ', '2017-12-19', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', ' ', 'unregistered');
INSERT INTO `user` VALUES ('19067812', '18877778888', '刘萌', '63a9f0ea7bb98050796b649e85481845', '刘刘', '1982-08-06', 'User/UserHeadImage/20171212030838515.png', '																										这个用户很懒，什么都没有写哦。', '0000005450', '0000000000', '男', '地址信息', 'user');
INSERT INTO `user` VALUES ('22543587', '13968852205', '风一样的女子', 'b201481cf2d79c888379fc3b429ad845', '陈佳佳', '1988-01-19', 'User/UserHeadImage/20171212113042545.jpg', '这个用户很懒，什么都没有写哦。', '0000000700', '0000000002', '女', '海南', 'unregistered');
INSERT INTO `user` VALUES ('24668468', '13305899963', '纯哥666', 'd7a81b4ed050136ae078233f5cdad47c', '都念纯', '1995-04-23', 'User/UserHeadImage/20171213054206193.jpg', '这个用户很懒，什么都没有写哦。', '0000010050', '0000000000', '女', '上海', 'unregistered');
INSERT INTO `user` VALUES ('30392420', '18843019788', '喵君', 'ef306f8eece169a0febaa91def6da46d', '陈丽竹', '1997-11-11', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000006000', '0000000005', '女', 'changchun', 'user');
INSERT INTO `user` VALUES ('35125736', '12233311123', '12233311123', 'a1a6369bc9fe23799a9a3b8caa31f6f9', '', '1966-06-06', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', '地址信息', 'unregistered');
INSERT INTO `user` VALUES ('38793583', '18843019785', '也许忘记。', 'c4dee6b5993f8a1c6aedf1588651a54a', '陈晓', '1980-03-05', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', 'taiwan', 'user');
INSERT INTO `user` VALUES ('43104933', '18843104933', 'miao', '63a9f0ea7bb98050796b649e85481845', '342ty', '1998-02-06', 'User/UserHeadImage/20171220104413589.jpg', '																																																				这个用户很懒，什么都没有写哦。1', '0000072950', '0000000011', '男', '某个地方啦啦啦', 'manager');
INSERT INTO `user` VALUES ('43104944', '18843104944', 'miao', '63a9f0ea7bb98050796b649e85481845', '王三三', '1991-12-08', 'User/UserHeadImage/20171219105200201.jpg', '																																个性签名还是应该要有滴					', '0000001270', '0000000004', '女', '黄土高坡', 'manager');
INSERT INTO `user` VALUES ('50134750', '11122223333', '11122223333', '83a3ce7163e45c3ca03eceaa90cd7a11', '1', '1966-06-06', 'User/UserHeadImage/20171221015410496.jpg', '													这个用户很懒，什么都没有写哦。', '0000110110', '0000000000', '男', '地址信息', 'user');
INSERT INTO `user` VALUES ('58098033', '18843019784', 'little devil', 'ec3867eaadd75bf7e06e864ba75a1113', 'qiao', '1996-08-26', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '女', 'jilin', 'user');
INSERT INTO `user` VALUES ('76385423', '18485863458', '啦啦啦', '42a1ddf5240e19adbda5a419800fc8ef', '不知道', '2017-12-27', 'User/UserHeadImage/20171212113042545.jpg', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '男', '吉林大学', 'unregistered');
INSERT INTO `user` VALUES ('89237486', '18843019858', '静静', '088fb90a818af994fe49e00da9352abb', '冷静', '1997-08-08', 'User/UserHeadImage/20171212030838515.png', '这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '女', 'jiutai', 'user');
INSERT INTO `user` VALUES ('89342648', '18896526632', '芊儿~', 'b28880e65af46bea2f68c3ca81d207cf', '万蔓芊', '1998-02-06', 'User/UserHeadImage/20171212030838515.png', '																																																				这个用户很懒，什么都没有写哦。', '0000000000', '0000000000', '女', '湖北', 'user');

-- ----------------------------
-- Table structure for `user_picture`
-- ----------------------------
DROP TABLE IF EXISTS `user_picture`;
CREATE TABLE `user_picture` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `picture` varchar(255) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`user_id`,`picture`),
  CONSTRAINT `userppp` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_picture
-- ----------------------------
INSERT INTO `user_picture` VALUES ('43104933', 'UploadPhoto/20171219114608201.jpg');
INSERT INTO `user_picture` VALUES ('43104933', 'UploadPhoto/20171219114629891.jpg');
INSERT INTO `user_picture` VALUES ('43104944', 'UploadPhoto/20171213054206193.jpg');
INSERT INTO `user_picture` VALUES ('43104944', 'UploadPhoto/20171214042415841.jpg');
INSERT INTO `user_picture` VALUES ('43104944', 'UploadPhoto/20171214060107644.jpg');

-- ----------------------------
-- View structure for `money`
-- ----------------------------
DROP VIEW IF EXISTS `money`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `money` AS select `user`.`user_id` AS `user_id`,`user`.`huaya` AS `huaya_num`,`user`.`huaguan` AS `huaguan_num` from `user` ;
DROP TRIGGER IF EXISTS `moneySub`;
DELIMITER ;;
CREATE TRIGGER `moneySub` BEFORE INSERT ON `productorder` FOR EACH ROW begin
if (new. count_price>(select huaya_num from money where money.user_id=new.order_by))
then 
set new.order_by=null;
else 
update money set money.huaya_num=money.huaya_num-new.count_price  where new.order_by=money.user_id;
update money set money.huaya_num=money.huaya_num+new.count_price  where new.order_to=money.user_id;
end if;
end
;;
DELIMITER ;
