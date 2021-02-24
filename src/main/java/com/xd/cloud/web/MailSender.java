/*
 * Copyright (c) 2014, 2015, dhl and/or its affiliates. All rights reserved.
 * dhl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.service.core.UserService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class MailSender {
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
    private SimpleMailMessage simpleMailMessage;
	@Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
    private TaskExecutor taskExecutor;
	@Autowired
    private UserService userService;
    /**
     * 构建邮件内容，发送邮件。
     * @param user  用户
     * @param url   链接
     */
    public void send(String username,String email) {
    	String host = UtilTools.getConfig().getProperty("host");
		Map<String, String> map = new HashMap<String, String>(1);
		String name = null;
		try {
			name = URLEncoder.encode(username,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
		}
		String uuid = UUID.randomUUID().toString();
		User user = userService.getUserByname(username);
		user.setToken(uuid);
		userService.update(user);
//		String name = EncryptUtil.encrypt(username);
		String url = host+"tool/reset/"+name+"/"+uuid;
		map.put("url", url);
		map.put("username", username);
		
		String to = email;
		String text = "";
        try {
//            从FreeMarker模板生成邮件内容
        	Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        this.taskExecutor.execute(new SendMailThread(to,null,text));
    }
    
    public void send(String email) {
    	String host = UtilTools.getConfig().getProperty("host");
		Map<String, String> map = new HashMap<String, String>(1);
		String name = null;
		try {
			name = URLEncoder.encode(email,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
		}
		String uuid = UUID.randomUUID().toString();
		User user = userService.getUserBymail(email);
		user.setPwdtoken(uuid);
		userService.update(user);
//		String name = EncryptUtil.encrypt(username);
		String url = host+"tool/resetpwd/"+name+"/"+uuid;
		map.put("url", url);
		
		String to = email;
		String text = "";
        try {
//            从FreeMarker模板生成邮件内容
        	Template template = freeMarkerConfigurer.getConfiguration().getTemplate("pwdmail.html");
			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        this.taskExecutor.execute(new SendMailThread(to,null,text));
    }
    
//  内部线程类，利用线程池异步发邮件。
  private class SendMailThread implements Runnable {
      private String to;
      private String subject;
      private String content;
      private SendMailThread(String to, String subject, String content) {
          super();
          this.to = to;
          this.subject = subject;
          this.content = content;
      }
      @Override
      public void run() {
          sendMail(to, subject, content);
      }
  }
	
  /**
   * 发送邮件
   * @param to        收件人邮箱
   * @param subject   邮件主题
   * @param content   邮件内容
   */
  public void sendMail(String to, String subject, String content) {
      try {
          MimeMessage message = javaMailSender.createMimeMessage();
          MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "GBK");//UTF-8
          messageHelper.setFrom(simpleMailMessage.getFrom());
          if (subject != null) {
              messageHelper.setSubject(subject);
          } else {
              messageHelper.setSubject(simpleMailMessage.getSubject());
          }
          messageHelper.setTo(to);
          messageHelper.setText(content, true);
         javaMailSender.send(message);
      } catch (MessagingException e) {
          e.printStackTrace();
      }
  }

//	public void sendmail(String username,String email) {
//
//		String host = UtilTools.getConfig().getProperty("host");
//		Map<String, String> map = new HashMap<String, String>(1);
//		try {
//			username = URLEncoder.encode(username,"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			return new MessageBean(false, Constants.ERROR_5);
//		}
//		String url = host+"tool/reset/"+username;
//		map.put("url", url);
//		map.put("username", username);
//		String text = "";
//		try {
//			// 从FreeMarker模板生成邮件内容
//			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
//			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
//		} catch (Exception e) {
//			return new MessageBean(false, Constants.ERROR_5);
//		}
//
//		MimeMessage mailMessage = mailSender.createMimeMessage();
//		try {
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "GBK");
//			messageHelper.setTo(email);
//			// messageHelper.setTo("dhl_starcraft@aliyun.com");
//			messageHelper.setFrom(mailSender.getUsername());
//			messageHelper.setSubject("凤凰微课大赛帐户激活");
//			// true 表示启动HTML格式的邮件
//			messageHelper.setText(text, true);
//			messageHelper.setSentDate(new Date());
//			mailSender.send(mailMessage);
//		} catch (Exception e) {
//			return new MessageBean(false, Constants.ERROR_5);
//		}
//
//		return new MessageBean(true, Constants.SUCCESS_4);
//	}

}
