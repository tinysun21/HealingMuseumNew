package com.skcj.HealingMuseum.util;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

public class MessageCache {
	private static final Log log = LogFactory.getLog (MessageCache.class);

	// Bean 설정으로 저장되는 값
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// 
	private static MessageCache messageCache;
	private MessageCache() {
		this.messageCache = this;
	}
	
	public static MessageCache getInstance() {
		if(messageCache == null) {
			messageCache = new MessageCache();
		}
		return  messageCache;
	}
	
    public String getMessage(String code) {
    	Object[] args = null;
    	Locale locale = Locale.getDefault();
    	
    	String message = null;
    	try {
    		message = messageSource.getMessage(code, args, locale);
    	} catch(Exception e) {
    		message = null;
    	}
    	return message;
    }

    public String getMessage(String code, Object[] args) {
    	Locale locale = Locale.getDefault();
    	String message = null;
    	try {
    		message = messageSource.getMessage(code, args, locale);
    	} catch(Exception e) {
    		message = null;
    	}
    	return message;
    }
    
    public String getMessage(String code, Object[] args, Locale locale) {
    	String message = null;
    	try {
    		message = messageSource.getMessage(code, args, locale);
    	} catch(Exception e) {
    		message = null;
    	}
    	return message;
    }

    public String getMessage(String code, Locale locale, Object... args) {
    	String message = null;
    	try {
    		message = messageSource.getMessage(code, args, locale);
    	} catch(Exception e) {
    		message = null;
    	}
    	return message;
    }
   
}
