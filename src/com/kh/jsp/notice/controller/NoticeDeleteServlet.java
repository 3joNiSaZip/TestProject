package com.kh.jsp.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;

@WebServlet("/deleteNotice.no")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		int result = new NoticeService().deleteNotice(nno);
		
		String page="";
		if(result>0) {
			page="selectList.no";
		}else {
			page="veiws/common/errorPage.jsp";
			request.setAttribute("msg", "공지 사항 삭제 실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
