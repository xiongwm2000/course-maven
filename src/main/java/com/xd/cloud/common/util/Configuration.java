package com.xd.cloud.common.util;

public class Configuration {
	/*
	 * 云平台连接地址
	 */
	public static final String url = UtilTools.getConfig().getProperty("url");
	
	/*
	 * 云平台用户名
	 */
	public static final String username = UtilTools.getConfig().getProperty("username");

	/*
	 *云平台密码
	 */
	public static final String password = UtilTools.getConfig().getProperty("password");

	/*
	 *云平台ternantName
	 */
	public static final String tenantName = UtilTools.getConfig().getProperty("tenantName");
	
	
	/*
	 *云平台ternantName
	 */
	public static final String gateone = UtilTools.getConfig().getProperty("gateone");
	
	/*
	 * 连接腾讯云存储参数
	 */
	public static final String bucketName = UtilTools.getConfig().getProperty("bucketName");
	public static final String appId = UtilTools.getConfig().getProperty("appId");
	public static final String secretId = UtilTools.getConfig().getProperty("secretId");
	public static final String secretKey = UtilTools.getConfig().getProperty("secretKey");
	public static final String region = UtilTools.getConfig().getProperty("region");
	public static final String cosImgFilePath = UtilTools.getConfig().getProperty("cosImgFilePath");
	public static final boolean isOpenCos = UtilTools.getConfig().getProperty("isOpenCos").trim().equals("true");
	public static final boolean isOpenVod = UtilTools.getConfig().getProperty("isOpenVod").trim().equals("true");
	
	/**
	 * 数据库连接地址
	 * @author WY
	 *
	 */
	public interface DBConnection {
//		public static final String driver = DBParams.getConfig().getProperty("database.driverClassName");
//		public static final String url = DBParams.getConfig().getProperty("database.url");
//		public static final String username = DBParams.getConfig().getProperty("database.username");
//		public static final String password = DBParams.getConfig().getProperty("database.password");
		
	}

}
