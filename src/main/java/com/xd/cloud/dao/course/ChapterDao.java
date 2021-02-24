/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.course.Chapter;

/**
 * 课程章节目录Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class ChapterDao extends BaseDao<Chapter> {
	
	public List<Chapter> getChapter(int id,int cid)
	{
		
		if (String.valueOf(id) == null)
		{
			String hql = "from Chapter where pid is null and cid=?";
			List<Chapter> list = find(hql,new Object[]{cid});
			return list;
		}
		else
		{
			String hql = "from Chapter where pid = ? and cid=?";
			List<Chapter> list = find(hql,new Object[]{id,cid});
			return list;
		}
	}
	
	public List<Chapter> getAllChapter(int cid){
		String hql="from Chapter where pid is null and cid=?"; 
		return find(hql,new Object[]{cid});
	}
	
	public Chapter getChapter(int cid,String name)
	{
		String hql = "from Chapter where cid = ? and name = ?";
		List<Chapter> list = find(hql,new Object[]{cid,name});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public List<Chapter> getChapte(int id)
	{
		String hql = "from Chapter where id = ?";
	
		return find(hql,new Object[]{id});
	}
	public List<Chapter> getCh(int cid)
	{
		String hql = "from Chapter where cid = ?";
	
		return find(hql,new Object[]{cid});
	}
	/**
	 * 修改课程章节
	 */
	public void update(int id,String name,int sortnumber)
	{
		String hql = "update Chapter set name=?,sortnumber=? where id=?";
	    executeHql(hql,new  Object[]{name,sortnumber,id});
	}
	
}
