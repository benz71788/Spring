package com.naver.myhome5.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome5.model.BoardBean;

/*
 * @Component를 이용해서 스프링 컨테이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
 * 모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기
 * 어렵습니다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서
 * @Component를 상속하여 다음과 같은 세 개의 애노테이션을 제공합니다.
 * 
 * 	1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
 * 	2. @Repository - 데이터 베이스 연동을 처리하는 DAO클래스
 * 	3. @Service - 비즈니스 로직을 처리하는 Service 클래스
 */
@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * @Autowired
	 * public void setSqlSession(SqlSession sqlSession){
	 * 		this.sqlSession = SqlSession;
	 * } // setter DI설정
	 */

	@Override
	public void insertBoard(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("Test.board_insert", bean);
		
	}

	@Override
	public List<BoardBean> getBoardList(int page) throws Exception {
		// TODO Auto-generated method stub
		List<BoardBean> list = new ArrayList<BoardBean>();
		
		list = sqlSession.selectList("Test.getBoardList", page);
		return list;
	}

	@Override
	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		count = ((Integer) sqlSession.selectOne("Test.getListCount")).intValue();
		return count;
	}

	@Override
	public BoardBean getBoardCount(int num) throws Exception {
		// TODO Auto-generated method stub
		BoardBean board = new BoardBean();
		board = sqlSession.selectOne("Test.board_cont", num);
		return board;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("Test.board_hit", num);
	}

	@Override
	public void boardEdit(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("Test.board_edit", bean);
	}

	@Override
	public void boardDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("Test.board_delete", num);
	}
	
	@Override
	public void refUpdate(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("reply_count", bean);
	}

	@Override
	public void refInsert(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("reply_insert", bean);
	}

}
