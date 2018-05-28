package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.member.model.service.MemberService;

@WebServlet("/mDelete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("mid");
		MemberService ms = new MemberService();
		
		HttpSession session = request.getSession(false);
		
		if(ms.deleteMember(userId)!=0) {
			System.out.println("회원 삭제 성공!");
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		}else {
			System.out.println("회원삭제에 실패했습니다.");
			request.setAttribute("msg", "회원 삭제 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
