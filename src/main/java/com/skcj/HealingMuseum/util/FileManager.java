package com.skcj.HealingMuseum.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	public static boolean deleteFiles(String path){ 
    	boolean deleteFlag = false;
    	File f = new File(path);
    	
    	if(f.exists()){
    		deleteFlag = f.delete();
    	}

    	return deleteFlag;
	}
	
	public static boolean deleteAllFiles(String path){ 
		File file = new File(path); 
		File[] tempFile = file.listFiles(); //폴더내 파일을 배열로 가져온다.
		if(tempFile.length >0){ 
			for (int i = 0; i < tempFile.length; i++) { 
				if(tempFile[i].isFile()){ 
					tempFile[i].delete();
				}else{ //재귀함수 
					deleteAllFiles(tempFile[i].getPath()); 
				} 
				tempFile[i].delete(); 
			} 
		}else{
			logger.debug("path가 존재하지 않습니다.");
		}
			
		return file.delete(); 
		
	}

	
}
