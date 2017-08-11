package com.skcj.HealingMuseum.Admin.dao;


/**
 *  날짜                 버전                        작성자
 *  
 *  2015.10.05           v.0.0.1					 최연석
 *  			 					
 *  
 *  
 *  
 */




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.skcj.HealingMuseum.common.TSData;

@Repository("Admin/DAO")
public class AdminDAO extends SqlSessionDaoSupport {

	private final int SUCCESS = 1;
	private final int INSERT_FAIL = 0;


	private AdminDAO() {
	}

	private static AdminDAO instance = new AdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}
	
	/**
	 * 2017.05.13 
	 * YS CHOI
	 * 여기부터 New 버전 새로작성
	 * 
	 */
	
	public void insertAdminImage(TSData param) throws Exception {
		getSqlSession().insert("admin.insertAdminImage", param);
	}
	
	public void updateAdminImage(TSData param) throws Exception {
		getSqlSession().update("admin.updateAdminImage", param);
	}
	
	public int getAdminImageListCnt(TSData param) throws Exception
	{
		return getSqlSession().selectOne("admin.selectAdminImageListCnt", param);
	}
	
	public List<TSData> getAdminImageList(TSData param) throws Exception
	{
		return getSqlSession().selectList("admin.selectAdminImageList", param);
	}
	
	public TSData getAdminImage(TSData param) throws Exception
	{
		return getSqlSession().selectOne("admin.selectAdminImage", param);
	}
	
	public void updateAdminImageSlideCnt(int inx) throws Exception {
		getSqlSession().update("admin.updateAdminImageSlideCnt", inx);
	}
}
