package com.ezen.springboard.service.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.vo.BoardFileVO;
import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 게시글 등록
	public void insertBoard(BoardVO boardVO, List<BoardFileVO> fileList) {
		mybatis.insert("BoardDAO.insertBoard", boardVO);
		
		if(fileList.size() > 0) {
			// 게시글 번호를 담아주는 작업
			for(BoardFileVO boardFile : fileList) {
				boardFile.setBoardNo(boardVO.getBoardNo());
				
				// List를 매퍼로 보내는 방식 1: 하나씩 꺼내서 매퍼의 쿼리 호출
				mybatis.insert("BoardDAO.insertBoardFile", boardFile);
			}
		}
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
	
	// 게시글 상세 조회
	public BoardVO getBoard(int boardNo) {
		Map<String, Object> testMap = mybatis.selectOne("BoardDAO.getBoardMap", boardNo);
		System.out.println(testMap.toString());
		
		return mybatis.selectOne("BoardDAO.getBoard", boardNo);
	}
	
	// 조회수 증가
	public void updateBoardCnt(int boardNo) {
		mybatis.update("BoardDAO.updateBoardCnt", boardNo);
	}
	
	// 첨부파일 리스트 조회
	public List<BoardFileVO> getBoardFileList(int boardNo) {
		return mybatis.selectList("BoardDAO.getBoardFileList", boardNo);
	}
	
	// 게시글 수정
	public void updateBoard(BoardVO boardVO, List<BoardFileVO> uFileList) {
		mybatis.update("BoardDAO.updateBoard", boardVO);
		
		if(uFileList.size() > 0) {
			for(int i = 0; i < uFileList.size(); i++) {
				if(uFileList.get(i).getFileStatus().equals("U")) {
					mybatis.update("BoardDAO.updateBoardFile", uFileList.get(i));
				} else if(uFileList.get(i).getFileStatus().equals("D")) {
					mybatis.delete("BoardDAO.deleteBoardFile", uFileList.get(i));
				} else if(uFileList.get(i).getFileStatus().equals("I")) {
					mybatis.insert("BoardDAO.insertBoardFile", uFileList.get(i));
				}
			}
		}
	}
}
