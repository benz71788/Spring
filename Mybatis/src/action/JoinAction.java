package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import model.Member;

public class JoinAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws Exception{
	   	String id=request.getParameter("id");
	   	String password=request.getParameter("password");
	   	
	 
}