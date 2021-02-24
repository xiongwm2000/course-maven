DELIMITER ;;
CREATE TRIGGER `insergrade` AFTER INSERT ON `t_grade` FOR EACH ROW begin
	insert into classdisk.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classexam.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into classresource.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
	insert into mooc.t_grade(id,name,describle,levelid)values(new.id,new.name,new.describle,new.levelid);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updatergrade`;
DELIMITER ;;
CREATE TRIGGER `updatergrade` AFTER UPDATE ON `t_grade` FOR EACH ROW begin
update classdisk.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classexam.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update classresource.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
update mooc.t_grade set name=new.name,describle=new.describle,levelid=new.levelid where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deletegrade`;
DELIMITER ;;
CREATE TRIGGER `deletegrade` BEFORE DELETE ON `t_grade` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_grade   where id=@oid;
	delete from classexam.t_grade  where id=@oid;
	delete from classresource.t_grade where id=@oid;
	delete from mooc.t_grade where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inserglevel`;
DELIMITER ;;
CREATE TRIGGER `inserglevel` AFTER INSERT ON `t_level` FOR EACH ROW begin
	insert into classdisk.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classexam.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into classresource.t_level(id,name,describle)values(new.id,new.name,new.describle);
	insert into mooc.t_level(id,name,describle)values(new.id,new.name,new.describle);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updaterlevel`;
DELIMITER ;;
CREATE TRIGGER `updaterlevel` AFTER UPDATE ON `t_level` FOR EACH ROW begin
update classdisk.t_level set name=new.name,describle=new.describle where id=old.id;
update classexam.t_level set name=new.name,describle=new.describle where id=old.id;
update classresource.t_level set name=new.name,describle=new.describle where id=old.id;
update mooc.t_level set name=new.name,describle=new.describle where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deletelevel`;
DELIMITER ;;
CREATE TRIGGER `deletelevel` BEFORE DELETE ON `t_level` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_level   where id=@oid;
	delete from classexam.t_level  where id=@oid;
	delete from classresource.t_level where id=@oid;
	delete from mooc.t_level where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `insermajor`;
DELIMITER ;;
CREATE TRIGGER `insermajor` AFTER INSERT ON `t_major` FOR EACH ROW begin
	insert into classdisk.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into classexam.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into classresource.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
	insert into mooc.t_major(id,name,parentid,orgid)values(new.id,new.name,new.parentid,new.orgid);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updatermajor`;
DELIMITER ;;
CREATE TRIGGER `updatermajor` AFTER UPDATE ON `t_major` FOR EACH ROW begin
update classdisk.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classexam.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update classresource.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
update mooc.t_major set name=new.name,parentid=new.parentid,orgid=new.orgid where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deletemajor`;
DELIMITER ;;
CREATE TRIGGER `deletemajor` BEFORE DELETE ON `t_major` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_major   where id=@oid;
	delete from classexam.t_major  where id=@oid;
	delete from classresource.t_major where id=@oid;
	delete from mooc.t_major where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inserorg`;
DELIMITER ;;
CREATE TRIGGER `inserorg` AFTER INSERT ON `t_org` FOR EACH ROW begin
	insert into classdisk.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );
	insert into classexam.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );
	insert into classresource.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid ); 
	insert into mooc.t_org(id,name, describle,address,category,logoimg,xcimg,zzimg,fmimg,siteid,userid,status,reply,email,phone,isenable,parentid )values(new.id,new.name, new.describle,new.address,new.category,new.logoimg,new.xcimg,new.zzimg,new.fmimg,new.siteid,new.userid,new.status,new.reply,new.email,new.phone,new.isenable,new.parentid );


end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateorg`;
DELIMITER ;;
CREATE TRIGGER `updateorg` AFTER UPDATE ON `t_org` FOR EACH ROW begin
update classdisk.t_org set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classexam.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update classresource.t_org  set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
update mooc.t_org set id=new.id,name=new.name, describle=new.describle,address=new.address,category=new.category,logoimg=new.logoimg,xcimg=new.xcimg,zzimg=new.zzimg,fmimg=new.fmimg,siteid=new.siteid,userid=new.userid,status=new.status,reply=new.reply,email=new.email,phone=new.phone,isenable=new.isenable,sortnumber=new.sortnumber,parentid=new.parentid where id = old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleteorg`;
DELIMITER ;;
CREATE TRIGGER `deleteorg` BEFORE DELETE ON `t_org` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_org   where id=@oid;
	delete from classexam.t_org   where id=@oid;
	delete from classresource.t_org   where id=@oid;
	delete from mooc.t_org   where id=@oid;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inserorguser`;
DELIMITER ;;
CREATE TRIGGER `inserorguser` AFTER INSERT ON `t_orguser` FOR EACH ROW begin
	insert into classdisk.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classexam.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into classresource.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);
	insert into mooc.t_orguser(id,userid,orgid,status,reply)values(new.id,new.userid,new.orgid,new.status,new.reply);

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateorguser`;
DELIMITER ;;
CREATE TRIGGER `updateorguser` AFTER UPDATE ON `t_orguser` FOR EACH ROW begin
update classdisk.t_orguser set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classexam.t_orguser set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update classresource.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
update mooc.t_orguser  set userid=new.userid,orgid=new.orgid,status=new.status,reply=new.reply where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleteorguser`;
DELIMITER ;;
CREATE TRIGGER `deleteorguser` BEFORE DELETE ON `t_orguser` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_orguser   where id=@oid;
	delete from classexam.t_orguser   where id=@oid;
	delete from classresource.t_orguser   where id=@oid;
	delete from mooc.t_orguser   where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inserrole`;
