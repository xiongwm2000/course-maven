-- ----------------------------
-- Trigger structure for insergrade
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `insergrade` AFTER INSERT ON `t_grade` FOR EACH ROW begin
	insert into classresource.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classdisk.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classsgz.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classmooc.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classexam.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updatergrade
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updatergrade` AFTER UPDATE ON `t_grade` FOR EACH ROW begin
update classresource.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classdisk.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classexam.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classsgz.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classmooc.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;

end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deletegrade
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deletegrade` BEFORE DELETE ON `t_grade` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_grade where id=@oid;
    delete from classexam.t_grade where id=@oid;
	delete from classsgz.t_grade where id=@oid;
	delete from classdisk.t_grade where id=@oid;
    delete from classmooc.t_grade where id=@oid;
	delete from classdisk.t_grade where id=@oid;
    delete from classmooc.t_grade where id=@oid;
	
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inserglevel
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inserglevel` AFTER INSERT ON `t_level` FOR EACH ROW begin
	insert into classresource.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classsgz.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classdisk.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classmooc.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classexam.t_level(id,name,describle)values(new.id,new.name,new.describle);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updaterlevel
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updaterlevel` AFTER UPDATE ON `t_level` FOR EACH ROW begin
update classresource.t_level set name=new.name,describle=new.describle where id=old.id;
update classsgz.t_level set name=new.name,describle=new.describle where id=old.id;
update classexam.t_level set name=new.name,describle=new.describle where id=old.id;
update classdisk.t_level set name=new.name,describle=new.describle where id=old.id;
update classmooc.t_level set name=new.name,describle=new.describle where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deletelevel
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deletelevel` BEFORE DELETE ON `t_level` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_level where id=@oid;
	delete from classexam.t_level where id=@oid;
	delete from classsgz.t_level where id=@oid;
	delete from classdisk.t_level where id=@oid;
              delete from classmooc.t_level where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for insermajor
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `insermajor` AFTER INSERT ON `t_major` FOR EACH ROW begin
	insert into classresource.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into classexam.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into classsgz.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into classdisk.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
              insert into classmooc.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updatermajor
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updatermajor` AFTER UPDATE ON `t_major` FOR EACH ROW begin
update classresource.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classexam.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classsgz.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classdisk.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classmooc.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deletemajor
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deletemajor` BEFORE DELETE ON `t_major` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_major where id=@oid;
	delete from classexam.t_major where id=@oid;
	delete from classsgz.t_major where id=@oid;
	delete from classdisk.t_major where id=@oid;
	delete from classmooc.t_major where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for insermessage
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `insermessage` AFTER INSERT ON `t_message` FOR EACH ROW begin
	insert into classresource.t_message(id,userid,isread,title,content,createdate,hrefcon,type)values(new.id,new.userid,new.isread,new.title,new.content,new.createdate,new.hrefcon,new.type);
	insert into classexam.t_message(id,userid,isread,title,content,createdate,hrefcon,type)values(new.id,new.userid,new.isread,new.title,new.content,new.createdate,new.hrefcon,new.type);
	insert into classsgz.t_message(id,userid,isread,title,content,createdate,hrefcon,type)values(new.id,new.userid,new.isread,
	new.title,new.content,new.createdate,new.hrefcon,new.type);
	insert into classdisk.t_message(id,userid,isread,title,content,createdate,hrefcon,type)values(new.id,new.userid,new.isread,new.title,new.content,new.createdate,new.hrefcon,new.type);
	insert into classmooc.t_message(id,userid,isread,title,content,createdate,hrefcon,type)values(new.id,new.userid,new.isread,new.title,new.content,new.createdate,new.hrefcon,new.type);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updatermessage
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updatermessage` AFTER UPDATE ON `t_message` FOR EACH ROW begin
	update classresource.t_message set userid=new.userid,isread=new.isread,title=new.title,content=new.content,createdate=new.createdate,hrefcon=new.hrefcon,type=new.type where id=old.id;
	update classexam.t_message set userid=new.userid,isread=new.isread,title=new.title,content=new.content,createdate=new.createdate,hrefcon=new.hrefcon,type=new.type where id=old.id;
	update classsgz.t_message set userid=new.userid,isread=new.isread,title=new.title,content=new.content,createdate=new.createdate,hrefcon=new.hrefcon,type=new.type where id=old.id;
	update classdisk.t_message set userid=new.userid,isread=new.isread,title=new.title,content=new.content,createdate=new.createdate,hrefcon=new.hrefcon,type=new.type where id=old.id;
	update classmooc.t_message set userid=new.userid,isread=new.isread,title=new.title,content=new.content,createdate=new.createdate,hrefcon=new.hrefcon,type=new.type where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deletemessage
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deletemessage` BEFORE DELETE ON `t_message` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_message  where id=@oid;
    delete from classexam.t_message  where id=@oid;
	delete from classsgz.t_message  where id=@oid;
	delete from classdisk.t_message  where id=@oid;
    delete from classmooc.t_message  where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inserorg
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inserorg` AFTER INSERT ON `t_org` FOR EACH ROW begin
	insert into classresource.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );
	insert into classexam.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );
	insert into classsgz.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );
	insert into classdisk.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid ); 
              insert into classmooc.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid ); 
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updateorg
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updateorg` AFTER UPDATE ON `t_org` FOR EACH ROW begin
update classresource.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classexam.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classsgz.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classdisk.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classmooc.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deleteorg
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deleteorg` BEFORE DELETE ON `t_org` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_org   where id=@oid;
	delete from classexam.t_org   where id=@oid;
	delete from classsgz.t_org   where id=@oid;
	delete from classdisk.t_org   where id=@oid;
              delete from classmooc.t_org   where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inserorguser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inserorguser` AFTER INSERT ON `t_orguser` FOR EACH ROW begin
	insert into classresource.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classexam.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classsgz.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classdisk.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classmooc.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updateorguser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updateorguser` AFTER UPDATE ON `t_orguser` FOR EACH ROW begin
