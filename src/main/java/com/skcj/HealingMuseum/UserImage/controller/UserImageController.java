package com.skcj.HealingMuseum.UserImage.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.skcj.HealingMuseum.Admin.controller.AdminController;
import com.skcj.HealingMuseum.UserImage.service.UserImageService;
import com.skcj.HealingMuseum.common.Enum.WebPathEnum;
import com.skcj.HealingMuseum.common.ResponseModel;
import com.skcj.HealingMuseum.common.BizException;
import com.skcj.HealingMuseum.common.Enum.UserImageStatusEnum;
import com.skcj.HealingMuseum.common.TSData;
import com.skcj.HealingMuseum.common.paging.PaginationInfo;
import com.skcj.HealingMuseum.common.paging.PagingParamsConvert;
import com.skcj.HealingMuseum.util.FileManager;
import com.skcj.HealingMuseum.util.PropUtil;


/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class UserImageController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserImageController.class);
	@Resource(name="UserImage/Service")
	private UserImageService userImageService;
	
	// 이미지 리스트 불러오기
	@RequestMapping("/user_image_list.do")
	public String UserImageList(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_list.do");	

		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		//PagingParamsConvert pagingParamsConvert = new PagingParamsConvert();
		
		try {
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			
			if(memberDTO != null){
				params.set("id", memberDTO.getString("ID"));
				// Paging 데이터 조회
				/*
				params.set("pageUnit", 5);
				List<TSData> userImageListData = userImageService.getUserImageList(pagingParamsConvert.convertPagingParams(params));
				
				PaginationInfo pageInfo = pagingParamsConvert.getPageInfo();
				// Paging 데이터 총 건수 조회
				pageInfo.setTotalRecordCount(userImageService.getUserImageListCnt(params));
				
			    model.addAttribute("imageList", userImageListData);
			    model.addAttribute("pageInfo", pageInfo);    
			    model.addAttribute("iparam", params);
				*/
				
				List<TSData> userImageListData = userImageService.getUserImageList(params);
				model.addAttribute("imageList", userImageListData);
				
			}else{
				model.addAttribute("imageList", null);
			    model.addAttribute("imageListSize", 0);    
			    model.addAttribute("paging", 1);    
			}
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "user_image_list";
	}
	
	// 이미지 업로드 폼
	@RequestMapping("/user_image_upload_form.do")
	public String UserImageUploadForm(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_upload_form.do");	
		
		return "user_image_upload_form";
	}
	
	// 이미지 업로드
	@RequestMapping("/user_image_upload.do")
	public String UserImageUpload(Model model, HttpServletRequest request) {
		logger.debug("CALL:: user_image_upload.do");	

		try {
			String u_id = "";
			File newFile = null;
		    String IMAGE_FULL_PATH = "";  // 실제 저장되는 경로    
			
			HttpSession session = request.getSession();
			
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			
			if(memberDTO.getString("ID") != null){
				u_id = memberDTO.getString("ID");
			}
			
			if(StringUtils.isNotBlank(u_id)){
			
				MultipartRequest multi =null;
			    
				try{	
				    int sizeLimit = 10 * 1024 * 1024;
				    String savepath = WebPathEnum.USER_IMAGE_SAVE_PATH.getString();
				    ServletContext context = session.getServletContext();
				    String contextPath = context.getRealPath(savepath) + File.separator + u_id;
				    logger.debug("유저이미지Context경로 = "+contextPath);				    
				    File saveDirectory = new File(contextPath);
				    if(!saveDirectory.exists()){
				    	saveDirectory.mkdirs();
				    }
				    		    
				    multi = new MultipartRequest(request, // 1. 요청 객체
				    		contextPath, // 2. 업로드될 파일이 저장될 파일 경로명
				        sizeLimit, // 3. 업로드될 파일의 최대 크기(1Mb)
				        "UTF-8", // 4. 인코딩 타입 지정
				        new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
				    ); // 이 시점을 기해 파일은 이미 저장이 되었다
				    
				    String fileName = multi.getFilesystemName("image").replaceAll("\\p{Space}", "").trim();  
				    String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  //현재시간
				    
				    int i = -1;
				          i = fileName.lastIndexOf("."); // 파일 확장자 위치
				          String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
				    
				    File oldFile = new File(contextPath, multi.getFilesystemName("image"));
				    newFile = new File(contextPath, realFileName); 
				    
				    oldFile.renameTo(newFile); // 파일명 변경	    
				    
				    // 웹 FULL_PATH 로 변경작업	    
				    IMAGE_FULL_PATH = StringUtils.replace(contextPath, WebPathEnum.WEB_RELATIVE_PATH.getString(), WebPathEnum.WEB_ABSOLUTE_PATH.getString())
				    		+ File.separator +newFile.getName();
				    logger.debug("이미지_풀_패스 == "+IMAGE_FULL_PATH);
				    
					TSData param = new TSData();
					param.set("keyword", multi.getParameter("keyword"));
					logger.debug("imageDTO.setKeyword 키워드 == "+multi.getParameter("keyword"));
					param.set("title", multi.getParameter("title"));
					param.set("content", multi.getParameter("content"));
					param.set("path", IMAGE_FULL_PATH);
					logger.debug("newFile.getName() == "+newFile.getName());
					param.set("id", u_id);
					
					userImageService.insertUserImage(param);
					
					model.addAttribute("image_upload_result", UserImageStatusEnum.IMAGE_WRITE_SUCCESS.getInt());
					
			    }catch(Exception e){
			    	logger.error("시스템 오류!!", e);	  
			    	model.addAttribute("image_upload_result", UserImageStatusEnum.IMAGE_WRITE_FAIL.getInt());    	  	
			    }
						
			}else{
				model.addAttribute("image_upload_result", UserImageStatusEnum.IMAGE_WRITE_FAIL_NO_LOGIN.getInt());
			}
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "user_image_upload_result";
	}	
	
	@RequestMapping("/user_image_update_form.do")
	public String UserImageUpdateForm(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_update_form.do");	
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try {
			TSData userImageData = userImageService.getUserImage(params);
			model.addAttribute("imageDTO", userImageData);
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "user_image_update_form";
	}
	
	// 이미지 수정
	@RequestMapping("/user_image_update.do")
	public String UserImageUpdate(Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_update.do");	
		
		try {
			String u_id = "";
			File newFile = null;
		    String IMAGE_FULL_PATH = "";  // 실제 저장되는 경로    
		    boolean isImageChanged = false;

			HttpSession session = request.getSession();
			TSData memberDTO =  (TSData) session.getAttribute("memberDTO");
			u_id = memberDTO.getString("ID");
			
			if(StringUtils.isNotBlank(u_id)){
			
				MultipartRequest multi =null;
			    
				try{	
				    int sizeLimit = 10 * 1024 * 1024;
				    String savepath = WebPathEnum.USER_IMAGE_SAVE_PATH.getString();
				    ServletContext context = session.getServletContext();
				    String contextPath = context.getRealPath(savepath) + File.separator + u_id;
				    logger.debug("유저이미지Context경로 = "+contextPath);				    
				    File saveDirectory = new File(contextPath);
				    if(!saveDirectory.exists()){
				    	saveDirectory.mkdirs();
				    }
				    		    
				    multi = new MultipartRequest(request, // 1. 요청 객체
				    		contextPath, // 2. 업로드될 파일이 저장될 파일 경로명
				        sizeLimit, // 3. 업로드될 파일의 최대 크기(1Mb)
				        "UTF-8", // 4. 인코딩 타입 지정
				        new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
				    ); // 이 시점을 기해 파일은 이미 저장이 되었다
				    
				    int index = Integer.parseInt(multi.getParameter("inx"));
					String old_path = multi.getParameter("path");
					logger.debug("이미지index == "+index);
					logger.debug("이미지old_path == "+old_path);
					
					if(multi.getFilesystemName("image") != null){
					    	
					    isImageChanged = true;
					    
					    String fileName = multi.getFilesystemName("image").replaceAll("\\p{Space}", "").trim();  
					    String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  //현재시간
					    
					    int i = -1;
					          i = fileName.lastIndexOf("."); // 파일 확장자 위치
					          String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
					    
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
					UserImageDTO n_imageDTO = new UserImageDTO();
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
					UserImageDAO imageDAO = UserImageDAO.getInstance();
					int result = imageDAO.updateUserImage(n_imageDTO);
					*/
					
					TSData param = new TSData();
					param.set("id", memberDTO.getString("ID"));
					param.set("inx", index);
					param.set("keyword", multi.getParameter("keyword"));
					param.set("title", multi.getParameter("title"));
					logger.debug("content== "+multi.getParameter("content"));
					param.set("content", multi.getParameter("content"));
					if(isImageChanged){
						param.set("path", IMAGE_FULL_PATH);
						logger.debug("newFile.getName() == "+newFile.getName());
					}else{
						param.set("path", old_path);
					}
					
					userImageService.updateUserImage(param);
					
			    }catch(Exception e){
			    	logger.error("시스템 오류!!", e);
			    	model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_UPDATE_FAIL.getInt());	
			    	return "commonErrorPage";
			    }
				
				model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_UPDATE_SUCCESS.getInt());
		
			}else{
				model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_UPDATE_FAIL_NO_LOGIN.getInt());
			}
			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "user_image_result";
	}
	
	// 이미지 삭제
	@RequestMapping("/user_image_delete.do")
	public String UserImageDelete(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_delete.do");	
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try {
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
			
			String str_userImagePath = "";
			int _inx = Integer.parseInt(request.getParameter("inx"));
			
			TSData userImageDTO = userImageService.getUserImage(params);
			
			String imageFileName = userImageDTO.getString("PATH");
			String tempPath = WebPathEnum.USER_IMAGE_SAVE_PATH.getString() + "/" + memberDTO.getString("ID") + "/";
			imageFileName = imageFileName.substring(imageFileName.indexOf(tempPath)+StringUtils.length(tempPath), imageFileName.length());
			logger.debug("이미지 파일명 == "+imageFileName);	
			
		    ServletContext context = session.getServletContext();
		    String contextPath = context.getRealPath((WebPathEnum.USER_IMAGE_SAVE_PATH.getString() + "/" + memberDTO.getString("ID")));
		    logger.debug("유저이미지Context경로 = "+contextPath);
		    
		    str_userImagePath = contextPath+"/"+imageFileName;
		    logger.debug("str유저이미지Path경로 = "+str_userImagePath);
		    
		    boolean deleteFlag = FileManager.deleteFiles(str_userImagePath);
		    
	    	if(deleteFlag){
	    		logger.debug("파일삭제 성공"+str_userImagePath);	
	    		userImageService.deleteUserImage(params);	
	    		model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_DELETE_SUCCESS.getInt());
	    	}
	    	else{
	    		logger.debug("파일삭제 실패"+str_userImagePath);
	    		model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_DELETE_FAIL.getInt());
	    	}	    
			
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			model.addAttribute("user_image_result", UserImageStatusEnum.IMAGE_DELETE_FAIL.getInt());
			return "commonErrorPage";
		}
		
		return "user_image_result";
	}
	
	// 이미지 슬라이드
	@RequestMapping("/user_image_slide.do")
	public String UserImageSlide(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: user_image_slide.do");	
		
		ResponseModel resModel = new ResponseModel();
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		String showCheck[] = request.getParameterValues("showCheck");
		if(showCheck != null){
			logger.debug("showCheck.length = "+ showCheck.length);
			for(int i=0; i<showCheck.length; i++){
				logger.debug("showCheck["+i+"] = "+ showCheck[i]);
			}
		}	
		
		try{
			ArrayList<TSData> showList = new ArrayList<TSData>();
			
			HttpSession session = request.getSession();
			TSData memberDTO = (TSData) session.getAttribute("memberDTO");
		    
			if(memberDTO != null){
				params.set("id", memberDTO.getString("ID"));
				List<TSData> raw_imageList = userImageService.getUserImageList(params);
			    logger.debug("유저raw이미지리스트.size() =="+raw_imageList.size());
			    
			    if(raw_imageList.size() < 1){
			    	throw new BizException(PropUtil.prop("web.user.img.slide.noimage.cd", request), 
			    			PropUtil.prop("web.user.img.slide.noimage.msg", request));
			    }
			    
			    if(showCheck != null){	    	
			    	List<TSData> removeIArr = new ArrayList<TSData>();
			    	for(int i=0; i<raw_imageList.size(); i++){
			    		boolean dupFlag = false;
				    	int rawInx = raw_imageList.get(i).getInt("INX");
				    	logger.debug("for loop rawInx = "+rawInx);
				    	for(int j=0; j<showCheck.length; j++){
				    		if(rawInx == Integer.parseInt(showCheck[j])){
				    			dupFlag = true;
				    		}
				    	}
				    	if(!dupFlag){
				    		removeIArr.add(raw_imageList.get(i));
				    		logger.debug("raw_imageList.remove("+i+"), INX = "+rawInx);
				    	}
				    }
			    	raw_imageList.removeAll(removeIArr);
			    }
			    logger.debug("유저show이미지리스트.size() =="+raw_imageList.size());
			    
			    int num[] = new int[raw_imageList.size()];
		    	Random rand =new Random(12);
				rand.setSeed(System.currentTimeMillis());
				
				// 랜덤함수이용 (1~이미지개수) 사이의 겹치지않는 난수 발생	
				for(int i=0; i<raw_imageList.size(); i++)
				{
					num[i]=rand.nextInt(raw_imageList.size());
					logger.debug("num["+i+"]=="+num[i]);
					
					for(int j=0; j<i; j++)
					{
						if(num[i]==num[j])
						{
							num[i]=rand.nextInt(raw_imageList.size());
							j=-1; //바꿨는데도 또 같은 수가 나왔을 경우를 대비
						}
					}	
				}
				//발생한 난수 확인
				for(int i=0; i<num.length; i++)
				{
					logger.debug("난수 : "+num[i]);
				}
	
			
				// 이미지Path를 ArrayList<String> image_path 에 삽입
			    for(int k=0; k<raw_imageList.size(); k++){
			    	if(raw_imageList.get(num[k]) == null){
			    		showList.add(k, null);
			    		logger.debug("이미지pathList["+k+"]== add null");
			    	}else{
			    		showList.add(k, raw_imageList.get(num[k]));
			    		logger.debug("이미지showList["+k+"].getPath=="+showList.get(k).getString("PATH"));
			    		logger.debug("이미지showList["+k+"].getContent=="+showList.get(k).getString("CONTENT"));
			    		userImageService.updateUserImageSlideCnt(raw_imageList.get(num[k]).getInt("INX"));
			    	}
			    	
			    }
			    
			    /*
				if(raw_imageList.size() < 6){	// 저장 된 이미지가 6개 이하일 경우
				
					// 랜덤함수이용 1~imageList.size()사이의 겹치지않는 난수 발생	
					for(int i=0; i<raw_imageList.size(); i++)
					{
						num[i]=rand.nextInt(raw_imageList.size());
						logger.debug("num["+i+"]=="+num[i]);
						
						for(int j=0; j<i; j++)
						{
							if(num[i]==num[j])
							{
								num[i]=rand.nextInt(raw_imageList.size());
								j=-1; //바꿨는데도 또 같은 수가 나왔을 경우를 대비
							}
						}	
					}
					//발생한 난수 6개 확인출력
					for(int m=0; m<num.length; m++)
					{
						logger.debug("난수 : "+num[m]);
					}
		
				
					// 이미지Path를 ArrayList<String> image_path 에 삽입
				    for(int k=0; k<raw_imageList.size(); k++){
				    	if(raw_imageList.get(num[k]) == null){
				    		showList.add(k, null);
				    		logger.debug("이미지pathList["+k+"]== add null");
				    	}else{
				    		showList.add(k, raw_imageList.get(num[k]));
				    		logger.debug("이미지showList["+k+"].getPath=="+showList.get(k).getString("PATH"));
				    		userImageService.updateUserImageSlideCnt(raw_imageList.get(num[k]).getInt("INX"));
				    	}
				    	
				    }
					
				}else{
					// 랜덤함수이용 1~imageList.size()사이의 겹치지않는 난수 6개 발생	
					for(int i=0; i<num.length; i++)
					{
						num[i]=rand.nextInt(raw_imageList.size());
						logger.debug("num["+i+"]=="+num[i]);
						
						for(int j=0; j<i; j++)
						{
							if(num[i]==num[j])
							{
								num[i]=rand.nextInt(raw_imageList.size());
								j=-1; //바꿨는데도 또 같은 수가 나왔을 경우를 대비
							}
						}	
					}
					//발생한 난수 6개 확인출력
					for(int m=0; m<num.length; m++)
					{
						logger.debug("난수 : "+num[m]);
					}
					
					// 이미지Path를 ArrayList<String> image_path 에 삽입
				    for(int k=0; k<num.length; k++){
				    	if(raw_imageList.get(num[k]) == null){
				    		showList.add(k, null);
				    		logger.debug("이미지pathList["+k+"]== add null");
				    	}else{
				    		showList.add(k, raw_imageList.get(num[k]));
				    		logger.debug("이미지showList["+k+"].getPath=="+showList.get(k).getString("PATH"));
				    		userImageService.updateUserImageSlideCnt(raw_imageList.get(num[k]).getInt("INX"));
				    	}
				    	
				    }
				}
			    */
			    
			    model.addAttribute("showList", showList);
		    
			}else{
				logger.debug("현재 로그인 상태가 아닙니다.");
				return "commonErrorPage";
			}
		} catch(BizException e) {
			logger.error("처리 중 오류!!", e);
			resModel.setResCd(e.getErrorCode());
			resModel.setResMsg(e.getMessage());
			model.addAttribute("resMd", resModel);
			return "commonErrorPage";
		}  catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "fullscreen_slide";
	}
}
