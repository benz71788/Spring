package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class ListAction implements Action {
		@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		List<Member> list = mdao.getList();
		
		forward.setPath("/member/member_list.jsp");
		forward.setRedirect(false);
		request.setAttribute("totallist", list);
	
		return forward;
	}
}
