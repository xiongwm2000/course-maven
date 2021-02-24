/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.course;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 课程分类关联
 * 
 * @since V1.0
 * 
 */
@Entity
@Table(name = "t_coursecategory")
public class CourseCategory extends BaseEntity {
	
	private int courseid;
	private int categoryid;
	
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	
}
