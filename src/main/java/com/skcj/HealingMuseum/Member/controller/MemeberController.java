package com.skcj.HealingMuseum.Member.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  날짜                 버전                        작성자
 *  
 *  2015.10.03           v.0.0.1					 김현종
 *  2015.10.05			 					
 *  2015.10.12			 v.0.0.3					 최연석
 *  
 *  
 */


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcj.HealingMuseum.Member.service.MemberService;
import com.skcj.HealingMuseum.UserImage.dao.UserImageDAO;
import com.skcj.HealingMuseum.UserImage.service.UserImageService;
import com.skcj.HealingMuseum.common.TSData;
import com.skcj.HealingMuseum.util.FileManager;
import com.skcj.HealingMuseum.util.PropUtil;
import com.skcj.HealingMuseum.common.Enum.CommonStatusEnum;
import com.skcj.HealingMuseum.common.Enum.MemberIdStatusEnum;
import com.skcj.HealingMuseum.common.Enum.MemberLoginStatusEnum;
import com.skcj.HealingMuseum.common.Enum.MypageStatusEnum;
import com.skcj.HealingMuseum.common.Enum.WebPathEnum;
import com.skcj.HealingMuseum.common.ResponseModel;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class MemeberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemeberController.class);
	@Resource(name="Member/Service")
	private MemberService memberService;
	@Resource(name="UserImage/Service")
	private UserImageService userImageService;
	
	@RequestMapping("/join_form.do")
	public String join_view(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: join_form.do");

		return "join_form";
	}

	@RequestMapping("/login_form.do")
	public String login_view(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: login_form.do");

		return "login_form";
	}

	@RequestMapping("/join.do")
	public String join(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: join.do");
		
		/*
		model.addAttribute("request", request);
		command = new MJoinCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{ 
		    memberService.joinMember(params);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("result", CommonStatusEnum.FAIL.getInt());
			return "commonErrorPage";
		} 
		 
		model.addAttribute("result", CommonStatusEnum.SUCCESS.getInt());
		
		try{ 
			TSData memberData = memberService.getMember(params);
			
			memberService.updateMemberLogin(memberData);	// 로그인했다는 정보 삽입
		   	  
		   	 if(memberData.getInt("Admin") == 1){
		   		 model.addAttribute("result", MemberLoginStatusEnum.ADMIN_LOGIN_SUCCESS.getInt());	// 관리자 로그인 성공
		   	 }else{
		   		 model.addAttribute("result", MemberLoginStatusEnum.MEMBER_LOGIN_SUCCESS.getInt());	// 로그인 성공
		   	 } 
		   	  
		   	HttpSession session = request.getSession();
		   	session.setAttribute("memberDTO", memberData);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("result", CommonStatusEnum.FAIL.getInt());
			return "commonErrorPage";
		} 
		
		return "join_result";
	}

	@RequestMapping("/id_check.do")
	public String IdCheck(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: id_check.do");
		/*
		model.addAttribute("request", request);
		command = new MIdCheckCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		TSData memberInfo = new TSData();
		try{ 
			memberInfo = memberService.getMember(params);
	    	
	    	if(memberInfo == null  || memberInfo.isEmpty()){
	    		model.addAttribute("message", MemberIdStatusEnum.ID_NOT_EXTST.getInt());
		    }else{
		    	model.addAttribute("message", MemberIdStatusEnum.ID_EXTST.getInt());
		    }
		    
		    			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		} 
		
		model.addAttribute("id", params.getString("id"));
		
		return "id_check";
	}

	@RequestMapping(value="/login.json", method = RequestMethod.POST)
	public void Login(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: login.json");
		
		/*
		model.addAttribute("request", request);
		command = new MLoginCommand();
		command.execute(model);
		*/
		
		ResponseModel resModel = new ResponseModel();
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			String id = params.getString("id");	// 사용자가 입력한 id
			String pw = params.getString("pw"); // 사용자가 입력한 pw

			TSData memberData = memberService.getMember(params);
					
			if(memberData!=null){	//  id 존재 확인
		    	
			      if(StringUtils.equals(memberData.getString("PW"), pw)){	// pw 확인
			    	  
			    	  memberService.updateMemberLogin(memberData);	// 로그인했다는 정보 삽입
			    	  request.getSession().setAttribute("memberDTO", memberData);
			        
			      }else{
			    	  //model.addAttribute("login_result", MemberLoginStatusEnum.WRONG_PASSWORD.getInt());	// 비밀번호 안맞음
			    	  resModel.setResCd(PropUtil.prop("web.common.login.fail.pwd.cd", request));
			    	  resModel.setResMsg(PropUtil.prop("web.common.login.fail.pwd.msg", request));
			      }
			      
			}else{	// id 없음
				//model.addAttribute("login_result", MemberLoginStatusEnum.ID_NOT_EXTST.getInt()); 
				resModel.setResCd(PropUtil.prop("web.common.login.fail.id.cd", request));
				resModel.setResMsg(PropUtil.prop("web.common.login.fail.id.msg", request));
			}
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			resModel.setResCd(PropUtil.prop("web.err.system.cd", request));
			resModel.setResMsg(PropUtil.prop("web.err.system.msg", request));	
		} 
		
		model.addAttribute("resMd", resModel);
	}
	
	@RequestMapping("/logout.do")
	public String Logout(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: logout.do");
		
		HttpSession session=request.getSession();
	    if(session!=null){
	      // 세션정보 초기화	
	      session.invalidate();
	    }
		
		return "index";

	}

	@RequestMapping("/mypage_form.do")
	public String mypage_form(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("mypage_form.do 실행");
		
		/*
		model.addAttribute("request", request);
		command = new MLoginCommand();
		command.execute(model);
		*/
		
		return "mypage_form";

	}
	
	@RequestMapping("/mypage_form_update.do")
	public String mypage_form_update(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: mypage_form_update.do");
		
		/*
		model.addAttribute("request", request);
		command = new MUpdateCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try {
			memberService.updateMember(params);
			
			TSData memberData = memberService.getMember(params);
	    	request.getSession().setAttribute("memberDTO", memberData);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("mypage_result", MypageStatusEnum.MYPAGE_UPDATE_FAIL.getInt());
			return "mypage_result";
		} 
		
		 model.addAttribute("mypage_result", MypageStatusEnum.MYPAGE_UPDATE_SUCCESS.getInt());
		
		return "mypage_result";

	}
	
	@RequestMapping("/delete_member.do")
	public String deleteMember(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: delete_member.do");

		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try {
			boolean imageDeleteFlag = false;
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			params.set("id", memberDTO.getString("ID"));	
			
		    ServletContext context = session.getServletContext();
		    String userImageDirectoryPath = context.getRealPath((WebPathEnum.USER_IMAGE_SAVE_PATH.getString() + "/" + memberDTO.getString("ID")));
		    logger.debug("userImageDirectoryPath = "+userImageDirectoryPath);
		    
		    boolean deleteAllFlag = FileManager.deleteAllFiles(userImageDirectoryPath);
		    
		    if(deleteAllFlag){
		    	imageDeleteFlag = true;
	    		logger.debug("유저 디렉토리 삭제 성공 : " + userImageDirectoryPath);
		    }else{    		
	    		logger.debug("유저 디렉토리 삭제 실패 : " + userImageDirectoryPath);
	    	}
		    
		    
		    if(imageDeleteFlag){
		    	memberService.deleteMember(params);
				
			    if(session!=null){
			      // 세션정보 초기화	
			      session.invalidate();
			    }
			    
				model.addAttribute("mypage_result", MypageStatusEnum.MYPAGE_DELETE_SUCCESS.getInt());
		    }else{
		    	model.addAttribute("mypage_result", MypageStatusEnum.MYPAGE_DELETE_FAIL.getInt());
		    }
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("mypage_result", MypageStatusEnum.MYPAGE_DELETE_FAIL.getInt());
			return "mypage_result";
		} 
		
		return "mypage_result";

	}

}
