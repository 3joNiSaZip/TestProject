package com.kh.jsp.board.model.dao;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import com.kh.jsp.board.model.vo.BoardComment;

public class BoardCommentDao {
	private Properties prop;
	
	public BoardCommentDao() {
		prop = new Properties();
		String fileName = BoardCommentDao.class.getResource("/config/board/boardcomment-query.properties").getPath();
		
		try {
			
			prop.load(new FileReader(fileName));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertComment(Connection con, BoardComment bco) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertComment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bco.getBno());
			pstmt.setString(2, bco.getCcontent());
			pstmt.setString(3, bco.getCwriter());
			
			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<BoardComment> selectList(Connection con, int bno) {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BoardComment> clist =null;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			clist = new ArrayList<BoardComment>();
			
			while(rset.next()) {
				BoardComment bco = new BoardComment();
				bco.setCno(rset.getInt("CNO"));
				bco.setBno(bno);
				bco.setCcontent(rset.getString("CCONTENT"));
				bco.setCwriter(rset.getString("USERNAME"));
				bco.setCdate(rset.getDate("CDATE"));
				
				clist.add(bco);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return clist;
	}

	public int updateComment(Connection con, BoardComment bco) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateComment");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bco.getCcontent());
			pstmt.setInt(2, bco.getCno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection con, int cno) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query = prop.getProperty("deleteComment");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
