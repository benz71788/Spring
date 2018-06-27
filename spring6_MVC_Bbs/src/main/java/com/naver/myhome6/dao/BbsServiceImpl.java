package com.naver.myhome6.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome6.model.BbsBean;

@Service("boardService")
public class BbsServiceImpl implements BbsService{
	
	/*
	 * 3개의 DAO 파일을 작성하였습니다.
	 * 만약 BbsDAOImpl_old를 사용하고 싶다면 @Autowired할 클래스 이름만 바꾸어 주고
	 * BbsDAOImpl_old.java 파일은 @Repository를 붙여주면 됩니다.
	 * 
	 * 이 처럼 DAO 파일이 바뀌어도 이곳에서만 클래스 명만 바꾸어 주면 됩니다.
	 * DAO를 간접 사용하고 있는 BbsAction2.java는 수정할 필요가 없습니다.
	 */
	
	@Autowired
	private BbsDAOImpl bbsDAO;

	@Override
	public void insertBbs(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.insertBbs(bbsbean);
	}

	@Override
	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		int result = bbsDAO.getListCount();
		return result;
	}

	@Override
	public List<BbsBean> getBbsList(int page) throws Exception {
		// TODO Auto-generated method stub
		List<BbsBean> list = new ArrayList<BbsBean>();
		list = bbsDAO.getBbsList(page);
		return list;
	}

	@Override
	public BbsBean getBbsCont(int num) throws Exception {
		// TODO Auto-generated method stub
		BbsBean bbsbean  = new BbsBean();
		bbsbean = bbsDAO.getBbsCont(num);
		return bbsbean;
	}

	@Override
	public void bbsHit(int num) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.bbsHit(num);
		
	}

	@Override
	public void editBbs(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.editBbs(bbsbean);
		
	}

	@Override
	public void deleteBbs(int bbs_num) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.deleteBbs(bbs_num);
	}

	@Override
	public int getListCount3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return bbsDAO.getListCount3(m);
	}

	@Override
	public List<BbsBean> getBbsList3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return bbsDAO.getBbsList3(m);
	}

	@Override
	public void refEdit(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.refEdit(bbsbean);
		
	}

	@Override
	public void bbsReplyOk(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		bbsDAO.bbsReplyOk(bbsbean);
	}

}
