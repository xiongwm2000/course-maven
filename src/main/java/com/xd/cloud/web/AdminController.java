/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.Role;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.service.core.OrgService;
import com.xd.cloud.service.core.OrgUserService;
import com.xd.cloud.service.core.RoleService;
import com.xd.cloud.service.core.UserRoleService;
import com.xd.cloud.service.core.UserService;

/**
 * 系统管理平台控制入口
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		String url = "index";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpServletRequest request) {
		
		User user = getSessionUser(request);
		if (user != null)
		{
			String url = "redirect:/admin/user";
			return new ModelAndView(url);
		}
		String url = "admin/login";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
  //------------------用户管理------------------------------
    /**
     * 用户管理页面
     * @param request
     * @return
     */
	@RequestMapping("/user")
	public ModelAndView user(HttpServletRequest request) {
		String url = "admin/user";
		ModelAndView mv = new ModelAndView(url);
		List<User> list = userService.getAll();
		mv.addObject("users", list);
		List<Role> list2 = roleService.getAll();
		mv.addObject("roles", list2);
		return mv;
	}
	
	 /**
	 * 保存用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveuser")
	@ResponseBody
	public Object saveuser(HttpServletRequest request,User c) {
		c.setPassword(UtilTools.md5Digest(c.getPassword()));
		boolean flag = userService.save(c);
		return new MessageBean(flag,flag?Constants.SUCCESS_1:Constants.ERROR_3);
	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/edituser")
	@ResponseBody
	public Object edituser(HttpServletRequest request,User c) {
		userService.update(c);
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 编辑用户角色
	 * @param request
	 * @param c
	 */
	@RequestMapping("/edituserrole")
	@ResponseBody
	public Object edituserrole(HttpServletRequest request,@RequestBody List<Map<String,Object>> list) {
//		userRoleService.update(list);
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 编辑用户机构
	 * @param request
	 * @param c
	 */
	@RequestMapping("/edituserorg")
	@ResponseBody
	public Object edituserorg(HttpServletRequest request,int userid,int orgid) {
		orgUserService.update(userid, orgid);
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/deluser")
	public ModelAndView deluser(HttpServletRequest request, int id) {
		userService.remove(id);
		String url = "redirect:/admin/user";
		return new ModelAndView(url);
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/deleteuser")
	@ResponseBody
	public MessageBean deleteUser(HttpServletRequest request, int uid) {
		userService.remove(uid);
		return new MessageBean(true);
	}
	
	/**
	 * 禁用用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/forbidden")
	@ResponseBody
	public MessageBean forbidden(HttpServletRequest request, int uid) {
		User user = userService.get(uid);
		int status = user.getStatus() == -1 ? 0 : -1;
		user.setStatus(status); // 临时：-1禁用
		userService.update(user);
		return new MessageBean(true);
	}
	
	//----------------------end----------------------------------
	
    //------------------角色管理------------------------------
    /**
     * 角色管理页面
     * @param request
     * @return
     */
	@RequestMapping("/role")
	public ModelAndView role(HttpServletRequest request) {
		String url = "admin/role";
		ModelAndView mv = new ModelAndView(url);
		List<Role> list = roleService.getAll();
		mv.addObject("roles", list);
		return mv;
	}
	
	 /**
	 * 保存角色
	 * @param request
	 * @return
	 */
	@RequestMapping("/saverole")
	@ResponseBody
	public Object saverole(HttpServletRequest request,Role c) {
		boolean flag = roleService.save(c);
		return new MessageBean(flag,flag?Constants.SUCCESS_1:Constants.ERROR_1);
	}
	
	/**
	 * 编辑角色
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editrole")
	@ResponseBody
	public Object editrole(HttpServletRequest request,Role c) {
		roleService.update(c.getId(), c.getName());
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delrole")
	public ModelAndView delrole(HttpServletRequest request, int id) {
		roleService.remove(id);
		String url = "redirect:/admin/role";
		return new ModelAndView(url);
	}
	
	//----------------------end----------------------------------
	//------------------机构管理------------------------------
	/**
     * 角色管理页面
     * @param request
     * @return
     */
	@RequestMapping("/org")
	public ModelAndView org(HttpServletRequest request) {
		String url = "admin/org";
		ModelAndView mv = new ModelAndView(url);
		List<Org> list = orgService.getAll();
		mv.addObject("orgs", list);
		return mv;
	}
	
	/**
	 * 保存机构
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorg")
	@ResponseBody
	public Object saveorg(HttpServletRequest request,Org c) {
		boolean flag = orgService.save(c);
		return new MessageBean(flag,flag?Constants.SUCCESS_1:Constants.ERROR_2);
	}
	
	/**
	 * 编辑机构
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editorg")
	@ResponseBody
	public Object editorg(HttpServletRequest request,int id,String name,String describle,String address,String email,String category) {
		Org org=orgService.get(id);
		
		org.setName(name);
		org.setDescrible(describle);
		org.setAddress(address);
		org.setEmail(email);
		org.setCategory(category);
		orgService.update(org);
		
		User user = getSessionUser(request);
		user.setOrg(org);
		setSessionUser(request, user);
		
		return new MessageBean(true,"修改成功");
	}
	
	/**
	 * 删除机构
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delorg")
	public ModelAndView delorg(HttpServletRequest request, int id) {
		orgService.remove(id);
		String url = "redirect:/admin/org";
		return new ModelAndView(url);
	}
	//----------------------end----------------------------------
}
