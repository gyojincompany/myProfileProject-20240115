package com.gyojincompany.home.dto;

import java.security.Timestamp;

public class MemberDto {
	
	private String mid;//회원아이디
	private String pw;//회원비밀번호
	private String mname;//회원이름
	private String memail;//회원이메일
	private Timestamp mdate;//회원등록일
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDto(String mid, String pw, String mname, String memail, Timestamp mdate) {
		super();
		this.mid = mid;
		this.pw = pw;
		this.mname = mname;
		this.memail = memail;
		this.mdate = mdate;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public Timestamp getMdate() {
		return mdate;
	}
	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}
	
	

}
