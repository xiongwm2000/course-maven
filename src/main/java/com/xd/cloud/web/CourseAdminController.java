/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
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
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.xd.cloud.common.Constants;
import com.xd.cloud.common.P2FConst;
import com.xd.cloud.common.bean.ChannelBean;
import com.xd.cloud.common.bean.ContentBean;
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.util.Configuration;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.Role;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.course.Course;
import com.xd.cloud.service.cms.AdvertService;
import com.xd.cloud.service.cms.CarouselService;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.cms.ContentService;
import com.xd.cloud.service.cms.LinkService;
import com.xd.cloud.service.core.OrgService;
import com.xd.cloud.service.core.OrgUserService;
import com.xd.cloud.service.core.RoleService;
import com.xd.cloud.service.core.UserService;
import com.xd.cloud.service.course.ChapterService;
import com.xd.cloud.service.course.CourseService;
import com.xd.cloud.service.course.QcloudFileService;
import com.xd.cloud.service.hd.HdOrgService;
import com.xd.cloud.service.hd.HdService;
import com.xd.cloud.service.hd.HdUserService;

/**
 * 机构后台管理控制入口
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Controller
@RequestMapping("/courseadmin")
public class CourseAdminController extends BaseController {

	@Autowired
	private ContentService contentService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private AdvertService advertService;
	@Autowired
	private CarouselService carouselService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private HdOrgService hdOrgService;
	@Autowired
	private HdUserService hdUserService;
	@Autowired
	private HdService hdService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private QcloudFileService qcloudFileService;

	/**
	 * 课程后台管理首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("/courseadmin/course/1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到课程管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/{pageNo}")
	public ModelAndView course(HttpServletRequest request, @PathVariable int pageNo) {
		String url = "courseadmin/course";
		ModelAndView mv = new ModelAndView(url);
		User user = getSessionUser(request);
		Page page = courseService.getPageCourseByuserid(pageNo, Constants.PAGE_SIZE, user.getId());
		mv.addObject("courses", page.getResult());
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		return mv;
	}

	/**
	 * 获得课程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getcourse")
	@ResponseBody
	public MessageBean getcourse(HttpServletRequest request, int courseid) {
		Course course = courseService.getCourseByid(courseid);
		return new MessageBean(true, "", course);
	}

	/**
	 * 增加课程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/savecourse")
	@ResponseBody
	public Object savecourse(HttpServletRequest request, Course c) {
		User user = getSessionUser(request);
		String str = courseService.saveCourse(c, user.getId());
		return new MessageBean(str.equals(Constants.SUCCESS_1), str);
	}

	/**
	 * 跳转到管理课程详细信息页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/tocmanage/{id}/info")
	public ModelAndView toeditcourse(HttpServletRequest request, @PathVariable String id) {
		int cid = Integer.parseInt(id);
		String url = "courseadmin/coursemanage";
		ModelAndView mv = new ModelAndView(url);
		Course course = courseService.getCourseByid(cid);
		mv.addObject("course", course);
		return mv;
	}

	/**
	 * 编辑课程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editcourse")
	@ResponseBody
	public Object editcourse(HttpServletRequest request, int id, String name, String describle, String category,
			String feature, String thumb, String prize, String level, String video, String oldimgpath, String website) {

		if (!thumb.equals(oldimgpath)) {
			String upath = request.getSession().getServletContext().getRealPath("/");
			oldimgpath = upath + oldimgpath.substring(1).replace("/", "\\");
			File file = new File(oldimgpath);
			if (file.exists()) {
				file.delete();
			}
		}

		courseService.updateCourse(id, name, describle, category, feature, UtilTools.timeTostrHMS(new Date()), thumb,
				prize, level, video, website);
		return new MessageBean(true, Constants.SUCCESS_2);
	}

	/**
	 * 删除课程
	 */
	@RequestMapping("/delcourse")
	@ResponseBody
	public Object delcourse(HttpServletRequest request, int id) {
		courseService.deleteCourse(id);
		return new MessageBean(true, Constants.SUCCESS_3);
	}

	/**
	 * 增加章节
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/savechapter")
	@ResponseBody
	public Object savechapter(HttpServletRequest request, String name, int cid, int pid, int sortnumber) {

		String str = chapterService.saveChapter(name, cid, pid, sortnumber);
		return new MessageBean(str.equals(Constants.SUCCESS_1), str);
	}

	/**
	 * 栏目管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/channel")
	public ModelAndView channel(HttpServletRequest request) {
		String url = "courseadmin/channel";
		ModelAndView mv = new ModelAndView(url);
		User user = getSessionUser(request);
		List<Course> list = courseService.getCourseByuserid(user.getId());
		int len = list.size();
		mv.addObject("len", len);
		mv.addObject("courses", list);
		return mv;
	}

	private void addList(List<ChannelBean> list, Channel cc) {
		Set<Channel> children = cc.getChildren();
		for (Channel str : children) {
			ChannelBean cb = new ChannelBean();
			cb.setId(str.getId());
			cb.setName(str.getName());
			cb.setPid(str.getParent() == null ? 0 : str.getParent().getId());
			cb.setParentName(str.getParent() == null ? "" : str.getParent().getName());
			list.add(cb);
			addList(list, str);
		}
	}

	/**
	 * 取得所有栏目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getchannel")
	@ResponseBody
	public Object getchannel(HttpServletRequest request, int siteId) {

		List<ChannelBean> list = new ArrayList<ChannelBean>();
		List<Channel> channels = channelService.getChannelBySiteID(siteId);
		int len = channels.size();
		for (int i = 0; i < len; i++) {
			Channel cc = channels.get(i);
			ChannelBean cb = new ChannelBean();
			cb.setId(cc.getId());
			cb.setName(cc.getName());
			cb.setPid(cc.getParent() == null ? 0 : cc.getParent().getId());
			cb.setParentName(cc.getParent() == null ? "" : cc.getParent().getName());
			cb.setSortnumber(cc.getSortnumber());
			list.add(cb);
			addList(list, cc);
		}
		return new MessageBean(true, len, list);
	}

	/**
	 * 保存栏目
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/savechannel")
	@ResponseBody
	public Object savechannel(HttpServletRequest request, String name, int sortnumber, int parentid, int siteId) {

		Channel c = new Channel();
		c.setCreatetime(UtilTools.timeTostrHMS(new Date()));
		c.setSiteid(siteId);
		c.setParent(parentid == 0 ? null : channelService.get(parentid));
		c.setName(name);
		c.setSortnumber(sortnumber);
		channelService.save(c);
		return new MessageBean(true, Constants.SUCCESS_1);
	}

	/**
	 * 删除栏目
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delchannel")
	@ResponseBody
	public Object delchannel(HttpServletRequest request, int id) {
		channelService.remove(id);
		return new MessageBean(true, Constants.SUCCESS_3);
	}

	/**
	 * 编辑栏目
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editchannel")
	@ResponseBody
	public Object editchannel(HttpServletRequest request, int id, String name, int sortnumber, int parentid) {
		if (parentid == 0) {
			channelService.update(id, name, sortnumber, UtilTools.timeTostrHMS(new Date()));
			return new MessageBean(true, Constants.SUCCESS_2);
		}
		channelService.update(id, name, sortnumber, UtilTools.timeTostrHMS(new Date()), parentid);
		return new MessageBean(true, Constants.SUCCESS_2);
	}

	/**
	 * 删除章节
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Object del(HttpServletRequest request, int cid, int id) {

		chapterService.remove(cid, id);
		return new MessageBean(true, Constants.SUCCESS_3);
	}

	/**
	 * 内容管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/content/{pageNO}/{siteId}")
	public ModelAndView content(HttpServletRequest request, @PathVariable String pageNO, @PathVariable int siteId) {
		String url = "courseadmin/content";
		ModelAndView mv = new ModelAndView(url);

		User user = getSessionUser(request);
		List<Course> list = courseService.getCourseByuserid(user.getId());
		mv.addObject("courses", list);

		Page page = contentService.getListBySiteID(Integer.parseInt(pageNO), Constants.PAGE_SIZE, siteId);

		mv.addObject("contents", page.getResult());

		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		mv.addObject("siteId", siteId);

		List<Channel> listc = new ArrayList<Channel>();
		List<Channel> channels = channelService.getChannelBySiteID(siteId);
		int len = channels.size();
		mv.addObject("len", len);
		for (int i = 0; i < len; i++) {
			Channel cc = channels.get(i);
			listc.add(cc);
			addListc(listc, cc);
		}
		mv.addObject("channels", listc);

		return mv;
	}

	private void addListc(List<Channel> list, Channel cc) {
		Set<Channel> children = cc.getChildren();
		for (Channel str : children) {
			list.add(str);
			addListc(list, str);
		}
	}

	/**
	 * 用户管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/usermanage/{pageNo}")
	public ModelAndView userManage(HttpServletRequest request, @PathVariable int pageNo) {
		String url = "courseadmin/usermanage";
		ModelAndView mv = new ModelAndView(url);
		Page page = userService.getAllUser(pageNo, Constants.PAGE_SIZE);
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		mv.addObject("users", page.getResult());
		List<Role> roles = roleService.getAll();
		mv.addObject("roles", roles);
		return mv;
	}

	@RequestMapping("/content")
	public ModelAndView content(HttpServletRequest request) {
		User user = getSessionUser(request);
		List<Course> list = courseService.getCourseByuserid(user.getId());
		int siteId = 0;
		if (list != null && list.size() > 0) {
			siteId = list.get(0).getSiteid();
		}
		ModelAndView mv = content(request, "1", siteId);
		return mv;
	}

	/**
	 * 获得内容
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getcontentbyid")
	@ResponseBody
	public Object getcontentbyid(HttpServletRequest request, int id) {
		Org org = getSessionOrg(request);
		Content content = contentService.get(id);
		ContentBean contentBean = new ContentBean();
		contentBean.setId(id);
		contentBean.setPath(content.getPath());
		contentBean.setTitle(content.getTitle());
		contentBean.setChannelname(content.getChannel().getName());
		contentBean.setChannelid(content.getChannel().getId().toString());
		contentBean.setDescrible(content.getDescrible());
		contentBean.setType(content.getType());
		contentBean.setContent(content.getContent());
		return new MessageBean(true, "", contentBean);
	}

	/**
	 * 保存内容
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/savecontent")
	@ResponseBody
	public Object savecontent(HttpServletRequest request, String title, String content, String describle,
			String thumbnail, int channelid, int siteId) {
		Org org = getSessionOrg(request);
		contentService
				.save(title, content, describle, UtilTools.timeTostrHMS(new Date()), thumbnail, channelid, siteId);
		return new MessageBean(true, Constants.SUCCESS_1);
	}

	/**
	 * 保存文档
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/savewd")
	@ResponseBody
	public Object savewd(HttpServletRequest request, String title, String describle, String path, int channelid,
			int siteId, int type, String content) {
		String swfpath = path;
		if (type != 2) {
			swfpath = path + ".swf";
		}
		contentService.savewd(title, describle, UtilTools.timeTostrHMS(new Date()), path, channelid, siteId, swfpath,
				type, content);
		return new MessageBean(true, Constants.SUCCESS_1);
	}

	/**
	 * 得到内容
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/getcontent")
	@ResponseBody
	public Object getcontent(HttpServletRequest request, int id) {
		Content con = contentService.get(id);
		return new MessageBean(true, Constants.SUCCESS_4, con.getContent());
	}

	/**
	 * 编辑内容
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editcontent")
	@ResponseBody
	public Object editcontent(HttpServletRequest request, int id, String title, String describle, String content,
			String thumbnail, int channelid, String oldpath) {
		contentService.update(id, title, describle, content, UtilTools.timeTostrHMS(new Date()), thumbnail, channelid);
		return new MessageBean(true, Constants.SUCCESS_2);
	}

	/**
	 * 编辑文档
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editwd")
	@ResponseBody
	public Object editwd(HttpServletRequest request, int id, String title, String describle, String path,
			int channelid, int type, String oldpath, String content) {

		if (!path.equals(oldpath)) {
			String upath = request.getSession().getServletContext().getRealPath("/");
			oldpath = upath + oldpath.substring(1).replace("/", "\\");
			File file = new File(oldpath);
			File swffile = new File(oldpath + ".swf");
			if (file.exists()) {
				file.delete();
			}
			if (swffile.exists()) {
				file.delete();
			}
		}

		String swfpath = path;
		if (type != 2) {
			swfpath = path + ".swf";
		}
		contentService.updatewd(id, title, describle, UtilTools.timeTostrHMS(new Date()), path, channelid, swfpath,
				type, content);
		return new MessageBean(true, Constants.SUCCESS_2);
	}

	/**
	 * 删除内容
	 * 
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delcontent")
	@ResponseBody
	public Object delcontent(HttpServletRequest request, int id) {
		contentService.remove(id);
		return new MessageBean(true, Constants.SUCCESS_3);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "qqfile", required = true) MultipartFile file) {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (!file.isEmpty()) {
				byte[] data = file.getBytes();
				String upath = request.getSession().getServletContext().getRealPath("/");
				String path = UtilTools.getConfig().getProperty("uploadpath");
				String filename = file.getOriginalFilename();
				String suffix = FileType.getSuffixByFilename(filename);
				path = path + suffix;
				path = PathFormat.parse(path, filename);
				File file2 = new File(upath + path);
				FileUtils.writeByteArrayToFile(file2, data);
				if(Configuration.isOpenCos){
					Date now = new Date();
					String cosImgFilePath = Configuration.cosImgFilePath + now.getTime() + suffix;
					/* {"code":0,"message":"SUCCESS","request_id":"NTk0Yzg4NGVfY2IwZTc3XzllN2ZfYjdiZQ==",
					 "data":{"access_url":"http://objectstorage-1253440178.file.myqcloud.com/image/1498187853631.jpg",
					 "resource_path":"/1253440178/objectstorage/image/1498187853631.jpg",
					 "source_url":"http://objectstorage-1253440178.cossh.myqcloud.com/image/1498187853631.jpg",
					 "url":"http://sh.file.myqcloud.com/files/v2/1253440178/objectstorage/image/1498187853631.jpg",
					 "vid":"0a18fc1dc07c274d04aeac108284cc7a1498187854"}}*/
					String qcloudMessage = qcloudFileService.uploadImgQcloud(cosImgFilePath, file2.getAbsolutePath());
					JSONObject json = new JSONObject(qcloudMessage); // 上传失败解析不了自动跳入catch
					JSONObject jsonData = json.getJSONObject("data");
					path = jsonData.getString("source_url");
					System.out.println(path);
					file2.delete();
				}
				int w = 0;
				int h = 0;
				try {
					BufferedImage bufferedImage = ImageIO.read(file2);
					w = bufferedImage.getWidth();
					h = bufferedImage.getHeight();
				} catch (Exception ee) {
				}
				out.print("{\"success\": " + true + ",\"imgpath\":\"" + path + "\",\"imgw\":\"" + w + "\",\"imgh\":\""
						+ h + "\"}");
				// out.write("<script>parent.callback('sucess')</script>");
			} else {
				// out.write("<script>parent.callback('fail')</script>");
				out.print("{\"success\": " + false + "}");
			}

		} catch (Exception e) {
			out.print("{\"success\": " + false + "}");
		}
	}

	/**
	 * 上传视频
	 * 
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("/uploadwd")
	public void uploadwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "qqfile", required = true) MultipartFile file) {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (!file.isEmpty()) {
				byte[] data = file.getBytes();
				String upath = request.getSession().getServletContext().getRealPath("/");
				String path = UtilTools.getConfig().getProperty("videopath");
				String filename = file.getOriginalFilename();
				String suffix = FileType.getSuffixByFilename(filename);
				path = path + suffix;
				path = PathFormat.parse(path, filename);
				File file2 = new File(upath + path);
				FileUtils.writeByteArrayToFile(file2, data);
				// 上传到云
				if(Configuration.isOpenVod){
					String fileId = qcloudFileService.uploadVideoQcloud(file2.getAbsolutePath(), suffix, new Date().getTime()+"");
					if(!fileId.isEmpty()){ // 上传成功
						String videoUrl = qcloudFileService.getVideoInfo(fileId);
						file2.delete();
						if(!videoUrl.isEmpty()){
							out.print("{\"success\": " + true + ",\"imgpath\":\"" + videoUrl + "\",\"suffix\":\"" + suffix
									+ "\",\"filename\":\"" + filename + "\"}");
							return;
						}
						out.print("{\"success\": " + false + "}");
					}else{
						out.print("{\"success\": " + true + ",\"imgpath\":\"" + path + "\",\"suffix\":\"" + suffix
								+ "\",\"filename\":\"" + filename + "\"}");
					}
					return;
				}
				// 转 swf
				String wdpath = file2.getPath();
				if (suffix.equals(".mp4")) {
					out.print("{\"success\": " + true + ",\"imgpath\":\"" + path + "\",\"suffix\":\"" + suffix
							+ "\",\"filename\":\"" + filename + "\"}");
					return;
				}
				boolean flag = print2swf(wdpath);
				if (!flag) {
					out.print("{\"success\": " + false + "}");
				} else {
					out.print("{\"success\": " + true + ",\"imgpath\":\"" + path + "\",\"suffix\":\"" + suffix
							+ "\",\"filename\":\"" + filename + "\"}");
				}

			} else {
				out.print("{\"success\": " + false + "}");
			}

		} catch (Exception e) {
			out.print("{\"success\": " + false + "}");
		}
	}

	public boolean print2swf(String filepath) {
		try {
			ComThread.InitSTA();
			// Create Server object
			ActiveXComponent p2f = new ActiveXComponent("Print2Flash3.Server");

			// Setup interface and protection options
			ActiveXComponent defProfile = new ActiveXComponent(p2f.getProperty("DefaultProfile").toDispatch());
			defProfile.setProperty("InterfaceOptions", P2FConst.INTLOGO | P2FConst.INTZOOMSLIDER | P2FConst.INTPREVPAGE
					| P2FConst.INTGOTOPAGE | P2FConst.INTNEXTPAGE);
			defProfile.setProperty("ProtectionOptions", P2FConst.PROTDISPRINT | P2FConst.PROTENAPI);

			// Convert document
			p2f.invoke("ConvertFile", filepath);

			System.out.println("Conversion completed successfully");
			return true;
		} catch (Exception e) {
			System.out.println("An error occurred at conversion: " + e.toString());
			// e.printStackTrace();
			return false;
		} finally {
			ComThread.Release();
		}
	}

}
