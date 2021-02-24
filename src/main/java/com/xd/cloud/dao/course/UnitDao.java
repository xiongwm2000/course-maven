/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.entity.course.Unit;

/**
 * 课程內容Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class UnitDao extends BaseDao<Unit> {
	/**
	 * 查询所有课程内容
	 */
	public List<Unit> getAllUnit(int cid){
		//String hql="select name,type,path from Unit u  join ChapterUnit cu on u.id=cu.uid where courseid=?";
		String hql="from Unit"; 
	
		return find(hql);
	}
}
