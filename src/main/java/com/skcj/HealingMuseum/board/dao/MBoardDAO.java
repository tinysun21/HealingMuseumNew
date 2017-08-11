package com.skcj.HealingMuseum.board.dao;


/**
 *  날짜                 버전                        작성자
 *  
 *  2015.10.05           v.0.0.1					 최연석
 *  2017.05.20			   v.2.0.1   					최연석
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
import org.springframework.ui.Model;

import com.skcj.HealingMuseum.common.TSData;

@Repository("Board/DAO")
public class MBoardDAO extends SqlSessionDaoSupport {

	private final int SUCCESS = 1;
	private final int INSERT_FAIL = 0;


	public MBoardDAO() {
	}

	private static MBoardDAO instance = new MBoardDAO();

	public static MBoardDAO getInstance() {
		return instance;
	}
	
	
	/**
	 * 2017.05.13 
	 * YS CHOI
	 * 여기부터 New 버전 새로작성
	 * 
	 */
	
	public int getBoardListCnt(TSData param) throws Exception
	{
		return getSqlSession().selectOne("board.selectBoardListCnt", param);
	}
	
	public List<TSData> getBoardList(TSData param) throws Exception
	{
		return getSqlSession().selectList("board.selectBoardList", param);
	}
	
	public void writeBoard(TSData param) throws Exception {
		getSqlSession().insert("board.insertBoard", param);
	}
	
	public TSData getBoard(TSData param) throws Exception
	{
		return getSqlSession().selectOne("board.selectBoard", param);
	}
	
	public void deleteBoard(int param) throws Exception {
		getSqlSession().delete("board.deleteBoard", param);
	}
	
	public void updateBoard(TSData param) throws Exception {
		getSqlSession().update("board.updateBoard", param);
	}
}
