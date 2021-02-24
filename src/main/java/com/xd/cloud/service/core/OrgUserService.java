/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.core.OrgDao;
import com.xd.cloud.dao.core.OrgUserDao;
import com.xd.cloud.dao.core.UserDao;
import com.xd.cloud.entity.core.OrgUser;

/**
 * 机构下的用户service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class OrgUserService {

	@Autowired
	private OrgUserDao orgUserDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrgDao orgDao;
	
	public OrgUser get(int id)
	{
		return orgUserDao.get(id);
	}
	
	public void remove(int id)
	{
		OrgUser c = orgUserDao.get(id);
		if (c != null)
			orgUserDao.delete(c);
	}
	
	public void update(OrgUser c)
	{
		orgUserDao.update(c);
	}
	
	public void update(int id,int status,String reply)
	{
		orgUserDao.update(id, status, reply);
	}
	
	public void update(int userid,int orgid)
	{
		OrgUser ou = orgUserDao.getByuserid(userid);
		if (ou != null)
		{
			ou.setOrg(orgDao.get(orgid));
			update(ou);
		}
		else
		{
			OrgUser orgUser = new OrgUser();
			
			orgUser.setUser(userDao.get(userid));
			orgUser.setOrg(orgDao.get(orgid));
			orgUserDao.save(orgUser);
		}
	}
	
	public List<OrgUser> getAll()
	{
		return orgUserDao.getAll();
	}
	
	public OrgUser getByuserid(int userid)
	{
		return orgUserDao.getByuserid(userid);
	}
	
	public Page getAllUserByorgid(int pageNo,int pageSize,int orgid,int userid)
	{
		return orgUserDao.getAllUserByorgid(pageNo, pageSize, orgid,userid);
	}
}
