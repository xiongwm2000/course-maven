/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.EvaluationBean;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.course.Chapter;
import com.xd.cloud.entity.course.ChapterUnit;
import com.xd.cloud.entity.course.Course;
import com.xd.cloud.entity.course.Evaluation;
import com.xd.cloud.entity.course.Unit;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.cms.ContentService;
import com.xd.cloud.service.core.UserService;
import com.xd.cloud.service.course.ChapterService;
import com.xd.cloud.service.course.CourseService;
import com.xd.cloud.service.course.EvaluationService;
//import java.util.Set;

/**
 * 单个课程控制入口
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController{

	@Autowired
	private CourseService courseService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private UserService userService;
	@Autowired
	private EvaluationService evaluationService;
	
	/**
     * 跳转到登录界面
     */
	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpServletRequest request,HttpServletResponse response) {
		String url = "course/login";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
	/**
	 * 保存评分
	 * @param request
	 * @param userid
	 * @param courseid
	 * @param score
	 * @return
	 */
	@RequestMapping("/saveevaluation")
	@ResponseBody
	public Object saveevaluation(HttpServletRequest request,int courseid,int score) {
		User user = getSessionUser(request);
		if (user == null)
		{
			return  new MessageBean(false,"");
		}
		String str=courseService.saveevaluation(user.getId(), courseid, score,user);
		return  new MessageBean(true,str);
	}
	
	/**
     * 跳转到注册界面
     */
	@RequestMapping("/toregeister")
	public ModelAndView toregeister(HttpServletRequest request,HttpServletResponse response) {
		String url = "course/regeister";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}

	/**
     * 注册
     * @param request
     * @param user
     * @return
     */
	@RequestMapping("/regteacher")
	@ResponseBody
	public Object regteacher(HttpServletRequest request,User user) {
		String str = userService.saveHdT(user);
		if (Constants.SUCCESS_5.equals(str))
		{	
			return new MessageBean(true,str);
		}
		else
		{
			return new MessageBean(false,str);
		}
	}
	
	
	/**
     * 判断用户是否登录
     * @param currUser
     * @return
     */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, User user) {
		// ModelAndView view = new ModelAndView();
		Subject users = SecurityUtils.getSubject();
		String password = UtilTools.md5Digest(user.getPassword()).toLowerCase();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
		token.setRememberMe(true);
		try {
			users.login(token);
			boolean flag = users.isAuthenticated();
			User tuser = userService.getUserByname(user.getUsername());
			if(tuser.getStatus() < 0){ // 临时：小于0 为被禁用
				return new MessageBean(false, "账户已被禁用，请联系管理员");
			}
			setSessionUser(request, tuser);
			// OrgUser orgUser = orgUserService.getByuserid(tuser.getId());
			// if (orgUser != null)
			// {
			// setSessionOrg(request, orgUser.getOrg());
			// }
			// return "redirect:/user/tocenter";
			return new MessageBean(true, "");
		} catch (UnknownAccountException e) {
			token.clear();
			return new MessageBean(false, Constants.ERROR_7);
		} catch (IncorrectCredentialsException ee) {
			token.clear();
			return new MessageBean(false, Constants.ERROR_8);
		}
	}

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
    	Subject subject = SecurityUtils.getSubject();
    	if (subject.isAuthenticated()) {
    		subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
    	}
    	setSessionUser(request, null);
    	String url = "redirect:/";
		ModelAndView mv = new ModelAndView(url);
		return mv;
    }
    
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
		String url = "course/home";
		ModelAndView mv = new ModelAndView(url);
		List<Course> gc = courseService.getCoursebyprize("产品入门");
		List<Course> sc = courseService.getCoursebyprize("产品进阶");
		List<Course> share = courseService.getCoursebyprize("大咖分享");
		mv.addObject("gclist", gc);
		mv.addObject("sclist", sc);
		mv.addObject("shlist", share);
		return mv;
	}
	/**
	 * 跳转课程搜索页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/search/{key}")	
	public ModelAndView tosearch(HttpServletRequest request,@PathVariable String key) {
		String url = "course/search";
		ModelAndView mv = new ModelAndView(url);
		key = UtilTools.converStr(key);
		List<Course> clist=new ArrayList<Course>();
			 clist=courseService.getCoursebykey(key);
       mv.addObject("clist",clist);
        mv.addObject("num",clist.size());
        mv.addObject("key",key);

         return mv;
	}
	
	/**
	 * 手机特等奖页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/teprize")
	public ModelAndView teprize(HttpServletRequest request) {
		String url = "course/teprize";
		ModelAndView mv = new ModelAndView(url);
		List<Course> list = courseService.getCoursebyprize(Constants.TEPRIZE);
		mv.addObject("clist", list);
		return mv;
	}
	
	@RequestMapping("/oneprize")
	public ModelAndView oneprize(HttpServletRequest request) {
		String url = "course/oneprize";
		ModelAndView mv = new ModelAndView(url);
		List<Course> list = courseService.getCoursebyprize(Constants.ONEPRIZE);
		mv.addObject("clist", list);
		return mv;
	}
	
	/**
	 * 跳转课程分类页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/cinfo")
	public ModelAndView tocinfo(HttpServletRequest request) {
		String url = "course/cinfo";
		ModelAndView mv = new ModelAndView(url);
		Page page = courseService.getAllCourse(1, 16);

		mv.addObject("clist", page.getResult());
		// mv.addObject("totalpage", page.getTotalPageCount());
		// mv.addObject("currentpage", page.getCurrentPageNo());
		mv.addObject("totalcount", page.getTotalCount());
		return mv;
	}
	
	@RequestMapping("/getcourses")
	@ResponseBody
	public Object getcourses(HttpServletRequest request,String level,String prize,int pageNo) {
		Page page=new Page();	
		
		if("".equals(prize)||"n".equals(level)){
			 page=courseService.getAllCourse(pageNo, 16);
		}
		if(level!=""&&prize==""){
			 page=courseService.getListBylevel(pageNo, 16, level);
		}
		if(level==""&&prize!=""){
			 page=courseService.getListByprize(pageNo, 16, prize);
		}
		if(level!=""&&prize!=""){
			 page=courseService.getCourseList(pageNo, 0, level, prize);
		}
		long totalcount = page.getTotalCount()==0?1:page.getTotalCount();
		return new MessageBean(true,"",totalcount);
		
	}
	
	@RequestMapping("/getcourselist")
	@ResponseBody
	public Object getcourselist(HttpServletRequest request,int pageNo,String level,String prize) {
		
		Page page=new Page();
	
		if("".equals(prize)||"".equals(level)){
			 page=courseService.getAllCourse(pageNo, 16);
		}
		if(level!=""&&prize==""){
			 page=courseService.getListBylevel(pageNo, 16, level);
		}
		if(level==""&&prize!=""){
			 page=courseService.getListByprize(pageNo, 16, prize);
		}
		if(level!=""&&prize!=""){
			 page=courseService.getCourseList(pageNo, 0, level, prize);
		}
		List<Course> courses=page.getResult();
		
		return new MessageBean(true,courses.size(),courses);
	}
	
	@RequestMapping("/topcourseinfo/{courseid}")
	public ModelAndView pcourseinfo(HttpServletRequest request,@PathVariable int courseid) {
		String url = "course/pcourseinfo";
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("courseid", courseid);
		Course course = courseService.getCourseByid(courseid);
		User user=courseService.getCourseuser(course.getUserid());
		List<Channel> channles = channelService.getChannelBySiteID(course.getSiteid());

		mv.addObject("course", course);
		mv.addObject("channles", channles);
		mv.addObject("us", user);
		return mv;
	}
	@RequestMapping("/tocourseinfo/{courseid}")
	public ModelAndView courseinfo(HttpServletRequest request,@PathVariable int courseid) {
		String url = "course/courseinfo";
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("courseid", courseid);
		Course course = courseService.getCourseByid(courseid);
		User user=courseService.getCourseuser(course.getUserid());
		List<Channel> channles = channelService.getChannelBySiteID(course.getSiteid());
//		List<Content> content=contentService.getContentBySiteID(course.getSiteid());
	    List<Evaluation> elist=courseService.getEvaluationbycid(course.getId());
	    List<Evaluation> evaluations=courseService.getEvaluationComBycid(courseid);
	    List<Evaluation> evaluationlim=evaluationService.getEvaluationBycidLimited(courseid);
	    mv.addObject("evaluations", evaluations);
	    mv.addObject("evaluationlim", evaluationlim);
	    
		int score=0;
		if(elist.size()>0){
			for(Evaluation e:elist){
				score+=e.getScore();
			}
			score=score/elist.size();
		}
//		if (channles != null && channles.size() > 0)
//		{
//			Channel c = channles.get(0);
//			if (c != null)
//			{
//				List<Content> clist = contentService.getListByChannelId(c.getId());
//				if (clist != null)
//				{
//					Content con = clist.get(0);
//					mv.addObject("con", con);
//				}
//			}
//		}
		//System.out.println(score);
		mv.addObject("size",elist.size());
		mv.addObject("score",score);
		mv.addObject("course", course);
		mv.addObject("channles", channles);
		mv.addObject("us", user);
		return mv;
	}

	/**
	 *  章节资源
	 * @param request
	 * @return
	 */
	@RequestMapping("/getContent")
	@ResponseBody
	public Object getContent(HttpServletRequest request,int courseid,int chapterid) {
		
		Course course = courseService.getCourseByid(courseid);
		List<Channel> channles = channelService.getChannelBySiteID(course.getSiteid());
		Content con = null;
		if (chapterid == -1)
		{
			if (channles != null && channles.size() > 0)
			{
				Channel c = channles.get(0);
				if (c != null)
				{
					List<Content> clist = contentService.getListByChannelId(c.getId());
					if (clist != null &&clist.size()>0)
					{
						con = clist.get(0);
					}
				}
			}
		}
		else
		{
			List<Content> clist = contentService.getListByChannelId(chapterid);
			if (clist != null&&clist.size()>0)
			{
				con = clist.get(0);
			}
		}
		return new MessageBean(true, "", con);
	}
	
	/**
	 * 课程首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index/{courseid}")
	public ModelAndView index(HttpServletRequest request,@PathVariable int courseid) {
		String url = "course/index";
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("courseid", courseid);
		Course course = courseService.getCourseByid(courseid);
		mv.addObject("course", course);
		return mv;
	}
	
	/**
	 * 课程资源
	 * @param request
	 * @return
	 */
	@RequestMapping("/resources/{courseid}")
	public ModelAndView resources(HttpServletRequest request,@PathVariable int courseid) {
		String url = "course/resources";
		ModelAndView mv = new ModelAndView(url);
		List<Chapter> list = new ArrayList<Chapter>();
		List<Chapter> chapters=chapterService.getAllChapter(courseid);
		Course course=courseService.getCourseByid(courseid);
		int len = chapters.size();
		for (int i=0;i<len;i++)
		{
			Chapter cc = chapters.get(i);
			list.add(cc);
			addList(list,cc);
		}
		
		List<ChapterUnit> cus=chapterService.getAllUnit(courseid);
		
		Page page=chapterService.getChapterUnitPage(1,Constants.PAGE_RESSIZE,courseid);
		
		mv.addObject("totalcount", page.getTotalCount());
		mv.addObject("size", cus.size());
		mv.addObject("course", course);
		mv.addObject("courseid", courseid);
		mv.addObject("chapterid", 0);
		mv.addObject("type", 0);
		mv.addObject("chapters", list);
		return mv;
	}
	
	
	
	@RequestMapping("/reslist")
	@ResponseBody
	public Object getRes(HttpServletRequest request,int pageNo,int courseid,int chapterid,int type) {
		
		Page page=new Page();
		List<Unit> units=new ArrayList();
		if(chapterid==0&&type==0){
			page=chapterService.getChapterUnitPage(pageNo,Constants.PAGE_RESSIZE,courseid);
		}
		if(chapterid!=0&&type==0){
			page=chapterService.getChapterUnitPage(pageNo, Constants.PAGE_RESSIZE, courseid, chapterid);
		}
		if(chapterid==0&&type!=0){
			page=chapterService.getUnitPageByType(pageNo, Constants.PAGE_RESSIZE, courseid, type);
		}
		if(chapterid!=0&&type!=0){
			page=chapterService.getUnitPageByType(pageNo, Constants.PAGE_RESSIZE, chapterid, courseid, type);
		}
		
		List<ChapterUnit> chapterunits=page.getResult();
		for(ChapterUnit chapterunit:chapterunits){
			units.add(chapterunit.getUnit());
		}

		return new MessageBean(true,units.size(),units);
	}
	
	
	/**
	 *  章节资源
	 * @param request
	 * @return
	 */
	@RequestMapping("/getchapres")
	@ResponseBody
	public Object getChapRes(HttpServletRequest request,int pageNo,int courseid,int chapterid) {
		
		Page page=chapterService.getChapterUnitPage(pageNo,Constants.PAGE_RESSIZE,courseid, chapterid);
		
		long totalcount = page.getTotalCount()==0?1:page.getTotalCount();
		return new MessageBean(true,"",totalcount);
	}
	
	/**
	 *  章节资源
	 * @param request
	 * @return
	 */
	@RequestMapping("/gettyperes")
	@ResponseBody
	public Object getTypeRes(HttpServletRequest request,int pageNo,int courseid,int chapterid,int type) {
		
		Page page=new Page();
		if(chapterid==0&&type==0){
			page=chapterService.getChapterUnitPage(pageNo,Constants.PAGE_RESSIZE,courseid);
		}
		if(chapterid!=0&&type==0){
			page=chapterService.getChapterUnitPage(pageNo, Constants.PAGE_RESSIZE, courseid, chapterid);
		}
		if(chapterid==0&&type!=0){
			page=chapterService.getUnitPageByType(pageNo, Constants.PAGE_RESSIZE, courseid, type);
		}
		if(chapterid!=0&&type!=0){
			page=chapterService.getUnitPageByType(pageNo, Constants.PAGE_RESSIZE, chapterid, courseid, type);
		}
		long totalcount = page.getTotalCount()==0?1:page.getTotalCount();
		return new MessageBean(true,"",totalcount);
	}
	
	
	
	/**
	 * 教学效果
	 * @param request
	 * @return
	 */
	@RequestMapping("/effect/{courseid}/{chanelid}")
	public ModelAndView effect(HttpServletRequest request,@PathVariable int courseid,@PathVariable int chanelid) {
		String url = "course/effect";
		
		//取得课程
		
//		Course course = courseService.getCourseByid(courseid);
//		List<Channel> channels = channelService.getChannelBySiteID(course.getSiteid());
		ModelAndView mv = new ModelAndView(url);
//		mv.addObject("channels", channels);
		mv.addObject("courseid", courseid);
		
		/*通过chanelid获取Channel集合*/
		Channel c = channelService.get(chanelid);
		if (c != null)
		{/*求上一级菜单*/
			Channel pc = c.getParent();
			
			if (pc != null)
			{
				/*channel是一级菜单集合*/
				mv.addObject("channel", pc);
			}
			else
			{
				mv.addObject("channel", c);
			}
		}
	
		
		Content con = contentService.getContentByid(chanelid);
		if (con == null)
		{
			if (c != null && c.getParent() == null)
			{
				Channel cc = (Channel)c.getChildren().toArray()[0];
				if (cc != null)
				{
					con = contentService.getContentByid(cc.getId());
				}
			}
		}
		mv.addObject("content", con);
		return mv;
	}
	
	private void addList(List<Chapter> list,Chapter cc)
	{
		Set<Chapter> children = cc.getChildren();
		for (Chapter str : children) {  
			list.add(str);
			addList(list,str);
		}  
	}
	
	/**
	 *  章节资源
	 * @param request
	 * @return
	 */
	@RequestMapping("/commentsave")
	@ResponseBody
	public Object commentsave(HttpServletRequest request,int courseid,String content) {
		User user = getSessionUser(request);
		if (user == null)
		{
			return  new MessageBean(true,"未登录");
		}
		Evaluation evaluation=new Evaluation();
		evaluation.setCourseid(courseid);
		evaluation.setEvatime(UtilTools.timeTostrHMS(new Date()));
		evaluation.setEcommnent(content);
		evaluation.setUser(user);
		evaluationService.save(evaluation);
		
		EvaluationBean evaluationBean=new EvaluationBean();
		evaluationBean.setCourseid(courseid);
		evaluationBean.setEcommnent(content);
		evaluationBean.setEvatime(evaluation.getEvatime());
		evaluationBean.setHeadimg(evaluation.getUser().getHeadimg());
		evaluationBean.setRealname(evaluation.getUser().getRealname());
		evaluationBean.setScore(evaluation.getScore());
		evaluationBean.setUserid(evaluation.getUser().getId());
		evaluationBean.setUsername(evaluation.getUser().getUsername());
		return new MessageBean(true,"",evaluationBean);
	}
}
