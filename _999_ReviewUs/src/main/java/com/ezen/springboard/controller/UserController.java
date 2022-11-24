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
	
	//회원가입 페이지로 이동
	@GetMapping("/join.do")
	public String joinView() {
		return"user/join";
	}
	
	//회원가입 진행
	@PostMapping(value="/join.do", produces="application/text; charset=UTF8")
	public String join(UserVO userVO, Model model) {
		int joinResult = userService.join(userVO);
		
		//회원가입 실패 시
		if(joinResult == 0) {
			model.addAttribute("joinMsg", "회원가입에 실패하였습니다. 다시 한 번 확인해주세요.");
			return "user/join";
		}
		
		//회원가입 성공 시
		model.addAttribute("joinMsg", userVO.getUserNm() + " 리뷰어님 환영합니다!");
		return "user/login";
	}
	
	//아이디 중복확인
	@PostMapping("/idCheck.do")
	@ResponseBody
	public String idCheck(UserVO userVO) {
		String returnStr = "";
		
		int idCnt = userService.idCheck(userVO.getUserId());
		
		if(idCnt > 0) returnStr = "duplicatedId";
		else returnStr = "availableId";
		
		return returnStr;
	}
	
	//닉네임 중복확인
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