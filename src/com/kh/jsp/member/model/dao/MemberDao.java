package com.kh.jsp.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jsp.member.model.vo.Member;

public class MemberDao {

	public Member selectMember(Member m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member resultMember = null;
		System.out.println("Dao로 넘어온 값 : "+m);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jsp","jsp");
			con.setAutoCommit(false);
			con.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?";
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				resultMember =new Member();
				
				resultMember.setUserId(rset.getString("USERID"));
				resultMember.setPassword(rset.getString("PASSWORD"));
				resultMember.setUserName(rset.getString("USERNAME"));
				resultMember.setGender(rset.getString("GENDER"));
				resultMember.setAge(rset.getInt("AGE"));
				resultMember.setEmail(rset.getString("EMAIL"));
				resultMember.setPhone(rset.getString("PHONE"));
				resultMember.setAddress(rset.getString("ADDRESS"));
				resultMember.setHobby(rset.getString("HOBBY"));
				resultMember.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("DB에서 검색된 결과 : "+resultMember);
		return resultMember;
	}

	public int insertMember(Member m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		System.out.println("Dao로 넘어온 값 : "+m);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jsp","jsp");
			con.setAutoCommit(false);
			con.createStatement();
			
			String query = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";

			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			result = pstmt.executeUpdate();
			
			if(result > 0) con.commit();
			else con.rollback();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			// 7. 모든 처리가 완료된 후에는 DB객체들을 닫아준다. 
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("DB에서 나온 결과 : "+result);
		return result;
	}

	public int updateMember(Member m) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result=0;
		
		System.out.println("Dao로 넘어온 값 : "+m);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jsp","jsp");
			con.createStatement();
			
			String query = "UPDATE MEMBER SET "
					+ "PASSWORD=?, AGE=?, EMAIL=?, PHONE=?, "
					+ "ADDRESS=?, HOBBY=? WHERE USERID=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, m.getPassword());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getHobby());
			pstmt.setString(7, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) con.commit();
			else con.rollback();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		System.out.println("DB에서 나온 결과 : "+result);
		return result;
	}

	public int updateMember(String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jsp","jsp");
			con.createStatement();
			
			String query ="DELETE FROM MEMBER WHERE USERID=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) con.commit();
			else con.rollback();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}

	public int checkId(String userId) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","jsp","jsp");
			con.createStatement();
			
			String query ="SELECT * FROM MEMBER WHERE USERID=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result++;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	
		return result;
	}
}
