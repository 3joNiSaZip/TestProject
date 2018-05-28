package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;

@WebServlet("/bUpView.bo")
public class BoardUPdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUPdateViewServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("num"));
		
		Board b = new BoardService().selectOne(bno);
		
		String page= "";
		if(b != null){
			page="views/board/boardUpdate.jsp";
			request.setAttribute("b",b);
		}else{
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 수정 접근 실패");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
