/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.cache;

import java.io.Serializable;

/**
 * 缓存的用户角色
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 */
public class CacheUserRole implements Serializable {
	private String rolekey;

	public String getRolekey() {
		return rolekey;
	}

	public void setRolekey(String rolekey) {
		this.rolekey = rolekey;
	}

}
