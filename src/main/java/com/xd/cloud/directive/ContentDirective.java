/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xd.cloud.entity.cms.Content;
import com.xd.cloud.service.cms.ContentService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 文章内容标签
 * 
 * @since  V1.0
 * 
 */
public class ContentDirective implements TemplateDirectiveModel{
	
	@Autowired
	private ContentService contentService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String cid = params.get("cid").toString();
		int id = Integer.parseInt(cid);
        Content con = contentService.get(id);
 
        env.setVariable("con", ObjectWrapper.DEFAULT_WRAPPER.wrap(con));
        body.render(env.getOut());
	}

}
