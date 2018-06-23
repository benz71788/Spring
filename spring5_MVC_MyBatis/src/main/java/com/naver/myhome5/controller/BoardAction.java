package com.naver.myhome5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.myhome5.model.BoardBean;
import com.naver.myhome5.model.BoardDAO;

@Controller
public class BoardAction {
	
	@RequestMapping(value="/board_write.nhn")
	public String board_write() {
		return "/board/board_write";
	}
	
	@RequestMapping(value="/board_write_ok.nhn",
			method=RequestMethod.POST)
	public String board_write_ok(BoardBean board) {
		
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.boardInsert(board);
		
		List<BoardBean> list = new ArrayList<BoardBean>();
		list = boardDAO.getBoardList();
		

		
		return "/board/board_list";
	}
}
