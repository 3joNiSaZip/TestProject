package com.kh.jsp.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{
	
	@Override
	public File rename(File oldFile){
		long currentTime = System.currentTimeMillis();
		System.out.println(currentTime);
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		int randomNumber = (int)(Math.random()*100000);
		
		//확장자명 가져오기
		String name = oldFile.getName();
		String body = null;
		String ext = null;
		int dot = name.lastIndexOf(".");
		if(dot != -1){
			//dot전까지
			body = name.substring(0, dot);
			//dot 포함
			ext = name.substring(dot);
		}else{
			//확장자가 없는 경우
			body = name;
			ext = "";
		}
		
		String fileName = ft.format(new Date(currentTime)) + randomNumber + ext;
		
		File newFile = new File(oldFile.getParent(), fileName);
		
		return newFile;
	}

}