DELIMITER ;;
CREATE TRIGGER `inserrole` AFTER INSERT ON `t_role` FOR EACH ROW begin
	insert into classdisk.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classexam.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into classresource.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
	insert into mooc.t_role(id,name,rolekey)values(new.id,new.name,new.rolekey);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updaterrole`;
DELIMITER ;;
CREATE TRIGGER `updaterrole` AFTER UPDATE ON `t_role` FOR EACH ROW begin
update classdisk.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classexam.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update classresource.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
update mooc.t_role set name=new.name,rolekey=new.rolekey where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleterole`;
DELIMITER ;;
CREATE TRIGGER `deleterole` BEFORE DELETE ON `t_role` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_role   where id=@oid;
	delete from classexam.t_role  where id=@oid;
	delete from classresource.t_role  where id=@oid;
	delete from mooc.t_role  where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inseruser`;
DELIMITER ;;
CREATE TRIGGER `inseruser` AFTER INSERT ON `t_user` FOR EACH ROW begin
	insert into classdisk.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classexam.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into classresource.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
	insert into mooc.t_user(id,username,realname,password,email,status,headimg,sex,describle,code,codeimg,token,pwdtoken,job,phone,qq,isenable,createtime,platform,storagesize,field,birthday,city,province,modifiedtime,majorid,rip,joindate)values(new.id,new.username,new.realname,new.password,new.email,new.status,new.headimg,new.sex,new.describle,new.code,new.codeimg,new.token,new.pwdtoken,new.job,new.phone,new.qq,new.isenable,new.createtime,new.platform,new.storagesize,new.field,new.birthday,new.city,new.province,new.modifiedtime,new.majorid,new.rip,new.joindate);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateuser`;
DELIMITER ;;
CREATE TRIGGER `updateuser` AFTER UPDATE ON `t_user` FOR EACH ROW begin
update classdisk.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classexam.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update classresource.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
update mooc.t_user set username=new.username,realname=new.realname,points=new.points,password=new.password,email=new.email,status=new.status,headimg=new.headimg,sex=new.sex,describle=new.describle,code=new.code,codeimg=new.codeimg,token=new.token,pwdtoken=new.pwdtoken,job=new.job,phone=new.phone,qq=new.qq,isenable=new.isenable,createtime=new.createtime,platform=new.platform,storagesize=new.storagesize,field=new.field ,birthday=new.birthday,city=new.city,province=new.province,modifiedtime=new.modifiedtime,majorid=new.majorid,rip=new.rip,joindate=new.joindate where id = old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleteuser`;
DELIMITER ;;
CREATE TRIGGER `deleteuser` BEFORE DELETE ON `t_user` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_user   where id=@oid;
	delete from classexam.t_user   where id=@oid;
	delete from classresource.t_user   where id=@oid;
	delete from mooc.t_user   where id=@oid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inseruserrole`;
DELIMITER ;;
CREATE TRIGGER `inseruserrole` AFTER INSERT ON `t_userrole` FOR EACH ROW begin
	insert into classdisk.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classexam.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into classresource.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
	insert into mooc.t_userrole(id,userid,roleid,status,reply)values(new.id,new.userid,new.roleid,new.status,new.reply);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateuserrole`;
DELIMITER ;;
CREATE TRIGGER `updateuserrole` AFTER UPDATE ON `t_userrole` FOR EACH ROW begin
update classdisk.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classexam.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update classresource.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
update mooc.t_userrole set userid=new.userid,roleid=new.roleid,status=new.status,reply=new.reply where id=old.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleteuserrole`;
DELIMITER ;;
CREATE TRIGGER `deleteuserrole` BEFORE DELETE ON `t_userrole` FOR EACH ROW begin
	set   @oid=OLD.id;
	delete from  classdisk.t_userrole   where id=@oid;
	delete from classexam.t_userrole   where id=@oid;
	delete from classresource.t_userrole   where id=@oid;
	delete from mooc.t_userrole   where id=@oid;
end
;;
DELIMITER ;
