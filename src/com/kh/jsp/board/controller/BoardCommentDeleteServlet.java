package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardCommentService;

@WebServlet("/deleteComment.bo")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardCommentDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int result = new BoardCommentService().deleteComment(cno);
		
		String page="";
		if(result > 0) {
			page="selectOne.bo?num="+bno;
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "댓글 수정 실패!!");
		}
	request.getRequestDispatcher(page).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
