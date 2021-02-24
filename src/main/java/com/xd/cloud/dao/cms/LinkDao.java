/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.cms;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.cms.Link;

/**
 * 链接
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class LinkDao extends BaseDao<Link> {
	public List<Link> getAll()
	{
		String hql = "from Link";
		return find(hql);
	}
	
	public void update(int id,String name,String linkurl,int sortNumber)
	{
		String hql = "update Link set name =?,linkurl=?,sortnumber=? where id=?";
		executeHql(hql, new Object[]{name,linkurl,sortNumber,id});
	}
	
	public List<Link> getSortList()
	{
		String hql = "from Link order by sortNumber asc";
		return find(hql);
	}
	
	public List<Link> getLinkBySiteID(int siteId)
	{
		String hql = "from Link where siteid = ? order by sortNumber asc";
		return find(hql,new Object[]{siteId});
	}
}
