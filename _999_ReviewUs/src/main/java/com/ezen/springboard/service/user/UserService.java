package com.ezen.springboard.service.user;

import com.ezen.springboard.vo.UserVO;

public interface UserService {
	//ID 중복체크
	int idCheck(String userId);
	
	//Nickname 중복체크
	int nmCheck(String userNm);
	
	//회원가입
	int join(UserVO userVO);
	
	//로그인
	UserVO login(UserVO userVO);
	
	// 아이디찾기
	UserVO findId(UserVO userVO);
	
	// 패스워드 찾기
	UserVO findPassword(UserVO userVO);
	
	// 패스워드 변경
	void updatePassword(UserVO userVO);
}
