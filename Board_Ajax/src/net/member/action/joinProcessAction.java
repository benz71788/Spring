package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class joinProcessAction implements Action{

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
		
		MemberDAO mdao = new MemberDAO();
		Member m = new Member();
		
		m.setId(id);
		m.setPassword(password);
		m.setName(name);
		m.setAge(age);
		m.setGender(gender);
		m.setEmail(email);
		
		int result = mdao.join(m);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		
		
		//삽입이 된 경우
		int isid = mdao.isId(id, password);
		if(isid!=-1) {
			out.println("<script>");
			out.println("alert('등록된 아이디가 있습니다.')");
			out.println("history.back();");
			out.println("</script>");
			return null;
		}
		
		
		out.println("<script>");
		if(result==1) {
		out.println("alert('가입이 완료되었습니다.')");
		out.println("location.href='./login.net'");
		
		}else if(result==-1){
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요')");
			//out.println("location.href='./join.net';");//새로고침
			out.println("history.back()");//비밀번호를 제외한 다른 데이터
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
