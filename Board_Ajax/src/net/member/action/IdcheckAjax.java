package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class IdcheckAjax implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		MemberDAO mdao = new MemberDAO();
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		int result = mdao.checkId(id);
		
		response.setContentType("text//html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println(result);
		return null;
	}

}
