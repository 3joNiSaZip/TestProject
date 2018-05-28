package com.kh.jsp.notice.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;


public class NoticeService {
	
	public ArrayList<Notice> selectList() {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(con);
		close(con);
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection con = getConnection();
		
		int result = new NoticeDao().insertNotice(con, n);
		
		if(result > 0) {
			commit(con);
		}else rollback(con);
		
		close(con);
		
		return result;
	}

	public Notice selectOne(String num) {
		Connection con = getConnection();
		int result = 0;
		
		System.out.println("num : "+num);
		
		Notice n = new NoticeDao().selectOne(con, num);
		if(n!=null) {
			result= new NoticeDao().updateCount(con, n.getNno());
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		close(con);
		
		return n;
	}

	public int updateNotice(Notice n) {
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, n);
		
		if(result >0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteNotice(int nno) {
		Connection con = getConnection();
		int result = new NoticeDao().deleteNotice(con, nno);
		
		if(result==1) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Notice> searchNotice(String condition, String keyword) {
		Connection con = getConnection();
		ArrayList<Notice> list=null;
		NoticeDao nDao = new NoticeDao();
		
		if(condition.equals("title")) {
			list = nDao.searchTitle(con, keyword);
		}else if(condition.equals("writer")) {
			list = nDao.searchWriter(con, keyword);
		}else if(condition.equals("content")) {
			list = nDao.searchContent(con, keyword);
		}else {
			list = nDao.selectList(con);
		}
		
		close(con);
		
		return list;
	}
	
}






