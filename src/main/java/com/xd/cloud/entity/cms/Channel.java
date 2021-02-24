/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.cms;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 栏目bean
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Entity
@Table(name = "t_channel")
public class Channel extends BaseEntity {

	private int siteid;
	private String name;
	private int sortnumber = 10000;
	private String createtime;
	private String modifytime;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	private Channel parent;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	/*children集合排序*/
	@OrderBy(value = "sortnumber ASC,id ASC")
	private Set<Channel> children = new LinkedHashSet<Channel>();
	//模板
	private String prefix;
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Set<Channel> getChildren() {
		return children;
	}

	public void setChildren(Set<Channel> children) {
		this.children = children;
	}

	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	public int getSiteid() {
		return siteid;
	}

	public void setSiteid(int siteid) {
		this.siteid = siteid;
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

	public int getSortnumber() {
		return sortnumber;
	}

	public void setSortnumber(int sortnumber) {
		this.sortnumber = sortnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
