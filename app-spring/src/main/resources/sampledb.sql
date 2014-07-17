DROP DATABASE IF EXISTS zhanglijun;
CREATE DATABASE zhanglijun DEFAULT CHARACTER SET utf8;
USE zhanglijun;

##创建用户表
CREATE TABLE t_user (
   id   INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(30),
   credits INT,
   password  VARCHAR(32),
   last_visit datetime,
   last_ip  VARCHAR(23)
)ENGINE=InnoDB;

##创建用户登录日志表
CREATE TABLE t_login_log (
   id  INT AUTO_INCREMENT PRIMARY KEY,
   user_id   INT,
   ip  VARCHAR(23),
   login_datetime datetime
)ENGINE=InnoDB; 

##插入初始化数据
INSERT INTO t_user (name,password) VALUES('admin','123456');
COMMIT;