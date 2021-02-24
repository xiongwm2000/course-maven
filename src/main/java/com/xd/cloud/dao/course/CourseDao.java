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
import com.xd.cloud.entity.course.Course;

/**
 * 课程Dao
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Repository
public class CourseDao extends BaseDao<Course> {

	public Course getCourse(int userid, String name) {
		String hql = "from Course where userid = ? and name = ?";
		List<Course> list = find(hql, new Object[] { userid, name });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Course> getCoursebykey(String key) {
		String hql = "from Course where name like ?";
		return find(hql, new Object[] { "%" + key + "%" });
	}

	public List<Course> getCourseByuserid(int userid) {
		String hql = "from Course where userid = ?";
		return find(hql, new Object[] { userid });
	}

	public List<Course> getAllCourse() {
		String hql = "from Course";
		return find(hql);
	}

	public List<Course> getCourse(String level) {
		String hql = "from Course where level=? order by createtime desc";
		return find(hql, new Object[] { level });
	}

	public List<Course> getCourse(String level, String prize) {
		String hql = "from Course where level=? and prize=?";
		return find(hql, new Object[] { level, prize });
	}

	/**
	 * 主页
	 * 
	 * @param prize
	 * @return
	 */
	public List<Course> getCoursebyprize(String prize) {
		String hql = "from Course where prize=? order by modifytime desc";
		return find(hql, new Object[] { prize });
	}

	public Page getPageCourseByuserid(int pageNo, int pageSize, int userid) {
		String hql = "from Course where userid = ?";
		return pagedQuery(hql, pageNo, pageSize, userid);
	}

	/**
	 * 分页// http://.../course/cinfo
	 */
	public Page getAllCourse(int pageNo, int pageSize) {
		String hql = "from Course order by modifytime desc";
		return pagedQuery(hql, pageNo, pageSize);
	}

	public Page getListBylevel(int pageNo, int pageSize, String level) {
		String hql = "from Course where level=? order by createtime desc";
		return pagedQuery(hql, pageNo, pageSize, level);
	}

	// 根据标签筛选课程
	public Page getListByprize(int pageNo, int pageSize, String prize) {
		String hql = "from Course where prize=? order by modifytime desc";
		return pagedQuery(hql, pageNo, pageSize, prize);
	}

	public Page getCourseList(int pageNo, int pageSize, String level, String prize) {
		String hql = "from Course where level=? and prize=? order by modifytime desc";
		return pagedQuery(hql, pageNo, pageSize, level, prize);
	}

	/**
	 * 根据id查询课程
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourseByid(int id) {
		String hql = "from Course where id = ?";
		return get(hql, new Object[] { id });
	}

	/**
	 * 修改课程
	 * 
	 * @param id
	 * @param name
	 * @param describle
	 * @param category
	 * @param feature
	 * @param modifytime
	 */
	public void updateCourse(int id, String name, String describle, String category, String feature, String modifytime,
			String thumb, String prize, String level, String video, String website) {
		String hql = "update Course set name=?,describle=?,category=?,feature=?,modifytime=?,thumb=?,prize=?,level=?,video=?,website=? where id=?";
		executeHql(hql, new Object[] { name, describle, category, feature, modifytime, thumb, prize, level, video,
				website, id });
	}

}
