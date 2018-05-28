package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.common.MyFileRenamePolicy;
import com.kh.jsp.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class InsertThumbnailServlet
 */
@WebServlet("/insert.tn")
public class InsertThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		System.out.println(title);
		
		//폼전송을 multipart/form-data로 전송하는 경우에는 기존처럼
		//request.getParameter로 값을 받을 수 없다.
		//cos.jar가 파일도 받고 폼의 다른 값을도 받아주는 역할을 한다.
		//com.orelilly.servlet의 약자이다.
		if(ServletFileUpload.isMultipartContent(request)) {
			// 전송 파일 용량 제한 : 10Mbyte 제한한 경우
			int maxSize = 1024 * 1024 * 10;

			// 웹서버 컨테이너 경로 추출함
			String root = request.getSession().getServletContext().getRealPath("/resources");
			
			System.out.println(root);
						
			// 파일 저장 경로(ex : web/board_uploadFiles/) 정함
			String savePath = root + "/thumbnail_uploadFiles/";
			
			//객체 생성시 파일을 저장하고 그에 대한 정보를 가져오는 형태이다.
			//즉 파일의 정보를 검사하여 저장하는 형태가 아닌, 저장한 다음 검사 후 삭제를 해야 한다.
			
			//사용자가 올린 파일명을 그대로 저장하지는 않는 것이 일반적이다.
			//1. 같은 파일명이 있는 경우 이전 파일을 덮어 쓸 수 있다.
			//2. 한글로 된 파일명, 특수기호나 띄어쓰기 등은 서버에 따라 문제가 생길 수 도 있다.
			//DefaultFileRenamePolicy는 cos.jar안에 존재하는 클래스로
			//같은 파일 명이 존재하는지를 검사하고 있을 경우에는 파일명 뒤에 숫자를 붙여준다.
			//ex : aaa.zip, aaa1.zip, aaa2.zip
			
			//DefaultFileRenamePolicy 사용
			//MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",new DefaultFileRenamePolicy());
			
			//FileRenamePolicy 상속 후 오버라이딩
			MultipartRequest multiRequest
			  = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			//다중 파일을 묶어서 업로드 하기에 컬렉션을 사용한다.
			//저장한 파일의 이름을 저장할 arrayList를 생성한다.
			ArrayList<String> saveFiles = new ArrayList<String>();
			//원본 파일의 이름을 저장할 arrayList를 생성한다.
			ArrayList<String> originFiles = new ArrayList<String>();
			
			//파일이 전송된 폼의 이름을 반환한다.
			Enumeration<String> files = multiRequest.getFileNames();
			
			//각 파일의 정보를 구해 온 후 DB에 저장할 목적의 데이터를 꺼내온다.
			while(files.hasMoreElements()){
				String name = files.nextElement();
				
				System.out.println("name : " + name);
				//저장된 경로에 저장된 파일 시스템 이름을 가져온다
				saveFiles.add(multiRequest.getFilesystemName(name));
				originFiles.add(multiRequest.getOriginalFileName(name));
				
				System.out.println("filesystem name : " + multiRequest.getFilesystemName(name));
				System.out.println("originFile : " + multiRequest.getOriginalFileName(name));
			}
			
			//multipartRequest객체에서 파일 외의 값을 가져올 수도 있다.
			String multiTitle = multiRequest.getParameter("title");
			String multiContent = multiRequest.getParameter("content");
			System.out.println(multiTitle);
			System.out.println(multiContent);
			
			
			//Board객체 생성
			Board b = new Board();
			b.setBtitle(multiTitle);
			b.setBcontent(multiContent);
			b.setBwriter(String.valueOf(((Member)(request.getSession().getAttribute("member"))).getUserId()));
			
			//Attachment객체 생성하여 arrayList 객체 생성
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			//전송순서 역순으로 파일이 list에 저장되기 때문에 반복문을 역으로 수행함
			for(int i = originFiles.size() - 1; i >= 0 ; i--){
				Attachment at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				System.out.println(at);
				fileList.add(at);
				
			}
			System.out.println(fileList);
			//service로 전송
			int result = new BoardService().insertThumbnail(b, fileList);
			
			System.out.println(result);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/selectList.tn");
			}else {
				//실패시 저장된 사진 삭제
				for(int i = 0; i < saveFiles.size(); i++) {
					//파일시스템에 저장된 이름으로 파일 객체 생성함
					File failedFile = new File(savePath + saveFiles.get(i));
					System.out.println(failedFile);
					//true false 리턴됨
					//실행하고 출력하면 당연히 false나오기 때문에 출력하면서 삭제
					System.out.println(failedFile.delete());
				}
				//에러페이지로 메세지 전달
				request.setAttribute("msg", "사진게시판 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
