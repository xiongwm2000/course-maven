/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.hd;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.common.Constants;
import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.hd.HdUser;

/**
 * 参加活动的用户
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class HdUserDao extends BaseDao<HdUser> {
	public List<HdUser> getAll()
	{
		String hql = "from HdUser";
		return find(hql);
	}
	
	public List<HdUser> getAllUser(int identity,String size)
	{
		if (size != null)
		{
			String hql = "from HdUser where status=? and identity=?";
			return find(hql,new Object[]{Constants.SP_2,identity},1,Integer.parseInt(size));
		}
		else
		{
			String hql = "from HdUser where status=? and identity=?";
			return find(hql,new Object[]{Constants.SP_2,identity});
		}
	}
	
	public void update(int id,int status,String reply)
	{
		if (reply != null)
		{
			String hql = "update HdUser set status =? , reply =?  where id=?";
			executeHql(hql, new Object[]{status,reply,id});
			
		}
		else
		{
			String hql = "from HdUser where id=? and status =?";
			executeHql(hql, new Object[]{id,status});
		}
		
	}
	
	public List<HdUser> getHduserByorgid(int hdid,int orgid)
	{
		String hql = "from HdUser hu where hdid=? and status =? and hu.user.org.id=?";
		return find(hql, new Object[]{hdid,Constants.SP_2,orgid});
	}
	
	public List<HdUser> getHdByuserid(int userid)
	{
		String hql = "from HdUser where userid=?";
		return find(hql, new Object[]{userid});
	}
	
	public HdUser getHU(int userid,int hdid)
	{
		String hql = "from HdUser where userid=? and hdid =?";
		List<HdUser> list = find(hql,new Object[]{userid,hdid});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public HdUser getHdXS(int userid,int hdid)
	{
		String hql = "from HdUser where userid=? and hdid =? and identity = 0 and status = ?";
		List<HdUser> list = find(hql,new Object[]{userid,hdid,Constants.SP_2});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid,int identity){
		String hql = "from HdUser where hdid=? and identity=?";
		return pagedQuery(hql, pageNo, pageSize,hdid,identity);
    }
	
	public List getChartData()
	{
		String sql = "select count(*),substring(hduser.signtime,1,7) from (select * from t_hduser union select * from t_hdorg) as hduser group by date_format(hduser.signtime,'%Y-%m');";
		return getCurrentSession().createSQLQuery(sql).list();
	}
}
