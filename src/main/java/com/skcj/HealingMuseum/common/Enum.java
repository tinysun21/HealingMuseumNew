package com.skcj.HealingMuseum.common;

public class Enum {
	public enum WebPathEnum{  
		WEB_ABSOLUTE_PATH("http://52.68.52.65:12010")
		,WEB_RELATIVE_PATH("/home/ubuntu/healing-museum/webapps")
		,USER_IMAGE_SAVE_PATH("images/USER_IMAGES")
		,ADMIN_IMAGE_SAVE_PATH("images/ADMIN_IMAGES");
		
		private String path;

	    private WebPathEnum(String path) {
	        this.path = path; 
	    }

	    public String getString() {
	        return path; 
	    }
	}
	
	public enum CommonStatusEnum{  
		SUCCESS(1)
		,FAIL(0);
		
		private int status;

	    private CommonStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum ImageStatusEnum{  
		IMAGE_WRITE_SUCCESS(1)
		,IMAGE_WRITE_FAIL(0)
		,IMAGE_UPDATE_SUCCESS(1)
		,IMAGE_UPDATE_FAIL(0);
		
		private int status;

	    private ImageStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum MBoardStatusEnum{  
		BOARD_WRITE_SUCCESS(1)
		,BOARD_WRITE_FAIL(-1)
		,BOARD_UPDATE_SUCCESS(2)
		,BOARD_UPDATE_FAIL(-2)
		,BOARD_DELETE_SUCCESS(3)
		,BOARD_DELETE_FAIL(-3);
		
		private int status;

	    private MBoardStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum MemberIdStatusEnum{  
		ID_EXTST(1)
		,ID_NOT_EXTST(-1);
		
		private int status;

	    private MemberIdStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum MemberLoginStatusEnum{  
		MEMBER_LOGIN_SUCCESS(1)
		,ADMIN_LOGIN_SUCCESS(10)
		,ID_NOT_EXTST(0)
		,WRONG_PASSWORD(-1)
		,ETC_ERROR(-9)
		,LOG_OUT(-2);
		
		private int status;

	    private MemberLoginStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum UserImageStatusEnum{  
		IMAGE_WRITE_SUCCESS(1)
		,IMAGE_WRITE_FAIL(0)
		,IMAGE_WRITE_FAIL_NO_LOGIN(-1)
		,IMAGE_UPDATE_SUCCESS(1)
		,IMAGE_UPDATE_FAIL(0)
		,IMAGE_UPDATE_FAIL_NO_LOGIN(-1)
		,IMAGE_DELETE_SUCCESS(2)
		,IMAGE_DELETE_FAIL(-2);
		
		private int status;

	    private UserImageStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
	public enum MypageStatusEnum{  
		MYPAGE_WRITE_SUCCESS(1)
		,MYPAGE_WRITE_FAIL(-1)
		,MYPAGE_UPDATE_SUCCESS(2)
		,MYPAGE_UPDATE_FAIL(-2)
		,MYPAGE_DELETE_SUCCESS(3)
		,MYPAGE_DELETE_FAIL(-3);
		
		private int status;

	    private MypageStatusEnum(int status) {
	        this.status = status; 
	    }

	    public int getInt() {
	        return status; 
	    }
		
	}
	
}
