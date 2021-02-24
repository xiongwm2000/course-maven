/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.hd;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 活动
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_hd")
public class Hd extends BaseEntity{

	private String name;
	private String starttime;
	private String endtime;
	private String describle;
	//站点ID
	private int siteid;
	//初审,复审，终审时间
	private String cstime;
	private String fstime;
	private String zstime;
	//模板
	private String prefix;
	
	//创建活动的机构以及用户
	private int userid;
	private int orgid;
	//活动缩略图
	private String thumb;
	
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getOrgid() {
		return orgid;
	}
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getCstime() {
		return cstime;
	}
	public void setCstime(String cstime) {
		this.cstime = cstime;
	}
	public String getFstime() {
		return fstime;
	}
	public void setFstime(String fstime) {
		this.fstime = fstime;
	}
	public String getZstime() {
		return zstime;
	}
	public void setZstime(String zstime) {
		this.zstime = zstime;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
