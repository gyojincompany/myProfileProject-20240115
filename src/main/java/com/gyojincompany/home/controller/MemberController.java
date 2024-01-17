package com.gyojincompany.home.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.home.dao.BoardDao;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/")
	public String root() {
		
		return "index";
	}
	
	@GetMapping(value = "/index")
	public String index() {
		
		return "index";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		
		return "join";
	}
	
	@PostMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		int checkFlag = dao.idCheckDao(request.getParameter("mid"));
		//checkFlag가 0이면 회원가입가능, 1이면 회원가입불가
		if(checkFlag == 0) {
			dao.joinDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("memberName", request.getParameter("mname"));
			model.addAttribute("memberId", request.getParameter("mid"));
		} else {
			model.addAttribute("joinFail", 1);
		}
		
		return "joinOk";
	}
	
	

}
