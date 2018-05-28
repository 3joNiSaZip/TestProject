package com.kh.jsp.board.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.*;
import java.util.*;

import com.kh.jsp.board.model.dao.BoardCommentDao;
import com.kh.jsp.board.model.vo.BoardComment;

public class BoardCommentService {
	
	public ArrayList<BoardComment> selectList(int bno) {
		Connection con = getConnection();
		BoardCommentDao bcDao = new BoardCommentDao();
		ArrayList<BoardComment> clist = bcDao.selectList(con, bno);
		
		if(clist !=null) commit(con);
		else rollback(con);
		
		close(con);
		return clist;
	}
	
	
	public int insertComment(BoardComment bco) {
		Connection con = getConnection();
		
		BoardCommentDao bcDao = new BoardCommentDao();
		
		int result = bcDao.insertComment(con, bco);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}


	public int updateComment(BoardComment bco) {
		Connection con = getConnection();
		BoardCommentDao bcDao= new BoardCommentDao();
		int result = bcDao.updateComment(con, bco);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}


	public int deleteComment(int cno) {
		Connection con =getConnection();
		BoardCommentDao bcDao = new BoardCommentDao();
		int result = bcDao.deleteComment(con, cno);
		
		if(result > 0)	commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	
	
}
