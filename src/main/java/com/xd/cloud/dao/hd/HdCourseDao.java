/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.hd;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.hd.HdCourse;

/**
 * 活动下的课程
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Repository
public class HdCourseDao extends BaseDao<HdCourse> {
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid){
		String hql = "from HdCourse where hdid=?";
		return pagedQuery(hql, pageNo, pageSize,hdid);
    }
	
	public List<HdCourse> getHdByuserid(int hdid,int userid) {
		String hql = "from HdCourse where hd.id = ? and course.userid=?";
		return find(hql, new Object[] { hdid,userid });
	}
}
