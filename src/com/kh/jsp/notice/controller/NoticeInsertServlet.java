package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");
		String title = request.getParameter("title");
		String date= request.getParameter("date");
		String content= request.getParameter("content");
		
		java.sql.Date day = null;
		if(date != "") {
			String[] dateArr = date.split("-");
			int[] drr = new int[dateArr.length];
			
			for(int i = 0;i<dateArr.length;i++) {
				drr[i] = Integer.parseInt(dateArr[i]);
			}
			day = new java.sql.Date(new GregorianCalendar(drr[0], drr[1]-1, drr[2]).getTimeInMillis());
		}else {
			day = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}
		
		//DB에 전달할 공지사항 글 한 개 생성
		Notice n = new Notice();
		
		n.setNtitle(title);
		n.setNwriter(userId);
		n.setNcontent(content);	
		n.setNdate(day);
		
		int result = new NoticeService().insertNotice(n);
		
		System.out.println("result : "+result);
		String page="";
		if(result > 0) {
			page="views/notice/noticeList.jsp";
			request.setAttribute("list", new NoticeService().selectList());
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 등록 실패!!");
		}
		
		request.getRequestDispatcher(page).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
