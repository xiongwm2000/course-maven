/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common;

/**
 * 整个应用通用常量类
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class Constants {
	// 分页大小
	public static final int PAGE_SIZE = 10;
	//参数学校的分页大小
	public static final int PAGE_SCHOOLSIZE = 24;
	//微信学校列表
	public static final int PAGE_WXSCHOOLSIZE = 10;
	//课程资源的分页大小
	public static final int PAGE_RESSIZE = 12;
	// 角色key值，不要修改这些值
	public static final String ROLE_ADMIN = "admin";
	public static final String ORG_ADMIN = "orgadmin";
	public static final String HD_ADMIN = "hdadmin";
	public static final String TEACHER = "teacher";
	public static final String STUDENT = "student";
	public static final String EXPERT = "expert";
//	//后台左侧菜单------跟前端菜单对应
//	public static final String MENU_ROLE = "role";
//	public static final String MENU_ORG = "org";
//	public static final String MENU_USER = "user";
	
	public static final String STATUS_1 = "通过";
	public static final String STATUS_2 = "不通过";
	
	public static final String SUCCESS_1 = "保存成功";
	public static final String SUCCESS_2 = "编辑成功";
	public static final String SUCCESS_3 = "删除成功";
	public static final String SUCCESS_4 = "发送成功";
	public static final String SUCCESS_5 = "注册成功";
	public static final String SUCCESS_6 = "入驻成功";
	public static final String SUCCESS_7 = "重置成功";
	//错误信息
	public static final String ERROR_1 = "角色已经存在";
	public static final String ERROR_2 = "机构已经存在";
	public static final String ERROR_3 = "用户已经存在";
	public static final String ERROR_4 = "没有找到该账号";
	public static final String ERROR_5 = "发送邮件失败";
	public static final String ERROR_6 = "邮箱已经存在";
	public static final String ERROR_7 = "登陆用户不对";
	public static final String ERROR_8 = "登陆密码不对";
	public static final String ERROR_9 = "身份证号已经存在";
	public static final String ERROR_10 = "发送失败";
	public static final String ERROR_11 = "课程已经存在";
	//后台管理员登陆，后台缓存
	public static final String HD = "hdadmin";
	public static final String ORG = "org";
	//缓存用户
	public static final String USER = "user";
	//审批状态
	public static final int SP_0 = 0;//待审批
	public static final int SP_1 = 1;//审批不通过
	public static final int SP_2 = 2;//审批通过
	
	public static final int IDENTITY_0 = 0;//待审批
	public static final int IDENTITY_1 = 1;//审批不通过
	
	//默认模板路径
	public static final String TEMPLATE_HD_CHANNEL = "hd/newslist";
	public static final String TEMPLATE_HD_CONTENT = "hd/news";
	public static final String TEMPLATE_HD_INDEX = "hd/index";
	
	//默认上传作品，生成的课程目录结构
	public static final String CHAPTER_NAME1 = "第一章";
	public static final String CHAPTER_NAME2 = "第一节";
	
	//課程內容類型
	public static final int COURSE_VIDEO = 0;//視頻
	public static final int COURSE_PPT = 1;
	public static final int COURSE_WORD = 2;
	public static final int COURSE_ZIP = 3;
	public static final int COURSE_HTML = 4;
	//微课上传的4种类型
	public static final String COURSE_1 = "教学视频";
	public static final String COURSE_2 = "教学课件";
	public static final String COURSE_3 = "教学设计";
	public static final String COURSE_4 = "辅助材料";
	
	public static final String TEPRIZE = "特等奖";
	public static final String ONEPRIZE = "一等奖";
}
