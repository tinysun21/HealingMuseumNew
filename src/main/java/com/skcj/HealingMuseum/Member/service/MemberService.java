package com.skcj.HealingMuseum.Member.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skcj.HealingMuseum.Admin.service.AdminService;
import com.skcj.HealingMuseum.Member.dao.MemberDAO;
import com.skcj.HealingMuseum.common.TSData;

@Service("Member/Service")
public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Resource(name="Member/DAO")
	private MemberDAO dao;
	
	public int getMemberListCnt(TSData param) throws Exception
	{
		return this.dao.getMemberListCnt(param);
	}
	
	public List<TSData> getMemberList(TSData param) throws Exception
	{
		return this.dao.getMemberList(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void joinMember(TSData param) throws Exception {
		dao.joinMember(param);
	}
	
	public TSData getMember(TSData param) throws Exception
	{
		return this.dao.getMember(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateMemberLogin(TSData param) throws Exception {
		dao.updateMemberLogin(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateMember(TSData param) throws Exception {
		dao.updateMember(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void deleteMember(TSData param) throws Exception {
		dao.deleteMember(param);
	}
}
