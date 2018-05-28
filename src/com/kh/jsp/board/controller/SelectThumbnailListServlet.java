package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;

/**
 * Servlet implementation class SelectThumbnailListServlet
 */
@WebServlet("/selectList.tn")
public class SelectThumbnailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectThumbnailListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("views/thumbnail/thumbnailList.jsp");
		
		ArrayList<HashMap<String, Object>> list = new BoardService().selectThumbnailList();
		
		System.out.println(list);
		
		String page = "";
		if(list != null) {
			page = "views/thumbnail/thumbnailList.jsp";
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "사진게시판 조회 실패!");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
