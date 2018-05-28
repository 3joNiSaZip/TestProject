package com.kh.jsp.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*
	 Singleton 패턴을 활용한 DB Connection 객체 사용하기
	 
	 -- Singleton 패턴 --
	 한 프로젝트 내에서 단 하나의 객체만으로 특정 로직을 수행하는 클래스 설계 방식
	 
	 */
	public static Connection getConnection() {
		Connection con = null;
		Properties prop = new Properties();
		
		String fileName = JDBCTemplate.class.getResource("/config/driver.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
			con.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		try {
			if(con !=null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
			try {
				if(stmt !=null && !stmt.isClosed()) {
				stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			if(con !=null && !con.isClosed()) {
				con.commit();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			if(con !=null && !con.isClosed()) {
				con.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
