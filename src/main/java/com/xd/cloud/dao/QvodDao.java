/*
 * Copyright (c) 2014, 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.dao;

import java.io.File;
import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Vod;
import com.qcloud.Utilities.SHA1;
import com.qcloud.Utilities.Json.JSONObject;
import com.xd.cloud.common.util.Configuration;
/**
 * 云点播
 * @author 云计算应用与开发项目组
 * @since V1.0
 */
public class QvodDao {
	
	public static void main(String[] args) {
		String msg = new QvodDao().getVideoInfo("9031868222988492325");
		System.out.println(msg);
	}
	
	/**
	 * 获取视频详细信息 
	 * 原画视频地址
	 * @return
	 */
	public String getVideoInfo(String fileId){
		QcloudApiModuleCenter module = getQcloudApi();
		String msg = "";
		if(!fileId.isEmpty()){
			try {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileId", fileId);
				String result_json = module.call("GetVideoInfo", params);
				System.out.println(result_json);
				JSONObject result = new JSONObject(result_json);
				int code = result.getInt("code");
				if(code == 0){
					JSONObject basicInfo = result.getJSONObject("basicInfo");
					// basicInfo视频源文件地址
					msg = basicInfo.getString("sourceVideoUrl");
					System.out.println(msg);
				}else{
					// 返回的错误信息
					System.out.println("error：" + result.getString("message"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("获取视频信息失败");
			}
		}
		return msg;
	}

	private QcloudApiModuleCenter getQcloudApi(){
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		String SecretID = Configuration.secretId;
		String SecretKey = Configuration.secretKey;
		String region = Configuration.region;

		config.put("SecretId", SecretID);
		config.put("SecretKey", SecretKey);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", region);
		return new QcloudApiModuleCenter(new Vod(), config);
	}
	
	public String getConnection(String path, String suffix, String name) {
		String fileId = "";
		QcloudApiModuleCenter module = getQcloudApi();
		try {
			System.out.println("starting");
			String fileName = path;
			long fileSize = new File(fileName).length();
			String fileSHA1 = SHA1.fileNameToSHA(fileName);

			int fixDataSize = 1024 * 1024 * 5; // 每次上传字节数，可自定义[512*1024 ~
												// 5*1024*1024]
			int firstDataSize = 1024 * 512; // 最小片字节数（默认不变）
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;

			String result = null;

			while (remainderSize > 0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileSha", fileSHA1);
				params.put("fileType", suffix);
				params.put("fileName", name);
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", fileName);
				params.put("isTranscode", 1);

				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = new JSONObject(result);
				code = json_result.getInt("code");
				if (code == -3002) { // 服务器异常返回，需要重试上传(offset=0, dataSize=512K)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return "error";
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					System.out.println(fileId);
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return fileId;
	}
}