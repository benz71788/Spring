package com.json.jsonroot.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.json.jsonroot.model.BoardVO;

@Repository
public class DAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	

	public BoardVO get_whereid(int id) {
		// TODO Auto-generated method stub
		System.out.println("여기는 get_whereid()입니다.");
		return sqlSession.selectOne("select_whereid", id);
	}

	public void setInsert(BoardVO b) {
		// TODO Auto-generated method stub
		System.out.println("여기는 setInsert()입니다.");
		sqlSession.insert("data_insert", b);
	}

	public List<BoardVO> selectall() {
		// TODO Auto-generated method stub
		System.out.println("여기는 selectall() 입니다."); 
		return sqlSession.selectList("select_all");
	}
}
