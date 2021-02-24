/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.cms;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 友情链接bean
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_link")
public class Link extends BaseEntity{
	private String name;
	private String linkurl;
	private int sortnumber = 10000;
	private int siteid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public int getSortnumber() {
		return sortnumber;
	}
	public void setSortnumber(int sortnumber) {
		this.sortnumber = sortnumber;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	
}
