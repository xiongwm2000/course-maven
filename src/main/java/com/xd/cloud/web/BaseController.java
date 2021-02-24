/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import javax.servlet.http.HttpServletRequest;

import com.xd.cloud.common.Constants;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.User;


/**
 * 活动后台管理基类
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class BaseController {
	
	//缓存用户信息
	protected User getSessionUser(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute(Constants.USER);
		return user;
	}
	
	protected void setSessionUser(HttpServletRequest request,User user) {
		request.getSession().setAttribute(Constants.USER,user);
	}
	
	//机构管理员登陆后台
	protected Org getSessionOrg(HttpServletRequest request) {
//		Org hd = (Org)request.getSession().getAttribute(Constants.ORG);
		User user = (User)request.getSession().getAttribute(Constants.USER);
		return user.getOrg();
	}
	
//	protected void setSessionOrg(HttpServletRequest request,Org org) {
//		request.getSession().setAttribute(Constants.ORG,org);
//	}
}
