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
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.xd.cloud.common.bean.MessageBean;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.cms.Advert;
import com.xd.cloud.entity.cms.Carousel;
import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.entity.cms.Link;
import com.xd.cloud.entity.hd.Hd;
import com.xd.cloud.service.cms.AdvertService;
import com.xd.cloud.service.cms.CarouselService;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.cms.ContentService;
import com.xd.cloud.service.cms.LinkService;
import com.xd.cloud.service.hd.HdOrgService;
import com.xd.cloud.service.hd.HdService;
import com.xd.cloud.service.hd.HdUserService;

/**
 * 活动后台管理控制入口
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Controller
@RequestMapping("/hdadmin")
public class HdAdminController extends BaseController{
	
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
	private HdService hdService;
	@Autowired
	private HdOrgService hdOrgService;
	@Autowired
	private HdUserService hdUserService;
	
	//--------------活动界面的登录注册-------------------
    
	/**
	 * 编辑活动
	 * @param request
	 * @param c
	 */
	@RequestMapping("/edithd")
	@ResponseBody
	public Object edithd(HttpServletRequest request,Hd c) {
		hdService.update(c.getId(),c.getName(),c.getStarttime(),c.getEndtime(),c.getDescrible(),c.getCstime(),c.getFstime(),c.getZstime(),c.getThumb());
		return new MessageBean(true,Constants.SUCCESS_2);
	}
    
	//----------------end-----------------------
	/**
	 * 活动后台首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index/{hdid}")
	public ModelAndView index(HttpServletRequest request,@PathVariable String hdid) {
		String url = "hdadmin/index";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	/**
	 * 栏目管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/channel/{hdid}")
	public ModelAndView channel(HttpServletRequest request,@PathVariable String hdid) {
		String url = "hdadmin/channel";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		int siteId = hd.getSiteid();
		List<Channel> list = new ArrayList<Channel>();
		List<Channel> channels = channelService.getChannelBySiteID(siteId);
		int len = channels.size();
		for (int i=0;i<len;i++)
		{
			Channel cc = channels.get(i);
			list.add(cc);
			addList(list,cc);
		}
		mv.addObject("channels", list);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	private void addList(List<Channel> list,Channel cc)
	{
		Set<Channel> children = cc.getChildren();
		for (Channel str : children) {  
			list.add(str);
			addList(list,str);
		}  
	}
	
	/**
	 * 取得所有栏目
	 * @param request
	 * @return
	 */
	@RequestMapping("/getchannel")
	@ResponseBody
	public Object getchannel(HttpServletRequest request,int hdid) {
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		List<Channel> channels = channelService.getChannelBySiteID(siteId);
		return new MessageBean(true,"",channels);
	}
	
	/**
	 * 保存栏目
	 * @param request
	 * @param c
	 */
	@RequestMapping("/savechannel")
	@ResponseBody
	public Object savechannel(HttpServletRequest request,String name,int sortnumber,int parentid,int hdid) {
		
		Channel c = new Channel();
		c.setCreatetime(UtilTools.timeTostrHMS(new Date()));
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		c.setSiteid(siteId);
		c.setParent(parentid == 0?null:channelService.get(parentid));
		c.setName(name);
		c.setSortnumber(sortnumber);
		c.setPrefix(Constants.TEMPLATE_HD_CHANNEL);
		channelService.save(c);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 编辑栏目
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editchannel")
	@ResponseBody
	public Object editchannel(HttpServletRequest request,int id,String name,int sortnumber) {
		channelService.update( id, name, sortnumber,UtilTools.timeTostrHMS(new Date()));
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除栏目
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delchannel")
	@ResponseBody
	public Object delchannel(HttpServletRequest request, int id) {
		channelService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 内容管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/content/{hdid}/{pageNO}")
	public ModelAndView content(HttpServletRequest request,@PathVariable String hdid,@PathVariable String pageNO) {
		String url = "hdadmin/content";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		int siteId = hd.getSiteid();
		Page page = contentService.getListBySiteID(Integer.parseInt(pageNO), Constants.PAGE_SIZE, siteId);
		
		mv.addObject("contents", page.getResult());
		
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		
		List<Channel> list = new ArrayList<Channel>();
		List<Channel> channels = channelService.getChannelBySiteID(siteId);
		int len = channels.size();
		for (int i=0;i<len;i++)
		{
			Channel cc = channels.get(i);
			list.add(cc);
			addList(list,cc);
		}
		mv.addObject("channels", list);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	 /**
	 * 保存内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/savecontent")
	@ResponseBody
	public Object savecontent(HttpServletRequest request,String title,String content,String describle,String thumbnail,int channelid,int hdid) {
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		contentService.save(title, content, describle, UtilTools.timeTostrHMS(new Date()),thumbnail, channelid, siteId);
		return  new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 编辑内容
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editcontent")
	@ResponseBody
	public Object editcontent(HttpServletRequest request,int id,String title,String describle,String content,String thumbnail,int channelid) {
		contentService.update(id,title,describle,content,UtilTools.timeTostrHMS(new Date()),thumbnail,channelid);
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除内容
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delcontent")
	@ResponseBody
	public Object delcontent(HttpServletRequest request, int id) {
		contentService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 得到内容
	 * @param request
	 * @param c
	 */
	@RequestMapping("/getcontent")
	@ResponseBody
	public Object getcontent(HttpServletRequest request, int id) {
		Content con = contentService.get(id);
		return new MessageBean(true,Constants.SUCCESS_3,con.getContent());
	}
	
	/**
	 * 友情链接管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/link/{hdid}")
	public ModelAndView link(HttpServletRequest request,@PathVariable String hdid) {
		String url = "hdadmin/link";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		int siteId = hd.getSiteid();
		List<Link> links = linkService.getLinkBySiteID(siteId);
		mv.addObject("links", links);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	 /**
	 * 保存链接
	 * @param request
	 * @return
	 */
	@RequestMapping("/savelink")
	@ResponseBody
	public Object savelink(HttpServletRequest request,String name,String linkurl,int sortnumber,int hdid) {
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		Link c = new Link();
		c.setSiteid(siteId);
		c.setName(name);
		c.setLinkurl(linkurl);
		c.setSortnumber(sortnumber);
		linkService.save(c);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 编辑链接
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editlink")
	@ResponseBody
	public Object editlink(HttpServletRequest request,Link c) {
		linkService.update(c.getId(), c.getName(), c.getLinkurl(), c.getSortnumber());
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除链接
	 * @param request
	 * @param c
	 */
	@RequestMapping("/dellink")
	@ResponseBody
	public Object dellink(HttpServletRequest request, int id) {
		linkService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 广告位管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/advert/{hdid}")
	public ModelAndView advert(HttpServletRequest request,@PathVariable String hdid) {
		String url = "hdadmin/advert";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		int siteId = hd.getSiteid();
		List<Advert> adverts = advertService.getAdvertBySiteID(siteId);
		mv.addObject("adverts", adverts);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	 /**
	 * 保存广告位
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveadvert")
	@ResponseBody
	public Object saveadvert(HttpServletRequest request,String name,String linkurl,int sortnumber,int hdid) {
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		Advert c = new Advert();
		c.setSiteid(siteId);
		c.setName(name);
		c.setLinkurl(linkurl);
		c.setSortnumber(sortnumber);
		advertService.save(c);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 编辑广告位
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editadvert")
	@ResponseBody
	public Object editadvert(HttpServletRequest request,Advert c) {
		advertService.update(c.getId(), c.getName(), c.getLinkurl(), c.getSortnumber());
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除广告位
	 * @param request
	 * @param c
	 */
	@RequestMapping("/deladvert")
	@ResponseBody
	public Object deladvert(HttpServletRequest request, int id) {
		advertService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 轮播管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/carousel/{hdid}")
	public ModelAndView carousel(HttpServletRequest request,@PathVariable String hdid) {
		String url = "hdadmin/carousel";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		int siteId = hd.getSiteid();
		List<Carousel> carousels = carouselService.getBySiteID(siteId);
		mv.addObject("carousels", carousels);
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	 /**
	 * 保存轮播
	 * @param request
	 * @return
	 */
	@RequestMapping("/savecarousel")
	@ResponseBody
	public Object savecarousel(HttpServletRequest request,String name,String linkurl,int sortnumber,String path,int hdid) {
		Hd hd = hdService.get(hdid);
		int siteId = hd.getSiteid();
		Carousel c = new Carousel();
		c.setSiteid(siteId);
		c.setName(name);
		c.setLinkurl(linkurl);
		c.setSortnumber(sortnumber);
		c.setPath(path);
		carouselService.save(c);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 编辑轮播
	 * @param request
	 * @param c
	 */
	@RequestMapping("/editcarousel")
	@ResponseBody
	public Object editcarousel(HttpServletRequest request,Carousel c) {
		carouselService.update(c.getId(), c.getName(), c.getLinkurl(), c.getSortnumber(),c.getPath());
		return new MessageBean(true,Constants.SUCCESS_2);
	}
	
	/**
	 * 删除轮播
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delcarousel")
	@ResponseBody
	public Object delcarousel(HttpServletRequest request, int id) {
		carouselService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 参加活动的学校管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/hdorg/{hdid}/{pageNO}")
	public ModelAndView hdorg(HttpServletRequest request,@PathVariable String hdid,@PathVariable String pageNO) {
		String url = "hdadmin/hdorg";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		Page page = hdOrgService.getAllByhdid(Integer.parseInt(pageNO), Constants.PAGE_SIZE, hd.getId());
		
		mv.addObject("orgs", page.getResult());
		
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	/**
	 * 删除参加活动的学校
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delhdorg")
	@ResponseBody
	public Object delhdorg(HttpServletRequest request, int id) {
		hdOrgService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 审批参加的学校
	 * @param request
	 * @param c
	 */
	@RequestMapping("/sphdorg")
	@ResponseBody
	public Object sphdorg(HttpServletRequest request, int id,int status,String reply) {
		hdOrgService.update(id, status, reply);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	

	/**
	 * 参加活动的选手管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/hduser/{hdid}/{pageNO}")
	public ModelAndView hduser(HttpServletRequest request,@PathVariable String hdid,@PathVariable String pageNO) {
		String url = "hdadmin/hduser";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		Page page = hdUserService.getAllByhdid(Integer.parseInt(pageNO), Constants.PAGE_SIZE, hd.getId(),Constants.IDENTITY_0);
		
		mv.addObject("hdusers", page.getResult());
		
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	/**
	 * 参加活动的专家管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/hdzjuser/{hdid}/{pageNO}")
	public ModelAndView hdzjuser(HttpServletRequest request,@PathVariable String hdid,@PathVariable String pageNO) {
		String url = "hdadmin/hdzjuser";
		ModelAndView mv = new ModelAndView(url);
		int hid = Integer.parseInt(hdid);
		Hd hd = hdService.get(hid);
		Page page = hdUserService.getAllByhdid(Integer.parseInt(pageNO), Constants.PAGE_SIZE, hd.getId(),Constants.IDENTITY_1);
		
		mv.addObject("hdusers", page.getResult());
		
		mv.addObject("totalpage", page.getTotalPageCount());
		mv.addObject("currentpage", page.getCurrentPageNo());
		
		mv.addObject("hdadmin", hd);
		return mv;
	}
	
	/**
	 * 删除参加活动的用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/delhduser")
	@ResponseBody
	public Object delhduser(HttpServletRequest request, int id) {
		hdUserService.remove(id);
		return new MessageBean(true,Constants.SUCCESS_3);
	}
	
	/**
	 * 审批参加的用户
	 * @param request
	 * @param c
	 */
	@RequestMapping("/sphduser")
	@ResponseBody
	public Object sphduser(HttpServletRequest request, int id,int status,String reply) {
		hdUserService.update(id, status, reply);
		return new MessageBean(true,Constants.SUCCESS_1);
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
				BufferedImage bufferedImage = ImageIO.read(file2);   
				int w = bufferedImage.getWidth();   
				int h = bufferedImage.getHeight(); 
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
	 * 下载机构填写的报名表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response,String attach) {
		try {
			 String path = request.getSession().getServletContext().getRealPath("/");
			 File file = new File(path+attach);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("报名表.doc".getBytes("UTF-8"),"ISO-8859-1"));
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
}
