package com.skcj.HealingMuseum.View.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcj.HealingMuseum.Admin.service.AdminService;
import com.skcj.HealingMuseum.common.TSData;

@Controller
public class VController {
	private static final Logger logger = LoggerFactory.getLogger(VController.class);
	
	@Resource(name="Admin/Service")
	private AdminService adminService;

	@RequestMapping("/index.do")
	public String Index(Model model) {
		logger.debug("CALL:: index.do");	
		
		return "index";
	}
	
	@RequestMapping("/fullscreen_page.do")
	public String FullscreenPage(Model model) {
		logger.debug("CALL:: fullscreen_page.do");
		
		return "fullscreen_page";
	}
	
	@RequestMapping("/fullscreen_slide.do")
	public String FullscreenSlide(@RequestParam Map<String, String> reqParam,  Model model, HttpServletRequest request) throws Exception
	{
		logger.debug("CALL:: fullscreen_slide.do");
		/*
		model.addAttribute("request", request);
		command = new VFullscreenSlideCommand();
		command.execute(model);
		*/
		
		TSData params = new TSData();
		params.putAll(reqParam);
		
		logger.debug("================ param ====================");
		logger.debug(params.toString());
		logger.debug("================ //param ====================");
		
		try{
			//String KEYWORD[] = new String[] {"기쁨", "슬픔", "화남", "즐거움"};
			String keyword = "기쁨";
			int num[] = new int[6];
			ArrayList<TSData> showList = new ArrayList<TSData>();
			
			int int_keyword = Integer.parseInt(params.getString("keyword"));
		    if(int_keyword==0){
		    	int_keyword=1;
		    }
		    
		    if(int_keyword == 1){
		    	keyword = "기쁨";
		    }else if(int_keyword == 2){
		    	keyword = "슬픔";
		    }else if(int_keyword == 3){
		    	keyword = "화남";
		    }else if(int_keyword == 4){
		    	keyword = "즐거움";
		    }
		    params.set("keyword", keyword);
		    logger.debug("이미지keyword =="+keyword);
		        
		    List<TSData> raw_imageList = adminService.getAdminImageList(params);
		    
		    logger.debug("raw이미지리스트.size() =="+raw_imageList.size());
		    
		    for(int y=0; y<raw_imageList.size(); y++){
		    	logger.debug("이미지리스트.index["+y+"]=="+raw_imageList.get(y).getInt("INX"));
		    }
		    
	    	Random rand =new Random(12);
			rand.setSeed(System.currentTimeMillis());

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
		    		adminService.updateAdminImageSlideCnt(raw_imageList.get(num[k]).getInt("INX"));
		    	}
		    	
		    }
		      
		    model.addAttribute("showList", showList);
	
		} catch(Exception e) {
			logger.error("시스템 오류!!", e);
			return "commonErrorPage";
		}
		
		return "fullscreen_slide";
	}
}