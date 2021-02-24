/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.cms;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 内容bean
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_content")
public class Content extends BaseEntity{

	private String title;
	private String content;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "channelid")
	private Channel channel;
	
	private int siteid;
	private String describle;
	private String createtime;
	private String modifytime;

	//模板
	private String prefix;
	private String thumbnail; 
	//浏览量
	private int clicknumber = 0;
	//文档类型：1：文档,2:视频,3:富文本
	private int type;
	
	private String path;
	private String swfpath;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSwfpath() {
		return swfpath;
	}
	public void setSwfpath(String swfpath) {
		this.swfpath = swfpath;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getClicknumber() {
		return clicknumber;
	}
	public void setClicknumber(int clicknumber) {
		this.clicknumber = clicknumber;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPrefix() {
		return prefix;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

}
