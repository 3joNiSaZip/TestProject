package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

@WebServlet("/searchNotice.no")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition = request.getParameter("con");
		String keyword = request.getParameter("keyword");
		
		ArrayList<Notice> list = new NoticeService().searchNotice(condition, keyword);
		
		String page="";
				
		if(list != null) {
			page="views/notice/noticeList.jsp";
			request.setAttribute("list", list);
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "공지 사항 검색 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
