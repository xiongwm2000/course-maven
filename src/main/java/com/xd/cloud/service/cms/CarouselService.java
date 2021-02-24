/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.cms.CarouselDao;
import com.xd.cloud.entity.cms.Carousel;

/**
 * 轮播service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class CarouselService {

	@Autowired
	private CarouselDao carouselDao;
	
	public void save(Carousel c)
	{
		carouselDao.save(c);
	}
	
	public void update(Carousel c)
	{
		carouselDao.update(c);
	}
	
	public void update(int id,String name,String linkurl,int sortNumber,String path)
	{
		carouselDao.update(id, name, linkurl, sortNumber,path);
	}
	
	public void remove(int id)
	{
		Carousel c = carouselDao.get(id);
		if (c != null)
			carouselDao.delete(c);
	}
	
	public List<Carousel> getAll()
	{
		return carouselDao.getAll();
	}
	
	public List<Carousel> getSortList()
	{
		return carouselDao.getSortList();
	}
	
	public List<Carousel> getBySiteID(int siteId)
	{
		return carouselDao.getBySiteID(siteId);
	}
}
