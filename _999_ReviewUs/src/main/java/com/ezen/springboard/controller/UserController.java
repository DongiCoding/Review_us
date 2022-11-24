package com.ezen.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.springboard.service.user.UserService;
import com.ezen.springboard.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//ȸ������ �������� �̵�
	@GetMapping("/join.do")
	public String joinView() {
		return"user/join";
	}
	
	//ȸ������ ����
	@PostMapping(value="/join.do", produces="application/text; charset=UTF8")
	public String join(UserVO userVO, Model model) {
		int joinResult = userService.join(userVO);
		
		//ȸ������ ���� ��
		if(joinResult == 0) {
			model.addAttribute("joinMsg", "ȸ�����Կ� �����Ͽ����ϴ�. �ٽ� �� �� Ȯ�����ּ���.");
			return "user/join";
		}
		
		//ȸ������ ���� ��
		model.addAttribute("joinMsg", userVO.getUserNm() + " ������ ȯ���մϴ�!");
		return "user/login";
	}
	
	//���̵� �ߺ�Ȯ��
	@PostMapping("/idCheck.do")
	@ResponseBody
	public String idCheck(UserVO userVO) {
		String returnStr = "";
		
		int idCnt = userService.idCheck(userVO.getUserId());
		
		if(idCnt > 0) returnStr = "duplicatedId";
		else returnStr = "availableId";
		
		return returnStr;
	}
	
	//�г��� �ߺ�Ȯ��
	@PostMapping("/nmCheck.do")
	@ResponseBody
	public String nmCheck(UserVO userVO) {
		String returnStr = "";
		
		int nmCnt = userService.nmCheck(userVO.getUserNm());
		
		if(nmCnt > 0) returnStr = "duplicatedNm";
		else returnStr = "availableNm";
		
		return returnStr;
	}
}