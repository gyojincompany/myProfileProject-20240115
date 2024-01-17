package com.gyojincompany.home.dao;

public interface MemberDao {
	public void joinDao(String mid, String mpw, String mname, String memail);//회원가입
	public int idCheckDao(String mid);//가입하려는 id의 존재여부 체크
}
