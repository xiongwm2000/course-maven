/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao;

import org.json.JSONObject;

import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.xd.cloud.common.util.Configuration;

/**
 * 对象存储
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class QcloudDao {

	String bucketName = Configuration.bucketName;

	public COSClient getConnection() {
		long appId = Long.parseLong(Configuration.appId);
		String secretId = Configuration.secretId;
		String secretKey = Configuration.secretKey;
		String region = Configuration.region;
		// 设置要操作的bucket

		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion(region);
		COSClient cosClient = new COSClient(clientConfig, cred);
		return cosClient;
	}

	public String uploadFile(String cosFilePath, String localFilePath) {
		COSClient cosClient = this.getConnection();
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosFilePath, localFilePath);
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		String retMessage = this.getRetMessage(uploadFileRet);
		System.out.println(retMessage);
		return retMessage;
	}

	public String downloadFile(String cosFilePath, String localPathDown) {
		COSClient cosClient = this.getConnection();
		GetFileLocalRequest getFileLocalRequest = new GetFileLocalRequest(bucketName, cosFilePath, localPathDown);
		getFileLocalRequest.setUseCDN(false);
		getFileLocalRequest.setReferer("*.myweb.cn");
		String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
		String retMessage = this.getRetMessage(getFileResult);
		System.out.println(retMessage);
		return retMessage;
	}

	public String getRetMessage(String uploadFileRet) {
		JSONObject jsonRet = new JSONObject(uploadFileRet);
		int code = Integer.parseInt(jsonRet.get("code").toString().trim());
		if (code == -1) {
			return "输入参数错误";
		} else if (code == -4018) {
			return "文件已经存在";
		} else if (code == -2) {
			return "网络错误";
		} else if (code == -3) {
			return "网连接cos时发生异常错误";
		}
		return uploadFileRet;
	}

}