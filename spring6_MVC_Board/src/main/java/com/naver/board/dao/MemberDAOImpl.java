package com.naver.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.board.model.MemberBean;

/*
 * DAO(Data Access Object) 클래스
 * - 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이
 * 		이루어지는 클래스 입니다.
 * - 어떤 Action 클래스가 호출되더라도 그에 해당하는
 * 		데이터 베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */
@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int isId(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		MemberBean member = sqlSession.selectOne("Member1.idcheck", bean.getId());
		if(member != null) {
			if(member.getId().equals(bean.getId())) {
				result = -1;
				if(member.getPass().equals(bean.getPass())) {
					result = 1;
				}
			}
		} else {
			System.out.println("idcheck() 결과 = null");
		}
		return result;
	}

	public List<MemberBean> getList() throws Exception {
		// TODO Auto-generated method stub
		List<MemberBean> list = sqlSession.selectList("Member1.getList");
		return list;
	}

	public MemberBean member_info(String id) throws Exception {
		// TODO Auto-generated method stub
		MemberBean member = sqlSession.selectOne("Member1.idcheck", id);
		return member;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		int result = sqlSession.delete("Member1.delete", id);
		return result;
	}

	public int update(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = sqlSession.update("Member1.update", bean);
		return result;
	}

	public int insert(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = sqlSession.insert("Member1.insert", bean);
		return result;
	}

}
