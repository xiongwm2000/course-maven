/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.danga.MemCached.MemCachedClient;

/**
 * 缓存Memcached中的管理类
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 */
public class CacheManager {

	@Autowired
	private MemCachedClient memCachedClient;

	// 时间 缓存时间
	public static final int TIMEOUT = 360000;// 秒

	private int expiry = TIMEOUT;

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	// 用户的缓存规则
	private final String USER_PREFIX = "user_";
	// 用户角色的缓存规则
	private final String USERROLE_PREFIX = "userrole_";

	/**
	 * 根据登录名获取缓存的用户角色
	 * 
	 * @param username
	 *            ：用户登录名
	 * @return
	 */
	public List<CacheUserRole> getCacheUserRole(String username) {
		List<CacheUserRole> obj = (List<CacheUserRole>) memCachedClient.get(USERROLE_PREFIX + username);
		return obj;
	}

	public void setCacheUserRole(String username, List<CacheUserRole> curlist) {
		// 先清空缓存
		memCachedClient.delete(USERROLE_PREFIX + username);
		// 先放到Memcached中一份
		memCachedClient.set(USERROLE_PREFIX + username, curlist, expiry);
	}

	/**
	 * 根据登录名获取缓存的用户
	 * 
	 * @param username
	 *            ：用户登录名
	 * @return
	 */
	public CacheUser getCacheUser(String username) {
		CacheUser obj = (CacheUser) memCachedClient.get(USER_PREFIX + username);
		return obj;
	}

	public void setCacheUser(String username, CacheUser cu) {
		// 先清空缓存
		memCachedClient.delete(USER_PREFIX + username);
		// 先放到Memcached中一份
		memCachedClient.set(USER_PREFIX + username, cu, expiry);
	}

}