update classresource.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classexam.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classsgz.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classdisk.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classmooc.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deleteorguser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deleteorguser` BEFORE DELETE ON `t_orguser` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_orguser   where id=@oid;
	delete from classexam.t_orguser   where id=@oid;
	delete from classsgz.t_orguser   where id=@oid;
	delete from classdisk.t_orguser   where id=@oid;
	delete from classmooc.t_orguser   where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inserrole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inserrole` AFTER INSERT ON `t_role` FOR EACH ROW begin
	insert into classresource.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classexam.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classsgz.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classdisk.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classmooc.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updaterrole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updaterrole` AFTER UPDATE ON `t_role` FOR EACH ROW begin
update classresource.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classexam.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classsgz.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classdisk.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classmooc.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deleterole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deleterole` BEFORE DELETE ON `t_role` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_role  where id=@oid;
    delete from classexam.t_role  where id=@oid;
	delete from classsgz.t_role  where id=@oid;
	delete from classdisk.t_role  where id=@oid;
              delete from classmooc.t_role  where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inseruser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inseruser` AFTER INSERT ON `t_user` FOR EACH ROW begin
	insert into classresource.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classexam.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classsgz.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classdisk.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classmooc.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updateuser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updateuser` AFTER UPDATE ON `t_user` FOR EACH ROW begin
update classresource.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classexam.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classsgz.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classdisk.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classmooc.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deleteuser
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deleteuser` BEFORE DELETE ON `t_user` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_user   where id=@oid;
	delete from classexam.t_user   where id=@oid;
	delete from classsgz.t_user   where id=@oid;
	delete from classdisk.t_user   where id=@oid;
	delete from classmooc.t_user   where id=@oid;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for inseruserrole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `inseruserrole` AFTER INSERT ON `t_userrole` FOR EACH ROW begin
	insert into classresource.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classexam.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classsgz.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classdisk.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classmooc.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for updateuserrole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `updateuserrole` AFTER UPDATE ON `t_userrole` FOR EACH ROW begin
update classresource.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classexam.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classsgz.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classdisk.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classmooc.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
end;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for deleteuserrole
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `deleteuserrole` BEFORE DELETE ON `t_userrole` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from classresource.t_userrole   where id=@oid;
	delete from classexam.t_userrole   where id=@oid;
	delete from classsgz.t_userrole   where id=@oid;
	delete from classdisk.t_userrole   where id=@oid;
	delete from classmooc.t_userrole   where id=@oid;
end;;
DELIMITER ;
