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
import com.xd.cloud.dao.core.UserRoleDao;
import com.xd.cloud.entity.core.UserRole;

/**
 * 用户角色service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	public UserRole get(int id)
	{
		return userRoleDao.get(id);
	}
	
	public void remove(int id)
	{
		UserRole c = userRoleDao.get(id);
		if (c != null)
			userRoleDao.delete(c);
	}
	
	public void update(UserRole c)
	{
		userRoleDao.update(c);
	}
	
//	public void update(List<Map<String,Object>> list)
//	{
//		for (Map<String, Object> map : list) {
//			int userid = (int) map.get("userid");
//			int[] roleids = (int[]) map.get("roleids");
//			userRoleDao.removeByuserid(userid);
//			for (int roleid:roleids)
//			{
//				UserRole ur = new UserRole();
//				ur.setUserid(userid);
//				ur.setRoleid(roleid);
//				save(ur);
//			}
//		}
//	}
	
	public boolean save(UserRole c)
	{
//		OrgUser r = orgDao.getOrgByname(c.getName());
//		if (r == null)
//		{
//			orgUserDao.save(c);
//			return true;
//		}
		return false;
	}
	
	public List<UserRole> getAll()
	{
		return userRoleDao.getAll();
	}
	
	public Page getAllUserByorgid(int pageNo,int pageSize,int orgid)
	{
		return userRoleDao.getAllUserByorgid(pageNo, pageSize, orgid);
	}
}
