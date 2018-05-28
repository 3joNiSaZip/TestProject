package com.kh.jsp.board.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;

public class BoardDao {

	private Properties prop;
	
	public BoardDao() {
		prop = new Properties();
		String fileName = BoardDao.class.getResource("/config/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Board> list = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectList");
		try {
			/*stmt = con.createStatement();
			rset = pstmt.executeQuery(query);
			*/
			pstmt = con.prepareStatement(query);
			
			//조회할 숫자 startRow와 endRow 계산
			int startRow = (currentPage - 1) * limit+1;
			int endRow = startRow + (limit-1);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			
			while(rset.next()) {

				int bno = rset.getInt(2);
				String title = rset.getString(3);
				String content = rset.getString(4);
				String writer = rset.getString(5);
				int count = rset.getInt(6);
				String file = rset.getString(7);
				Date bdate = rset.getDate(8);
				
				list.add(new Board(bno, title, content, writer, count, file, bdate));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			//close(stmt);
			close(pstmt);
		}

		return list;
	}

	public int insertBoard(Connection con, Board b) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		System.out.println(b);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			pstmt.setString(3, b.getBwriter());
			pstmt.setString(4, b.getBoardfile());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(rset);
		}
			
		return result;
	}

	public Board selectOne(Connection con, int bno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = new Board();
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b.setBno(bno);
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("USERNAME"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBoardfile(rset.getString("BOARDFILE"));
				b.setBdate(rset.getDate("BDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public int updateCount(Connection con, int num) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateCount(Connection con, Board b) {
		PreparedStatement pstmt = null;
		int result= 0;
		
		String query = prop.getProperty("updateCount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b.getBno());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection con, Board b) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			pstmt.setString(3, b.getBoardfile());
			pstmt.setInt(4, b.getBno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int deleteBoard(Connection con, int bno) {
		PreparedStatement pstmt = null;
		int result =0 ; 
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int insertThumbnailContent(Connection con, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			pstmt.setString(3, b.getBwriter());
			pstmt.setNull(4, Types.VARCHAR);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int bno = 0;

		String query = prop.getProperty("selectCurrval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				bno = rset.getInt("currval");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return bno;
	}

	public int insertAttachment(Connection con, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		System.out.println(fileList);
		
		try {
			for(int i = 0; i < fileList.size(); i++){
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, fileList.get(i).getBno());
				pstmt.setString(2, fileList.get(i).getOriginName());
				pstmt.setString(3, fileList.get(i).getChangeName());
				pstmt.setString(4, fileList.get(i).getFilePath());
				
				int level = 0;
				if(i != 0)	level = 1;
				
				pstmt.setInt(5, level);
				
				result += pstmt.executeUpdate();
			}
			System.out.println("result : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<HashMap<String, Object>> selectThumbnailList(Connection con) {
		Statement stmt = null;
		ArrayList<HashMap<String, Object>> list = null;
		HashMap<String, Object> hmap = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectThumbnailMap");
		
		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<HashMap<String, Object>>(); 
			
			while(rset.next()) {
				hmap = new HashMap<String, Object>();
				
				hmap.put("bno", rset.getInt("bno"));
				hmap.put("btitle", rset.getString("btitle"));
				hmap.put("bcontent", rset.getString("bcontent"));
				hmap.put("bwriter", rset.getString("username"));
				hmap.put("bcount", rset.getString("bcount"));
				hmap.put("bdate", rset.getDate("bdate"));
				hmap.put("fno", rset.getInt("fno"));
				hmap.put("originName", rset.getString("origin_name"));
				hmap.put("changeName", rset.getString("change_name"));
				hmap.put("filePath", rset.getString("file_path"));
				hmap.put("uploadDate", rset.getString("upload_date"));
				
				list.add(hmap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public HashMap<String, Object> selectThumbnailMap(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Object> hmap = null;
		Board b = null;
		Attachment at = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectThumbnailOne");
				
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			
			//System.out.println(rset.getArray(1));
			
			while(rset.next()){
				
				b = new Board();
				b.setBno(rset.getInt("bno"));
				b.setBtitle(rset.getString("btitle"));
				b.setBcontent(rset.getString("bcontent"));
				b.setBwriter(rset.getString("username"));
				b.setBcount(rset.getInt("bcount"));
				b.setBdate(rset.getDate("bdate"));
				
				at = new Attachment();
				at.setFno(rset.getInt("fno"));
				at.setOriginName(rset.getString("origin_name"));
				at.setChangeName(rset.getString("change_name"));
				at.setFilePath(rset.getString("file_path"));
				at.setUploadDate(rset.getDate("upload_date"));
				
				System.out.println(at);
				
				list.add(at);
				
			}
			hmap = new HashMap<String, Object>();
			
			hmap.put("board", b);
			hmap.put("attachment", list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hmap;
	}

	public Attachment selectOneAttachment(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Attachment file = null;
		
		String query = prop.getProperty("selectOneAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				file = new Attachment();
				
				file.setFno(rset.getInt("fno"));
				file.setBno(rset.getInt("bno"));
				file.setOriginName(rset.getString("origin_name"));
				file.setChangeName(rset.getString("change_name"));
				file.setFilePath(rset.getString("file_path"));
				file.setUploadDate(rset.getDate("upload_date"));
				file.setFileLevel(rset.getInt("file_level"));
				file.setDownloadCount(rset.getInt("download_count"));
				file.setDelflag(rset.getString("delflag"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return file;
	}

	public List<Board> selectTop5(Connection con) {
		
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTop5");
		Board b = null;
		List<Board> bList = new ArrayList<Board>();
		
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				b=new Board();
				b.setBno(rset.getInt("BNO"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBwriter(rset.getString("BWRITER"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBcount(rset.getInt("BCOUNT"));
				bList.add(b);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return bList;
	}
}
