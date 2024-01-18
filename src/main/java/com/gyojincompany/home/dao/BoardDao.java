package com.gyojincompany.home.dao;

import java.util.List;

import com.gyojincompany.home.dto.QAboardDto;

public interface BoardDao {
	
	public List<QAboardDto> listDao();//모든 글 리스트 가져오기

}
