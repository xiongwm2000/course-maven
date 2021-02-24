/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xd.cloud.entity.cms.Channel;
import com.xd.cloud.entity.course.Course;
import com.xd.cloud.service.cms.ChannelService;
import com.xd.cloud.service.course.CourseService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模块标签
 * 
 * @since  V1.0
 * 
 */
public class ChannelListDirective implements TemplateDirectiveModel{
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private CourseService courseService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String cid = params.get("cid").toString();
		int id = Integer.parseInt(cid);
		Course course = courseService.getCourseByid(id);
		List<Channel> channels = channelService.getChannelBySiteID(course.getSiteid());
		
//        List<Channel> clist = channelService.getSortList();
 
        env.setVariable("channels", ObjectWrapper.DEFAULT_WRAPPER.wrap(channels));
        body.render(env.getOut());
	}

}
