package com.ezen.springboard.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.springboard.service.board.BoardService;
import com.ezen.springboard.vo.BoardFileVO;
import com.ezen.springboard.vo.BoardVO;
import com.ezen.springboard.vo.Criteria;
import com.ezen.springboard.vo.PageVO;
import com.ezen.springboard.vo.UserVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/*
	 * 클라이언트가 이벤트를 발생시키면 요청이 발생되고 
	 * 컨트롤러에서 @RequestMapping으로 매핑된 매핑주소와 요청주소가 같으면
	 * 해당 메소드를 실행한다.
	 * 해당 메소드안에서는 DB접근이 필요할 때는 Service의 메소드를 호출하여
	 * ServiceImpl -> DAO -> Mapper까지 실행한다.
	 * DB접근이 필요없는 경우는 해당 메소드에서 바로 jsp(화면단)을 리턴한다.
	 * 화면에서 요청발생 -> Controller -> Service -> ServiceImpl -> DAO -> Mapper
	 * DB에서 가져올 데이터가 있을 경우에는 위 순서의 역순으로 진행
	 * */
//	@RequestMapping("login.do")
//	public void login() {
//		boardService.login();
//	}
	
	// 게시글 등록 화면으로 이동
	@GetMapping("/insertBoard.do")
	public String insertBoardView(HttpSession session) {
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/user/login.do";
		}
		
		return "board/insertBoard";
	}
	
	// 게시글 등록
	@PostMapping("/insertBoard.do")
	public String insertBoard(BoardVO boardVO, MultipartFile[] uploadFiles, 
			HttpServletRequest request) {
		int boardNo = boardService.insertBoard(boardVO);
		
		// 파일업로드 기능 구현
		if(uploadFiles.length > 0) {
			List<BoardFileVO> fileList = new ArrayList<BoardFileVO>();
			
			// 업로드 폴더 지정
			// request.getSession().getServletContext().getRealPath("/"): WAS의 루트패스
			// workspace_spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\_025_SpringBoard_FileUpload
			String attachPath = request.getSession().getServletContext().getRealPath("/") 
				+ "/upload/";
					
			File directory = new File(attachPath);
			
			// 해당 폴더가 존재하지 않으면 폴더 생성
			if(!directory.exists()) {
				directory.mkdir();
			}
		}
		
		// 등록 후 게시글 목록으로 이동
		return "redirect:/board/getBoardList.do";
		// 등록 후 게시글 상세로 이동
		//return "redirect:/board/getBoard.do?boardNo=" + boardVO.getBoardNo();
	}
	
	// 공지 등록 화면으로 이동
	@GetMapping(value="/insertAdminboard.do", produces="application/text; charset=UTF-8;")
	public String insertAdminboardView(HttpSession session, Model model) {
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		if(loginUser == null || loginUser.getUserRole().equals("normal")) {
			model.addAttribute("notadminMsg", "관리자만 공지를 작성할 수 있습니다.");
			return "/index.jsp";
		}
		
		return "board/insertAdminboard";
	}
	
	// 공지글 등록
	@PostMapping("/insertAdminboard.do")
	public String insertAdminboard(BoardVO boardVO) {
		boardService.insertAdminboard(boardVO);
		
		// 등록 후 게시글 목록으로 이동
		return "redirect:/board/getAdminboardList.do";
		// 등록 후 게시글 상세로 이동
		//return "redirect:/board/getAdminboard.do?boardNo=" + boardVO.getBoardNo();
	}
	
	// 게시글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(@RequestParam("boardNo") int boardNo) {
		boardService.deleteBoard(boardNo);
		
		return "redirect:/board/getBoardList.do";
	}
	
	// 공지글 삭제
	@RequestMapping("/deleteAdminboard.do")
	public String deleteAdminboard(@RequestParam("boardNo") int boardNo) {
		boardService.deleteAdminboard(boardNo);
		
		return "redirect:/board/getAdminboard.do";
	}
	
	// 공지글 수정
	@PostMapping("/updateAdminboard.do")
	public String updateAdminboard(BoardVO boardVO) {
		boardService.updateAdminboard(boardVO);
		
		return "redirect:/board/getAdminboard.do?boardNo=" + boardVO.getBoardNo();
	}
	
	// 게시판으로 이동
	@GetMapping("/getBoardList.do")
	public String getBoardListView() {
		return "board/getBoardList";
	}
	
	// 게시판에서 홈(index)으로 이동
	@GetMapping("/index.do")
	public String indexView() {
		return "../../index";
	}
	
	// 게시글 목록 화면으로 이동
	@RequestMapping("/getBoardList.do")
	public String getBoardList(Model model, @RequestParam Map<String, String> paramMap, Criteria cri) {
		// model을 만들어 list던져줌
		List<BoardVO> boardList = boardService.getBoardList(paramMap, cri);
		
		model.addAttribute("boardList", boardList);
		
		if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
			model.addAttribute("searchCondition", paramMap.get("searchCondition"));
		}
		if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
			model.addAttribute("searchKeyword", paramMap.get("searchKeyword"));
		}
		
		// 검색했을때, 검색한 조건만 가져와야 하기 떄문에
		int total = boardService.getBoardTotalCnt(paramMap);
		
		model.addAttribute("pageVO", new PageVO(cri, total));
		
		return "board/getBoardList";
	}
	
}
