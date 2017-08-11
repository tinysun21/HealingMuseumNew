package com.skcj.HealingMuseum.common;

public class BizException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
    private String message;
    
	/**
	 * 생성자
	 * @param String
	 * @return void
	 */
    public BizException(String errorCode) {
    	super(errorCode);
    }
    
    /**
     * 생성자
     * @param errorCode
     * @param message
     */
    public BizException(String errorCode, String message) {
    	super(errorCode);
    	this.errorCode = errorCode;
    	this.message = message;
    }

	/**
	 * 생성자
	 * @param String		Biz클래서에서 던져주는 message코드(message코드 관련정보는 환경설정됨)
	 * @param Throwable
	 * @return void
	 */
    public BizException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }
    
    public String getErrorCode() {
		return errorCode;
	}
    
    public String getMessage(){
    	return message;
    }
}
