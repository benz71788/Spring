package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		request.setCharacterEncoding("euc-kr");
		boolean result =false;
		boolean usercheck=false;
		int num = Integer.parseInt(request.getParameter("num"));
	
		//글쓴이 인지 확인하기 위해 저장된 비밀번호와 입력한 비밀번호를 비교합니다.
		usercheck = bdao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
		
		//비밀번호가 다른 경우
		if(usercheck==false) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}		
		boarddata.setBOARD_NUM(num);
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 board 객체에 저장합니다.
		result = bdao.delete(boarddata);
		request.setAttribute("boarddata", boarddata);
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if(result ==false) {
			System.out.println("삭제 실패");
			return null;
		}
		System.out.println("삭제 성공");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다..')");
		out.println("location.href='./BoardList.bo';");
		out.println("</script>");
		out.close();
		return null;
	}

}
