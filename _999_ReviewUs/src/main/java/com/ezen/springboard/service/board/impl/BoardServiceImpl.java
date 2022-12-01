package com.ezen.springboard.service.board.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.service.board.BoardService;
import com.ezen.springboard.vo.BoardFileVO;
import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;

@Repository
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 등록
	@Override
	public void insertBoard(BoardVO boardVO, List<BoardFileVO> fileList) {
		boardDAO.insertBoard(boardVO, fileList);
	}
	
	// 공지글 등록
	@Override
	public void insertAdminboard(BoardVO boardVO) {
		boardDAO.insertAdminboard(boardVO);
	}
	
	// 게시글 삭제
	@Override
	public void deleteBoard(int boardNo) {
		boardDAO.deleteBoard(boardNo);
	}
	
	// 공지글 삭제
	@Override
	public void deleteAdminboard(int boardNo) {
		boardDAO.deleteAdminboard(boardNo);
	}
	
	// 공지글 수정
	@Override
	public void updateAdminboard(BoardVO boardVO) {
		boardDAO.updateAdminboard(boardVO);
		
	}

	// 게시글 목록 조회
	@Override
	public List<BoardVO> getBoardList(@RequestParam Map<String, String> paramMap, Criteria cri){
		return boardDAO.getBoardList(paramMap, cri);
	}
	
	//게시글 총 개수 조회
	//검색했을 때는 검색된 게시글의 총 개수를 조회
	@Override
	public int getBoardTotalCnt(Map<String, String> paramMap) {
		return boardDAO.getBoardTotalCnt(paramMap);
	}
	
	// 게시글 상세 조회
	@Override
	public BoardVO getBoard(int boardNo) {
		return boardDAO.getBoard(boardNo);
	}
	
	// 조회수 증가
	@Override
	public void updateBoardCnt(int boardNo) {
		boardDAO.updateBoardCnt(boardNo);
	}
	
	// 첨부파일 리스트 조회
	@Override
	public List<BoardFileVO> getBoardFileList(int boardNo) {
		return boardDAO.getBoardFileList(boardNo);
	}
	
	// 게시글 수정
	@Override
	public void updateBoard(BoardVO boardVO, List<BoardFileVO> uFileList) {
		boardDAO.updateBoard(boardVO, uFileList);
	}
	
}
