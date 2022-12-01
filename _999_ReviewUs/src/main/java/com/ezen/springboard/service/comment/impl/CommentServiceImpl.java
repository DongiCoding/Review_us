package com.ezen.springboard.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.springboard.service.comment.CommentService;
import com.ezen.springboard.vo.CommentVO;

@Repository
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;

	@Override
	public List<CommentVO> getComment(int bno) {
		return commentDAO.getComment(bno);
	}

	@Override
	public void insertComment(CommentVO commentVO) {
		commentDAO.insertComment(commentVO);
	}

	@Override
	public void updateComment(CommentVO commentVO) {
		commentDAO.updateComment(commentVO);
	}

	@Override
	public void deleteComment(CommentVO commentVO) {
		commentDAO.deleteComment(commentVO);
	}

	@Override
	public CommentVO commentSelect(CommentVO commentVO) {
		return commentDAO.commentSelect(commentVO);
	}
	


}
