package com.skcj.HealingMuseum.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skcj.HealingMuseum.board.dao.MBoardDAO;
import com.skcj.HealingMuseum.common.TSData;

@Service("Board/Service")
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	@Resource(name="Board/DAO")
	private MBoardDAO dao;
	
	public int getBoardListCnt(TSData param) throws Exception
	{
		return this.dao.getBoardListCnt(param);
	}
	
	public List<TSData> getBoardList(TSData param) throws Exception
	{
		return this.dao.getBoardList(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void writeBoard(TSData param) throws Exception 
	{
		dao.writeBoard(param);
	}
	
	public TSData getBoard(TSData param) throws Exception
	{
		return this.dao.getBoard(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void deleteBoard(int param) throws Exception {
		dao.deleteBoard(param);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateBoard(TSData param) throws Exception {
		dao.updateBoard(param);
	}
}
