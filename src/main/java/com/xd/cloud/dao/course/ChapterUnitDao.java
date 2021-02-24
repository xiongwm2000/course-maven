/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;
import com.xd.cloud.dao.Page;
import com.xd.cloud.entity.course.ChapterUnit;

/**
 * 课程內容关联Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class ChapterUnitDao extends BaseDao<ChapterUnit> {
	/**
	 * 查询所有课程内容
	 */
	public List<ChapterUnit> getChapterUnit(int courseid, int chapterid){
		String hql="from ChapterUnit where courseid = ? and chapterid = ?"; 
		return find(hql,new Object[]{courseid,chapterid});
	}
	
	public List<ChapterUnit> getAllChapterUnit(int courseid){
		String hql="from ChapterUnit where courseid = ?"; 
		return find(hql,new Object[]{courseid});
	}
	
	
//	chapterid!=0&&type==0
	public Page getChapterUnitPage(int pageNo,int pageSize,int courseid, int chapterid){
		String hql = "from ChapterUnit where courseid = ? and chapterid = ?";
		return pagedQuery(hql, pageNo, pageSize,new Object[]{courseid,chapterid});
    }
//	chapterid==0&&type==0
	public Page getChapterUnitPage(int pageNo,int pageSize,int courseid){
		String hql = "from ChapterUnit where courseid = ?";
		return pagedQuery(hql, pageNo, pageSize,new Object[]{courseid});
    }
//	chapterid==0&&type!=0
	public Page getUnitPageByType(int pageNo,int pageSize,int courseid,int type){
		String hql = "from ChapterUnit where courseid = ? and unit.type = ?";
		return pagedQuery(hql, pageNo, pageSize,new Object[]{courseid,type});
    }
//	chapterid!=0&&type!=0
	public Page getUnitPageByType(int pageNo,int pageSize,int chapterid,int courseid,int type){
		String hql = "from ChapterUnit where chapterid=? and courseid = ? and unit.type = ?";
		return pagedQuery(hql, pageNo, pageSize,new Object[]{chapterid,courseid,type});
    }
}
