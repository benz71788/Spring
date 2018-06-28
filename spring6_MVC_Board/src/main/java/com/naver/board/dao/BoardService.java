package com.naver.board.dao;

import java.util.List;
import java.util.Map;

import com.naver.board.model.BoardBean;


public interface BoardService {
	
	public void insertBoard(BoardBean bean) throws Exception;
	
	public List<BoardBean> getBoardList(Map map) throws Exception;
	
	public int getListCount() throws Exception;
	
	public BoardBean getBoardCont(int num) throws Exception;
	
	public void boardHit(int num) throws Exception;
	
	public void boardEdit(BoardBean bean) throws Exception;
	
	public void boardDelete(int num) throws Exception;
	
	public int getListCount3(Map m) throws Exception;
	
	public List<BoardBean> getBoardList3(Map m) throws Exception;
	
	public void refUpdate(BoardBean bean) throws Exception;
	
	public void refInsert(BoardBean bean) throws Exception;
}
