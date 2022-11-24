package com.ezen.springboard.service.board.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.springboard.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 게시글 등록
	public void insertBoard(BoardVO boardVO) {
		mybatis.insert("BoardDAO.insertBoard", boardVO);
	}
}
