drop table if exists t_role;
create table t_role
(
 id          int not null AUTO_INCREMENT,
 name        char(50) not null,
 rolekey     char(50) not null,
 primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_role values ('1', '系统管理员', 'admin');
insert into t_role values ('2', '机构管理员', 'orgadmin');
insert into t_role values ('3', '老师', 'teacher');
insert into t_role values ('4', '学生', 'student');
insert into t_role values ('5', '企业管理员', 'enterpriseadmin');

drop table if exists t_user;
create table `t_user` (
  `id`                 int(11) not null auto_increment,
  `bm`                 varchar(50) default null,
  `xh`                 varchar(50) default null,
  `xjh`                varchar(50) default null,
  `username`           varchar(100) not null,
  `realname`           varchar(100) default null,
  `password`           varchar(100) not null,
  `email`              varchar(100) default null,
  `status`             int(11) default '0',
  `headimg`            varchar(100) default null,
  `sex`                 varchar(10) default '0',
  `describle`          varchar(1000) default null,
  `code`               varchar(100) default null,
  `codeimg`            varchar(100) default null,
  `token`              varchar(100) default null,
  `pwdtoken`           varchar(100) default null,
  `job`                varchar(100) default null,
  `phone`              varchar(20) default null,
  `qq`                 varchar(20) default null,
  `isenable`           int(11) default '0',
  `createtime`         varchar(100) default null,
  `platform`           varchar(1000) default null,
  `storagesize`        int(11) default '500',
  `field`              varchar(1000) default null,
  `birthday`           varchar(100) default null,
  `joindate`           varchar(100) default null,
  `province`           varchar(100) default null,
  `city`               varchar(100) default null,
  `modifiedtime`       varchar(100) default null,
  `majorid`            int(11) default null,
  `rip`                varchar(100) default null,
  `points`             int(11) default '0',
  `isrecommend`        int(11) default '0',  
  primary key  (`id`),
  unique key `username` (`username`),
  unique key `email` (`email`)
) engine=innodb default charset=utf8;

insert into t_user(id,username,realname,password,email,status) values ('1', 'admin','管理员', 'e10adc3949ba59abbe56e057f20f883e', 'admin@qq.com', '2');

/*组织机构*/
drop table if exists `t_org`;
CREATE TABLE `t_org` (
  `id`                 int(11) NOT NULL auto_increment,
  `bm`                 varchar(50) default null,
  `name`               varchar(50) NOT NULL,
  `describle`          varchar(2000) default NULL,
  `address`            varchar(255) default NULL,
  `category`           varchar(100) default NULL,
  `logoimg`            varchar(100) default NULL,
  `xcimg`              varchar(100) default NULL,
  `zzimg`              varchar(100) default NULL,
  `fmimg`              varchar(100) default NULL,
  `siteid`             int(11) default null,
  `userid`             int(11) default NULL,
  `status`             int(11) default NULL,
  `reply`              varchar(255) default NULL,
  `email`              varchar(100) default NULL,
  `phone`              varchar(20) default null,
  `isenable`           int(11) default 0,
  `parentid`           int(11) default null,
  `sortnumber`         int(11) DEFAULT '10000',
  isschool             int(11) default 0,
  prov                 varchar(255) default NULL,
  city                 varchar(255) default NULL,
  web                  varchar(255) default NULL,
  stage                varchar(255) default NULL,  
  field                varchar(255) default NULL,
  type                 varchar(255) default NULL,
  num                  int(11) default 0,
  code                 varchar(255) default NULL,
  frimg                varchar(255) default NULL,
  username             varchar(255) default NULL,
  positions            varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  CONSTRAINT `t_org_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT t_org_ibfk_2 FOREIGN KEY (parentid) REFERENCES t_org (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*insert into `t_org` values ('1',null, '先电', '先电', '先电', '高职', '先电', '先电', '先电', '先电', null, '1', '0', '先电', '163@163.com', '13865478291', '0',null,10000,null,null,null,null,null,null,0,null,null);*/
insert into `t_org` values ('1',null, '苏高职', '苏高职', '苏高职', '高职', '苏高职', '苏高职', '苏高职', '苏高职', null, '1', '0', '苏高职', '163@163.com', 
'13865478291', '0',null,10000,0,null,null,null,null,null,null,0,null,null,null,null);

drop table if exists t_orguser;
create table t_orguser
(
 id          int not null AUTO_INCREMENT,
 userid      int(11) not null,
 orgid       int(11) not null,
 status      int default 0,
 reply       varchar(255) default null,
 primary key (id),
 UNIQUE KEY (userid,orgid),
 CONSTRAINT t_orguser_ibfk_1 FOREIGN KEY (userid) REFERENCES t_user (id) ON DELETE CASCADE,
 CONSTRAINT t_orguser_ibfk_2 FOREIGN KEY (orgid) REFERENCES t_org (id) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_orguser values ('1', 1, 1,2,null);

drop table if exists t_userrole;
create table t_userrole
(
 id          int not null AUTO_INCREMENT,
 userid      int(11) not null,
 roleid      int(11) not null,
 status      int default 0,
 reply       varchar(255) default null,
 primary key (id),
 UNIQUE KEY (userid,roleid),
 CONSTRAINT t_userrole_ibfk_1 FOREIGN KEY (userid) REFERENCES t_user (id) ON DELETE CASCADE,
 CONSTRAINT t_userrole_ibfk_2 FOREIGN KEY (roleid) REFERENCES t_role (id) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `t_userrole` VALUES ('1', '1', '1', '2', null);
INSERT INTO `t_userrole` VALUES ('2', '1', '2', '2', null);
INSERT INTO `t_userrole` VALUES ('3', '1', '3', '2', null);
INSERT INTO `t_userrole` VALUES ('4', '1', '4', '2', null);

/*教务相关的信息专业*/
drop table if exists t_major;
create table t_major
(
 id          int not null AUTO_INCREMENT,
 bm          varchar(50) default null,
 name        varchar(100) not null,
 parentid	 int(11) default null,
 orgid       int(11) default null,
 myear       varchar(100) default null,
 intro				text default null,
 status				varchar(50) default '未发布',
 primary key (id),
 constraint t_major_ibfk_1 foreign key (orgid) references t_org(id) on delete cascade,
 constraint t_major_ibfk_2 foreign key (parentid) references t_major(id) on delete cascade
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `t_major` VALUES ('1',null, '专业', null, '1',null,null,'未发布');

/*级别：小学，初中，高中*/
drop table if exists t_level;
create table t_level
(
   id                  int not null AUTO_INCREMENT,
   name                varchar(255) not null, 
   describle           varchar(255) default null, 
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
insert into `t_level` values ('1', '高职', null);

/*年级*/
drop table if exists t_grade;
create table t_grade
(
   id                  int not null AUTO_INCREMENT,
   name                varchar(255) default null, 
   describle           varchar(255) default null, 
   levelid             int(20) default null, 
   primary key (id),
   CONSTRAINT t_grade_ibfk_1 FOREIGN KEY (levelid) REFERENCES t_level(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
insert into `t_grade` values ('1', '一年级', null,1);

/*日志*/
drop table if exists t_log;
CREATE TABLE t_log (  
  id                  int not null auto_increment,
  userid              int(11) default null,
  ip                  varchar(100) default null,
  equipment           varchar(100) default null,
  courseid            int(11) default null,
  objecttype          varchar(100) default null,
  objectid            varchar(100) default null,
  createdate          varchar(100) default null,
  content             varchar(255) default null,
  operation           varchar(100) default null,
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists t_datachange_log;
CREATE TABLE t_datachange_log (  
  id                  int not null auto_increment,
  userid              int(11) default null,
  objecttype          varchar(100) default null,
  objectid            varchar(100) default null,
  createdate          varchar(100) default null,
  content             varchar(255) default null,
  operation           varchar(100) default null,
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*消息*/
drop table if exists t_message;
CREATE TABLE t_message (  
  id                  int not null auto_increment,
  userid              int(11) default null,
  isread              int(11) default 0,
  title               varchar(255) default null,
  content             varchar(1000) default null,
  createdate          varchar(100) default null,
  hrefcon             varchar(100) default null,
  type                int(11) default 0,
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*用户积分配置表*/
drop table if exists t_pointsetting;
CREATE TABLE t_pointsetting (
  id                  int not null auto_increment,  
  jfkey               varchar(255) default null,
  content             varchar(100) default null,
  point               int(11) default null,
  type                int(11) default 0,
  primary key (id),
  UNIQUE KEY (jfkey)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `t_pointsetting` values ('1','_zykupload_','资源库上传',1, 0);
insert into `t_pointsetting` values ('2','_zykpilun_','资源评论',1, 0);
insert into `t_pointsetting` values ('3','_zykdownload_','资源下载',1, 0);
insert into `t_pointsetting` values ('4','_zykckis1_','资源审核通过',1, 0);
insert into `t_pointsetting` values ('5','_zykckis2_','资源审核未通过',-1, 0);

insert into `t_pointsetting` values ('6','_moocsavecourse_','云课堂创建班次',1, 1);
insert into `t_pointsetting` values ('7','_moocsaveunit_','云课堂创建单元',1, 1);
insert into `t_pointsetting` values ('8','_moocsaveapparise_','云课堂评论课程',1, 1);
insert into `t_pointsetting` values ('9','_moocsaveunitquestion_','云课堂提问',1, 1);
insert into `t_pointsetting` values ('10','_moocreplyunitquestion_','云课堂回答提问',1, 1);
insert into `t_pointsetting` values ('11','_moocsavenote_','云课堂记录笔记',1, 1);
insert into `t_pointsetting` values ('12','_moocsaveunitapparise_','云课堂单元评论',1, 1);
insert into `t_pointsetting` values ('13','_moocfinishexam_','云课堂完成试卷',1, 1);
insert into `t_pointsetting` values ('14','_moocfinishlearn_','云课堂完成单元学习',1, 1);
insert into `t_pointsetting` values ('15','_moocsavetaskans_','云课堂完成投票任务',1, 1);
insert into `t_pointsetting` values ('18','_moocjoincourse_','云课堂加入课程',1, 1);

/*用户积分明细表*/
drop table if exists t_userpoint;
CREATE TABLE t_userpoint (  
  id                  int not null auto_increment,
  userid              int(11) default null,
  point               int(11) default null,
  action              varchar(255) default null,
  createdate          varchar(100) default null,
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*机构管理员*/
drop table if exists t_usermanageorg;
create table t_usermanageorg
(
 id          int not null auto_increment,
 userid      int(11) default null,
 orgid       int(11) default null,
 status      int(11) default null,
 primary key (id),
 constraint t_usermanageorg_ibfk_1 foreign key (userid) references t_user (id) on delete cascade,
 constraint t_usermanageorg_ibfk_2 foreign key (orgid) references t_org (id) on delete cascade
)engine=innodb auto_increment=1 default charset=utf8;

/*班级*/
drop table if exists t_classes;
create table t_classes
(
 id                 int not null auto_increment,
 name               varchar(50) not null,
 majorid            int(11) default null,
 stucount           int(11) default 0,
 yearly             varchar(50) default null,
 createtime         varchar(50) default null,
 status             varchar(50) default null,
 primary key (id),
 constraint t_classes_ibfk_1 foreign key (majorid) references t_major(id) on delete cascade
)engine=innodb auto_increment=1 default charset=utf8;

drop table if exists t_classuser;
create table t_classuser
(
 id                int not null auto_increment,
 classid           int(11) default null,
 userid            int(11) default null,
 status            varchar(50) default null,
 primary key (id),
 constraint t_classuser_ibfk_1 foreign key (classid) references t_classes(id) on delete cascade,
 constraint t_classuser_ibfk_2 foreign key (userid) references t_user(id) on delete cascade
)engine=innodb auto_increment=1 default charset=utf8;