/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.dao.course.EvaluationDao;
import com.xd.cloud.entity.course.Evaluation;

/**
 * 章节service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class EvaluationService {

	@Autowired
	private EvaluationDao evaluationDao;
	
	public void save(Evaluation evaluation)
	{
		evaluationDao.save(evaluation);
	}
	public List<Evaluation> getEvaluationBycidLimited(int courseid){
		return evaluationDao.getEvaluationBycidLimited(courseid);
	}
	
}
