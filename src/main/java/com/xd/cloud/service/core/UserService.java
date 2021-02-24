/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.cms.SiteDao;
import com.xd.cloud.dao.core.OrgDao;
import com.xd.cloud.dao.core.OrgUserDao;
import com.xd.cloud.dao.core.RoleDao;
import com.xd.cloud.dao.core.UserDao;
import com.xd.cloud.dao.core.UserRoleDao;
import com.xd.cloud.entity.cms.Site;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.core.OrgUser;
import com.xd.cloud.entity.core.Role;
import com.xd.cloud.entity.core.User;
import com.xd.cloud.entity.core.UserRole;

/**
 * 用户service
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private OrgUserDao orgUserDao;
	@Autowired
	private SiteDao siteDao;

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return 返回id,username，realname，password
	 */
	public Object[] findUser(String username) {
		return userDao.findUser(username);
	}

	/**
	 * 根据用户名查询用户角色信息
	 * 
	 * @param username
	 * @return 角色rolekey的列表
	 */
	public List<Object> findUserRole(String username) {
		return userRoleDao.findUserRole(username);
	}

	public List<UserRole> getUserRole(String username) {
		User user = userDao.getUserByname(username);
		return userRoleDao.getByuserid(user.getId());
	}

	public User get(int id) {
		return userDao.get(id);
	}

	public void remove(int id) {
		User c = userDao.get(id);
		if (c != null)
			userDao.delete(c);
	}

	public void update(User c) {
		userDao.update(c);
	}

	public String update(String job, String realname, String describle, String headimg, int sex, String code,
			String codeimg, int userid, String orgid) {
		User cuser = userDao.getUserBycode(code);
		if (cuser != null && cuser.getId() != userid) {
			return Constants.ERROR_9;
		}
		userDao.update(job, realname, describle, headimg, sex, code, codeimg, userid);

		if (orgid != null && !"".equals(orgid)) {
			OrgUser oo = orgUserDao.getByuserid(userid);
			if (oo == null) {
				OrgUser ou = new OrgUser();
				ou.setUser(userDao.get(userid));
				ou.setOrg(orgDao.get(Integer.parseInt(orgid)));
				ou.setStatus(Constants.SP_0);
				orgUserDao.save(ou);
			} else {
				oo.setOrg(orgDao.get(Integer.parseInt(orgid)));
				orgUserDao.update(oo);
			}
		}
		return Constants.SUCCESS_2;
	}

	public String saveOrgAdmin(String email, String category, String name, String describle, String logoimg,
			String xcimg, String zzimg, String fmimg, String address, int userid) {
		Org org = orgDao.getOrgByname(name);
		if (org != null) {
			return Constants.ERROR_2;
		}
		org = new Org();
		org.setName(name);
		org.setDescrible(describle);
		org.setLogoimg(logoimg);
		org.setXcimg(xcimg);
		org.setZzimg(zzimg);
		org.setFmimg(fmimg);
		org.setAddress(address);
		org.setUserid(userid);
		org.setStatus(Constants.SP_2);
		org.setCategory(category);
		org.setEmail(email);

		Site site = new Site();
		site.setName(name);
		siteDao.save(site);
		// 站点默认新建栏目

		org.setSiteid(site.getId());
		orgDao.save(org);

		OrgUser orgUser = new OrgUser();
		orgUser.setOrg(org);
		orgUser.setUser(userDao.get(userid));
		orgUser.setStatus(Constants.SP_2);
		orgUserDao.save(orgUser);

		UserRole ur = new UserRole();
		ur.setUserid(userid);
		Role role = roleDao.getRoleBykey(Constants.ORG_ADMIN);
		ur.setRole(role);
		ur.setStatus(Constants.SP_2);
		userRoleDao.save(ur);
		return Constants.SUCCESS_6;
	}

	public String saveHdT(User c) {
		User r = userDao.getUserByname(c.getUsername());
		if (r == null) {
			User r2 = userDao.getUserBymail(c.getEmail());
			if (r2 == null) {
				String pwd = UtilTools.md5Digest(c.getPassword()).toLowerCase();
				c.setPassword(pwd);
				userDao.save(c);

				UserRole ur = new UserRole();
				ur.setUserid(c.getId());
				Role role = roleDao.getRoleBykey(Constants.TEACHER);
				ur.setRole(role);
				ur.setStatus(Constants.SP_2);
				userRoleDao.save(ur);
				return Constants.SUCCESS_5;
			} else {
				return Constants.ERROR_6;
			}
		} else {
			return Constants.ERROR_3;
		}
	}

	public boolean save(User c) {
		User r = userDao.getUserByname(c.getUsername());
		if (r == null) {
			userDao.save(c);
			return true;
		}
		return false;
	}

	public User getUserByname(String username) {
		return userDao.getUserByname(username);
	}

	public User getUserBymail(String email) {
		return userDao.getUserBymail(email);
	}

	public User getUserByToken(String username, String token) {
		return userDao.getUserByToken(username, token);
	}

	public User getUserByPwdtoken(String email, String token) {
		return userDao.getUserByPwdtoken(email, token);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public Page getAllUser(int pageNo, int pageSize) {
		return userDao.getAllUser(pageNo, pageSize);
	}
}
