/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.course.CategoryDao;
import com.xd.cloud.entity.course.Category;

/**
 * 课程分类service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getCategory(String id,String cid)
	{
		return categoryDao.getCategory(id,cid);
	}
}
