package com.kh.jsp.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bfdown.bo")
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardFileDownServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("path");
		
		// 저장된 폴더의 경로 가져오기
		String saveFoler = request.getServletContext().getRealPath("/resources/bUploadFiles");
		
		// 파일을 서버에서 내보낼 출력 스트림을 위한 객체 선언 및 생성( 다운로드 주체 )
		ServletOutputStream downOut = response.getOutputStream();
		
		// 네트워크 상의 헤더 정보에 포함시켜 파일을 전송 할 설정 정보를 등록한다.
		response.addHeader("Content-Disposition", "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
		
		File downFile = new File(saveFoler + "/"+fileName);
		
		// 전송할 파일의 크기만큼 응답할 데이터 공간을 마련한다.
		response.setContentLength((int)downFile.length());
		
		// 폴더에서 파일 읽기용 스트림 객체를 선언 
		BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(downFile));
		
		// 주어진 파일의 Byte 수 만큼 파일을 읽어온다.
		int readData = 0;
		while((readData = bin.read()) != -1) {
			downOut.write(readData);
		}
		
		downOut.close();
		bin.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
