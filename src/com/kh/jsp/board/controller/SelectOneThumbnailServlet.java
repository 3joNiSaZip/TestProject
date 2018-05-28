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
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;

/**
 * Servlet implementation class SelectOneThumbnailServlet
 */
@WebServlet("/selectOne.tn")
public class SelectOneThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println(num);
		
		HashMap<String, Object> hmap = new BoardService().selectThumbnailMap(num);
		
		System.out.println(hmap);
		
		Board b = (Board) hmap.get("board");
		ArrayList<Attachment> fileList = (ArrayList<Attachment>) hmap.get("attachment");
		
		String page = "";
		if(hmap != null && !hmap.isEmpty()) {
			page = "views/thumbnail/thumbnailDetail.jsp";
			request.setAttribute("b", b);
			request.setAttribute("fileList", fileList);
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "사진게시판 상세보기 실패");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
