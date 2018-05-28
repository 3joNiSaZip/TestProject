package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/deleteBoard.bo")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "form-data 타입으로 전송해야 합니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		// 웹 루트 경로 확보
		String root = request.getServletContext().getRealPath("/");

		// 업로드 되는 파일이 저장 될 폴더 생성 및 경로 설정
		String savePath = root+"resources/bUploadFiles";
		
		// request -> MultipartRequest
		MultipartRequest mrequest = new MultipartRequest(request, savePath);

		int bno = Integer.parseInt(mrequest.getParameter("bno"));
		int result=new BoardService().deleteBoard(bno);
		String page="";
		if(result>0) {
			page="/selectList.bo";
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 삭제 실패!!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
