/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.cms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.cms.ChannelDao;
import com.xd.cloud.dao.course.CourseDao;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.course.Course;

/**
 * 栏目service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class ChannelService {

	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private CourseDao courseDao;
	
	public Channel get(int id)
	{
		return channelDao.get(id);
	}
	public void save(Channel c)
	{
		channelDao.save(c);
	}
	
	public void update(Channel c)
	{
		channelDao.update(c);
	}
	
	public void update(int id,String name,int sortNumber,String modifytime,int parentid)
	{
		channelDao.update( id, name, sortNumber,modifytime,parentid);
	}
	
	public void update(int id,String name,int sortNumber,String modifytime)
	{
		channelDao.update( id, name, sortNumber,modifytime);
	}
	
	public void remove(int id)
	{
		Channel c = channelDao.get(id);
		if (c != null)
		{
			Channel pc = c.getParent();
			if (pc != null)
			{
				Set<Channel> set = pc.getChildren();
				set.remove(c);
				c.setParent(null);
			}
			channelDao.delete(c);
		}
	}
	
	public List<Channel> getChannelBySiteID(int siteId)
	{
		return channelDao.getChannelBySiteID(siteId);
	}
	
//	getChannelByid(int id)
	public Channel getChannelByid(int id)
	{
		return channelDao.getChannelByid(id);
	}
	public List<Channel> getChannelBysid(int cid){
		Course course=courseDao.get(cid);
		return channelDao.getChannelBysid(course.getSiteid());
	}
	public List<Channel> getAll()
	{
		return channelDao.getAll();
	}
	
	public List<Channel> getSortList()
	{
		return channelDao.getSortList();
	}
}
