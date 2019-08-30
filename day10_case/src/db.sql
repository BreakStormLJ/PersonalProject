/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - day17
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`day17` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `day17`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`NAME`,`gender`,`username`,`password`) values (1,'张三','男','zhangsan','123456'),(2,'李四','女','lisi','456789');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`gender`,`age`,`address`,`qq`,`email`) values (16,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(17,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(18,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(19,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(20,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(21,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(22,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(23,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(24,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(25,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(26,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(27,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(28,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(29,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(30,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(31,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(32,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(33,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(34,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(35,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(36,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(37,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(38,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(39,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(40,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(41,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(42,'王五','男',32,'陕西','3435201','wangwu_itcast@163.com'),(43,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(45,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(46,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(47,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(48,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(49,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(50,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(51,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(52,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(53,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(54,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(55,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(56,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(57,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(58,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(59,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(60,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(61,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(62,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(63,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(64,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(65,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(66,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(67,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(68,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(69,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(70,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(71,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(72,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(73,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(74,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(75,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(76,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(77,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(78,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(79,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(80,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(81,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(82,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(83,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(84,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(85,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(86,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(87,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(88,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(89,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(90,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(91,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(92,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(93,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(94,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(95,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(96,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(97,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(98,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(99,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(100,'张三','男',12,'广东','34567801','zhs_itcast@163.com'),(101,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(102,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com'),(104,'李四','女',22,'广西','3436401','lisi_itcast@163.com'),(105,'王五','男',32,'湖南','3435201','wangwu_itcast@163.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

SELECT * FROM USER WHERE 1=1 AND NAME LIKE '%李%' AND address LIKE '%广%' LIMIT 0,5;
SELECT COUNT(*) FROM USER WHERE 1=1 AND NAME LIKE '%李%' AND address LIKE '%广%';
