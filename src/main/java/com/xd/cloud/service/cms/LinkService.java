/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.cms.LinkDao;
import com.xd.cloud.entity.cms.Link;

/**
 * 链接service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class LinkService {

	@Autowired
	private LinkDao linkDao;
	
	public void save(Link c)
	{
		linkDao.save(c);
	}
	
	public void update(Link c)
	{
		linkDao.update(c);
	}
	
	public void update(int id,String name,String linkurl,int sortNumber)
	{
		linkDao.update(id, name, linkurl, sortNumber);
	}
	
	public void remove(int id)
	{
		Link c = linkDao.get(id);
		if (c != null)
			linkDao.delete(c);
	}
	
	public List<Link> getAll()
	{
		return linkDao.getAll();
	}
	
	public List<Link> getSortList()
	{
		return linkDao.getSortList();
	}
	
	public List<Link> getLinkBySiteID(int siteId)
	{
		return linkDao.getLinkBySiteID(siteId);
	}
}
