/*
 * Copyright (c) 2014, 2015, dhl and/or its affiliates. All rights reserved.
 * dhl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class UtilTools {

	public static String COFING_FILE = "/xd.properties";
	public static Properties p;

	public static Properties getConfig() {
		if (p == null) {
			p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(COFING_FILE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * 默认是utf-8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String converStr(String str) {
		return converStr(str, "UTF-8");
	}

	/**
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String converStr(String str, String encode) {
		if (str == null || str.equals("null")) {
			return "";
		}
		try {
			byte[] tmpbyte = str.getBytes("ISO8859_1");
			if (encode != null) {
				// 如果指定编码方式
				str = new String(tmpbyte, encode);
			} else {
				// 用系统默认的编码
				str = new String(tmpbyte);
			}
			return str;
		} catch (Exception e) {
		}
		return str;
	}
	
	public static String timeTostrHMS(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String timeTostr(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}
	public static int beforeday(String myString)
	{
		String nowdate = timeTostr(new Date());
//		String myString = "2015-01-16";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		Date now = null;
		try {
			d = sdf.parse(myString);
			now = sdf.parse(nowdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.compareTo(now);
	}
	
	public static String replaceStr(String str) {
		str = str.replaceAll("%2F", "/");
		return str;
	}

	public static String md5Digest(String src){    
        // 定义数字签名方法, 可用：MD5, SHA-1    
		try
		{
        MessageDigest md = MessageDigest.getInstance("MD5");    
        byte[] b = md.digest(src.getBytes("utf-8"));    
              
        return byte2HexStr(b);    
		}catch(Exception e)
		{
			return "";
		}
   }  
     
 
    private static String byte2HexStr(byte[] b) {    
        StringBuilder sb = new StringBuilder();    
        for (int i = 0; i < b.length; i++) {    
            String s = Integer.toHexString(b[i] & 0xFF);    
            if (s.length() == 1) {    
                 sb.append("0");    
            }    
                    
            sb.append(s.toUpperCase());    
         }    
                
         return sb.toString();    
    }   
}
