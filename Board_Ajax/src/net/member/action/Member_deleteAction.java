package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Member_deleteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, 
         HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      request.setCharacterEncoding("euc-kr");
      String id = request.getParameter("id");
      if (id.equals("admin")) {
         response.setContentType("text/html;charset=euc-kr");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('관리자는 삭제 하지 않습니다. ');");
            out.println("history.back()");
            out.println("</script>");
            out.close();
            return null;
      } else {
    	  response.setContentType("text/html;charset=euc-kr");
          PrintWriter out=response.getWriter();
          out.println("<script>");
          out.println("alert('계정을 삭제하였습니다. ');");
          out.println("history.back()");
          out.println("</script>");
          out.close();
         MemberDAO mdao = new MemberDAO();
         mdao.delete(id);
         forward.setPath("member_list.net");
         forward.setRedirect(false);
      }
      return forward;
   }
}






