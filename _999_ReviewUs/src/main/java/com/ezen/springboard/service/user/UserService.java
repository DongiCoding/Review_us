package com.ezen.springboard.service.user;

import com.ezen.springboard.vo.UserVO;

public interface UserService {
	//ID 중복 체크
	int idCheck(String userId);
	
	//Nickname 중복 체크
	int nmCheck(String userNm);
	
	//회원가입
	int join(UserVO userVO);
	
	//로그인
	UserVO login(UserVO userVO);
}
