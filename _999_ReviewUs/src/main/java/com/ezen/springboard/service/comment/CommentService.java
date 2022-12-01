package com.ezen.springboard.service.comment;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ezen.springboard.vo.CommentVO;

@Component
public interface CommentService {

	//댓글 조회
	public List<CommentVO> getComment(int bno);
	
	//댓글 작성
	public void insertComment(CommentVO commentVO);
	
	//댓글 수정
	public void updateComment(CommentVO commentVO);
	
	//댓글 삭제
	public void deleteComment(CommentVO commentVO);
	
	//단일 댓글 조회
	public CommentVO commentSelect(CommentVO commentVO);
}
