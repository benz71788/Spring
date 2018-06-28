package com.naver.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.board.model.MemberBean;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAOImpl memberDAO;

	@Override
	public int isId(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = memberDAO.isId(bean);
		return result;
	}

	@Override
	public List<MemberBean> getList() throws Exception {
		// TODO Auto-generated method stub
		List<MemberBean> list = memberDAO.getList();
		return list;
	}

	@Override
	public MemberBean member_info(String id) throws Exception {
		// TODO Auto-generated method stub
		MemberBean member = memberDAO.member_info(id);
		return member;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		int result = memberDAO.delete(id);
		return result;
	}

	@Override
	public int update(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = memberDAO.update(bean);
		return result;
	}

	@Override
	public int insert(MemberBean bean) throws Exception {
		// TODO Auto-generated method stub
		int result = memberDAO.insert(bean);
		return result;
	}

}
