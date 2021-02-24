/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.course;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 课程章节下的內容
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_chapterunit")
public class ChapterUnit extends BaseEntity{
	private int courseid;
	private int chapterid;
//	private int uid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private Unit unit;
	
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getChapterid() {
		return chapterid;
	}
	public void setChapterid(int chapterid) {
		this.chapterid = chapterid;
	}
//	public int getUid() {
//		return uid;
//	}
//	public void setUid(int uid) {
//		this.uid = uid;
//	}
	
}
