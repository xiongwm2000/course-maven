/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.core;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.core.OrgUser;

/**
 * 机构下的用户Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class OrgUserDao extends BaseDao<OrgUser> {
	
	public List<OrgUser> getAll()
	{
		String hql = "from OrgUser";
		return find(hql);
	}
	
	public OrgUser getByuserid(int userid)
	{
		String hql = "from OrgUser where userid = ?";
		List<OrgUser> list = find(hql,new Object[]{userid});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public Page getAllUserByorgid(int pageNo,int pageSize,int orgid,int userid){
		String hql = "from OrgUser where orgid=? and userid != ?";
		return pagedQuery(hql, pageNo, pageSize,orgid,userid);
		
    }
	
	public void update(int id,int status,String reply)
	{
		if (reply != null)
		{
			String hql = "update OrgUser set status =? , reply =?  where id=?";
			executeHql(hql, new Object[]{status,reply,id});
			
		}
		else
		{
			String hql = "from OrgUser where id=? and status =?";
			executeHql(hql, new Object[]{id,status});
		}
		
	}
}
