/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 权限
 * 
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_permission")
public class Permission extends BaseEntity{

	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
