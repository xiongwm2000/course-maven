/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.core;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.common.Constants;
import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.core.Org;

/**
 * 机构Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class OrgDao extends BaseDao<Org> {
	
	public List<Org> getAll()
	{
		String hql = "from Org";
		return find(hql);
	}
	
	public void update(int id,String name,String describle,String logoimg, String xcimg,String zzimg,String fmimg,String address,String category)
	{
		String hql = "update Org set name =?,describle=?,logoimg=?,xcimg=?,zzimg=?,fmimg=?,address=?,category=? where id=?";
		executeHql(hql, new Object[]{name,describle,logoimg,xcimg,zzimg,fmimg,address,category,id});
	}
	
	public List<Org> getSPAll()
	{
		String hql = "from Org where status=?";
		return find(hql,new Object[]{Constants.SP_2});
	}
	
	public Org getOrgByname(String name)
	{
		String hql = "from Org where name = ?";
		List<Org> list = find(hql,new Object[]{name});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public List<Org> getOrgByNameAddress(String name,String address,int id)
	{
		String sql ="select t2.* from (select * from t_hdorg where hdid=? and status=?)t1,(select * from t_org where name like ? and address like ? and status=?)t2 where t1.orgid=t2.id";
		name="%"+name+"%";
		address="%"+address+"%";
		Class<Org> pojoClass=Org.class;
		List<Org> list = findSql(sql,new Object[]{id,Constants.SP_2,name,address,Constants.SP_2},pojoClass);
		if (list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	public List<Org> getOrgByAddress(String address,int id)
	{
		String sql ="select t2.* from (select * from t_hdorg where hdid=? and status=?)t1,(select * from t_org where address like ? and status=?)t2 where t1.orgid=t2.id";
		address="%"+address+"%";
		Class<Org> pojoClass=Org.class;
		List<Org> list = findSql(sql,new Object[]{id,Constants.SP_2,address,Constants.SP_2},pojoClass);
		if (list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	public List<Org> getOrgByName(String name,int id)
	{
		String sql ="select t2.* from (select * from t_hdorg where hdid=? and status=?)t1,(select * from t_org where name like ? and status=?)t2 where t1.orgid=t2.id";
		name="%"+name+"%";
		Class<Org> pojoClass=Org.class;
		List<Org> list = findSql(sql,new Object[]{id,Constants.SP_2,name,Constants.SP_2},pojoClass);
		if (list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	public List<Org> getOrgByHdid(int id)
	{
		String sql ="select t2.* from (select * from t_hdorg where hdid=? and status=?)t1,(select * from t_org where status=?)t2 where t1.orgid=t2.id";
		Class<Org> pojoClass=Org.class;
		List<Org> list = findSql(sql,new Object[]{id,Constants.SP_2,Constants.SP_2},pojoClass);
		if (list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
}
