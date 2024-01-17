package com.gyojincompany.home.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.home.dao.MemberDao;
import com.gyojincompany.home.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		
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
	
	@GetMapping(value = "/login")
	public String login() {
		
		return "login";
	}
	
	@PostMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		
		int checkFlag = dao.idPwCheckDao(request.getParameter("mid"), request.getParameter("mpw"));
		
		if(checkFlag == 1) { //아이디와 비밀번호가 모두 일치하는 레코드가 존재함->로그인 성공
			session.setAttribute("sessionId", request.getParameter("mid"));
			//로그인 성공시 세션에 현재 로그인한 아이디를 저장
			MemberDto memberDto = dao.memberInfoDao(request.getParameter("mid"));
			System.out.println(memberDto.getMdate());
			model.addAttribute("memberDto", memberDto);
			//로그인한 유저의 아이디로 검색하여 해당 유저의 모든 정보를 loginOk.jsp로 전달
		} else {
			model.addAttribute("loginFail", 1);
			//로그인 실패시 loginFail값을 1로 설정하여 loginOk.jsp로 전달
		}
		
		return "loginOk";
	}

}
