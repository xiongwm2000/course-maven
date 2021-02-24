/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.FileType;
import com.xd.cloud.common.Constants;
import com.xd.cloud.common.bean.CategoryBean;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.course.CGroup;
import com.xd.cloud.entity.course.Category;
import com.xd.cloud.entity.hd.HdCourse;
import com.xd.cloud.entity.hd.HdUser;
import com.xd.cloud.service.core.OrgService;
import com.xd.cloud.service.core.UserService;
import com.xd.cloud.service.course.CategoryService;
import com.xd.cloud.service.course.CourseService;
import com.xd.cloud.service.hd.HdOrgService;
import com.xd.cloud.service.hd.HdUserService;

/**
 * 用户相关控制器
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private HdOrgService hdOrgService;
	@Autowired
	private HdUserService hdUserService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categroyService;
	
	/**
     * 跳转到用户上传作品的界面
     */
	@RequestMapping("/tomyupload")
	public ModelAndView tomyupload() {
		String url = "user/myupload";
		ModelAndView mv = new ModelAndView(url);
		List<CGroup> list = courseService.getAllCgroup();
		mv.addObject("cgroups", list);
		return mv;
	}
	
	/**
     * 上传我的作品
     */
	@RequestMapping("/uploadmycourse")
	@ResponseBody
	public Object uploadmycourse(HttpServletRequest request,String name,String describle,String category,String thumb,int categoryid,String path,String pptpath,String zippath,String wordpath,int hdid) {
		User user = getSessionUser(request);
		String str = courseService.saveCourse(name, describle, category, thumb, categoryid, path,pptpath,zippath,wordpath, user.getId(),hdid);
		return new MessageBean(Constants.SUCCESS_1.equals(str), str);
	}
	
	/**
     * 活动下用户的作品
     */
	@RequestMapping("/myhdcourse")
	@ResponseBody
	public Object myhdcourse(HttpServletRequest request,int hdid) {
		User user = getSessionUser(request);
		List<HdCourse> list = courseService.getHdByuserid(hdid, user.getId());
		
		HdUser hduser = hdUserService.getHdXS(user.getId(), hdid);
		if (hduser == null)
		{
			return new MessageBean(false, "1");
		}
		if (list != null && list.size() > 0)
		{
			return new MessageBean(false, "2");
		}
		else
		{
			return new MessageBean(true, "");
		}
	}
	
	/**
	 * 取得分类，默认id传空代表获得第一层分类
	 * @param request
	 * @param id：课程分类id
	 * @param cid:课程分组的id
	 * @return
	 */
	@RequestMapping("/category")
	@ResponseBody
	public Object category(HttpServletRequest request,String id,String cid) {
		List<CategoryBean> list2 = new ArrayList();
		List<Category> list = categroyService.getCategory(id,cid);
		for (Category c:list)
		{
			CategoryBean cb = new CategoryBean();
			cb.setId(c.getId());
			cb.setName(c.getName());
			list2.add(cb);
		}
		return new MessageBean(true, "",list2);
	}
	
	/**
     * 跳转到用户的个人信息
     */
	@RequestMapping("/tocenter")
	public ModelAndView toregeister() {
		String url = "user/center";
		List<Org> orgs = orgService.getSPAll();
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("orgs", orgs);
		return mv;
	}
	
	/**
     * 跳转到我的活动
     */
	@RequestMapping("/editpassword")
	public ModelAndView tomyhd(HttpServletRequest request) {
		String url = "user/editpassword";
		User user = getSessionUser(request);
		List<HdUser> list = hdUserService.getHdByuserid(user.getId());
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("hdusers", list);
		return mv;
	}
	
	/**
     * 跳转到用户的编辑页面
     */
	@RequestMapping("/touseredit")
	public ModelAndView touseredit() {
		String url = "user/useredit";
		ModelAndView mv = new ModelAndView(url);
		List<Org> orgs = orgService.getSPAll();
		mv.addObject("orgs", orgs);
		return mv;
	}
	
	/**
     * 跳转到用户的个人信息
     */
	@RequestMapping("/toorgenter")
	public ModelAndView toorgenter() {
		String url = "user/orgenter";
		ModelAndView mv = new ModelAndView(url);
		return mv;
	}
	
	/**
	 * 编辑个人信息
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/useredit")
	@ResponseBody
	public Object useredit(HttpServletRequest request,int id,String headimg,String realname,int sex,String email) {
		User user = getSessionUser(request);
		user.setHeadimg(headimg);
		user.setRealname(realname);
		user.setSex(sex);
		
		user.setEmail(email);
		userService.update(user);
		setSessionUser(request, user);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	
	/**
     * 注册机构管理员
     * @param request
     * @param user
     * @return
     */
	@RequestMapping("/regorgadmin")
	@ResponseBody
	public Object regorgadmin(HttpServletRequest request,String eamil,String category,String name,String describle,String logoimg,String xcimg,String zzimg,String fmimg,String address,int userid) {
		String str = userService.saveOrgAdmin(eamil,category,name,describle,logoimg,xcimg,zzimg,fmimg,address,userid);
		Org org = orgService.getOrgByname(name);
		if (org != null)
		{
			User user = getSessionUser(request);
			user.setOrg(org);
		}
		return new MessageBean(str.equals(Constants.SUCCESS_6)?true:false, str);
	}
	
	/**
     * 跳转到用户报名页面
     */
	@RequestMapping("/tousersignup/{id}")
	public ModelAndView tousersign(HttpServletRequest request,@PathVariable String id) {
		String url = "user/usersignup";
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("hdid", id);
		return mv;
	}
	
	/**
     * 选手或者专家报名
     * @param request
     * @param user
     * @return
     */
	@RequestMapping("/usersign")
	@ResponseBody
	public Object usersign(HttpServletRequest request,int userid,String realname,String code,String codeimg,int identity,int hdid) {
		String str = hdUserService.userSign(userid, realname, code, codeimg, identity, hdid);
		return new MessageBean(Constants.SUCCESS_1.equals(str)?true:false, str);
	}
	
	
	 /**
     * 机构报名
     * @param request
     * @param user
     * @return
     */
	@RequestMapping("/orgsign")
	@ResponseBody
	public Object orgsign(HttpServletRequest request,int orgid,String email,String address,String attach,int hdid) {
		
		String str = hdOrgService.orgSign(orgid, email, address, attach,hdid);
		return new MessageBean(Constants.SUCCESS_1.equals(str)?true:false, str);
	}
	
	/**
	 * 上传
	 * 
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "qqfile", required = true) MultipartFile file) {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (!file.isEmpty()) {
				byte[] data = file.getBytes();
				String upath = request.getSession().getServletContext()
						.getRealPath("/");
				String path = UtilTools.getConfig().getProperty("uploadpath");
				String filename = file.getOriginalFilename();
				String suffix = FileType.getSuffixByFilename(filename);
				path = path + suffix;
				path = PathFormat.parse(path, filename);
				File file2 = new File(upath+path);
				FileUtils.writeByteArrayToFile(file2, data);
				   
				int w = 0;   
				int h = 0; 
				try
				{
					BufferedImage bufferedImage = ImageIO.read(file2);
					w = bufferedImage.getWidth();   
					h = bufferedImage.getHeight(); 
				}
				catch(Exception ee)
				{
					
				}
				out.print("{\"success\": \"true\",\"imgpath\":\"" + path+ "\",\"imgw\":\"" + w+ "\",\"imgh\":\"" + h+ "\"}");
				// out.write("<script>parent.callback('sucess')</script>");
			} else {
				// out.write("<script>parent.callback('fail')</script>");
				out.print("{\"success\": \"false\"}");
			}

		} catch (Exception e) {
			out.print("{\"success\": \"false\"}");
		}
	}
	
	/**
	 * 下载报名模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response) {
		try {
			 String path = request.getSession().getServletContext().getRealPath("/style/");
			 File file = new File(path+"/bmb.doc");
			 if (!file.exists())
			 {
				 return;
			 }
			 
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("报名模板.doc".getBytes("UTF-8"),"ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            //加上UTF-8文件的标识字符      
//            toClient.write(new byte []{(byte) 0xEF ,(byte) 0xBB ,(byte) 0xBF});
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证原密码
	 * @param request
	 * @param id
	 * @param oldpw
	 * @return
	 */
	@RequestMapping("/checkoldppw")
	@ResponseBody
	public Object checkoldppw(HttpServletRequest request,int id,String oldpw) {
//		int userid = user.getId();
		User user = userService.get(id);
		oldpw=UtilTools.md5Digest(oldpw).toLowerCase();
		return oldpw.equals(user.getPassword());
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param id
	 * @param newpw
	 * @return
	 */
	@RequestMapping("/updatepw")
	@ResponseBody
	public Object updatepw(HttpServletRequest request,int id,String newpw) {
//		int userid = user.getId();
		User user = userService.get(id);
		newpw=UtilTools.md5Digest(newpw).toLowerCase();
		user.setPassword(newpw);
		userService.update(user);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
}
