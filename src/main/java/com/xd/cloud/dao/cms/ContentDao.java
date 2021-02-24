/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.cms;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Content;

/**
 * 内容Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class ContentDao extends BaseDao<Content> {
	
	public Content preCon(int id,int channelid)
	{
		String hql = "from Content where id<? and channelid=? order by id desc";
		List<Content> list = find(hql,new Object[]{id,channelid});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public Content nextCon(int id,int channelid)
	{
		String hql = "from Content where id>? and channelid=? order by id asc";
		List<Content> list = find(hql,new Object[]{id,channelid});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public List<Content> getContentBySiteID(int siteId)
	{
		String hql = "from Content where siteid = ?";
		return find(hql,new Object[]{siteId});
	}
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	public Content getContentByid(int chanelid)
	{
		String hql = "from Content where channelid = ?";
		return get(hql,new Object[]{chanelid});
	}
	
	public List<Content> getListByChannelId(int channelid)
	{
		String hql = "from Content where channelid = ?";
		return find(hql,new Object[]{channelid});
	}
	
	public Page getListByChannelId(int pageNo,int pageSize,int channelid){
		String hql = "from Content where channelid=?";
		return pagedQuery(hql, pageNo, pageSize,channelid);
    }
	
	public Page getListBySiteID(int pageNo,int pageSize,int siteid){
		String hql = "from Content where siteid=?";
		return pagedQuery(hql, pageNo, pageSize,siteid);
    }
	
	public List<Content> getAll()
	{
		String hql = "from Content";
		return find(hql);
	}
	
	public Page getAll(int pageNo,int pageSize)
	{
		String hql = "from Content";
		return pagedQuery(hql, pageNo, pageSize);
	}
	
	
	public void update(int id,String title,String describle,String content, String modifytime,String thumbnail,int channelid)
	{
		String hql = "update Content set title =?,describle=?,content=?,modifytime=?,thumbnail=?,channelid=? where id=?";
		executeHql(hql, new Object[]{title,describle,content,modifytime,thumbnail,channelid,id});
	}
	
	public void updatewd(int id,String title,String describle,String modifytime,String thumbnail,int channelid,String swfpath,int type,String content)
	{
		String hql = "update Content set title =?,describle=?,modifytime=?,path=?,channelid=?,swfpath=?,type=?,content=? where id=?";
		executeHql(hql, new Object[]{title,describle,modifytime,thumbnail,channelid,swfpath,type,content,id});
	}
}
