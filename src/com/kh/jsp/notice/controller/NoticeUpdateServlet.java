package com.kh.jsp.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

@WebServlet("/update.no")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		Notice n = new Notice();
		n.setNtitle(title);
		n.setNcontent(content);
		n.setNno(nno);
		
		int result = new NoticeService().updateNotice(n);
		
		String page="";
		if(result > 0 ) {
			page="views/notice/noticeDetail.jsp";
			request.setAttribute("n", new NoticeService().selectOne(String.valueOf(nno)));
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지 사항 수정 실패!!");
			
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
