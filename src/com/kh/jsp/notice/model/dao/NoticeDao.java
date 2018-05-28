package com.kh.jsp.notice.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jsp.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop;
	
	public NoticeDao() {
		prop = new Properties();
		String fileName=NoticeDao.class.getResource("/config/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Notice> selectList(Connection con) {

		//공지사항 글 여러개 저장용 객체
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectList");
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNno(rset.getInt("NNO"));
				n.setNtitle(rset.getString("NTITLE"));
				n.setNcontent(rset.getString("NCONTENT"));
				n.setNwriter(rset.getString("USERNAME"));
				n.setNcount(rset.getInt("NCOUNT"));
				n.setNdate(rset.getDate("NDATE"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	public int insertNotice(Connection con, Notice n) {
		int result=0;
		PreparedStatement pstmt = null;
		try {
			String query = prop.getProperty("insertNotice");
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setString(3, n.getNwriter());
			pstmt.setDate(4, n.getNdate());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice selectOne(Connection con, String num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;

		String query= prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(num));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n=new Notice();
				n.setNno(rset.getInt("NNO"));
				n.setNtitle(rset.getString("NTITLE"));
				n.setNcontent(rset.getString("NCONTENT"));
				n.setNwriter(rset.getString("USERNAME"));
				n.setNcount(rset.getInt("NCOUNT"));
				n.setNdate(rset.getDate("NDATE"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	public int updateCount(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateCount");

		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateNotice(Connection con, Notice n) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query = prop.getProperty("updateNotice");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setInt(3, n.getNno());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteNotice(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> searchTitle(Connection con, String keyword) {
		ArrayList<Notice> list =null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("searchTitle");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			list=new ArrayList<Notice>();
			
			while(rset.next()) {
				int nno = rset.getInt("nno");
				String title = rset.getString("ntitle");
				String content=rset.getString("ncontent");
				String writer = rset.getString("username");
				int count = rset.getInt("ncount");
				Date date = rset.getDate("ndate");
				
				list.add(new Notice(nno, title, content, writer, count, date));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}

	public ArrayList<Notice> searchWriter(Connection con, String keyword) {
ArrayList<Notice> list =null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("searchWriter");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			list=new ArrayList<Notice>();
			
			while(rset.next()) {
				int nno = rset.getInt("nno");
				String title = rset.getString("ntitle");
				String content=rset.getString("ncontent");
				String writer = rset.getString("username");
				int count = rset.getInt("ncount");
				Date date = rset.getDate("ndate");
				
				list.add(new Notice(nno, title, content, writer, count, date));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}

	public ArrayList<Notice> searchContent(Connection con, String keyword) {
		ArrayList<Notice> list =null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("searchContent");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			list=new ArrayList<Notice>();
			
			while(rset.next()) {
				int nno = rset.getInt("nno");
				String title = rset.getString("ntitle");
				String content=rset.getString("ncontent");
				String writer = rset.getString("username");
				int count = rset.getInt("ncount");
				Date date = rset.getDate("ndate");
				
				list.add(new Notice(nno, title, content, writer, count, date));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}

}
