/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.ContentBean;
import com.xd.cloud.common.bean.HdCourseBean;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.bean.OrgBean;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.course.Course;
import com.xd.cloud.entity.hd.Hd;
import com.xd.cloud.entity.hd.HdCourse;
import com.xd.cloud.entity.hd.HdOrg;
import com.xd.cloud.entity.hd.HdUser;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.cms.ContentService;
import com.xd.cloud.service.core.OrgService;
import com.xd.cloud.service.core.OrgUserService;
import com.xd.cloud.service.core.UserService;
import com.xd.cloud.service.course.CourseService;
import com.xd.cloud.service.hd.HdCourseService;
import com.xd.cloud.service.hd.HdOrgService;
import com.xd.cloud.service.hd.HdService;
import com.xd.cloud.service.hd.HdUserService;

/**
 * 活动控制入口
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Controller
@RequestMapping("/hd")
public class HdController extends BaseController{
	
	@Autowired
	private ContentService contentService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private UserService userService;
	@Autowired
	private HdService hdService;
	@Autowired
	private HdOrgService hdOrgService;
	@Autowired
	private HdUserService hdUserService;
	@Autowired
	private HdCourseService hdCourseService;
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private CourseService courseService;
	
	/**
	 * 前台首页显示
	 */
	@RequestMapping("/toindex")
	public ModelAndView toindex(HttpServletRequest request) {
		String url = "course/index";
		ModelAndView mv = new ModelAndView(url);
		Course course = courseService.getCourseByid(1);
		List<Channel> channels = channelService.getChannelBySiteID(1);
		System.out.println(course.getName());
		mv.addObject("course", course);
		mv.addObject("channels", channels);
		return mv;
	}
	
	//--------------活动界面的登录注册-------------------
	
	/**
     * 跳转到忘记密码发邮件界面
     */
	@RequestMapping("/resendmail")
	public ModelAndView resendmail(HttpServletRequest request,HttpServletResponse response) {
		String url = "hd/resendmail";
		ModelAndView mv = new ModelAndView(url);
		
		return mv;
	}
	
	/**
     * 重置密码
     */
	@RequestMapping("/resetpwd")
	@ResponseBody
	public Object resetpwd(HttpServletRequest request,String email,String pwd) {
		User user = userService.getUserBymail(email);
		user.setPassword(UtilTools.md5Digest(pwd));
		userService.update(user);
		return new MessageBean(true, Constants.SUCCESS_7);
	}
	
	//----------------end-----------------------
	
	/**
	 * 活动首页
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/index/{id}")
	public ModelAndView index(HttpServletRequest request,@PathVariable String id) {
		Hd hd = hdService.get(Integer.parseInt(id));
		String url = hd.getPrefix();
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("hd", hd);
		List list = hdUserService.getChartData();
//		List list1 = new ArrayList
		JSONArray json = new JSONArray();
		JSONArray json2 = new JSONArray();
		int a = 0 ;
		for(int i=0;i<list.size();i++)
		{
			a += ((BigInteger)(((Object[])list.get(i))[0])).intValue();
			json.put(a);
			String b = (String)((Object[])list.get(i))[1];
			if (b != null && b.endsWith("-"))
			{
				b = b.substring(0, b.length()-1);
			}
			json2.put(b);
		}
		
		mv.addObject("data1", json.toString());
		mv.addObject("data2", json2.toString());
		return mv;
	}
	
	/**
	 * 栏目
	 * @param request
	 * @param id
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/channel/{id}")
	public ModelAndView channelid(HttpServletRequest request,@PathVariable String id,String pageNo) {
		
		int channelid = Integer.parseInt(id);
		Channel channel  = channelService.get(channelid);
		String url = channel.getPrefix();
		ModelAndView mv = new ModelAndView(url);
		int no = 1;
		if (pageNo != null && !"".equals(pageNo))
		{
			no = Integer.parseInt(pageNo);
		}
		Page page = contentService.getListByChannelId(no, Constants.PAGE_SIZE, channelid);

		mv.addObject("contents", page.getResult());
		mv.addObject("totalcount", page.getTotalCount());
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		mv.addObject("tab", pageNo==null?0:pageNo);
		return mv;
	}
	
	/**
	 * 参加活动一页数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/contentlist")
	@ResponseBody
	public Object contentlist(HttpServletRequest request,int channelid,int no) {
		Page page = contentService.getListByChannelId(no, Constants.PAGE_SIZE, channelid);

		List  list = page.getResult();
		List lists = new ArrayList();
		for (Object ho:list)
		{
			Content hh = (Content)ho;
			
			ContentBean cb = new ContentBean();
			cb.setThumbnail(hh.getThumbnail());
			cb.setTitle(hh.getTitle());
			cb.setId(hh.getId());
			cb.setCreatetime(hh.getCreatetime());
			cb.setDescrible(hh.getDescrible());
			lists.add(cb);
		}
		
		return new MessageBean(true, "", lists);
	}
	
	/**
	 * 内容
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/{id}")
	public ModelAndView contentid(HttpServletRequest request,@PathVariable String id) {
		int cid = Integer.parseInt(id);
		Content con = contentService.get(cid);
		int number = con.getClicknumber();
		number++;
		con.setClicknumber(number);
		contentService.update(con);
		String url = con.getPrefix();
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("content", con);
		Content preCon = contentService.preCon(cid, con.getChannel().getId());
		mv.addObject("precontent", preCon);
		Content nextCon = contentService.nextCon(cid, con.getChannel().getId());
		mv.addObject("nextcontent", nextCon);
		return mv;
	}
	
	/**
	 * 参加活动的学校
	 * @param request
	 * @return
	 */
	@RequestMapping("/hdorg/{id}/{no}")
	public ModelAndView hdorg(HttpServletRequest request,@PathVariable String id,@PathVariable String no) {
		String url = "hd/hdorg";
		ModelAndView mv = new ModelAndView(url);
		Page page = hdOrgService.getOrgsByhdid(Integer.parseInt(no), Constants.PAGE_SCHOOLSIZE, Integer.parseInt(id));
		
		List  list = page.getResult();
		List lists = new ArrayList();
		for (Object ho:list)
		{
			HdOrg hh = (HdOrg)ho;
			Org org = hh.getOrg();
			OrgBean ob = new OrgBean();
			ob.setLogoimg(org.getLogoimg());
			ob.setName(org.getName());
			lists.add(ob);
		}
		mv.addObject("hdorgs", lists);
		mv.addObject("totalcount", page.getTotalCount());
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		return mv;
	}
	
	/**
	 * 参加活动一页数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/schoollist")
	@ResponseBody
	public Object schoollist(HttpServletRequest request,String id,String no) {
		Page page = hdOrgService.getOrgsByhdid(Integer.parseInt(no), Constants.PAGE_SCHOOLSIZE, Integer.parseInt(id));

		List  list = page.getResult();
		List lists = new ArrayList();
		for (Object ho:list)
		{
			HdOrg hh = (HdOrg)ho;
			Org org = hh.getOrg();
			OrgBean ob = new OrgBean();
			ob.setId(org.getId());
			ob.setLogoimg(org.getLogoimg());
			ob.setName(org.getName());
			ob.setDescrible(org.getDescrible());
			ob.setFmimg(org.getFmimg());
			lists.add(ob);
		}
		
		return new MessageBean(true, "", lists);
	}
	
	/**
	 * 学校页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/hdschool/{hdid}/{id}")
	public ModelAndView hdschool(HttpServletRequest request,@PathVariable String hdid,@PathVariable String id) {
		
		String url = "hd/org";
		ModelAndView mv = new ModelAndView(url);
		Org org = orgService.get(Integer.parseInt(id));
		mv.addObject("school", org);
//		Set<User> users = org.getUsers();
		List<HdUser> hdlist = hdUserService.getHduserByorgid(Integer.parseInt(hdid), org.getId());
		mv.addObject("hdlist", hdlist);
		return mv;
	}
	
	/**
	 * 参加活动的专家
	 * @param request
	 * @return
	 */
	@RequestMapping("/hduser/{id}/{no}")
	public ModelAndView hduser(HttpServletRequest request,@PathVariable String id,@PathVariable String no) {
		String url = "hd/hduser";
		ModelAndView mv = new ModelAndView(url);
		Page page = hdOrgService.getAllByhdid(Integer.parseInt(no), Constants.PAGE_SIZE, Integer.parseInt(id));
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 活动下的课程
	 * @param request
	 * @return
	 */
	@RequestMapping("/hdcourse/{id}/{no}")
	public ModelAndView hdcourse(HttpServletRequest request,@PathVariable String id,@PathVariable String no) {
		String url = "hd/hdcourse";
		ModelAndView mv = new ModelAndView(url);
		Page page = hdCourseService.getAllByhdid(Integer.parseInt(no), Constants.PAGE_SIZE, Integer.parseInt(id));
//		mv.addObject("page", page);
		
		List  list = page.getResult();
		List lists = new ArrayList();
		for (Object ho:list)
		{
			HdCourse hh = (HdCourse)ho;
			HdCourseBean ob = new HdCourseBean();
			ob.setId(hh.getId());
			ob.setImg(hh.getCourse().getThumb());
			ob.setName(hh.getCourse().getName());
			lists.add(ob);
		}
		mv.addObject("hdcourses", lists);
		mv.addObject("totalcount", page.getTotalCount());
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		return mv;
	}
}
