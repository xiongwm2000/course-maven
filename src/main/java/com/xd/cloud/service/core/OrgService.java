/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.core.OrgDao;
import com.xd.cloud.entity.core.Org;

/**
 * 用户角色service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class OrgService {

	@Autowired
	private OrgDao orgDao;
	
	public Org get(int id)
	{
		return orgDao.get(id);
	}
	
	public Org getOrgByname(String name)
	{
		return orgDao.getOrgByname(name);
	}
	
	public void remove(int id)
	{
		Org c = orgDao.get(id);
		if (c != null)
			orgDao.delete(c);
	}
	
	public void update(Org c)
	{
		orgDao.update(c);
	}
	
	public void update(int id,String name,String describle,String logoimg, String xcimg,String zzimg,String fmimg,String address,String category)
	{
		orgDao.update(id, name, describle, logoimg,  xcimg, zzimg,fmimg, address, category);
		
	}
	
	public boolean save(Org c)
	{
		Org r = orgDao.getOrgByname(c.getName());
		if (r == null)
		{
			orgDao.save(c);
			return true;
		}
		return false;
	}
	
	public List<Org> getSPAll()
	{
		return orgDao.getSPAll();
	}
	
	public List<Org> getAll()
	{
		return orgDao.getAll();
	}
	
	public List<Org> getOrgByNameAddress(String name,String address,int id){
		return orgDao.getOrgByNameAddress(name, address,id);
	}
	
	public List<Org> getOrgByAddress(String address,int id){
		return orgDao.getOrgByAddress(address,id);
	}
	
	public List<Org> getOrgByName(String name,int id){
		return orgDao.getOrgByName(name, id);
	}
	
	public List<Org> getOrgByHdid(int id){
		return orgDao.getOrgByHdid(id);
	}
	
}