package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/mUpdate.me")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("member");
		
		System.out.println("updateservlet111111 : "+m);
		
		String userPwd = request.getParameter("userPwd");
		int age = Integer.parseInt(request.getParameter("age"));
		String tel = request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3");
		String email = request.getParameter("email");
		String address = request.getParameter("zipCode")+", "+request.getParameter("address1")+", "+request.getParameter("address2");
		String hobby = String.join(", ",request.getParameterValues("hobby"));
		
		m.setPassword(userPwd);
		m.setAge(age);
		m.setPhone(tel);
		m.setEmail(email);
		m.setAddress(address);
		m.setHobby(hobby);
		
		System.out.println("updateservlet2222 : "+m);
		
		MemberService ms = new MemberService();
		if(ms.updateMember(m) != 0) {
			//회원 정보 수정 성공시
			System.out.println("회원 정보 수정 성공!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		
		}else {
			//회원 정보 수정 실시
			System.out.println("회원 정보 수정 실패!");
			request.setAttribute("msg", "회원 정보 수정 실패");
			response.sendRedirect("views/common/errorPage.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
