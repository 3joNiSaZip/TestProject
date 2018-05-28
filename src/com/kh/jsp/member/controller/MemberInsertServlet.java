package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String tel = request.getParameter("tel1")+"-"+ request.getParameter("tel2")+"-"+request.getParameter("tel3");
		String email = request.getParameter("email");
		String address = request.getParameter("zipCode")+", "+request.getParameter("address1")+", "+request.getParameter("address2");
		String hobby = String.join(", ",request.getParameterValues("hobby"));
		

		Member m = new Member(userId, userPwd, userName, gender, age, email, tel, address, hobby);
		
		MemberService ms = new MemberService();
		
		if(ms.insertMember(m) != 0) {
			System.out.println("회원가입 성공");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}else {
			System.out.println("회원가입 실패");
			request.setAttribute("msg", "회원 가입 실패!!");
			response.sendRedirect("views/common/errorPage.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
