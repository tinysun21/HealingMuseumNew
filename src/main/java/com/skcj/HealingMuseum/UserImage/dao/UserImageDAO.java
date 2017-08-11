package com.skcj.HealingMuseum.UserImage.dao;


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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.skcj.HealingMuseum.common.TSData;

@Repository("UserImage/DAO")
public class UserImageDAO extends SqlSessionDaoSupport {

	private final int SUCCESS = 1;
	private final int INSERT_FAIL = 0;


	private UserImageDAO() {
	}

	private static UserImageDAO instance = new UserImageDAO();

	public static UserImageDAO getInstance() {
		return instance;
	}

	/**
	 * 2017.05.13 
	 * YS CHOI
	 * 여기부터 New 버전 새로작성
	 * 
	 */
    
	public int getUserImageListCnt(TSData param) throws Exception
	{
		return getSqlSession().selectOne("UserImage.selectUserImageListCnt", param);
	}
	
	public List<TSData> getUserImageList(TSData param) throws Exception
	{
		return getSqlSession().selectList("UserImage.selectUserImageList", param);
	}
	
	public void insertUserImage(TSData param) throws Exception {
		getSqlSession().insert("UserImage.insertUserImage", param);
	}
	
	public TSData getUserImage(TSData param) throws Exception
	{
		return getSqlSession().selectOne("UserImage.selectUserImage", param);
	}
	
	public void updateUserImage(TSData param) throws Exception {
		getSqlSession().update("UserImage.updateUserImage", param);
	}
	
	public void deleteUserImage(TSData param) throws Exception {
		getSqlSession().delete("UserImage.deleteUserImage", param);
	}
	
	public void updateUserImageSlideCnt(int inx) throws Exception {
		getSqlSession().update("UserImage.updateUserImageSlideCnt", inx);
	}
}
