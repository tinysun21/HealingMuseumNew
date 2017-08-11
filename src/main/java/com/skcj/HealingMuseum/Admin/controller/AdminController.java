package com.skcj.HealingMuseum.Admin.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 *  날짜                 버전                        작성자
 *  
 *  2015.10.03           v.0.0.1					 최연석
 *  2015.10.05	 		 v.0.0.2					 최연석			
 *  2015.10.06			 v.0.0.3					 최연석
 *  
 *  
 */



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.skcj.HealingMuseum.Admin.dao.AdminDAO;
import com.skcj.HealingMuseum.Admin.service.AdminService;
import com.skcj.HealingMuseum.Member.service.MemberService;
import com.skcj.HealingMuseum.common.Enum.ImageStatusEnum;
import com.skcj.HealingMuseum.common.Enum.WebPathEnum;
import com.skcj.HealingMuseum.common.TSData;
import com.skcj.HealingMuseum.common.paging.PaginationInfo;
import com.skcj.HealingMuseum.common.paging.PagingParamsConvert;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Resource(name="Admin/Service")
	private AdminService adminService;
	@Resource(name="Member/Service")
	private MemberService memberService;
	
	@RequestMapping("/admin_member_list.do")
	public String AdminMemberList(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: admin_member_list.do");	
		/*
		model.addAttribute("request", request);
		command = new AMemberListCommand();
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
			List<TSData> memberListData = memberService.getMemberList(pagingParamsConvert.convertPagingParams(params));
			
			PaginationInfo pageInfo = pagingParamsConvert.getPageInfo();
			// Paging 데이터 총 건수 조회
			pageInfo.setTotalRecordCount(memberService.getMemberListCnt(params));
			
			model.addAttribute("userList", memberListData);
			model.addAttribute("pageInfo", pageInfo);
			
			model.addAttribute("iparam", params);
			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}

		return "admin_member_list";
	}
	
	@RequestMapping("/admin_image_upload_form.do")
	public String AdminImageUploadForm(HttpServletRequest request, Model model)  throws Exception
	{
		logger.debug("CALL:: admin_image_upload_form.do");	
		
		return "admin_image_upload_form";
	}
	
	@RequestMapping("/admin_image_upload.do")
	public String AdminImageUpload(Model model, HttpServletRequest request)  throws Exception
	{
		logger.debug("CALL:: admin_image_upload.do");	
		
		/*
		model.addAttribute("request", request);	
		command = new AImageUploadCommand();
		command.execute(model);*/
		
		File newFile = null;
	    String IMAGE_FULL_PATH = "";  // 실제 DB(STRORAGE/images)에 저장되는 경로    
		
		Map<String, Object> map = model.asMap();
		request = (HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();

		MultipartRequest multi =null;
	    
		try{	
		    int sizeLimit = 10 * 1024 * 1024;
		    String savepath = WebPathEnum.ADMIN_IMAGE_SAVE_PATH.getString();
		    ServletContext context = session.getServletContext();
		    String contextPath = context.getRealPath(savepath);
		    logger.debug("이미지Context경로 = "+contextPath);
		    		    
		    multi = new MultipartRequest(request, // 1. 요청 객체
		    		contextPath, // 2. 업로드될 파일이 저장될 파일 경로명
		        sizeLimit, // 3. 업로드될 파일의 최대 크기(1Mb)
		        "UTF-8", // 4. 인코딩 타입 지정
		        new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
		    ); // 이 시점을 기해 파일은 이미 저장이 되었다
		    
		    String fileName = multi.getFilesystemName("image").replaceAll("\\p{Space}", "").trim();  
		    String now = new SimpleDateFormat("yyyyMMdd").format(new Date());  //현재시간
		    String image_name = multi.getParameter("title").replaceAll("\\p{Space}", "").trim() + "_" + now;
		    
		    int i = -1;
		          i = fileName.lastIndexOf("."); // 파일 확장자 위치
		          String realFileName = image_name + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
		    
		    File oldFile = new File(contextPath, multi.getFilesystemName("image"));
		    newFile = new File(contextPath, realFileName); 
		    
		    oldFile.renameTo(newFile); // 파일명 변경
		    
		    
		    // 웹 FULL_PATH 로 변경작업
		    IMAGE_FULL_PATH = StringUtils.replace(contextPath, WebPathEnum.WEB_RELATIVE_PATH.getString(), WebPathEnum.WEB_ABSOLUTE_PATH.getString())
		    		+ File.separator +newFile.getName();
		    logger.debug("이미지_풀_패스 == "+IMAGE_FULL_PATH);
		    
		    
		    
		    // DB에 정보 삽입
		    /*
		    AdminDTO imageDTO = new AdminDTO();
			imageDTO.setKeyword(multi.getParameter("keyword"));
			logger.debug("imageDTO.setKeyword 키워드 == "+imageDTO.getKeyword());
			imageDTO.setTitle(multi.getParameter("title"));
			imageDTO.setInfo(multi.getParameter("info"));
			
			imageDTO.setPath(IMAGE_FULL_PATH);
			logger.debug("newFile.getName() == "+newFile.getName());
			
			imageDTO.setIndate(new Timestamp(System.currentTimeMillis()));
				
			
			AdminDAO imageDAO = AdminDAO.getInstance();
			int result = imageDAO.insertImage(imageDTO);
			*/
			
			TSData params = new TSData();
			params.set("keyword", multi.getParameter("keyword"));
			logger.debug(".setKeyword 키워드 == "+multi.getParameter("keyword"));
			params.set("title", multi.getParameter("title"));
			params.set("info", multi.getParameter("info"));
			params.set("path", IMAGE_FULL_PATH);
			logger.debug("newFile.getName() == "+newFile.getName());
			
			adminService.insertAdminImage(params);
			
	    }catch(Exception e){
	    	model.addAttribute("image_upload_result", ImageStatusEnum.IMAGE_WRITE_FAIL.getInt());
	    	e.printStackTrace();	    	
	    	return "commonErrorPage";
	    }
		
		model.addAttribute("image_upload_result",  ImageStatusEnum.IMAGE_WRITE_SUCCESS.getInt());
		
		return "admin_image_upload_result";
	}
	
	
	// 이미지 리스트 불러오기
	@RequestMapping("/admin_image_list.do")
	public String AdminImageList(@RequestParam Map<String, String> reqParam, Model model, HttpServletRequest request)  throws Exception
	{
		logger.debug("CALL:: admin_image_list.do");	
		
		/*
		model.addAttribute("request", request);
		command = new AImageListCommand();
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
			params.set("pageSize", 5);
			List<TSData> adminImageData = adminService.getAdminImageList(pagingParamsConvert.convertPagingParams(params));
			
			PaginationInfo pageInfo = pagingParamsConvert.getPageInfo();
			// Paging 데이터 총 건수 조회
			pageInfo.setTotalRecordCount(adminService.getAdminImageListCnt(params));
			
			model.addAttribute("imageList", adminImageData);
			model.addAttribute("pageInfo", pageInfo);
			
			model.addAttribute("iparam", params);
			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "admin_image_list";
	}
	
	@RequestMapping("/admin_image_update_form.do")
	public String AdminImageUpdateForm(@RequestParam Map<String, String> reqParam, Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: admin_image_update_form.do");	
		/*
		model.addAttribute("request", request);
		command = new AImageUpdateFormCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try {
			TSData adminImage = adminService.getAdminImage(params);
		    model.addAttribute("imageDTO", adminImage);

		} catch(Exception e) {
			logger.error("선택된 Admin Image 를 찾을 수 없습니다!!", e);
			return "commonErrorPage";
		}
		
		return "admin_image_update_form";
	}
	
	@RequestMapping("/admin_image_update.do")
	public String AdminImageUpdate(Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: admin_image_update.do");	
		
		/*
		model.addAttribute("request", request);
		command = new AImageUpdateCommand();
		command.execute(model);
		*/
		
		File newFile = null;
	    String IMAGE_FULL_PATH = "";  // 실제 DB(STRORAGE/coupon_images)에 저장되는 경로    
	    boolean isImageChanged = false;
	    
		Map<String, Object> map = model.asMap();
		request = (HttpServletRequest) map.get("request");
		int index = Integer.parseInt(request.getParameter("index"));
		String old_path = request.getParameter("path");
		logger.debug("이미지index == "+index);
		logger.debug("이미지old_path == "+old_path);
		
		HttpSession session = request.getSession();

		MultipartRequest multi =null;
	    
		try{	
		    int sizeLimit = 10 * 1024 * 1024;
		    String savepath = WebPathEnum.ADMIN_IMAGE_SAVE_PATH.getString();
		    ServletContext context = session.getServletContext();
		    String contextPath = context.getRealPath(savepath);
		    logger.debug("이미지Context경로 = "+contextPath);
		    		    
		    multi = new MultipartRequest(request, // 1. 요청 객체
		    		contextPath, // 2. 업로드될 파일이 저장될 파일 경로명
		        sizeLimit, // 3. 업로드될 파일의 최대 크기(1Mb)
		        "UTF-8", // 4. 인코딩 타입 지정
		        new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
		    ); // 이 시점을 기해 파일은 이미 저장이 되었다
		    
			if(multi.getFilesystemName("image") != null){
			    	
			    isImageChanged = true;
			    
			    String fileName = multi.getFilesystemName("image").replaceAll("\\p{Space}", "").trim();  
			    String now = new SimpleDateFormat("yyyyMMdd").format(new Date());  //현재시간
			    String image_name = multi.getParameter("title").replaceAll("\\p{Space}", "").trim() + "_" + now;
			    
			    int i = -1;
			          i = fileName.lastIndexOf("."); // 파일 확장자 위치
			          String realFileName = image_name + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
			    
			    File oldFile = new File(contextPath, multi.getFilesystemName("image"));
			    newFile = new File(contextPath, realFileName); 
			    
			    oldFile.renameTo(newFile); // 파일명 변경
			    
			    
			    // 웹 FULL_PATH 로 변경작업
			    IMAGE_FULL_PATH = StringUtils.replace(contextPath, WebPathEnum.WEB_RELATIVE_PATH.getString(), WebPathEnum.WEB_ABSOLUTE_PATH.getString())
			    		+ File.separator +newFile.getName();
			    logger.debug("이미지_풀_패스 == "+IMAGE_FULL_PATH);
		    
		    }
		    
		    // DB에 정보 삽입
		    /*
		    AdminDTO n_imageDTO = new AdminDTO();
		    n_imageDTO.setIndex(index);
		    n_imageDTO.setKeyword(multi.getParameter("keyword"));
			n_imageDTO.setTitle(multi.getParameter("title"));
			n_imageDTO.setInfo(multi.getParameter("info"));
			
			if(isImageChanged){
				n_imageDTO.setPath(IMAGE_FULL_PATH);
				logger.debug("newFile.getName() == "+newFile.getName());
			}else{
				n_imageDTO.setPath(old_path);
			}
	
			AdminDAO imageDAO = AdminDAO.getInstance();
			int result = imageDAO.updateImage(n_imageDTO);
			*/
			
			TSData params = new TSData();
			params.set("index", index);
			params.set("keyword", multi.getParameter("keyword"));
			params.set("title", multi.getParameter("title"));
			params.set("info", multi.getParameter("info"));
			if(isImageChanged){
				params.set("path", IMAGE_FULL_PATH);
				logger.debug("newFile.getName() == "+newFile.getName());
			}else{
				params.set("path", old_path);
			}
			
			adminService.updateAdminImage(params);
			
	    }catch(Exception e){
	    	model.addAttribute("admin_image_result", ImageStatusEnum.IMAGE_WRITE_FAIL.getInt());
	    	e.printStackTrace();	    	
	    	return "commonErrorPage";
	    }
		
		 model.addAttribute("admin_image_result", ImageStatusEnum.IMAGE_WRITE_SUCCESS.getInt());
		 
		return "admin_image_result";
	}
}
