package com.skcj.HealingMuseum.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONView extends MappingJackson2JsonView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");

		ObjectMapper om = new ObjectMapper();
		ResponseModel resMd = (ResponseModel)model.get("resMd");
		
		String jsonStr = om.writeValueAsString(resMd);
		logger.info("응답메시지 : [" + jsonStr + "]");
		
		response.getWriter().write(jsonStr);
	}
	
}

