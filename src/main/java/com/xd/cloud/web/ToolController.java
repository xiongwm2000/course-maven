/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.service.core.UserService;

/**
 * 工具action入口
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Controller
@RequestMapping("/tool")
public class ToolController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping("/sendmail")
	@ResponseBody
	public Object sendmail(String username,String email) {
		try
		{
			mailSender.send(username, email);
			return new MessageBean(true,Constants.SUCCESS_4);
		}
		catch(Exception e)
		{
			return new MessageBean(false,Constants.ERROR_10);
		}
	}

	/**
	 * 忘记密码发邮件
	 * @param email
	 * @return
	 */
	@RequestMapping("/sendusermail")
	@ResponseBody
	public Object sendusermail(String email) {
		try
		{
			mailSender.send(email);
			return new MessageBean(true,Constants.SUCCESS_4);
		}
		catch(Exception e)
		{
			return new MessageBean(false,Constants.ERROR_10);
		}
	}
	
	/**
	 * 激活帐户
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/reset/{username}/{token}")
	public ModelAndView reset(HttpServletRequest request,@PathVariable String username,@PathVariable String token) {
		
		try {
			username = URLDecoder.decode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = userService.getUserByToken(username,token);
		user.setStatus(Constants.SP_2);
		String url = "hd/reset";
		userService.update(user);
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/resetpwd/{email}/{token}")
	public ModelAndView resetpwd(HttpServletRequest request,@PathVariable String email,@PathVariable String token) {
		
		try {
			email = URLDecoder.decode(email, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = userService.getUserByPwdtoken(email,token);
		String url = "hd/resetpwd";
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("email", user.getEmail());
		return mv;
	}
}
