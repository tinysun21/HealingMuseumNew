package com.skcj.HealingMuseum;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class SessionLocaleController {
	private static final Logger logger = LoggerFactory.getLogger(SessionLocaleController.class);
	
	@RequestMapping(value = "/setChangeLocale.do")
	public String changeLocale(@RequestParam(required = false) String locale, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Locale locales = null;
		// 넘어온 파라미터에 ko가 있으면 Locale의 언어를 한국어로 바꿔준다.
		// 그렇지 않을 경우 일본어로 설정한다.
		if (locale.matches("ja")) {
			locale = "ja";
			locales = Locale.JAPANESE;
		}
		else if (locale.matches("ko")) {
			locale = "ko";
			locales = Locale.KOREAN;
		} 
		else if (locale.matches("en")) {
			locale = "en";
			locales = Locale.ENGLISH;
		}
		else {
			locale = "ja";
			locales = Locale.JAPANESE;
		}
		
		// 세션에 존재하는 Locale을 새로운 언어로 변경해준다.
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);
		session.setAttribute("locale", locale);
		
		// 해당 컨트롤러에게 요청을 보낸 주소로 돌아간다.
		String redirectURL = "redirect:" + request.getHeader("referer");
		return redirectURL;
	}
}
