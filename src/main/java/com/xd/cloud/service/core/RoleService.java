/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.core.RoleDao;
import com.xd.cloud.entity.core.Role;

/**
 * 用户角色service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public Role get(int id)
	{
		return roleDao.get(id);
	}
	
	public void remove(int id)
	{
		Role c = roleDao.get(id);
		if (c != null)
		roleDao.delete(c);
	}
	
	public void update(Role c)
	{
		roleDao.update(c);
	}
	
	public void update(int id,String name)
	{
		roleDao.update(id, name);
	}
	
	public boolean save(Role c)
	{
		Role r = roleDao.getRoleByname(c.getName());
		if (r == null)
		{
			roleDao.save(c);
			return true;
		}
		return false;
	}
	
	public List<Role> getAll()
	{
		return roleDao.getAll();
	}
}
