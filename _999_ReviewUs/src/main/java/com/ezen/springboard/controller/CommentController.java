package com.ezen.springboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springboard.service.comment.CommentService;
import com.ezen.springboard.vo.CommentVO;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	//댓글 조회
	@RequestMapping("getComment.do")
	public void getComment(Model model, @RequestParam("bno") int bno) {
		List<CommentVO> commentList = commentService.getComment(bno);
		
		model.addAttribute("commentList", commentList);
		
	}
	
	//댓글 작성
	@PostMapping("/insertComment.do")
	public String insertComment(CommentVO commentVO) {
		
		commentService.insertComment(commentVO);
		
		return "redirect:/board/getBoard.do?boardNo=" + commentVO.getBno();
	}
	//댓글 수정
	@PostMapping("/updateComment.do")
	public String updateComment(CommentVO commentVO) {
		
		commentService.updateComment(commentVO);
		
		return "redirect:/board/getBoard.do?boardNo=" + commentVO.getBno();
	}
	
	//댓글 삭제
	@RequestMapping("/deleteComment.do")
	public String deleteComment(CommentVO commentVO) {
		
		commentService.deleteComment(commentVO);
		
		return "redirect:/board/getBoard.do?boardNo=" + commentVO.getBno();
		
	}
	
	//댓글 단일 조회 (수정 페이지)
	@GetMapping("/commentSelect.do")
	public void commentSelect(@RequestParam("bno") int bno, @RequestParam("cno") int cno, Model model) {
		
		CommentVO vo = new CommentVO();
		vo.setBno(bno);
		vo.setCno(cno);
		
		CommentVO commentVO = commentService.commentSelect(vo);
		
		model.addAttribute("commentVO", commentVO);
	}
	
}
