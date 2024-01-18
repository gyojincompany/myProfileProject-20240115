package com.gyojincompany.home.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gyojincompany.home.dao.BoardDao;
import com.gyojincompany.home.dao.MemberDao;
import com.gyojincompany.home.dto.MemberDto;
import com.gyojincompany.home.dto.QAboardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/board")
	public String board(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		List<QAboardDto> dtos = dao.listDao();
		
		model.addAttribute("list", dtos);
		
		return "list";
	}
	
	@GetMapping(value = "/writeForm")
	public String writeForm(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		
		String sessionId = (String) session.getAttribute("sessionId");
		
		if(sessionId == null) {//로그인하지 않은 회원이 글쓰기 버튼 클릭한 경우
			response.setContentType("text/html;charset=utf-8");//utf-8로 경고창에 출력될 문자셋 셋팅
			response.setCharacterEncoding("utf-8");
			
			PrintWriter printout = response.getWriter();
			
			printout.println("<script>alert('"+ "로그인한 회원만 글을 쓸 수 있습니다." +"');location.href='"+"login"+"';</script>");
			printout.flush();
		} else {
			MemberDao dao = sqlSession.getMapper(MemberDao.class);
			
			MemberDto memberDto = dao.memberInfoDao(sessionId);//현재 로그인 중인 아이디의 회원정보를 가져오기
			model.addAttribute("memberDto", memberDto);
		}
		
		
		
		return "writeForm";
	}
	
	
	
	
	

}
