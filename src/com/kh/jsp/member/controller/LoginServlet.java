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
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member m = new Member(userId, userPwd);
		
		MemberService ms = new MemberService();
		
		m = ms.selectMember(m);
		
		if(m != null) {
			//로그인 성공시
			System.out.println("로그인 성공");
			response.sendRedirect("index.jsp");
			
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
		}else {
			//로그인 실패시
			request.setAttribute("msg", "회원 로그인 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
			//RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp?msg=회원로그인실패");
			//view.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
