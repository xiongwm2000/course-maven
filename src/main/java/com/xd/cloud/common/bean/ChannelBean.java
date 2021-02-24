/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.bean;

import java.io.Serializable;

/**
 * 栏目通信的消息bean
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class ChannelBean implements Serializable {

	private int id;
	private String name;
	private int pid;
	private int sortnumber = 10000;
	private String parentName;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
}
