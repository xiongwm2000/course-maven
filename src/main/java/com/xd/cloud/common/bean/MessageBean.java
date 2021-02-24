/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.common.bean;

import java.io.Serializable;

/**
 * 所有与前台通信的消息bean
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class MessageBean implements Serializable{

	//是否成功
	private boolean success;
	//返回消息
	private Object msg;
	//其他对象
	private Object other;
	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}

	public MessageBean(boolean success)
	{
		this.success = success;
	}
	
	public MessageBean(boolean success,Object msg,Object other)
	{
		this.success = success;
		this.msg = msg;
		this.other = other;
	}
	
	public MessageBean(boolean success,Object msg)
	{
		this.success = success;
		this.msg = msg;
	}
	public MessageBean(Object other)
	{
		this.other = other;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
}
