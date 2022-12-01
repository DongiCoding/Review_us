package com.ezen.springboard.service.mypage;

import java.util.Map;

import com.ezen.springboard.vo.UserVO;


public interface MypageService {
	
	Map<String, String> getMypage(String userId);

	UserVO getUser(String userId);
	
	void updateUser(UserVO userVO);
	
	String pwdCheck(String userId);
	
	void deleteUser(String userId);
}
