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
import com.xd.cloud.entity.core.User;

/**
 * 用户Dao
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Repository
public class UserDao extends BaseDao<User> {
	
	public Object[] findUser(String username) {
		String sql = "select id,username,realname,password from t_user where username = ?";
		List l = findSql(sql, new Object[]{username});
		if (l != null && l.size() > 0) {
			return (Object[])l.get(0);
		} else {
			return null;
		}
	}
	
	public User getUserByname(String username) {
		String hql = "from User where username = ?";
		return get(hql, new Object[]{username});
	}
	
	public User getUserByToken(String username,String token) {
		String hql = "from User where username = ? and token = ?";
		return get(hql, new Object[]{username,token});
	}
	
	public User getUserByPwdtoken(String email,String pwdtoken) {
		String hql = "from User where email = ? and pwdtoken = ?";
		return get(hql, new Object[]{email,pwdtoken});
	}
	
	public void update(String job,String realname,String describle,String headimg,int sex,String code,String codeimg,int userid)
	{
		String hql = "update User set job =?,realname =?,describle=?,headimg=?,sex=?,code=?,codeimg=? where id=?";
		executeHql(hql, new Object[]{job,realname, describle, headimg, sex, code, codeimg, userid});
	}
	
	public User getUserBymail(String email) {
		String hql = "from User where email = ?";
		return get(hql, new Object[]{email});
	}
	
	public User getUserBycode(String code) {
		String hql = "from User where code = ?";
		return get(hql, new Object[]{code});
	}
	
	public List<User> getAll() {
		String hql = "from User";
		return find(hql);
	}
	
	public Page getAllUser(int pageNo, int pageSize) {
		String hql = "from User where id != 1 order by id desc";
		return pagedQuery(hql, pageNo, pageSize);
	}
}
