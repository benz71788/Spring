package com.naver.board.dao;

import java.util.List;

import com.naver.board.model.MemberBean;

public interface MemberService {
	
	public int isId(MemberBean bean) throws Exception;
	
	public List<MemberBean> getList() throws Exception;
	
	public MemberBean member_info(String id) throws Exception;
	
	public int delete(String id) throws Exception;
	
	public int update(MemberBean bean) throws Exception;
	
	public int insert(MemberBean bean) throws Exception;
}
