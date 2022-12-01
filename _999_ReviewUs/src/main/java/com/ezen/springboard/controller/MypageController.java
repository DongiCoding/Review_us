package com.ezen.springboard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.springboard.service.mypage.MypageService;
import com.ezen.springboard.vo.UserVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	private MypageService mypageService;
	
	@RequestMapping("/getMypage.do")
	public String getMypage(Model model, @RequestParam("userId") String userId, HttpSession session) {
		Map<String, String> getMypage = mypageService.getMypage(userId);		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		if(loginUser.getUserRole().equals("admin")) {
			return "admin/adMain";
		}
		session.setAttribute("loginUser", loginUser);
		model.addAttribute("getMypage", getMypage);
		
		return "mypage/mypageBasic";
	}
	
	@RequestMapping("/updateMypage.do")
	public String updateMypage(@RequestParam("userId") String userId, Model model, HttpSession session, UserVO userVO) {
		UserVO getUser = mypageService.getUser(userId);
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		session.setAttribute("loginUser", loginUser);
		model.addAttribute("getUser", getUser);
		
		return "mypage/mypageModify";
	}
	
	@PostMapping("/updateUser.do")
	public String updateUser(UserVO userVO) {
		System.out.println(userVO);
		
		mypageService.updateUser(userVO);
		
		return "mypage/mypageBasic";
	}

	
	@RequestMapping("/deleteMypage.do")
	public String deleteMypage(@RequestParam("userId") String userId, HttpSession session) {
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		session.setAttribute("loginUser", loginUser);
		return "mypage/mypageWithdrawal";
	}
	
	@PostMapping("/pwdCheck.do")
	@ResponseBody
	public String pwdCheck(@RequestParam("userId") String userId, UserVO userVO)  {
		System.out.println(userVO.getUserPwd());
		System.out.println(userId);
		
		String returnStr = " ";
		
		String pwdCheck = mypageService.pwdCheck(userId);
		
		System.out.println(pwdCheck);
		
		if(pwdCheck.equals(userVO.getUserPwd())) {
			returnStr = "deleteOk";
		}
		else {
			returnStr = "deleteFail";
		}
		
		return returnStr;
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") String userId) {
		mypageService.deleteUser(userId);
		
		
		return "index";
	}
	
	
}
