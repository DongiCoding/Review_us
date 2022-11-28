package com.ezen.springboard.service.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;

public interface BoardService {
	// 게시글 등록
	int insertBoard(BoardVO boardVO);
	
	// 공지글 등록
	void insertAdminboard(BoardVO boardVO);
	
	// 게시글 삭제
	void deleteBoard(int boardNo);
	
	// 공지글 삭제
	void deleteAdminboard(int boardNo);
	
	// 공지글 수정
	void updateAdminboard(BoardVO boardVO);
	
	// 게시글 목록 조회
	List<BoardVO> getBoardList(@RequestParam Map<String, String> paramMap, Criteria cri);
	
	//게시글 총 개수 조회
	//검색했을 때는 검색된 게시글의 총 개수를 조회
	int getBoardTotalCnt(Map<String, String> paramMap);
}
