package com.ezen.springboard.service.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.springboard.vo.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int idCheck(String userId) {
		return mybatis.selectOne("UserDAO.idCheck", userId);
	}
	
	public int nmCheck(String userNm) {
		return mybatis.selectOne("UserDAO.nmCheck", userNm);
	}
	
	public int join(UserVO userVO) {
		return mybatis.insert("UserDAO.join", userVO);
	}
	
	public UserVO login(UserVO userVO) {
		return mybatis.selectOne("UserDAO.login", userVO);
	}
	
	public UserVO findId(UserVO userVO) {
		System.out.println("Mybatis로 findId() 기능 처리");
		return mybatis.selectOne("UserDAO.findId", userVO);
	}

	public UserVO findPassword(UserVO userVO) {
		System.out.println("Mybatis로 findPassword() 기능 처리");
		return mybatis.selectOne("UserDAO.findPassword", userVO);
	}

	public void updatePassword(UserVO userVO) {
		System.out.println("Mybatis로 updatePassword() 기능 처리");
		mybatis.update("UserDAO.updatePassword", userVO);
	}
}