/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.hd;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;
import com.xd.cloud.entity.course.Course;

/**
 * 活动下的作品
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_hdcourse")
public class HdCourse extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hdid")
	private Hd hd;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseid")
	private Course course;
	
	
	public Hd getHd() {
		return hd;
	}
	public void setHd(Hd hd) {
		this.hd = hd;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
