package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardCommentService;
import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.BoardComment;

@WebServlet("/selectOne.bo")
public class BoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno =Integer.parseInt(request.getParameter("num"));
		Board b = new BoardService().selectOne(bno);
		ArrayList<BoardComment> clist = new BoardCommentService().selectList(bno);
		
		String page="";
		if(b != null) {
			page = "views/board/boardDetail.jsp";
			request.setAttribute("b", b);
			request.setAttribute("clist", clist);
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세 조회 실패!!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
