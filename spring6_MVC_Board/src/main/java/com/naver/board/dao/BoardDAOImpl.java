package com.naver.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.board.model.BoardBean;

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
public class BoardDAOImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void insertBoard(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("Board1.board_insert", bean);
	}

	public List<BoardBean> getBoardList(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<BoardBean> list = new ArrayList<BoardBean>();
		list = sqlSession.selectList("Board1.board_getList", map);
		return list;
	}

	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		int result = sqlSession.selectOne("Board1.board_listCount");
		return result;
	}

	public BoardBean getBoardCont(int num) throws Exception {
		// TODO Auto-generated method stub
		BoardBean boardBean = new BoardBean();
		boardBean = sqlSession.selectOne("Board1.board_cont", num);
		return boardBean;
	}

	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("Board1.board_hit", num);
	}

	public void boardEdit(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("Board1.board_edit", bean);
	}

	public void boardDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("Board1.board_delete", num);
	}

	public int getListCount3(Map m) throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		count = ((Integer)sqlSession.selectOne("Board1.boardfind_cnt", m)).intValue();
		return count;
	}

	public List<BoardBean> getBoardList3(Map m) throws Exception {
		// TODO Auto-generated method stub
		List<BoardBean> list = new ArrayList<BoardBean>();
		list = sqlSession.selectList("Board1.boardfind_list", m);
		return list;
	}

	public void refUpdate(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("Board1.ref_count", bean);
	}

	public void refInsert(BoardBean bean) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("Board1.reply_insert", bean);
	}

}
