package com.skcj.HealingMuseum.UserImage.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skcj.HealingMuseum.Admin.service.AdminService;
import com.skcj.HealingMuseum.Member.dao.MemberDAO;
import com.skcj.HealingMuseum.UserImage.dao.UserImageDAO;
import com.skcj.HealingMuseum.common.TSData;

@Service("UserImage/Service")
public class UserImageService {
	private static final Logger logger = LoggerFactory.getLogger(UserImageService.class);
	
	@Resource(name="UserImage/DAO")
	private UserImageDAO dao;
	
	public int getUserImageListCnt(TSData param) throws Exception
	{
		return this.dao.getUserImageListCnt(param);
	}
	
	public List<TSData> getUserImageList(TSData param) throws Exception
	{
		return this.dao.getUserImageList(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void insertUserImage(TSData param) throws Exception {
		dao.insertUserImage(param);
	}
	
	public TSData getUserImage(TSData param) throws Exception
	{
		return this.dao.getUserImage(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateUserImage(TSData param) throws Exception {
		dao.updateUserImage(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void deleteUserImage(TSData param) throws Exception {
		dao.deleteUserImage(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateUserImageSlideCnt(int inx) throws Exception {
		dao.updateUserImageSlideCnt(inx);
	}
}
