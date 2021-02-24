/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.course;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.course.ChapterDao;
import com.xd.cloud.dao.course.ChapterUnitDao;
import com.xd.cloud.dao.course.UnitDao;
import com.xd.cloud.entity.course.Chapter;
import com.xd.cloud.entity.course.ChapterUnit;
import com.xd.cloud.entity.course.Unit;

/**
 * 章节service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class ChapterService {

	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private UnitDao unitDao;
	@Autowired
	private ChapterUnitDao chapterunitDao;
	
	public String saveChapter(Chapter o)
	{
		chapterDao.save(o);
		return Constants.SUCCESS_1;
	}
	public List<Chapter> getChapter(int id,int cid)
	{
		return chapterDao.getChapter(id, cid);
	}
	public List<Chapter> getAllChapter(int cid){
		
		return chapterDao.getAllChapter(cid);
	}
	
	public void remove(int cid,int id)
	{
		Chapter c = chapterDao.get(id);
	if (c != null)
		{
			Chapter pc = c.getChapter();
			if (pc != null)
			{
				Set<Chapter> set = pc.getChildren();
				set.remove(c);
				c.setChapter(null);
			}
//			for(ChapterUnit cu:culist){
//				
//				chapterunitDao.delete(cu);
//				unitDao.delete(cu.getUnit());
//			}
			chapterDao.delete(c);
		}
		
	}
	
	public List<Chapter> getChapte(int id){
		return chapterDao.getChapte(id);
	}
	
	public String saveChapter(String name, int cid,int pid,int sortnumber)
	{
//		String cname=chapter.getName();
		Chapter c = chapterDao.getChapter(cid, name);
		if (c != null)
		{
			return Constants.ERROR_11;
		}
		
	    c = new Chapter();
		c.setName(name);
		c.setCid(cid);
		c.setSortnumber(sortnumber);
		c.setChapter(pid == 0?null:chapterDao.get(pid));
	    chapterDao.save(c);
		
		return Constants.SUCCESS_1;
	}
	
	public void updateChapter(int id,String name,int sortnumber){
		chapterDao.update(id, name, sortnumber);
	}
	
	//保存课程章节内容
	public String saveUnit(Unit u,int courseid,int chapterid)
	{
		unitDao.save(u);
		ChapterUnit cu=new ChapterUnit();
		cu.setCourseid(courseid);
		//cu.setChapterid(chapterid);
		cu.setChapterid(chapterid);
		cu.setUnit(u);
		chapterunitDao.save(cu);
		
		return Constants.SUCCESS_1;
	}
	
	/**
	 * 查询所有课程内容
	 */
	public List<ChapterUnit> getAllUnit(int cid){
		return chapterunitDao.getAllChapterUnit(cid);
	}
	
	/**
	 * 查询所有课程内容
	 */
	public List<ChapterUnit> getChapterUnit(int courseid,int chapterid){
		return chapterunitDao.getChapterUnit(courseid,chapterid);
	}
	
	public Page getChapterUnitPage(int pageNo,int pageSize,int courseid, int chapterid){
		return chapterunitDao.getChapterUnitPage(pageNo, pageSize,courseid, chapterid);
	}
	public Page getChapterUnitPage(int pageNo,int pageSize,int courseid){
		return chapterunitDao.getChapterUnitPage(pageNo, pageSize, courseid);
	}
	public Page getUnitPageByType(int pageNo,int pageSize,int courseid,int type){
		return chapterunitDao.getUnitPageByType(pageNo, pageSize, courseid, type);
	}
	public Page getUnitPageByType(int pageNo,int pageSize,int chapterid,int courseid,int type){
		return chapterunitDao.getUnitPageByType(pageNo, pageSize, chapterid, courseid, type);
	}
}
