/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xd.cloud.dao.Page;
import com.xd.cloud.service.hd.HdCourseService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 参数作品标签
 * 
 * @since  V1.0
 * 
 */
public class HdCourseDirective implements TemplateDirectiveModel{
	
	@Autowired
	private HdCourseService hdCourseService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		//要修改，等评分之后这个地方获取要按规则来选取
		String hdid = params.get("hdid").toString();
		String pagesize = params.get("pagesize").toString();
		int id = Integer.parseInt(hdid);
		int size = Integer.parseInt(pagesize);
        Page page = hdCourseService.getAllByhdid(1, size, id);
        
        env.setVariable("hdCourseList", ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getResult()));
        body.render(env.getOut());
	}

}
