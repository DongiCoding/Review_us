package com.ezen.springboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.service.board.BoardService;
import com.ezen.springboard.service.user.UserService;
import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.UserVO;

@Controller
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
	/*@Autowired
	private CommentService commentService;*/

	@GetMapping("/adMain.do")
	public String adminMain() {
		return "admin/adMain";
		
	}	
	
	@RequestMapping("/getAduserlist.do")
	public String getAduserList(Model model, @RequestParam Map<String, String> paramMap) {
		List<UserVO> aduserList = userService.getAduserList(paramMap);
		
		System.out.println(aduserList);
		
		model.addAttribute("aduserList", aduserList);
		
		if(paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals(""))
			model.addAttribute("searchCondition", paramMap.get("searchCondition"));
		
		if(paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals(""))
			model.addAttribute("searchKeyword", paramMap.get("searchKeyword"));
		
		return "admin/getAduserlist";
	}

	@RequestMapping("/getAdboardlist.do")
	public String getAdboardList(Model model, @RequestParam Map<String, String> paramMap) {
		List<BoardVO> boardList = boardService.getAdboardList(paramMap);
		
		model.addAttribute("boardList", boardList);
		
		if(paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals(""))
			model.addAttribute("searchCondition", paramMap.get("searchCondition"));
		
		if(paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals(""))
			model.addAttribute("searchKeyword", paramMap.get("searchKeyword"));
		
		return "admin/getAdboardlist";
	}
	
	/*@RequestMapping("/getAdcommentList.do")
	public String getAdCommentList(Model model, @RequestParam Map<String, String> paramMap) {
		List<CommentVO> commentList = commentService.getAdcommentList(paramMap);
		
		model.addAttribute("commentList", commentList);
		
		if(paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals(""))
			model.addAttribute("searchCondition", paramMap.get("searchCondition"));
		
		if(paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals(""))
			model.addAttribute("searchKeyword", paramMap.get("searchKeyword"));
		
		return "admin/getAdcommentList";
	}*/
	
	@RequestMapping("/updateUserRole.do")
	public String updateUserRole(@RequestParam("userId") String userId) {
		userService.updateUserRole(userId);
		
		return "redirect:/admin/getAduserlist.do";
	}
	@RequestMapping("/deleteAdBoard.do")
	public String deleteBoard(@RequestParam("boardNo") int boardNo) {
		boardService.deleteBoard(boardNo);
		
		
		
		return "redirect:/admin/getAdboardlist.do";
	}
}
