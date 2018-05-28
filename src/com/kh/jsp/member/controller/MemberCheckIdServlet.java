package com.kh.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.model.service.MemberService;

@WebServlet("/checkId.me")
public class MemberCheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberCheckIdServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		MemberService mService = new MemberService();
		System.out.println("userId");

		int result = mService.checkId(userId);
		System.out.println("result : "+result);
		
		
		if(result == 0) {
			response.getWriter().print("사용 가능한 아이디입니다.");
		}else {
			response.getWriter().print("이미 존재하는 아이디입니다.");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
