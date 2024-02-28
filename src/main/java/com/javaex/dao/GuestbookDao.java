package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> guestSelect() {
		System.out.println("GuestbookDao.guestSelect");
		
		List<GuestbookVo> guestbookVo = sqlSession.selectList("guestbook.select");
		
		return guestbookVo;
	}
	
	public int userInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.userInsert");
		
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		
		return count;
	}//userInsert()
	
	
	public int userDelete(GuestbookVo vo) {
		System.out.println("GuestbookDao.userDelete");
		
		int count = sqlSession.delete("guestbook.delete", vo);
		
		return count;
	}//userInsert()
	
	
	
}
