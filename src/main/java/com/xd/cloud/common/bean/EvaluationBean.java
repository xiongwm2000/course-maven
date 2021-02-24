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
public class EvaluationBean implements Serializable {

	private int courseid;
	private int score;
	private String evatime;
	private String ecommnent;
	private int userid;
	private String username;
	private String realname;
	private String headimg;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getEvatime() {
		return evatime;
	}
	public void setEvatime(String evatime) {
		this.evatime = evatime;
	}
	public String getEcommnent() {
		return ecommnent;
	}
	public void setEcommnent(String ecommnent) {
		this.ecommnent = ecommnent;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
}
