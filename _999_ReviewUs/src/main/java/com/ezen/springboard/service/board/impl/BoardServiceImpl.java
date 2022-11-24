package com.ezen.springboard.service.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springboard.service.board.BoardService;
import com.ezen.springboard.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 등록
	public void insertBoard(BoardVO boardVO) {
		boardDAO.insertBoard(boardVO);
	}
}
