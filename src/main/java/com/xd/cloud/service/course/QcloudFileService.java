/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.course;

import org.springframework.stereotype.Service;

import com.xd.cloud.dao.QcloudDao;
import com.xd.cloud.dao.QvodDao;

/**
 * 课程service
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Service
public class QcloudFileService {
	/**
	 * 图片
	 * @param cosFilePath
	 * @param localFilePath
	 * @return
	 */
	public String uploadImgQcloud(String cosFilePath,String localFilePath){
		QcloudDao qcloudDao = new QcloudDao();
		String qcloudMessage = qcloudDao.uploadFile(cosFilePath,localFilePath);
		return qcloudMessage;
	}

	/**
	 * 上传视频
	 * @param localFilePath
	 * @param suffix
	 * @param name
	 * @return
	 */
	public String uploadVideoQcloud(String localFilePath,String suffix, String name){
		QvodDao qvodDao = new QvodDao();
		return qvodDao.getConnection(localFilePath, suffix, name);
	}
	
	/**
	 * 获取视频信息
	 * @param fileId
	 * @return
	 */
	public String getVideoInfo(String fileId){
		QvodDao qvodDao = new QvodDao();
		return qvodDao.getVideoInfo(fileId);
	}
}
