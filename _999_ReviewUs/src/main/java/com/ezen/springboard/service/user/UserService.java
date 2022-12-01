package com.ezen.springboard.service.user;

import java.util.List;
import java.util.Map;

import com.ezen.springboard.vo.UserVO;

public interface UserService {
	//ID 以묐났泥댄겕
	int idCheck(String userId);
	
	//Nickname 以묐났泥댄겕
	int nmCheck(String userNm);
	
	//�쉶�썝媛��엯
	int join(UserVO userVO);
	
	//濡쒓렇�씤
	UserVO login(UserVO userVO);
	
	// �븘�씠�뵒李얘린
	UserVO findId(UserVO userVO);
	
	// �뙣�뒪�썙�뱶 李얘린
	UserVO findPassword(UserVO userVO);
	
	// �뙣�뒪�썙�뱶 蹂�寃�
	void updatePassword(UserVO userVO);
	
	void updateUserRole(String userId);
	
	List<UserVO> getAduserList(Map<String, String>paramMap);
	
}
