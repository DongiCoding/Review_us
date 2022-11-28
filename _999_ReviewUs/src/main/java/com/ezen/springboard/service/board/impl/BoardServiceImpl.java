package com.ezen.springboard.service.board.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.service.board.BoardService;
import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 등록
	public int insertBoard(BoardVO boardVO) {
		return boardDAO.insertBoard(boardVO);
	}
	
	// 공지글 등록
	public void insertAdminboard(BoardVO boardVO) {
		boardDAO.insertAdminboard(boardVO);
	}
	
	// 게시글 삭제
	public void deleteBoard(int boardNo) {
		boardDAO.deleteBoard(boardNo);
	}
	
	// 공지글 삭제
	public void deleteAdminboard(int boardNo) {
		boardDAO.deleteAdminboard(boardNo);
	}
	
	// 공지글 수정
	public void updateAdminboard(BoardVO boardVO) {
		boardDAO.updateAdminboard(boardVO);
		
	}
	
	// 게시글 목록 조회
	public List<BoardVO> getBoardList(@RequestParam Map<String, String> paramMap, Criteria cri){
		return boardDAO.getBoardList(paramMap, cri);
	}
	
	//게시글 총 개수 조회
	//검색했을 때는 검색된 게시글의 총 개수를 조회
	public int getBoardTotalCnt(Map<String, String> paramMap) {
		return boardDAO.getBoardTotalCnt(paramMap);
	}
}
