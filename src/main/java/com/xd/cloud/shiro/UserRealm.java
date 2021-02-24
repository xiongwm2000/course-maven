/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.cache.CacheManager;
import com.xd.cloud.common.cache.CacheUser;
import com.xd.cloud.common.cache.CacheUserRole;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.service.core.UserService;

/**
 * 用户realm
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		List<CacheUserRole> curlist = cacheManager.getCacheUserRole(username);
		if (curlist == null)
		{
			List<Object> urlist = userService.findUserRole(username);
			curlist = new ArrayList<CacheUserRole>();
			for (Object ur:urlist)
			{
				CacheUserRole cur = new CacheUserRole();
				String rolekey = (String)ur;
				cur.setRolekey(rolekey);
				curlist.add(cur);
			}
			//放用户角色缓存信息
			cacheManager.setCacheUserRole(username, curlist);
		}
		//角色名的集合
		Set<String> roles = new HashSet<String>();
		//权限名的集合
		Set<String> permissions = new HashSet<String>();
		
		for (CacheUserRole ur:curlist)
		{
			String rolekey = ur.getRolekey();
			roles.add(rolekey);
		}
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);
		
		
		return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
//		User user = userService.getUserByname(username);
		CacheUser cacheUser = cacheManager.getCacheUser(username);
		if (cacheUser == null)
		{
//			User user = userService.getUserByname(username);
			Object[] obj = userService.findUser(username);
			if(obj==null){
				//木有找到用户
				throw new UnknownAccountException(Constants.ERROR_9);
			}
			else
			{
				cacheUser = new CacheUser();
				cacheUser.setId(Integer.parseInt(obj[0].toString().trim()));
				cacheUser.setUsername(username);
				cacheUser.setRealname((String)obj[2]);
				cacheUser.setPassword((String)obj[3]);
				//放用户缓存信息
				cacheManager.setCacheUser(username, cacheUser);
			}
		}
		
		if (cacheUser != null)
		{
			ServletRequest request = ((WebSubject)SecurityUtils.getSubject()).getServletRequest();   
			HttpSession httpSession = ((HttpServletRequest)request).getSession();   
			
			if (httpSession.getAttribute(Constants.USER) == null)
			{
				User tempuser = new User();
				tempuser.setId(cacheUser.getId());
				tempuser.setUsername(username);
				tempuser.setRealname(cacheUser.getRealname());
				httpSession.setAttribute(Constants.USER,tempuser);
			}
		}
		
//		if(user==null){
//			//木有找到用户
//			throw new UnknownAccountException(Constants.ERROR_4);
//		}
		/* if(Boolean.TRUE.equals(user.getLocked())) {  
	            throw new LockedAccountException(); //帐号锁定  
	        } */
		
		/**
		 * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(cacheUser.getUsername(), cacheUser.getPassword(),getName());
		
		
		return info;
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}

}