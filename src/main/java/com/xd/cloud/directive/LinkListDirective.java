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

import com.xd.cloud.entity.cms.Link;
import com.xd.cloud.service.cms.LinkService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 链接标签
 * 
 * @since  V1.0
 * 
 */
public class LinkListDirective implements TemplateDirectiveModel{
	
	@Autowired
	private LinkService linkService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
        List<Link> clist = linkService.getSortList();
 
        env.setVariable("linkList", ObjectWrapper.DEFAULT_WRAPPER.wrap(clist));
        body.render(env.getOut());
	}

}
