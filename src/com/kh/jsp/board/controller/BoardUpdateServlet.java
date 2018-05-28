package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardCommentService;
import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.BoardComment;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardUpdateServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- 첨부 파일 업로드용 로직 작성 부분  --//
		
		// 업로드할 파일의 용량을 제한(10MB)
		int maxSize = 1024 * 1024 * 10;
		
		//enctype으로 전달이 되었는지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "form-data 타입으로 전송해야 합니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
		
		// 웹 루트 경로 확보
		String root = request.getServletContext().getRealPath("/");

		// 업로드 되는 파일이 저장 될 폴더 생성 및 경로 설정
		String savePath = root+"resources/bUploadFiles";
		
		// request -> MultipartRequest
		MultipartRequest mrequest = new MultipartRequest(
				request,
				savePath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		
		
		//-- 첨부 파일 업로드용 로직 작성 부분  --//
		
		
		BoardService bService = new BoardService();
		
		
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		int bno = Integer.parseInt(mrequest.getParameter("bno"));
		String fileName = mrequest.getParameter("file");
		
		System.out.println(bno);
		
		Board b = bService.selectOne(bno);
		
		ArrayList<BoardComment> clist = new BoardCommentService().selectList(bno);
		
		b.setBno(bno);
		b.setBtitle(title);
		b.setBcontent(content);
		
		if(fileName != null) {
			File originFile = new File(savePath+"/"+b.getBoardfile());
			originFile.delete();
			b.setBoardfile(fileName);
		}
		
		
		int result = bService.updateBoard(b);
		
		String page="";
		if(result>0){
			page="views/board/boardDetail.jsp";
			request.setAttribute("b", new BoardService().selectOne(bno));
			request.setAttribute("clist", clist);
		}else{
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 수정 실패!!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
