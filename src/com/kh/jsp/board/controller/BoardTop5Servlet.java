package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;

@WebServlet("/boardtop5.do")
public class BoardTop5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardTop5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> bList = new BoardService().selectTop5();

		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(bList, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
