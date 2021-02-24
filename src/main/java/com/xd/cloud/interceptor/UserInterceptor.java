/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.web.BaseController;

/**
 * 用户的拦截器，所有未激活帐户统一跳转
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 *
 */
public class UserInterceptor extends BaseController implements HandlerInterceptor {

	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			return true;
		} else {
			
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = request;
			User user = getSessionUser(httpRequest);
			int status = user.getStatus();
			String contextPath = request.getContextPath();
			if (status != Constants.SP_2)
			{
				String toUrl = httpRequest.getRequestURL().toString();
				//简单点处理，不包含
				if (toUrl != null && !toUrl.contains("/user/tocenter"))
				{
					response.sendRedirect(contextPath + "/user/tocenter");
					return false;
				}
				return true;
			}
			
			return true;
		}
	}
}
