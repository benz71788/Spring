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

	public List<ZipcodeBean2> findZipcode(String dong) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberBean findpwd(Map pm) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertMember(MemberBean m) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public MemberBean userCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteMember(MemberBean delm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void updateMember(MemberBean member) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
