/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.hd;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.core.UserDao;
import com.xd.cloud.dao.hd.HdDao;
import com.xd.cloud.dao.hd.HdUserDao;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.hd.HdUser;

/**
 * 参加活动的机构service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class HdUserService {

	@Autowired
	private HdUserDao hdUserDao;
	@Autowired
	private HdDao hdDao;
	@Autowired
	private UserDao userDao;
	
	public void save(HdUser c)
	{
		hdUserDao.save(c);
	}
	
	public HdUser get(int id)
	{
		return hdUserDao.get(id);
	}
	
	public void update(HdUser c)
	{
		hdUserDao.update(c);
	}
	
	public void update(int id,int status,String reply)
	{
		hdUserDao.update(id, status, reply);
	}
	
	public void remove(int id)
	{
		HdUser c = hdUserDao.get(id);
		if (c != null)
			hdUserDao.delete(c);
	}
	
	public List<HdUser> getAll()
	{
		return hdUserDao.getAll();
	}
	
	public List<HdUser> getAllUser(int identity,String size)
	{
		return hdUserDao.getAllUser(identity,size);
	}
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid,int identity)
	{
		return hdUserDao.getAllByhdid(pageNo, pageSize, hdid, identity);
	}
	
	public List<HdUser> getHdByuserid(int userid)
	{
		return hdUserDao.getHdByuserid(userid);
	}
	
	public List<HdUser> getHduserByorgid(int hdid,int orgid)
	{
		return hdUserDao.getHduserByorgid(hdid, orgid);
	}
	
	public HdUser getHdXS(int userid,int hdid)
	{
		return hdUserDao.getHdXS(userid, hdid);
	}
	
	public String userSign(int userid,String realname,String code,String codeimg,int identity,int hdid)
	{
		HdUser ho = hdUserDao.getHU(userid, hdid);
		if (ho != null)
		{
			return Constants.ERROR_3;
		}
		User o = userDao.get(userid);
		o.setRealname(realname);
		o.setCode(code);
		o.setCodeimg(codeimg);
		userDao.update(o);
		
		ho = new HdUser();
		ho.setHd(hdDao.get(hdid));
		ho.setUser(o);
		ho.setIdentity(identity);
		ho.setStatus(Constants.SP_0);
		
		ho.setSigntime(UtilTools.timeTostrHMS(new Date()));
		hdUserDao.save(ho);
		
		return Constants.SUCCESS_1;
	}
	
	public List getChartData()
	{
		return hdUserDao.getChartData();
	}
}
