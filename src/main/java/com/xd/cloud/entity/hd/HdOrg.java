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
import com.xd.cloud.entity.core.Org;

/**
 * 参加活动的学校
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_hdorg")
public class HdOrg extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hdid")
	private Hd hd;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgid")
	private Org org;
	private int status;
	//审核不通过的回复
	private String reply;
	//报名的附件
	private String attach;
	//报名时间
	private String signtime;
	
	public String getSigntime() {
		return signtime;
	}
	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Hd getHd() {
		return hd;
	}
	public void setHd(Hd hd) {
		this.hd = hd;
	}
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}
}
