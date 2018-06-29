package com.naver.myhome7.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.myhome7.dao.MemberService;

@Controller
public class MemberAction {
	
	@Autowired
	private MemberService memberService;
	
	//배포시 필요한 경로
//	private String saveFoler = "D:/workspace-sts/spring7_MVC_member/src/main/webapp/resources/upload";
	private String saveFolder = "D:\\workspace-sts\\spring7_MVC_member\\src\\main\\webapp\\resources\\upload";
	
	
	/*로그인 폼 뷰*/
	@RequestMapping(value="/member_login.nhn")
	public String member_login(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		return "member/member_login";
		//member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
	@RequestMapping(value="/member_join.nhn")
	public String member_join(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		return "member/member_join";
		//member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
	@RequestMapping(value="/member_idcheck.nhn", method=RequestMethod.POST)
	public void member_idcheck(
			@RequestParam(value = "join_id") String id,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = response.getWriter();
		int result = memberService.checkMemberId(id);
		out.print(result);
	}
}
