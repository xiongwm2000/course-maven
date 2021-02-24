/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.hd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.hd.HdDao;
import com.xd.cloud.entity.hd.Hd;

/**
 * 活动service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class HdService {

	@Autowired
	private HdDao hdDao;
	
	public void save(Hd c)
	{
		hdDao.save(c);
	}
	
	public Hd get(int id)
	{
		return hdDao.get(id);
	}
	
	public void update(Hd c)
	{
		hdDao.update(c);
	}
	
	public void update(int id,String name,String starttime,String endtime,String describle,String cstime,String fstime,String zstime,String thumb)
	{
		hdDao.update(id,name,starttime,endtime,describle,cstime,fstime,zstime,thumb);
	}
	
	public void remove(int id)
	{
		Hd c = hdDao.get(id);
		if (c != null)
			hdDao.delete(c);
	}
	
	
	public List<Hd> getAll()
	{
		return hdDao.getAll();
	}
	
	public List<Hd> getHdByorgid(int orgid)
	{
		return hdDao.getHdByorgid(orgid);
	}
}
