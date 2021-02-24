/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.ContentBean;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.bean.OrgBean;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.hd.Hd;
import com.xd.cloud.entity.hd.HdOrg;
import com.xd.cloud.entity.hd.HdUser;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.cms.ContentService;
import com.xd.cloud.service.core.OrgService;
import com.xd.cloud.service.core.UserService;
import com.xd.cloud.service.hd.HdOrgService;
import com.xd.cloud.service.hd.HdService;
import com.xd.cloud.service.hd.HdUserService;

/**
 * 微信action入口
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Controller
@RequestMapping("/wx")
public class WxController extends BaseController {
	
	@Autowired
	private HdOrgService hdOrgService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private HdUserService hdUserService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private HdService hdService;

	/**
     * 跳转到首页
     */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/index";
		Hd hd = hdService.get(1);
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("hd", hd);
		return mv;
	}
	/**
     * 跳转到大赛指南
     */
	@RequestMapping("/guide")
	public ModelAndView guide(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/guide";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}

	
	/**
     * 跳转到个人中心
     */
	@RequestMapping("/center")
	public ModelAndView center(HttpServletRequest request,HttpServletResponse response) {
		
		User user = getSessionUser(request);
		String url = "wx/center";
		if (user == null)
		{
			 url = "redirect:/wx/tologin";
			
		}
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}	
	
	/**
     * 跳转到个人中心修改页面
     */
	@RequestMapping("/touseredit")
	public ModelAndView touseredit(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/useredit";
		ModelAndView mv = new ModelAndView(url);
		List<Org> orgs = orgService.getSPAll();
		mv.addObject("orgs", orgs);
		return mv;
	}	
	
	
	/**
     * 参赛学校
     */
	@RequestMapping("/hdorg")
	public ModelAndView hdorg(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/hdorg";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}	
	
	/**
	 * 参加活动的学校
	 * @param request
	 * @return
	 */
	@RequestMapping("hdorg/{id}/{no}")
	public ModelAndView hdorg(HttpServletRequest request,@PathVariable String id,@PathVariable String no) {
		String url = "wx/hdorg";
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
	
	
	/*
	 * 参赛学校查询
	 * @param request
	 * @return
	 */
	@RequestMapping("searchOrg")
	@ResponseBody
	public Object searchOrg(HttpServletRequest request,String orgName,String address,String id) {
		
		int idInt=Integer.parseInt(id);
		
		List<Org> orgs=null;
		//name空，address空
		if(orgName.equals("")&&address.equals("所有地区")){
			orgs=orgService.getOrgByHdid(idInt);
		}
		//name空，address非空
		if(orgName.equals("")&&(!address.equals("所有地区"))){
			orgs=orgService.getOrgByAddress(address, idInt);
		}
		//name非空，address空
		if(!orgName.equals("")&&address.equals("所有地区")){
			orgs=orgService.getOrgByName(orgName,idInt);
		}
		//name非空，address非空
		if((!orgName.equals(""))&&(!address.equals("所有地区"))){
			orgs=orgService.getOrgByNameAddress(orgName, address,idInt);
		}		
		
		List lists = new ArrayList();
		if(orgs!=null){
			for (Org org:orgs)
			{
				OrgBean ob = new OrgBean();
				ob.setId(org.getId());
//				ob.setLogoimg(org.getLogoimg());
				ob.setName(org.getName());
				ob.setFmimg(org.getFmimg());
				lists.add(ob);
			}			
		}
		return new MessageBean(true, "", lists);
		
//		return new MessageBean(true, "", orgs);
		
	}	
	
	
	/**
     * 跳转到登录界面
     */
	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/login";
		ModelAndView mv = new ModelAndView(url);
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
	//初始化channelService
		ChannelService channelService = new ChannelService();
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
		Page page = contentService.getListByChannelId(no, Constants.PAGE_WXSCHOOLSIZE, channelid);

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
	 * 参加活动一页数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/schoollist")
	@ResponseBody
	public Object schoollist(HttpServletRequest request,String id,String no) {
		Page page = hdOrgService.getOrgsByhdid(Integer.parseInt(no), Constants.PAGE_WXSCHOOLSIZE, Integer.parseInt(id));

		List  list = page.getResult();
		List lists = new ArrayList();
		for (Object ho:list)
		{
			HdOrg hh = (HdOrg)ho;
			Org org = hh.getOrg();
			OrgBean ob = new OrgBean();
			ob.setId(org.getId());
//			ob.setLogoimg(org.getLogoimg());
			ob.setName(org.getName());
			ob.setFmimg(org.getFmimg());
			lists.add(ob);
		}
		
		return new MessageBean(true, "", lists);
	}

	/**
     * 新闻中心
     */
	@RequestMapping("/newslist")
	public ModelAndView newslist(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/newslist";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}

	
	
	/**
     * 跳转到大赛指南详情页面
     */
	@RequestMapping("/tonews/{id}")
	public ModelAndView news(HttpServletRequest request,HttpServletResponse response,@PathVariable String id) {
		String url = "wx/news";
		
		int cid = Integer.parseInt(id);
		Content con = contentService.get(cid);
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("content", con);
		return mv;
	}
	
	/**
     * 跳转到微课专家页面
     */
	@RequestMapping("/tohduser")
	public ModelAndView hduser(HttpServletRequest request,HttpServletResponse response) {
		String url = "wx/hduser";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
	/**
     * 跳转到微课专家详细信息页面
     */
	@RequestMapping("/touserinfo/{id}")
	public ModelAndView userinfo(HttpServletRequest request,HttpServletResponse response,@PathVariable String id) {
		String url = "wx/hduserinfo";
		int uid = Integer.parseInt(id);
		
		User hd_user=userService.get(uid);
		
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("hd_user", hd_user);
		return mv;
	}
	
	/**
	 * 学校页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/school/{hdid}/{id}")
	public ModelAndView school(HttpServletRequest request,@PathVariable String hdid,@PathVariable String id) {
		
		String url = "wx/hdschool";
		ModelAndView mv = new ModelAndView(url);
		Org org = orgService.get(Integer.parseInt(id));
		mv.addObject("school", org);
//		Set<User> users = org.getUsers();
		List<HdUser> hdlist = hdUserService.getHduserByorgid(Integer.parseInt(hdid), org.getId());
		mv.addObject("hdlist", hdlist);
		return mv;
	}
	
	/**
     * 跳转到我的活动
     */
	@RequestMapping("/tomyhd")
	public ModelAndView tomyhd(HttpServletRequest request) {
		String url = "wx/myhd";
		ModelAndView mv = new ModelAndView(url);
		User user = getSessionUser(request);
		List<HdUser> list = hdUserService.getHdByuserid(user.getId());
		mv.addObject("hdusers", list);
		return mv;
	}
		
}
