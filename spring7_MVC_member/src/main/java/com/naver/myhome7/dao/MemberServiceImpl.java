package com.naver.myhome7.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome7.model.MemberBean;
import com.naver.myhome7.model.ZipcodeBean2;

@Service("memberdService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired 
	private MemberDAOImpl memberDAO;
	
	@Override
	public int checkMemberId(String id) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		
		MemberBean mb = memberDAO.checkMemberId(id);
		if(mb != null) {
			result = 1;
		}
		return result;
	}

	@Override
	public List<ZipcodeBean2> findZipcode(String dong) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberBean findpwd(Map pm) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMember(MemberBean m) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberBean userCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(MemberBean delm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(MemberBean member) throws Exception {
		// TODO Auto-generated method stub
		
	}
    
	
	
}
