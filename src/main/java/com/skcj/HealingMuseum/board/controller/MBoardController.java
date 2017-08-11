package com.skcj.HealingMuseum.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  날짜                 버전                        작성자
 *  
 *  2015.10.03           v.0.0.1					 김현종
 *  2015.10.05			 v.0.0.2					김현종
 *  
 *  
 *  
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcj.HealingMuseum.board.service.BoardService;
import com.skcj.HealingMuseum.common.Enum.MBoardStatusEnum;
import com.skcj.HealingMuseum.common.Enum.UserImageStatusEnum;
import com.skcj.HealingMuseum.common.TSData;
import com.skcj.HealingMuseum.common.paging.PaginationInfo;
import com.skcj.HealingMuseum.common.paging.PagingParamsConvert;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class MBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(MBoardController.class);
	@Resource(name="Board/Service")
	private BoardService boardService;

	@RequestMapping("/board_list.do")
	public String BoardList(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: board_list.do");
		/*
		model.addAttribute("request", request);
		command = new MBoardListCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		PagingParamsConvert pagingParamsConvert = new PagingParamsConvert();
		
		try {
			// Paging 데이터 조회
			List<TSData> boardListData = boardService.getBoardList(pagingParamsConvert.convertPagingParams(params));
			
			PaginationInfo pageInfo = pagingParamsConvert.getPageInfo();
			// Paging 데이터 총 건수 조회
			pageInfo.setTotalRecordCount(boardService.getBoardListCnt(params));
			
			model.addAttribute("boardList", boardListData);
			model.addAttribute("pageInfo", pageInfo);
			
			model.addAttribute("iparam", params);
			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}		
		
		return "m_board_list";
	}



	@RequestMapping("/mb_write_form.do")
	public String MBWriteForm(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: mb_write_form.do");	
		
		return "mb_write_form";
	}
	
	@RequestMapping("/mb_write.do")
	public String MBWrite(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: mb_write.do");
		/*
		model.addAttribute("request", request);
		command = new MBoardWriteCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			params.set("id", memberDTO.get("ID"));
			
			boardService.writeBoard(params);
		} catch(Exception e) {
			model.addAttribute("board_result", MBoardStatusEnum.BOARD_WRITE_FAIL.getInt());
			logger.error("시스템 오류!!", e);
			return "mb_result";
		}	
		
		model.addAttribute("board_result", MBoardStatusEnum.BOARD_WRITE_SUCCESS.getInt());
		
		return "mb_result";
	}
	
	
	@RequestMapping("/mb_update_form.do")
	public String MBUpdateForm(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: mb_update_form.do");
		
		/*
		model.addAttribute("request", request);
		command = new MBUpdateFormCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			params.set("id", memberDTO.get("ID"));
			
			TSData board = boardService.getBoard(params);
		    model.addAttribute("mboardDTO", board);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}	
				
		return "mb_update_form";

	}
	
	@RequestMapping("/mb_delete.do")
	public String MBDelete(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("mb_delete.do 실행");
		/*
		model.addAttribute("request", request);
		command = new MBDeleteCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			int inx = Integer.parseInt((params.getString("inx")));
			boardService.deleteBoard(inx);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("board_result", MBoardStatusEnum.BOARD_DELETE_FAIL.getInt());
			return "mb_result";
		}	
		
		model.addAttribute("board_result", MBoardStatusEnum.BOARD_DELETE_SUCCESS.getInt());
		
		return "mb_result";

	}
		
	
	@RequestMapping("/mb_update.do")
	public String MBUpdate(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: mb_update.do");

		/*
		model.addAttribute("request", request);
		command = new MBUpdateCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			int inx = Integer.parseInt((params.getString("inx")));
			boardService.updateBoard(params);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("board_result", MBoardStatusEnum.BOARD_UPDATE_FAIL.getInt());
			return "mb_result";
		}	
		
		model.addAttribute("board_result", MBoardStatusEnum.BOARD_UPDATE_SUCCESS.getInt());
		
		return "mb_result";

	}
}

