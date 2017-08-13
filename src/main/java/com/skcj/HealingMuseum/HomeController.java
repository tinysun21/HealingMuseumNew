package com.skcj.HealingMuseum;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="config")
	private Properties prop;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "index";
	}
	
	@RequestMapping("/index.do")
	public String Index(Locale locale, Model model, HttpServletRequest request) {
		logger.debug("CALL:: index.do");	
		logger.debug("locale = " + locale);
		
		HttpSession session = request.getSession();
		if(StringUtils.isBlank((String)session.getAttribute("locale"))){
			// 세션에 존재하는 Locale을 새로운 언어로 변경해준다.
			String defaltLocale = prop.getProperty("lang.default");
			Locale locales = Locale.JAPANESE;
			
			if(StringUtils.equals("ja", defaltLocale)){
				defaltLocale = "ja";
				locales = Locale.JAPANESE;
			}
			else if(StringUtils.equals("ko", defaltLocale)){
				defaltLocale = "ko";
				locales = Locale.KOREAN;
			}else if(StringUtils.equals("en", defaltLocale)){
				defaltLocale = "en";
				locales = Locale.ENGLISH;
			}else{
				// 미설정 시 기본은 일본어로
				locales = Locale.JAPANESE;
			}
			
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);
			session.setAttribute("locale", defaltLocale);
		}
		
		return "index";
	}
	
}
