package com.kh.jsp.member.model.service;

import java.sql.Connection;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

public class MemberService {

	private MemberDao mDao = null;
	
	public MemberService() {
		mDao = new MemberDao();
	}
	
	public Member selectMember(Member m) {
		Member resultMember = mDao.selectMember(m);
		System.out.println(resultMember);
		return resultMember;
	}

	public int insertMember(Member m) {
		int result = 0;
		result = mDao.insertMember(m);
		
		return result;
	}

	public int updateMember(Member m) {
		int result = 0;
		result = mDao.updateMember(m);
		return result;
	}

	public int deleteMember(String userId) {
		int result = 0;
		result = mDao.updateMember(userId);
		return result;
	}

	public int checkId(String userId) {
		int result = -1;
		result = mDao.checkId(userId);
		return result;
	}
	
	
}
