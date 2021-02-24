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
import com.xd.cloud.entity.hd.HdOrg;

/**
 * 参加活动机构
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class HdOrgDao extends BaseDao<HdOrg> {
	public List<HdOrg> getAll()
	{
		String hql = "from HdOrg";
		return find(hql);
	}
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid){
		String hql = "from HdOrg where hdid=?";
		return pagedQuery(hql, pageNo, pageSize,hdid);
    }
	
	public Page getOrgsByhdid(int pageNo,int pageSize,int hdid){
		String hql = "from HdOrg where status = ? and hdid=?";
		return pagedQuery(hql, pageNo, pageSize,Constants.SP_2,hdid);
    }
	
	public void update(int id,int status,String reply)
	{
		if (reply != null)
		{
			String hql = "update HdOrg set status =? , reply =?  where id=?";
			executeHql(hql, new Object[]{status,reply,id});
		}
		else
		{
			String hql = "update HdOrg set status =? where id=?";
			executeHql(hql, new Object[]{status,id});
		}
	}
	
	public List<HdOrg> getByorgid(int orgid)
	{
		String hql = "from HdOrg where orgid=? and status =?";
		return find(hql,new Object[]{orgid,Constants.SP_2});
	}
	
	public HdOrg getHO(int orgid,int hdid)
	{
		String hql = "from HdOrg where orgid=? and hdid =?";
		List<HdOrg> list = find(hql,new Object[]{orgid,hdid});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public List getChartData()
	{
		String sql = "select count(*),substring(signtime,1,7) from t_hdorg group by date_format(signtime,'%Y-%m');";
		return getCurrentSession().createSQLQuery(sql).list();
	}
}
