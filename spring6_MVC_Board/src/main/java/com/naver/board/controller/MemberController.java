package com.naver.board.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.board.dao.MemberService;
import com.naver.board.model.MemberBean;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main.net")
	public String main() throws Exception{
		return "/member/main";
	}
	
	@RequestMapping(value="/join.net")
	public String join() throws Exception{
		return "/member/joinForm";
	}
	
	@RequestMapping(value="/joinProcess.net")
	public String joinProcess(MemberBean member,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int isId = memberService.isId(member);
		if(isId == -1) {
			out.println("<script>");
			out.println("alert('중복된 아이디가 없습니다.');");
			out.println("</script>");
		}
		
		int result = memberService.insert(member);
		out.println("<script>");
		if(result == 1) {
			out.println("alert('" + member.getId() + "님이 가입되셨습니다.');");
			out.println("location.href='login.net'");
		} else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다.');");
			out.println("history.back();");
		}
		out.println("</script>");
		return "redirect:/login.net";
	}
	
	@RequestMapping(value="/login.net")
	public String login() throws Exception{
		
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/loginProcess.net")
	public String loginProcess(MemberBean member,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		
		int result = memberService.isId(member);
		System.out.println("결과는 " + result);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", member.getId());
			
			return "redirect:/board_list.nhn";
		} else {
			String message = "";
			if(result == -1)
				message = "비밀번호가 일치하지 않습니다.";
			else
				message = "아이디가 존재하지 않습니다.";
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='./login.net';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
	}
	
	@RequestMapping(value="/member_list.net")
	public ModelAndView member_list() throws Exception{
		List<MemberBean> list = memberService.getList();
		
		ModelAndView model = new ModelAndView("/member/member_list");
		
		model.addObject("totallist", list);
		return model;
	}
	
	@RequestMapping(value="/member_info.net")
	public ModelAndView member_info(
			@RequestParam(value="id") String id) throws Exception{
		MemberBean bean = memberService.member_info(id);
		
		ModelAndView model = new ModelAndView("/member/member_info");
		
		model.addObject("memberinfo", bean);
		return model;
	}
	
	@RequestMapping(value = "/member_delete.net")
	public String member_delete(
			@RequestParam(value="id") String id,
			HttpServletResponse response) throws Exception{
		if(id.equals("admin")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자는 삭제 하지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
			return null;
		} else {
			int result = memberService.delete(id);
			
			return "redirect:/member_list.net";
		}
	}
	
	@RequestMapping(value="/member_update.net")
	public ModelAndView member_update(
			@RequestParam(value="id") String id) throws Exception{
		MemberBean bean = memberService.member_info(id);
		
		ModelAndView model = new ModelAndView("/member/updateForm");
		
		model.addObject("memberinfo", bean);
		return model;
	}
	
	@RequestMapping(value="/updateProcess.net")
	public String updateProcess(HttpServletResponse response,
			MemberBean member) throws Exception{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int result = memberService.update(member);
		out.println("<script>");
		if(result != 0) {
			out.println("alert('" + member.getId() + "님의 프로필이 수정 되었습니다.')");
			out.println("location.href='login.net'");
		} else {
			out.println("alert('수정되지 않았습니다.');");
			out.println("history.back();");
		}
		out.println("</script>");
		
		return null;
	}
	
	@RequestMapping(value="/logout.net")
	public String logout(
			HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/login.net";
	}
}
