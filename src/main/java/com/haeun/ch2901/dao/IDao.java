package com.haeun.ch2901.dao;

import java.util.ArrayList;

import com.haeun.ch2901.dto.ContentDto;

//Mapper
public interface IDao {
	
	//추상메소드 선언
	public ArrayList<ContentDto> listDao();	//리스트 불러오기
	public void writeDao(String mwriter, String mcontent);	//글쓰기
	public void deleteDao(String mid);			//삭제
	
}
