package com.json.jsonroot.dao;

import java.util.List;

import com.json.jsonroot.model.BoardVO;

public interface JsonService {
	public BoardVO get_whereid(int id) throws Exception;
	public void setInsert(BoardVO b) throws Exception;
	public List<BoardVO> selectall() throws Exception;
}
