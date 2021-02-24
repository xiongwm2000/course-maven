/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.core;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 机构
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_org")
public class Org extends BaseEntity{

	private String name;
	private String describle;
	private String logoimg;
	//宣传图片
	private String xcimg;
	//资质图片
	private String zzimg;
	//封面图片
	private String fmimg;
	//站点ID
	private int siteid;
	//激活
	private int status;	
	private String address;
	//创建机构用户
	private int userid;//有问题，删除用户的时候？？？
	//审核不通过的回复
	private String reply;
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="t_orguser", joinColumns={ @JoinColumn(name="orgid")}, 
	    inverseJoinColumns={ @JoinColumn(name = "userid") })
	private Set<User> users;
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getFmimg() {
		return fmimg;
	}
	public void setFmimg(String fmimg) {
		this.fmimg = fmimg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getLogoimg() {
		return logoimg;
	}
	public void setLogoimg(String logoimg) {
		this.logoimg = logoimg;
	}
	public String getXcimg() {
		return xcimg;
	}
	public void setXcimg(String xcimg) {
		this.xcimg = xcimg;
	}
	public String getZzimg() {
		return zzimg;
	}
	public void setZzimg(String zzimg) {
		this.zzimg = zzimg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
