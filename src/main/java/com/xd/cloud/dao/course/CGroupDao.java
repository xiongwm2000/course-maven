/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.course.CGroup;

/**
 * 课程分组Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class CGroupDao extends BaseDao<CGroup> {
	
	public List<CGroup> getAllCgroup()
	{
		String hql = "from CGroup";
		List<CGroup> list = find(hql);
		return list;
	}
}
