package kr.co.hta.board.web.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hta.board.annotation.LoginUser;
import kr.co.hta.board.service.BoardService;
import kr.co.hta.board.vo.Board;
import kr.co.hta.board.vo.User;
import kr.co.hta.board.web.form.BoardForm;
import kr.co.hta.board.web.views.DownloadView;

@Controller
@RequestMapping("/board")
public class BoardController {

	private String directory = "c:/upload/formfile";
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private DownloadView downloadView;
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		
		return "board/list.jsp";
	}
	
	@RequestMapping("detail.do")
	public String detail(@RequestParam("no") int boardNo, Model model) {
		model.addAttribute("board", boardService.getBoardDetail(boardNo));
		
		return "board/detail.jsp";
		
	}
	
	@RequestMapping("/del.do")
	// int no로 찾아도 되는데 보여주기 편하게 하기 위해서는 @RequestParam을 사용하면 전달되는 값을 추출해서 담을수 있다.
	public String delete(@RequestParam("no") int boardNo, @LoginUser User user) {

		boardService.deleteBoard(boardNo, user.getId());
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/form.do")
	public String form() {
		return "board/form.jsp";
	}
	
	@RequestMapping("/add.do")
	public String add(BoardForm boardForm, @LoginUser User user) throws Exception {
		
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setNick(user.getId());
		board.setContents(boardForm.getContents());
		
		MultipartFile upfile = boardForm.getUpfile();
		if (!upfile.isEmpty()) {
			String filename = upfile.getOriginalFilename();
			board.setFilename(filename);
			
			FileCopyUtils.copy(upfile.getBytes(), new File(directory, filename));
		}
		boardService.addNewBoard(board);
		
		return "redirect:/board/list.do";
	}
	
	public ModelAndView download(@RequestParam("no") int boardNo) {
		ModelAndView mav = new ModelAndView();
		
		Board board = boardService.getBoardDetail(boardNo);
		mav.addObject("directory", directory);
		mav.addObject("filename", board.getFilename());
		mav.setView(downloadView);
		
		return mav;
	}
}























