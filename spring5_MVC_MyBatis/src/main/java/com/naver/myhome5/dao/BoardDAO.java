package com.naver.myhome5.dao;

import java.util.List;

import com.naver.myhome5.model.BoardBean;

public interface BoardDAO {
	public void insertBoard(BoardBean bean) throws Exception;
	
	public List<BoardBean> getBoardList(int page) throws Exception;
	
	public int getListCount() throws Exception;
	
	public BoardBean getBoardCount(int num) throws Exception;
	
	public void boardHit(int num) throws Exception;
	
	public void boardEdit(BoardBean bean) throws Exception;
	
	public void boardDelete(int num) throws Exception;
	
	public void refUpdate(BoardBean bean) throws Exception;
	
	public void refInsert(BoardBean bean) throws Exception;
	
}
