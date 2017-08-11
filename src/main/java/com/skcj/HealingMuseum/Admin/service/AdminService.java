package com.skcj.HealingMuseum.Admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skcj.HealingMuseum.Admin.dao.AdminDAO;
import com.skcj.HealingMuseum.common.TSData;

@Service("Admin/Service")
public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Resource(name="Admin/DAO")
	private AdminDAO dao;
	
	@Transactional(rollbackFor = {Exception.class})
	public void insertAdminImage(TSData param) throws Exception {
		dao.insertAdminImage(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateAdminImage(TSData param) throws Exception {
		dao.updateAdminImage(param);
	}
	
	public int getAdminImageListCnt(TSData param) throws Exception
	{
		return this.dao.getAdminImageListCnt(param);
	}
	
	public List<TSData> getAdminImageList(TSData param) throws Exception
	{
		return this.dao.getAdminImageList(param);
	}

	public TSData getAdminImage(TSData param) throws Exception
	{
		return this.dao.getAdminImage(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateAdminImageSlideCnt(int inx) throws Exception {
		dao.updateAdminImageSlideCnt(inx);
	}
	
}
