
package com.kh.jsp.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

@WebServlet("/selectOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		
		Notice n = new NoticeService().selectOne(num);
		String page = "";
		if(n!=null) {
			page = "views/notice/noticeDetail.jsp";
			request.setAttribute("n", n);
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 상세 보기 실패");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
