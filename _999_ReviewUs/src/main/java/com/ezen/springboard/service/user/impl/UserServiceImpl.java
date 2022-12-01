package com.ezen.springboard.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springboard.service.user.UserService;
import com.ezen.springboard.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public int idCheck(String userId) {
		return userDAO.idCheck(userId);
	}

	@Override
	public int nmCheck(String userNm) {
		return userDAO.nmCheck(userNm);
	}

	@Override
	public int join(UserVO userVO) {
		return userDAO.join(userVO);
	}

	@Override
	public UserVO login(UserVO userVO) {
		return userDAO.login(userVO);
	}

	@Override
	public UserVO findId(UserVO userVO) {
		return userDAO.findId(userVO);
	}

	@Override
	public UserVO findPassword(UserVO userVO) {
		return userDAO.findPassword(userVO);
	}

	@Override
	public void updatePassword(UserVO userVO) {
		userDAO.updatePassword(userVO);
	}
}