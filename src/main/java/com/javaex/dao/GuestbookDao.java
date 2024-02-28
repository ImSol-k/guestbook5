package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}//getConnection()
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}//close()
	
	public void userInsert(GuestbookVo vo) {
		getConnection();
		
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();
		
		try {
			String query = "";
			query += " insert into guestbook ";
			query += " values(null, ?, ?, ?, now()) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getContent());
			
			GuestbookVo guestVo = new GuestbookVo(vo.getName(), vo.getPw(), vo.getContent());
			guestList.add(guestVo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
	}//userInsert()
	
	public GuestbookVo userDelete(String pw, int no) {
		getConnection();
		try {
			String query = "";
			query += " delete from guestbook where pw = ? and book_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setInt(2, no);
			
			int count = pstmt.executeUpdate();
			System.out.println(count+"건 성공");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return null;
	}//userInsert()
	
	public List<GuestbookVo> guestSelect() {
		getConnection();
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();
		
		try {
			String query = "";
			query += " select book_id, name, pw, content, date from guestbook ";
			
			pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String content = rs.getString("content");
				String date = rs.getString("date");
				
				GuestbookVo guestVo = new GuestbookVo(bookId, name, pw, content, date);
				guestList.add(guestVo);
				
			}
			
			for (int i = 0; i < guestList.size(); i++) {
				System.out.println("Dao: " + guestList.get(i).toString());
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return guestList;
	}
	
}
