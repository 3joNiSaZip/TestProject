package com.kh.jsp.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateViewServlet
 */
@WebServlet("/nUpView.no")
public class NoticeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		
		Notice n = new NoticeService().selectOne(num);
		
		String page = "";
		if(n != null) {
			page = "views/notice/noticeUpdate.jsp";
			request.setAttribute("n", n);
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 수정 페이지 접근 실패");
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
