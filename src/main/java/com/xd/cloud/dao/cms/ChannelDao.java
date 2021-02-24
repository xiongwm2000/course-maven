/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.cms;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.cms.Channel;

/**
 * 模块
 * 
 * @since  V1.0
 * 
 */
@Repository
public class ChannelDao extends BaseDao<Channel> {
	
	public List<Channel> getChannelBySiteID(int siteId)
	{
		/*查询一级菜单*/
		String hql = "from Channel where parentid = null and siteid = ? order by id asc";
		return find(hql,new Object[]{siteId});
	}
	public List<Channel> getChannelBysid(int siteId)
	{
		String hql = "from Channel where siteid = ?";
		return find(hql,new Object[]{siteId});
	}
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	public Channel getChannelByid(int id)
	{
		String hql = "from Channel where id = ?";
		return get(hql,new Object[]{id});
	}
	
	public List<Channel> getAll()
	{
		String hql = "from Channel";
		return find(hql);
	}
	
	public List<Channel> getSortList()
	{
		String hql = "from Channel order by sortNumber asc";
		return find(hql);
	}
	
	public void update(int id,String name,int sortNumber,String modifytime,int parentid)
	{
		String hql = "update Channel set name =?,sortnumber=?,modifytime=?,parentid=? where id=?";
		executeHql(hql, new Object[]{name,sortNumber,modifytime,parentid,id});
	}
	
	public void update(int id,String name,int sortNumber,String modifytime)
	{
		String hql = "update Channel set name =?,sortnumber=?,modifytime=? where id=?";
		executeHql(hql, new Object[]{name,sortNumber,modifytime,id});
	}
}
