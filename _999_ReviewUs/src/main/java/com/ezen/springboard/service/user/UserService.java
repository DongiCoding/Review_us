package com.ezen.springboard.service.user;

import com.ezen.springboard.vo.UserVO;

public interface UserService {
	//ID �ߺ� üũ
	int idCheck(String userId);
	
	//Nickname �ߺ� üũ
	int nmCheck(String userNm);
	
	//ȸ������
	int join(UserVO userVO);
	
	//�α���
	UserVO login(UserVO userVO);
}
