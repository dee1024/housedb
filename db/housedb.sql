/*
 Navicat Premium Data Transfer

 Source Server         : 123.56.11.93-2G
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : 123.56.11.93
 Source Database       : housedb

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : utf-8

 Date: 05/14/2017 21:24:24 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(50) NOT NULL DEFAULT '',
  `link` varchar(255) DEFAULT NULL,
  `parentsId` int(11) DEFAULT '0',
  `parentsName` varchar(255) DEFAULT NULL,
  `parentsCode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `area`
-- ----------------------------
BEGIN;
INSERT INTO `area` VALUES ('1', '北京', 'bj', 'https://bj.lianjia.com', '0', null, null, '1'), ('2', '上海', 'sh', 'https://sh.lianjia.com', '0', null, null, '1'), ('3', '广州', 'gz', 'https://gz.lianjia.com', '0', null, null, '1'), ('4', '深圳', 'sz', 'https://sz.lianjia.com', '0', null, null, '1'), ('11', '东城', 'dongcheng', null, '1', null, null, '2'), ('12', '西城', 'xicheng', null, '1', null, null, '2'), ('13', '朝阳', 'chaoyang', null, '1', null, null, '2'), ('14', '海淀', 'haidian', null, '1', null, null, '2'), ('15', '丰台', 'fengtai', null, '1', null, null, '2'), ('16', '石景山', 'shijingshan', null, '1', null, null, '2'), ('17', '通州', 'tongzhou', null, '1', null, null, '2'), ('18', '昌平', 'changping', null, '1', null, null, '2'), ('19', '大兴', 'daxing', null, '1', null, null, '2'), ('20', '亦庄开发区', 'yizhuangkaifaqu', null, '1', null, null, '2'), ('21', '顺义', 'shunyi', null, '1', null, null, '2'), ('22', '房山', 'fangshan', null, '1', null, null, '2'), ('23', '门头沟', 'mentougou', null, '1', null, null, '2'), ('24', '平谷', 'pinggu', null, '1', null, null, '2'), ('25', '怀柔', 'huairou', null, '1', null, null, '2'), ('26', '密云', 'miyun', null, '1', null, null, '2'), ('27', '延庆', 'yanqing', null, '1', null, null, '2'), ('28', '燕郊', 'yanjiao', null, '1', null, null, '2'), ('29', '浦东', 'pudongxinqu', null, '2', null, null, '2'), ('30', '闵行', 'minhang', null, '2', null, null, '2'), ('31', '宝山', 'baoshan', null, '2', null, null, '2'), ('32', '徐汇', 'xuhui', null, '2', null, null, '2'), ('33', '普陀', 'putuo', null, '2', null, null, '2'), ('34', '杨浦', 'yangpu', null, '2', null, null, '2'), ('35', '长宁', 'changning', null, '2', null, null, '2'), ('36', '松江', 'songjiang', null, '2', null, null, '2'), ('37', '嘉定', 'jiading', null, '2', null, null, '2'), ('38', '黄浦', 'huangpu', null, '2', null, null, '2'), ('39', '静安', 'jingan', null, '2', null, null, '2'), ('40', '闸北', 'zhabei', null, '2', null, null, '2'), ('41', '虹口', 'hongkou', null, '2', null, null, '2'), ('42', '青浦', 'qingpu', null, '2', null, null, '2'), ('43', '奉贤', 'fengxian', null, '2', null, null, '2'), ('44', '金山', 'jinshan', null, '2', null, null, '2'), ('45', '崇明', 'chongming', null, '2', null, null, '2'), ('46', '上海周边', 'shanghaizhoubian', null, '2', null, null, '2'), ('47', '天河', 'tianhe', null, '3', null, null, '3'), ('48', '越秀', 'yuexiu', null, '3', null, null, '3'), ('49', '荔湾', 'liwan', null, '3', null, null, '3'), ('50', '海珠', 'haizhu', null, '3', null, null, '3'), ('51', '番禺', 'panyu', null, '3', null, null, '3'), ('52', '白云', 'baiyun', null, '3', null, null, '3'), ('53', '黄埔', 'huangpugz', null, '3', null, null, '3'), ('54', '从化', 'conghua', null, '3', null, null, '3'), ('55', '增城', 'zengcheng', null, '3', null, null, '3'), ('56', '花都', 'huadou', null, '3', null, null, '3'), ('57', '萝岗', 'luogang', null, '3', null, null, '3'), ('58', '南沙', 'nansha', null, '3', null, null, '3'), ('59', '罗湖', 'luohu', null, '4', null, null, '3'), ('60', '福田', 'futian', null, '4', null, null, '3'), ('61', '南山', 'nanshan', null, '4', null, null, '3'), ('62', '盐田', 'yantian', null, '4', null, null, '3'), ('63', '宝安', 'baoan', null, '4', null, null, '3'), ('64', '龙岗', 'longgang', null, '4', null, null, '3'), ('65', '龙华新区', 'longhuaxinqu', null, '4', null, null, '3'), ('66', '光明新区', 'guangmingxinqu', null, '4', null, null, '3'), ('67', '坪山新区', 'pingshanxinqu', null, '4', null, null, '3'), ('68', '大鹏新区', 'dapengxinqu', null, '4', null, null, '3'), ('69', '岑村', 'cencun', null, '47', null, null, '3'), ('70', '车陂', 'chebei', null, '47', null, null, '3'), ('71', '长兴', 'changxing1', null, '47', null, null, '3'), ('72', '东圃', 'dongpu', null, '47', null, null, '3'), ('73', '东莞庄', 'dongguanzhuang', null, '47', null, null, '3'), ('74', '东站', 'dongzhan', null, '47', null, null, '3'), ('75', '高唐', 'gaotang', null, '47', null, null, '3'), ('76', '华景新城', 'huajingxincheng', null, '47', null, null, '3'), ('77', '汇景新城', 'huijingxincheng', null, '47', null, null, '3'), ('78', '黄村', 'huangcun', null, '47', null, null, '3'), ('79', '龙洞', 'longdong', null, '47', null, null, '3'), ('80', '龙口东', 'longkoudong', null, '47', null, null, '3'), ('81', '龙口西', 'longkouxi', null, '47', null, null, '3'), ('82', '石牌', 'shipai1', null, '47', null, null, '3'), ('83', '水荫', 'shuiyin', null, '47', null, null, '3'), ('84', '沙太北', 'shataibei', null, '47', null, null, '3'), ('85', '沙太南', 'shatainan', null, '47', null, null, '3'), ('86', '沙河', 'shahe1', null, '47', null, null, '3'), ('87', '天河北', 'tianhebei', null, '47', null, null, '3'), ('88', '天河公园', 'tianhegongyuan', null, '47', null, null, '3'), ('89', '棠下', 'tangxia1', null, '47', null, null, '3'), ('90', '天河南', 'tianhenan', null, '47', null, null, '3'), ('91', '体育中心', 'tiyuzhongxin', null, '47', null, null, '3'), ('92', '天润路', 'tianrunlu', null, '47', null, null, '3'), ('93', '五山', 'wushan', null, '47', null, null, '3'), ('94', '员村', 'yuancun', null, '47', null, null, '3'), ('95', '粤垦', 'yueken', null, '47', null, null, '3'), ('96', '珠江新城东', 'zhujiangxinchengdong', null, '47', null, null, '3'), ('97', '珠江新城西', 'zhujiangxinchengxi', null, '47', null, null, '3'), ('98', '珠江新城中', 'zhujiangxinchengzhong', null, '47', null, null, '3'), ('99', '北京路', 'beijinglu', null, '48', null, null, '3'), ('100', '东川路', 'dongchuanlu', null, '48', null, null, '3'), ('101', '东湖', 'donghu1', null, '48', null, null, '3'), ('102', '东山口', 'dongshankou', null, '48', null, null, '3'), ('103', '东风西', 'dongfengxi', null, '48', null, null, '3'), ('104', '东风东', 'dongfengdong', null, '48', null, null, '3'), ('105', '二沙岛', 'ershadao', null, '48', null, null, '3'), ('106', '桂花岗', 'guihuagang', null, '48', null, null, '3'), ('107', '环市东', 'huanshidong', null, '48', null, null, '3'), ('108', '海珠广场', 'haizhuguangchang', null, '48', null, null, '3'), ('109', '黄花岗', 'huanghuagang', null, '48', null, null, '3'), ('110', '建设路', 'jianshelu1', null, '48', null, null, '3'), ('111', '解放北', 'jiefangbei', null, '48', null, null, '3'), ('112', '解放南', 'jiefangnan', null, '48', null, null, '3'), ('113', '机场路', 'jichanglu', null, '48', null, null, '3'), ('114', '麓景', 'lujing', null, '48', null, null, '3'), ('115', '流花站前', 'liuhuazhanqian', null, '48', null, null, '3'), ('116', '农讲所', 'nongjiangsuo', null, '48', null, null, '3'), ('117', '盘福', 'panfu', null, '48', null, null, '3'), ('118', '人民路', 'renminlu', null, '48', null, null, '3'), ('119', '人民北', 'renminbei1', null, '48', null, null, '3'), ('121', '三元里', 'sanyuanli', null, '48', null, null, '3'), ('123', '淘金', 'taojin', null, '48', null, null, '3'), ('124', '五羊新城', 'wuyangxincheng', null, '48', null, null, '3'), ('125', '小北', 'xiaobei', null, '48', null, null, '3'), ('126', '西门口', 'ximenkou', null, '48', null, null, '3'), ('127', '西华路', 'xihualu', null, '48', null, null, '3'), ('128', '杨箕', 'yangji', null, '48', null, null, '3'), ('129', '越秀南', 'yuexiunan', null, '48', null, null, '3'), ('130', '陈家祠', 'chenjiaci', null, '49', null, null, '3'), ('131', '茶滘', 'chajiao', null, '49', null, null, '3'), ('132', '大坦沙', 'datansha', null, '49', null, null, '3'), ('134', '东塱', 'donglang', null, '49', null, null, '3'), ('135', '汾水', 'fenshui', null, '49', null, null, '3'), ('136', '芳村大道', 'fangcundadao', null, '49', null, null, '3'), ('137', '黄沙', 'huangsha', null, '49', null, null, '3'), ('138', '鹤洞', 'hedong1', null, '49', null, null, '3'), ('139', '花地大道', 'huadidadao', null, '49', null, null, '3'), ('140', '滘口', 'jiaokou1', null, '49', null, null, '3'), ('141', '坑口', 'kengkou', null, '49', null, null, '3'), ('142', '龙溪', 'longxi1', null, '49', null, null, '3'), ('144', '荔湾路', 'liwanlu', null, '49', null, null, '3'), ('145', '南岸路', 'nananlu', null, '49', null, null, '3'), ('148', '西场', 'xichang', null, '49', null, null, '3'), ('149', '西关', 'xiguan', null, '49', null, null, '3'), ('150', '西村', 'xicun', null, '49', null, null, '3'), ('152', '中山八', 'zhongshanba1', null, '49', null, null, '3'), ('153', '宝岗', 'baogang', null, '50', null, null, '3'), ('154', '滨江西', 'binjiangxi', null, '50', null, null, '3'), ('155', '滨江中', 'binjiangzhong', null, '50', null, null, '3'), ('156', '滨江东', 'binjiangdong', null, '50', null, null, '3'), ('157', '赤岗', 'chigang', null, '50', null, null, '3'), ('158', '昌岗', 'changgang1', null, '50', null, null, '3'), ('159', '东晓南', 'dongxiaonan', null, '50', null, null, '3'), ('160', '东晓路', 'dongxiaolu', null, '50', null, null, '3'), ('161', '工业大道中', 'gongyedadaozhong', null, '50', null, null, '3'), ('162', '官洲', 'guanzhou', null, '50', null, null, '3'), ('163', '广州大道南', 'guangzhoudadaonan', null, '50', null, null, '3'), ('164', '工业大道北', 'gongyedadaobei', null, '50', null, null, '3'), ('165', '工业大道南', 'gongyedadaonan', null, '50', null, null, '3'), ('166', '江南大道', 'jiangnandadao', null, '50', null, null, '3'), ('167', '金碧', 'jinbi', null, '50', null, null, '3'), ('168', '江南西', 'jiangnanxi', null, '50', null, null, '3'), ('169', '江燕路', 'jiangyanlu', null, '50', null, null, '3'), ('170', '南洲', 'nanzhou', null, '50', null, null, '3'), ('171', '琶洲', 'pazhou', null, '50', null, null, '3'), ('172', '前进路', 'qianjinlu', null, '50', null, null, '3'), ('173', '同福', 'tongfu', null, '50', null, null, '3'), ('174', '新港西', 'xingangxi', null, '50', null, null, '3'), ('175', '大石', 'dashi', null, '51', null, null, '3'), ('176', '大学城', 'daxuecheng', null, '51', null, null, '3'), ('177', '华南', 'huanan1', null, '51', null, null, '3'), ('178', '洛溪', 'luoxi', null, '51', null, null, '3'), ('179', '南浦', 'nanpu', null, '51', null, null, '3'), ('180', '祈福', 'qifu', null, '51', null, null, '3'), ('181', '石楼镇', 'shilouzhen', null, '51', null, null, '3'), ('182', '沙湾', 'shawan1', null, '51', null, null, '3'), ('183', '市桥', 'shiqiao1', null, '51', null, null, '3'), ('184', '石基', 'shiji1', null, '51', null, null, '3'), ('185', '新造', 'xinzao', null, '51', null, null, '3'), ('186', '亚运大道中', 'yayundadaozhong', null, '51', null, null, '3'), ('187', '亚运城', 'yayuncheng', null, '51', null, null, '3'), ('188', '钟村', 'zhongcun', null, '51', null, null, '3'), ('189', '白云大道南', 'baiyundadaonan', null, '52', null, null, '3'), ('190', '白云大道北', 'baiyundadaobei', null, '52', null, null, '3'), ('191', '从化', 'conghua1', null, '52', null, null, '3'), ('192', '大金钟', 'dajinzhong', null, '52', null, null, '3'), ('194', '广园路', 'guangyuanlu', null, '52', null, null, '3'), ('195', '黄石路', 'huangshilu', null, '52', null, null, '3'), ('196', '金沙洲', 'jinshazhou', null, '52', null, null, '3'), ('198', '罗冲围', 'luochongwei', null, '52', null, null, '3'), ('199', '南湖', 'nanhu5', null, '52', null, null, '3'), ('200', '人和', 'renhe1', null, '52', null, null, '3'), ('201', '石井', 'shijing', null, '52', null, null, '3'), ('205', '同德围', 'tongdewei', null, '52', null, null, '3'), ('206', '同和', 'tonghe1', null, '52', null, null, '3'), ('207', '新市', 'xinshi', null, '52', null, null, '3'), ('208', '钟落谭', 'zhongluotan', null, '52', null, null, '3'), ('209', '长洲岛', 'changzhoudao1', null, '53', null, null, '3'), ('210', '开发区', 'kaifaqu3', null, '53', null, null, '3'), ('211', '科学城', 'kexuecheng', null, '53', null, null, '3'), ('212', '区府', 'qufu', null, '53', null, null, '3'), ('214', '福和镇', 'fuhezhen', null, '54', null, null, '3'), ('215', '流溪河半岛', 'liuxihebandao', null, '54', null, null, '3'), ('216', '东湖公园', 'donghugongyuan', null, '55', null, null, '3'), ('218', '荔城', 'licheng1', null, '55', null, null, '3'), ('219', '三江镇', 'sanjiangzhen', null, '55', null, null, '3'), ('220', '石滩镇', 'shitanzhen', null, '55', null, null, '3'), ('221', '小楼镇', 'xiaolouzhen', null, '55', null, null, '3'), ('222', '象岭', 'xiangling', null, '55', null, null, '3'), ('223', '新塘', 'xintang', null, '55', null, null, '3'), ('224', '永和', 'yonghe1', null, '55', null, null, '3'), ('225', '正果镇', 'zhengguozhen', null, '55', null, null, '3'), ('226', '中新镇', 'zhongxinzhen', null, '55', null, null, '3'), ('227', '赤坭镇', 'chinizhen', null, '56', null, null, '3'), ('228', '芙蓉镇', 'furongzhen', null, '56', null, null, '3'), ('229', '花东镇', 'huadongzhen', null, '56', null, null, '3'), ('230', '花山镇', 'huashanzhen', null, '56', null, null, '3'), ('231', '旧区', 'jiuqu', null, '56', null, null, '3'), ('232', '山前大道', 'shanqiandadao', null, '56', null, null, '3'), ('233', '狮岭', 'shiling', null, '56', null, null, '3'), ('234', '炭步镇', 'tanbuzhen', null, '56', null, null, '3'), ('235', '铁路西', 'tieluxi', null, '56', null, null, '3'), ('236', '兴北镇', 'xingbeizhen', null, '56', null, null, '3'), ('237', '新区', 'xinqu1', null, '56', null, null, '3'), ('238', '开发东区', 'kaifadongqu', null, '57', null, null, '3'), ('239', '开发西区', 'kaifaxiqu', null, '57', null, null, '3'), ('242', '大岗镇', 'dagangzhen', null, '58', null, null, '3'), ('243', '东涌镇', 'dongyongzhen', null, '58', null, null, '3'), ('244', '黄阁', 'huangge', null, '58', null, null, '3'), ('245', '横沥镇', 'henglizhen2', null, '58', null, null, '3'), ('246', '金洲', 'jinzhou2', null, '58', null, null, '3'), ('247', '榄核镇', 'lanhezhen', null, '58', null, null, '3'), ('248', '南沙区府', 'nanshaqufu', null, '58', null, null, '3'), ('249', '新垦镇', 'xinkenzhen', null, '58', null, null, '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
--  Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 NOT NULL,
  `url` varchar(500) CHARACTER SET utf8 NOT NULL,
  `title` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `favcount` int(11) DEFAULT NULL,
  `cartcount` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `unitprice` decimal(10,0) DEFAULT NULL,
  `firstPayPrice` decimal(10,0) DEFAULT NULL,
  `taxPrice` decimal(10,0) DEFAULT NULL,
  `roomMainInfo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roomSubInfo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roomMainType` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roomSubType` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `areaMainInfo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `areaSubInfo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `communityName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `areaName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `schoolName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `decoratingDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `houseTypeDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `investmentDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `villageDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `schoolDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sellingPointDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `reason4saleDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `supportingDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `trafficDesc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `baseContent1` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent2` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent4` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent5` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent6` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent7` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent8` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent9` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent10` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent11` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `baseContent12` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent1` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent2` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent4` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent5` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent6` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent7` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent8` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent9` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `transactionContent10` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `html` mediumtext COLLATE utf8_bin,
  `roomSize` double(32,0) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1:在售；2:已成交;-1:已下架',
  `chengjiaoPrice` double DEFAULT NULL COMMENT '成交价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64037 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `houseindex`
-- ----------------------------
DROP TABLE IF EXISTS `houseindex`;
CREATE TABLE `houseindex` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` int(11) DEFAULT '0' COMMENT '-1:已下架；0:未拉取；1:已拉取；',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `lastcheckdate` date DEFAULT NULL,
  `hasdetail` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73389 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `houseprice`
-- ----------------------------
DROP TABLE IF EXISTS `houseprice`;
CREATE TABLE `houseprice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code_date` (`code`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35746 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `http_proxy`
-- ----------------------------
DROP TABLE IF EXISTS `http_proxy`;
CREATE TABLE `http_proxy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(50) NOT NULL,
  `port` int(11) NOT NULL,
  `location` varchar(50) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '-1已失效;0未使用;1已使用',
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_host` (`host`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11280 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `process`
-- ----------------------------
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(100) NOT NULL,
  `pageNo` int(10) unsigned DEFAULT '0',
  `finished` int(11) DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1:在售房源任务类别; 2:已成交房源任务类别',
  `createtime` date DEFAULT NULL,
  `finishtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_area` (`area`,`createtime`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38002 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
