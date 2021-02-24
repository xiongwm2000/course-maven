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

import com.xd.cloud.entity.hd.HdUser;
import com.xd.cloud.service.hd.HdUserService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 参数用户标签
 * 
 * @since  V1.0
 * 
 */
public class HdUserDirective implements TemplateDirectiveModel{
	
	@Autowired
	private HdUserService hdUserService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String identity = params.get("identity").toString();
		int ide = Integer.parseInt(identity);
		Object ss = params.get("pagesize");
		String size = null;
		if (ss != null)
		{
			size = params.get("pagesize").toString();
		}
		List<HdUser> list = hdUserService.getAllUser(ide,size);
        
        env.setVariable("userList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
        body.render(env.getOut());
	}

}
