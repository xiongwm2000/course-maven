/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.service.hd;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cloud.common.Constants;
import com.xd.cloud.common.util.UtilTools;
import com.xd.cloud.dao.Page;
import com.xd.cloud.dao.core.OrgDao;
import com.xd.cloud.dao.hd.HdDao;
import com.xd.cloud.dao.hd.HdOrgDao;
import com.xd.cloud.entity.core.Org;
import com.xd.cloud.entity.hd.Hd;
import com.xd.cloud.entity.hd.HdOrg;

/**
 * 参加活动的机构service
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Service
public class HdOrgService {

	@Autowired
	private HdOrgDao hdOrgDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private HdDao hdDao;
	
	public void save(HdOrg c)
	{
		hdOrgDao.save(c);
	}
	
	public HdOrg get(int id)
	{
		return hdOrgDao.get(id);
	}
	
	public void update(HdOrg c)
	{
		hdOrgDao.update(c);
	}
	
	public void update(int id,int status,String reply)
	{
		hdOrgDao.update(id, status, reply);
	}
	
	public void remove(int id)
	{
		HdOrg c = hdOrgDao.get(id);
		if (c != null)
			hdOrgDao.delete(c);
	}
	
	public String orgSign(int orgid,String email,String address,String attach,int hdid)
	{
		HdOrg ho = hdOrgDao.getHO(orgid, hdid);
		if (ho != null)
		{
			return Constants.ERROR_2;
		}
		Org org = orgDao.get(orgid);
		org.setEmail(email);
		org.setAddress(address);
		orgDao.update(org);
		
		ho = new HdOrg();
		Hd hd = hdDao.get(hdid);
		ho.setHd(hd);
		ho.setOrg(org);
		ho.setAttach(attach);
		ho.setStatus(Constants.SP_0);
		ho.setSigntime(UtilTools.timeTostrHMS(new Date()));
		
		hdOrgDao.save(ho);
		return Constants.SUCCESS_1;
	}
	
	public List<HdOrg> getAll()
	{
		return hdOrgDao.getAll();
	}
	
	public Page getAllByhdid(int pageNo,int pageSize,int hdid)
	{
		return hdOrgDao.getAllByhdid(pageNo, pageSize, hdid);
	}
	public Page getOrgsByhdid(int pageNo,int pageSize,int hdid){
		return hdOrgDao.getOrgsByhdid(pageNo, pageSize, hdid);
	}
	public List<HdOrg> getByorgid(int orgid)
	{
		return hdOrgDao.getByorgid(orgid);
	}
	
	public List getChartData()
	{
		return hdOrgDao.getChartData();
	}
}
