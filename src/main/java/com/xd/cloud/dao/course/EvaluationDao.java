/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao.course;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xd.cloud.dao.BaseDao;


import com.xd.cloud.entity.course.Evaluation;


/**
 * 课程分类关联Dao
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Repository
public class EvaluationDao extends BaseDao<Evaluation> {
	
	/**
	 * 
	 * 获取评分
	 * @return
	 */
	public List<Evaluation> getEvaluation(int userid,int courseid )
	{
		String hql = "from Evaluation where userid = ? and courseid = ?";
		return find(hql,new Object[]{userid,courseid});
	}
	
	public List<Evaluation> getEvaluationbycid(int courseid)
	{
		String hql = "from Evaluation where courseid = ? and score!=0";
		return find(hql,new Object[]{courseid});
	}
	public List<Evaluation> getEvaluationComBycid(int courseid)
	{
		String hql = "from Evaluation where courseid = ? and ecommnent is not null";
		return find(hql,new Object[]{courseid});
	}
	public List<Evaluation> getEvaluationBycidLimited(int courseid)
	{
		String hql = "from Evaluation where courseid = ? order by evatime desc";
		Object[] param=new Object[]{courseid};
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
				q.setFirstResult(0);
				q.setMaxResults(19);
			}
		}
		return q.list();
	}
}
