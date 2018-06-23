package com.naver.myhome5.model;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("util/SqlMapConfig.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			
			session = sf.openSession(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public List<BoardBean> getBoardList(){
		List<BoardBean> list = new ArrayList<BoardBean>();
		SqlSession session = null;
		try {
			session = getSession();
			
			list = session.selectList("getBoardList");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int boardInsert(BoardBean board) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();

			result = session.insert("boardInsert", board);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
