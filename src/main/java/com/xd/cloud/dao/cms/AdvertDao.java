/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.cms;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.cms.Advert;

/**
 * 广告位
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class AdvertDao extends BaseDao<Advert> {
	public List<Advert> getAll()
	{
		String hql = "from Advert";
		return find(hql);
	}
	
	public List<Advert> getSortList()
	{
		String hql = "from Advert order by sortNumber asc";
		return find(hql);
	}
	
	public List<Advert> getAdvertBySiteID(int siteId)
	{
		String hql = "from Advert where siteid = ? order by sortNumber asc";
		return find(hql,new Object[]{siteId});
	}
	
	public void update(int id,String name,String linkurl,int sortNumber)
	{
		String hql = "update Advert set name =?,linkurl=?,sortnumber=? where id=?";
		executeHql(hql, new Object[]{name,linkurl,sortNumber,id});
	}
}
