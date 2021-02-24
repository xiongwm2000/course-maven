/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.bean;

import java.io.Serializable;

/**
 * 院校通信的消息bean
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class OrgBean implements Serializable {

	private int id;
	private String name;
	private String logoimg;
	private String fmimg;
	private String describle;
	public String getFmimg() {
		return fmimg;
	}

	public void setFmimg(String fmimg) {
		this.fmimg = fmimg;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogoimg() {
		return logoimg;
	}

	public void setLogoimg(String logoimg) {
		this.logoimg = logoimg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
