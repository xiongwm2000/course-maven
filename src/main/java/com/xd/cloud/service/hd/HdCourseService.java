/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.hd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.hd.HdCourseDao;

/**
 * 活动下的课程service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class HdCourseService {

	@Autowired
	private HdCourseDao hdCourseDao;
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid)
	{
		return hdCourseDao.getAllByhdid(pageNo, pageSize, hdid);
	}
}
