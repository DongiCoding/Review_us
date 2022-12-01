package com.ezen.springboard.service.mypage.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.springboard.vo.UserVO;


@Repository
public class MypageDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public Map<String, String> getMypage(String userId) {
		return mybatis.selectOne("UserDAO.getMypage", userId);
	}

	public UserVO getUser(String userId) {
		return mybatis.selectOne("UserDAO.getUser", userId);
	}
	
	public void updateUser(UserVO userVO) {
		mybatis.update("UserDAO.updateUser", userVO);
	}
	
	public String pwdCheck(String userId) {
		return mybatis.selectOne("UserDAO.pwdCheck", userId);
	}
	
	public void deleteUser(String userId) {
		mybatis.delete("UserDAO.deleteUser", userId);
	}
}
