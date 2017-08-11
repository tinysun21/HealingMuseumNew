package com.skcj.HealingMuseum.Member.dao;


/**
 *  날짜                 버전                       작성자
 *  
 *  2015.10.03           v.0.0.1					김현종
 *  2015.10.06			 v.0.0.2					최연석				
 *  
 *  
 *  
 */




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.skcj.HealingMuseum.common.TSData;

@Repository("Member/DAO")
public class MemberDAO extends SqlSessionDaoSupport {

	private final int SUCCESS = 1;
	private final int JOIN_FAIL = 0;

	private final int ID_BE = 1;
	private final int ID_NOT_BE = -1;

	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	/**
	 * 2017.05.13 
	 * YS CHOI
	 * 여기부터 New 버전 새로작성
	 * 
	 */
    
	public int getMemberListCnt(TSData param) throws Exception
	{
		return getSqlSession().selectOne("member.selectMemberListCnt", param);
	}
	
	public List<TSData> getMemberList(TSData param) throws Exception
	{
		return getSqlSession().selectList("member.selectMemberList", param);
	}
	
	public void joinMember(TSData param) throws Exception {
		getSqlSession().insert("member.insertMember", param);
	}
	
	public TSData getMember(TSData param) throws Exception
	{
		return getSqlSession().selectOne("member.selectMember", param);
	}
	
	public void updateMemberLogin(TSData param) throws Exception {
		getSqlSession().update("member.updateMemberLogin", param);
	}
	
	public void updateMember(TSData param) throws Exception {
		getSqlSession().update("member.updateMember", param);
	}
	
	public void deleteMember(TSData param) throws Exception {
		getSqlSession().delete("member.deleteMember", param);
	}
}
