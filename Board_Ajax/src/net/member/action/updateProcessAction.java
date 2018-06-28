package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class updateProcessAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		
		Member m = new Member();
		m.setPassword(password);
		m.setAge(age);
		m.setEmail(email);
		m.setGender(gender);
		m.setId(id);
		m.setName(name);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.update(m);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		//삽입이 된 경우
		if(result==1) {
		out.println("alert('수정이 완료되었습니다.')");
		out.println("location.href='./main.net'");
		}else {
			out.println("aler('수정을 하지 못하였습니다.')");
			out.println("history.back();");
		}
		out.println("</script>");
		out.close();
		
		// TODO Auto-generated method stub
		return null;
	}

}
