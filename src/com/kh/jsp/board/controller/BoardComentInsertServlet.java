package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardCommentService;
import com.kh.jsp.board.model.vo.BoardComment;

@WebServlet("/insertComment.bo")
public class BoardComentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardComentInsertServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("replyContent");
		
		BoardComment bco = new BoardComment();
		bco.setBno(bno);
		bco.setCwriter(writer);
		bco.setCcontent(content);
		
		int result = new BoardCommentService().insertComment(bco);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/selectOne.bo?num="+bno);
		}else {
			request.setAttribute("msg", "댓글 작성 실패!!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
