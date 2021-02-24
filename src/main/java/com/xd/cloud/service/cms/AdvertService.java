/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.cms.AdvertDao;
import com.xd.cloud.entity.cms.Advert;

/**
 * 广告位service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class AdvertService {

	@Autowired
	private AdvertDao advertDao;
	
	public void save(Advert c)
	{
		advertDao.save(c);
	}
	
	public void update(Advert c)
	{
		advertDao.update(c);
	}
	
	public void update(int id,String name,String linkurl,int sortNumber)
	{
		advertDao.update(id, name, linkurl, sortNumber);
	}
	
	public void remove(int id)
	{
		Advert c = advertDao.get(id);
		if (c != null)
			advertDao.delete(c);
	}
	
	public List<Advert> getAll()
	{
		return advertDao.getAll();
	}
	
	public List<Advert> getSortList()
	{
		return advertDao.getSortList();
	}
	
	public List<Advert> getAdvertBySiteID(int siteId)
	{
		return advertDao.getAdvertBySiteID(siteId);
	}
}
