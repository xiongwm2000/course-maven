/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.course;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 课程章节目录
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_chapter")
public class Chapter extends BaseEntity{

	 private String name;
	 private int sortnumber = 10000;
	 private int cid;
	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinColumn(name = "pid")
	 private Chapter chapter;
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter", fetch = FetchType.EAGER)
	 private Set<Chapter> children = new LinkedHashSet<Chapter>();
		
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Set<Chapter> getChildren() {
		return children;
	}
	public void setChildren(Set<Chapter> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSortnumber() {
		return sortnumber;
	}
	public void setSortnumber(int sortnumber) {
		this.sortnumber = sortnumber;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
