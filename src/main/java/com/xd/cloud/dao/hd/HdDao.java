/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.hd;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.hd.Hd;

/**
 * 活动
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Repository
public class HdDao extends BaseDao<Hd> {
	public List<Hd> getAll() {
		String hql = "from Hd";
		return find(hql);
	}

	public List<Hd> getHdByorgid(int orgid) {
		String hql = "from Hd where orgid=?";
		return find(hql, new Object[] { orgid });
	}

	public void update(int id, String name, String starttime, String endtime, String describle, String cstime,
			String fstime, String zstime,String thumb) {
		String hql = "update Hd set name =?,starttime=?,endtime=?,describle=?,cstime=?,fstime=?,zstime=?,thumb=? where id=?";
		executeHql(hql, new Object[] { name, starttime, endtime, describle, cstime, fstime, zstime,thumb, id });
	}
}
