/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.course.Category;

/**
 * 课程分类Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class CategoryDao extends BaseDao<Category> {
	
	public List<Category> getCategory(String id,String cid)
	{
		
		if (id == null || "".equals(id))
		{
			String hql = "from Category where pid is null and cid=?";
			List<Category> list = find(hql,new Object[]{Integer.parseInt(cid)});
			return list;
		}
		else
		{
			String hql = "from Category where pid = ? and cid=?";
			List<Category> list = find(hql,new Object[]{Integer.parseInt(id),Integer.parseInt(cid)});
			return list;
		}
	}
}
