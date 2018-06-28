package com.naver.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.board.model.BoardBean;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	/*
	 * 3개의 DAO 파일을 작성하였습니다.
	 * 만약 BbsDAOImpl_old를 사용하고 싶다면 @Autowired할 클래스 이름만 바꾸어 주고
	 * BbsDAOImpl_old.java 파일은 @Repository를 붙여주면 됩니다.
	 * 
	 * 이 처럼 DAO 파일이 바뀌어도 이곳에서만 클래스 명만 바꾸어 주면 됩니다.
	 * DAO를 간접 사용하고 있는 BbsAction2.java는 수정할 필요가 없습니다.
	 */
	
	@Autowired
	private BoardDAOImpl boardDAO;

	@Override
	public void insertBoard(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.insertBoard(bean);
	}

	@Override
	public List<BoardBean> getBoardList(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<BoardBean> list = new ArrayList<BoardBean>();
		list = boardDAO.getBoardList(map);
		return list;
	}

	@Override
	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		int result = boardDAO.getListCount();
		return result;
	}

	@Override
	public BoardBean getBoardCont(int num) throws Exception {
		// TODO Auto-generated method stub
		BoardBean boardBean = new BoardBean();
		boardBean = boardDAO.getBoardCont(num);
		return boardBean;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.boardHit(num);
	}

	@Override
	public void boardEdit(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.boardEdit(bean);
	}

	@Override
	public void boardDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.boardDelete(num);
	}

	@Override
	public int getListCount3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.getListCount3(m);
	}

	@Override
	public List<BoardBean> getBoardList3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.getBoardList3(m);
	}

	@Override
	public void refUpdate(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.refUpdate(bean);
	}

	@Override
	public void refInsert(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.refInsert(bean);
	}
	
}
