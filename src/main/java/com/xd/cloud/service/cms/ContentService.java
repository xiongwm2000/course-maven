package com.xd.cloud.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.cms.ChannelDao;
import com.xd.cloud.dao.cms.ContentDao;
import com.xd.cloud.entity.cms.Content;

/**
 * 内容service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class ContentService {

	@Autowired
	private ContentDao contentDao;
	@Autowired
	private ChannelDao channelDao;
	
	public Content get(int id)
	{
		return contentDao.get(id);
	}
	
	public Content preCon(int id,int channelid)
	{
		return contentDao.preCon(id, channelid);
	}
	
	public Content nextCon(int id,int channelid)
	{
		return contentDao.nextCon(id, channelid);
	}
	
	public void save(Content c)
	{
		contentDao.save(c);
	}
	
	public void save(String title,String content,String describle,String createtime,String thumbnail,int channelid,int siteid)
	{
		Content c = new Content();
		c.setTitle(title);
		c.setDescrible(describle);
		c.setContent(content);
		c.setCreatetime(createtime);
		c.setSiteid(siteid);
		c.setPrefix(Constants.TEMPLATE_HD_CONTENT);
		c.setChannel(channelDao.get(channelid));
		c.setThumbnail(thumbnail);
//		c.setType(1);
		contentDao.save(c);
	}
	
	public void savewd(String title,String describle,String createtime,String path,int channelid,int siteid,String swfpath,int type,String content)
	{
		Content c = new Content();
		c.setTitle(title);
		c.setDescrible(describle);
		c.setCreatetime(createtime);
		c.setSiteid(siteid);
		c.setPrefix(Constants.TEMPLATE_HD_CONTENT);
		c.setChannel(channelDao.get(channelid));
		c.setSwfpath(swfpath);
		c.setType(type);
		c.setPath(path);
		c.setContent(content);
		contentDao.save(c);
	}
	
	public void update(Content c)
	{
		contentDao.update(c);
	}
	
	public void update(int id,String title,String describle,String content, String modifytime,String thumbnail,int channelid)
	{
		contentDao.update(id, title, describle, content,  modifytime,thumbnail, channelid);
	}
	
	public void updatewd(int id,String title,String describle,String modifytime,String path,int channelid,String swfpath,int type,String content)
	{
		contentDao.updatewd(id, title, describle,modifytime,path, channelid,swfpath,type,content);
	}
	
	public void remove(int id)
	{
		Content c = contentDao.get(id);
		if (c != null)
			contentDao.delete(c);
	}
	
	public List<Content> getContentBySiteID(int siteId)
	{
		return contentDao.getContentBySiteID(siteId);
	}
	
//	getChannelByid(int id)
	public Content getContentByid(int id)
	{
		return contentDao.getContentByid(id);
	}
	
	public Page getListBySiteID(int pageNo,int pageSize,int siteId)
	{
		return contentDao.getListBySiteID(pageNo,pageSize,siteId);
	}
	
	public List<Content> getListByChannelId(int channelId)
	{
		return contentDao.getListByChannelId(channelId);
	}
	
	public Page getListByChannelId(int pageNo,int pageSize,int channelid)
	{
		return contentDao.getListByChannelId(pageNo, pageSize, channelid);
	}
	
	public List<Content> getAll()
	{
		return contentDao.getAll();
	}
	
	public Page getAll(int pageNo,int pageSize){
		return contentDao.getAll(pageNo, pageSize);
	}
}
