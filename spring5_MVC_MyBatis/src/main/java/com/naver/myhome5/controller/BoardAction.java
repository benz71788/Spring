package com.naver.myhome5.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome5.dao.BoardDAOImpl;
import com.naver.myhome5.model.BoardBean;

@Controller
public class BoardAction {
	
	@Autowired
	private BoardDAOImpl boardService;
	
	@RequestMapping(value="/board_write.nhn")
	public String board_write() {
		return "/board/board_write";
	}
	
	@RequestMapping(value="/board_write_ok.nhn",
			method=RequestMethod.POST)
	public String board_write_ok(BoardBean board) throws Exception{
		boardService.insertBoard(board);	//저장메서드 호출
		return "redirect:/board_list.nhn";
	}
	
	@RequestMapping(value="/board_list.nhn")
	public ModelAndView board_list(
			@RequestParam(value="page", defaultValue="1") int page) throws Exception {
		List<BoardBean> boardlist = new ArrayList<BoardBean>();

		int limit = 10;	// 한 화면에 출력할 레코드 갯수
		
		int listcount = boardService.getListCount(); //총 리스트 수를 받아옴
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1)/limit;
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등 ...);
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...);
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		boardlist = boardService.getBoardList(page);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/board_list");
		
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		
		mv.addObject("boardlist", boardlist);
		
		return mv;
	}
	
	@RequestMapping(value="/board_cont.nhn")
	public ModelAndView board_cont(
			@RequestParam(value="board_num") int board_num,
			@RequestParam(value="page") int page,
			@RequestParam(value="state") String state) throws Exception{
		
		if(state.equals("cont")) {	//내용보기 일때만
			boardService.boardHit(board_num);
		}
		BoardBean board = boardService.getBoardCount(board_num);
		
		ModelAndView contM = new ModelAndView();
		contM.addObject("bcont", board);
		contM.addObject("page", page);
		
		if(state.equals("cont")) {
			contM.setViewName("board/board_cont");
		} else if(state.equals("edit")) {
			contM.setViewName("board/board_edit");
		} else if(state.equals("delete")) {
			contM.setViewName("board/board_del");
		} else if(state.equals("reply")) {
			contM.setViewName("board/board_reply");
		}

		return contM;
	}
	
	@RequestMapping(value="/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_eidt_ok(BoardBean bean, 
			@RequestParam(value="page") int page,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	//출력스트림 생성
		
		// 번호를 기준으로 DB 내용을 가져옵니다.
		BoardBean board = boardService.getBoardCount(bean.getBoard_num());
		if(!board.getBoard_pass().equals(bean.getBoard_pass())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.!');");
			out.println("history.back()");
			out.println("</script>");
		} else {	//비번이 같다면
			boardService.boardEdit(bean);
			
			return "redirect:/board_cont.nhn?board_num=" 
				+ bean.getBoard_num() + "&page=" + page + "&state=cont";
		}
		
		return null;
	}
	
	@RequestMapping(value="/board_del_ok.nhn", method = RequestMethod.POST)
	public String board_del_ok(BoardBean bean,
			@RequestParam("page") int page,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BoardBean board = boardService.getBoardCount(bean.getBoard_num());
		if(!board.getBoard_pass().equals(bean.getBoard_pass())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			boardService.boardDelete(bean.getBoard_num());
			return "redirect:/board_list.nhn?page=" + page;
		}
		
		return null;
	}
	
	@RequestMapping(value="/board_reply_ok.nhn", method = RequestMethod.POST)
	public String board_reply_ok(BoardBean bean,
			@RequestParam("page") int page) throws Exception{
		
		boardService.refUpdate(bean);
		
		bean.setBoard_re_seq(bean.getBoard_re_seq() + 1);
		bean.setBoard_re_lev(bean.getBoard_re_lev() + 1);
		bean.setBoard_readcount(0);
		
		boardService.refInsert(bean);
		
		return "redirect:/board_list.nhn?page=" + page;
	}
}
