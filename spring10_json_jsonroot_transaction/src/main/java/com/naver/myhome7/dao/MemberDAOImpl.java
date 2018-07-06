package com.naver.myhome7.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome7.model.MemberBean;
import com.naver.myhome7.model.ZipcodeBean2;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/****** 아이디 중복 체크 ******/
	public MemberBean checkMemberId(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return (MemberBean)sqlSession.selectOne("login_check", id);
	}
	
	/*우편 검색*/
	public List<ZipcodeBean2> findZipcode(String dong) throws Exception {
		// TODO Auto-generated method stub
		List<ZipcodeBean2> list = sqlSession.selectList("zipcodeList", dong);
		return list;
	}

	public MemberBean findpwd(Map pm) throws Exception {
		// TODO Auto-generated method stub
		
		return (MemberBean)sqlSession.selectOne("pwd_find", pm);
	}

	public void insertMember(MemberBean m) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("member_join", m);
		
	}

	public MemberBean userCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return (MemberBean)sqlSession.selectOne("login_check", id);
	}

	public void deleteMember(MemberBean delm) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("member_delete", delm);
	}

	public void updateMember(MemberBean member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("member_edit", member);
	}
}
