package com.gyojincompany.home.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.home.dao.BoardDao;

@Controller
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/test")
	public String test(Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		model.addAttribute("title", dao.testDao("1"));
		
		return "test";
	}

}
