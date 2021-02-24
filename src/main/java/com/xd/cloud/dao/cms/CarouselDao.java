/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.cms;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.cms.Carousel;

/**
 * 轮播Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class CarouselDao extends BaseDao<Carousel> {
	public List<Carousel> getAll()
	{
		String hql = "from Carousel";
		return find(hql);
	}
	
	public List<Carousel> getSortList()
	{
		String hql = "from Carousel order by sortNumber asc";
		return find(hql);
	}
	
	public List<Carousel> getBySiteID(int siteId)
	{
		String hql = "from Carousel where siteid = ? order by sortNumber asc";
		return find(hql,new Object[]{siteId});
	}
	
	public void update(int id,String name,String linkurl,int sortNumber,String path)
	{
		String hql = "update Carousel set name =?,linkurl=?,sortnumber=?,path=? where id=?";
		executeHql(hql, new Object[]{name,linkurl,sortNumber,path,id});
	}
}
