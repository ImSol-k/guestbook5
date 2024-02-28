package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> exeList() {
		System.out.println("service.exeList()");
		
		List<GuestbookVo> guestbookList = guestbookDao.guestSelect();
		
		return guestbookList;		
	}
	
	public void exeInsert(GuestbookVo guestbookVo) {
		System.out.println("service.exeInsert()");
				
		guestbookDao.userInsert(guestbookVo);
		
	}
	
	public void exeDelete(GuestbookVo guestbookvo) {
		System.out.println("service.exeDelete()");
		
		guestbookDao.userDelete(guestbookvo);
		
	}
	
}
