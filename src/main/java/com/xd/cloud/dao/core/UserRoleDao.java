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
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.core.UserRole;

/**
 * 用户角色Dao
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Repository
public class UserRoleDao extends BaseDao<UserRole> {

	public List<Object> findUserRole(String username) {
		// String hql = "from UserRole where status=? and userid = ?";
		String sql = "select r.rolekey from t_user as u,t_userrole as ur,t_role as r where u.username = ? and u.id = ur.userid and ur.roleid = r.id";
		return findSql(sql, new Object[] { username });
	}

	public List<UserRole> getAll() {
		String hql = "from UserRole";
		return find(hql);
	}

	public void removeByuserid(int userid) {
		String hql = "delete UserRole where userid=?";
		executeHql(hql, new Object[] { userid });
	}

	public List<UserRole> getByuserid(int userid) {
		String hql = "from UserRole where status=? and userid = ?";
		return find(hql, new Object[] { Constants.SP_2, userid });
	}

	public Page getAllUserByorgid(int pageNo, int pageSize, int orgid) {
		String hql = "from OrgUser where orgid=?";
		return pagedQuery(hql, pageNo, pageSize, orgid);

	}
}
