package com.ezen.springboard.service.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 게시글 등록
	public int insertBoard(BoardVO boardVO) {
		return mybatis.insert("BoardDAO.insertBoard", boardVO);
	}
	
	// 공지글 등록
	public void insertAdminboard(BoardVO boardVO) {
		mybatis.insert("BoardDAO.insertAdminboard", boardVO);
	}
	
	// 게시글 삭제
	public void deleteBoard(int boardNo) {
		mybatis.delete("BoardDAO.deleteBoard", boardNo);
	}
	
	// 공지글 삭제
	public void deleteAdminboard(int boardNo) {
		mybatis.delete("BoardDAO.deleteAdminboard", boardNo);
	}
	
	// 공지글 수정
	public void updateAdminboard(BoardVO boardVO) {
		mybatis.update("BoardDAO.updateAdminboard", boardVO);
	}
	
	// 게시글 목록 조회
	public List<BoardVO> getBoardList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("boardSearch", paramMap);
		
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		
		pMap.put("cri", cri);
		
		return mybatis.selectList("BoardDAO.getBoardList", pMap);
	}
	
	// 게시글의 총 개수
	// 검색했을 때 게시글의 총 개수
	public int getBoardTotalCnt(Map<String, String> paramMap) {
		return mybatis.selectOne("BoardDAO.getBoardTotalCnt", paramMap);
	}
}
