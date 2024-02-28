package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/guest/list", method={RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("Controller.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeList();
		model.addAttribute("gblist", guestbookList);
		System.out.println(guestbookList);
		
		return "/WEB-INF/views/addList.jsp";
	}
	
	@RequestMapping(value="/guest/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@RequestParam(value="name") String name,
						 @RequestParam(value="pw") String pw,
						 @RequestParam(value="content") String content) {
		System.out.println("Controller.insert()");
		
		GuestbookVo guestbookVo = new GuestbookVo(name, pw, content);
		guestbookService.exeInsert(guestbookVo);
		
		
		return "redirect:/guest/list";
	}
	
	@RequestMapping(value="/guest/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteFrom(@RequestParam("no") int no, Model model) {
		System.out.println("Controller.deleteForm()");
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
//	@RequestMapping(value="/guest/delete", method= {RequestMethod.GET, RequestMethod.POST})
//	public String delete2(@RequestParam("no") int no, 
//						 @RequestParam("pass") String pw) {
//		System.out.println("Controller.delete()");
//		
//		guestbookService.exeDelete(pw, no);
//		
//		return "redirect:/guest/list";
//	}
	@RequestMapping(value="/guest/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no, 
						 @RequestParam("pass") String pw) {
		System.out.println("Controller.delete()");
		
		GuestbookVo guestbookVo = new GuestbookVo(no, pw);
		guestbookService.exeDelete(guestbookVo);
		
		return "redirect:/guest/list";
	}
	
}
