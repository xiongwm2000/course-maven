/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.course;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.cms.SiteDao;
import com.xd.cloud.dao.core.UserDao;
import com.xd.cloud.dao.course.CGroupDao;
import com.xd.cloud.dao.course.ChapterDao;
import com.xd.cloud.dao.course.ChapterUnitDao;
import com.xd.cloud.dao.course.CourseCategoryDao;
import com.xd.cloud.dao.course.CourseDao;
import com.xd.cloud.dao.course.EvaluationDao;
import com.xd.cloud.dao.course.UnitDao;
import com.xd.cloud.dao.hd.HdCourseDao;
import com.xd.cloud.dao.hd.HdDao;
import com.xd.cloud.entity.cms.Site;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.course.CGroup;
import com.xd.cloud.entity.course.Chapter;
import com.xd.cloud.entity.course.ChapterUnit;
import com.xd.cloud.entity.course.Course;
import com.xd.cloud.entity.course.CourseCategory;
import com.xd.cloud.entity.course.Evaluation;
import com.xd.cloud.entity.course.Unit;
import com.xd.cloud.entity.hd.HdCourse;

/**
 * 课程service
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private UnitDao unitDao;
	@Autowired
	private ChapterUnitDao chapterUnitDao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CGroupDao cgroupDao;
	@Autowired
	private HdDao hdDao;
	@Autowired
	private HdCourseDao hdCourseDao;
	@Autowired
	private SiteDao siteDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EvaluationDao evaluationDao;

	public List<CGroup> getAllCgroup() {
		return cgroupDao.getAllCgroup();
	}

	public List<HdCourse> getHdByuserid(int hdid, int userid) {
		return hdCourseDao.getHdByuserid(hdid, userid);
	}

	public List<Course> getCourseByuserid(int userid) {
		return courseDao.getCourseByuserid(userid);
	}

	public String saveCourse(Course course, int userid) {
		String name = course.getName();
		Course c = courseDao.getCourse(userid, name);
		if (c != null) {
			return Constants.ERROR_11;
		}

		Site site = new Site();
		site.setName(name);
		siteDao.save(site);

		c = new Course();
		c.setName(name);
		c.setCreatetime(UtilTools.timeTostrHMS(new Date()));
		c.setModifytime(UtilTools.timeTostrHMS(new Date()));
		c.setUserid(userid);
		c.setCategory(course.getCategory());
		c.setDescrible(course.getDescrible());
		c.setSiteid(site.getId());
		c.setFeature(course.getFeature());
		c.setThumb(course.getThumb());
		c.setPrize(course.getPrize());
		c.setLevel(course.getLevel());
		c.setVideo(course.getVideo());
		courseDao.save(c);
		return Constants.SUCCESS_1;
	}

	public Course getCourseByid(int id) {
		return courseDao.getCourseByid(id);
	}

	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

	public List<Course> getCoursebykey(String key) {
		return courseDao.getCoursebykey(key);
	}

	public List<Course> getCourse(String level) {
		return courseDao.getCourse(level);
	}

	public List<Course> getCoursebyprize(String prize) {
		return courseDao.getCoursebyprize(prize);
	}

	public List<Course> getCourse(String level, String prize) {
		return courseDao.getCourse(level, prize);
	}

	public User getCourseuser(int userid) {
		return userDao.get(userid);
	}

	public Page getPageCourseByuserid(int pageNo, int pageSize, int userid) {
		Page page = courseDao.getPageCourseByuserid(pageNo, pageSize, userid);
		return courseDao.getPageCourseByuserid(pageNo, pageSize, userid);
	}

	public Page getAllCourse(int pageNo, int pageSize) {

		return courseDao.getAllCourse(pageNo, pageSize);
	}

	public Page getListByprize(int pageNo, int pageSize, String prize) {

		return courseDao.getListByprize(pageNo, pageSize, prize);
	}

	public Page getListBylevel(int pageNo, int pageSize, String level) {

		return courseDao.getListBylevel(pageNo, pageSize, level);
	}

	public Page getCourseList(int pageNo, int pageSize, String level, String prize) {

		return courseDao.getCourseList(pageNo, pageSize, level, prize);
	}

	public void updateCourse(int id, String name, String describle, String category, String feature, String modifytime,
			String thumb, String prize, String level, String video, String website) {
		courseDao.updateCourse(id, name, describle, category, feature, modifytime, thumb, prize, level, video, website);
	}

	public void deleteCourse(int courseid) {

		Course c = courseDao.get(courseid);
		courseDao.delete(c);
		// Site site =siteDao.get(c.getSiteid());
		// siteDao.delete(site);

	}

	// List<Chapter> chapter = chapterDao.getCh(courseid);
	// for(Chapter ch:chapter){
	//
	// Chapter pc = ch.getChapter();
	// if (pc != null)
	// {
	// Set<Chapter> set = pc.getChildren();
	// set.remove(ch);
	// ch.setChapter(null);
	// }
	// // List<ChapterUnit> cunit=chapterUnitDao.getChapterUnit(courseid,
	// ch.getId());
	// // for(ChapterUnit cu:cunit){
	// //
	// // chapterUnitDao.delete(cu);
	// // unitDao.delete(cu.getUnit());
	// //
	// // }
	// // chapterDao.delete(ch);
	// }
	public String saveevaluation(int userid, int courseid, int score, User user) {
		List<Evaluation> eva = evaluationDao.getEvaluation(userid, courseid);
		String str = "";
		if (eva.size() == 0) {
			Evaluation e = new Evaluation();
			e.setUser(user);
			e.setCourseid(courseid);
			e.setScore(score);
			e.setEvatime(UtilTools.timeTostrHMS(new Date()));
			evaluationDao.save(e);
			str = "评分成功！";
			return str;
		} else {
			int i = 0;
			for (Evaluation evaluation : eva) {
				if (evaluation.getScore() != 0) {
					str = "你已评论过，不可重复评论！";
					return str;
				} else {
					i = i + 1;
					if (i == eva.size()) {
						Evaluation e = new Evaluation();
						e.setUser(user);
						e.setCourseid(courseid);
						e.setScore(score);
						e.setEvatime(UtilTools.timeTostrHMS(new Date()));
						evaluationDao.save(e);
						str = "评分成功！";
						return str;
					}
				}
			}
			return str;
		}
	}

	public List<Evaluation> getEvaluationbycid(int courseid) {
		return evaluationDao.getEvaluationbycid(courseid);
	}

	public List<Evaluation> getEvaluationComBycid(int courseid) {
		return evaluationDao.getEvaluationComBycid(courseid);
	}

	public String saveCourse(String name, String describle, String category, String thumb, int categoryid, String path,
			String pptpath, String zippath, String wordpath, int userid, int hdid) {

		Course c = courseDao.getCourse(userid, name);
		if (c != null) {
			return Constants.ERROR_11;
		}
		c = new Course();
		c.setName(name);
		c.setCreatetime(UtilTools.timeTostrHMS(new Date()));
		c.setDescrible(describle);

		c.setUserid(userid);
		c.setThumb(thumb);
		c.setCategory(category);
		courseDao.save(c);

		int cid = c.getId();
		// 默认创建第一章，第一节，所有内容放在第一节下面
		Chapter chapter = new Chapter();
		chapter.setName(Constants.CHAPTER_NAME1);
		chapter.setCid(cid);
		chapter.setChapter(null);
		chapterDao.save(chapter);

		Chapter sequence = new Chapter();
		sequence.setName(Constants.CHAPTER_NAME2);
		sequence.setCid(c.getId());
		sequence.setChapter(chapter);
		chapterDao.save(sequence);

		Unit unit = new Unit();
		unit.setPath(path);
		unit.setType(Constants.COURSE_VIDEO);
		unit.setName(Constants.COURSE_1);
		unitDao.save(unit);

		ChapterUnit cu = new ChapterUnit();
		cu.setCourseid(cid);
		cu.setChapterid(sequence.getId());
		cu.setUnit(unit);
		chapterUnitDao.save(cu);

		Unit unitppt = new Unit();
		unitppt.setPath(pptpath);
		unitppt.setType(Constants.COURSE_PPT);
		unitppt.setName(Constants.COURSE_2);
		unitDao.save(unitppt);

		ChapterUnit cuppt = new ChapterUnit();
		cuppt.setCourseid(cid);
		cuppt.setChapterid(sequence.getId());
		cuppt.setUnit(unitppt);
		chapterUnitDao.save(cuppt);

		Unit unitword = new Unit();
		unitword.setPath(pptpath);
		unitword.setType(Constants.COURSE_WORD);
		unitword.setName(Constants.COURSE_3);
		unitDao.save(unitword);

		ChapterUnit cuword = new ChapterUnit();
		cuword.setCourseid(cid);
		cuword.setChapterid(sequence.getId());
		cuword.setUnit(unitword);
		chapterUnitDao.save(cuword);

		Unit unitzip = new Unit();
		unitzip.setPath(pptpath);
		unitzip.setType(Constants.COURSE_ZIP);
		unitzip.setName(Constants.COURSE_4);
		unitDao.save(unitzip);

		ChapterUnit cuzip = new ChapterUnit();
		cuzip.setCourseid(cid);
		cuzip.setChapterid(sequence.getId());
		cuzip.setUnit(unitzip);
		chapterUnitDao.save(cuzip);

		CourseCategory cc = new CourseCategory();
		cc.setCategoryid(categoryid);
		cc.setCourseid(cid);
		courseCategoryDao.save(cc);

		HdCourse hc = new HdCourse();
		hc.setCourse(c);
		hc.setHd(hdDao.get(hdid));
		hdCourseDao.save(hc);

		return Constants.SUCCESS_1;
	}
}
