/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.core;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 用户
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity{

	private String username;
	private String password;
	private String email;
	private String realname;
	//激活
	private int status;
	private String headimg;
	private int sex;
	private String describle;
	private String code;
	private String codeimg;
	//激活匹配使用
	private String token;
	//忘记密码匹配使用
	private String pwdtoken;
	private String job;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="t_orguser", joinColumns={ @JoinColumn(name="userid")}, 
    inverseJoinColumns={ @JoinColumn(name = "orgid") })
	private Org org;
	
	public String getPwdtoken() {
		return pwdtoken;
	}
	public void setPwdtoken(String pwdtoken) {
		this.pwdtoken = pwdtoken;
	}
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeimg() {
		return codeimg;
	}
	public void setCodeimg(String codeimg) {
		this.codeimg = codeimg;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
