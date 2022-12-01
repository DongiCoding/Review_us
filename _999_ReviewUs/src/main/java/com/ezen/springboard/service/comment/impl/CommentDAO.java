package com.ezen.springboard.service.comment.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.springboard.vo.CommentVO;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<CommentVO> getComment(int bno) {
		return mybatis.selectList("CommentDAO.getComment", bno);
	}
	
	public void insertComment(CommentVO commentVO) {
		mybatis.insert("CommentDAO.insertComment", commentVO);
	}
	
	public void updateComment(CommentVO commentVO) {
		mybatis.update("CommentDAO.updateComment", commentVO);
	}
	
	public void deleteComment(CommentVO commentVO) {
		mybatis.delete("CommentDAO.deleteComment", commentVO);
	}
	
	public CommentVO commentSelect(CommentVO commentVO) {
		return mybatis.selectOne("CommentDAO.commentSelect", commentVO);
	}
}
