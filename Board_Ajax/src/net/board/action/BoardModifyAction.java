package net.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.*;


public class BoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		request.setCharacterEncoding("euc-kr");
		BoardDAO bdao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		//전달 받은 파라미터 num 변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		
		//글쓴이 인지 확인하기 위해 저장된 비밀번호와 입력한 비밀번호를 비교합니다.
		boolean usercheck = bdao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
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
		//비밀번호가 일치하는 경우 수정 내용을 설정합니다.
		boarddata.setBOARD_NUM(num);
		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		
		///DAO에서 수정 메소드 호출하여 수정합니다.
		result = bdao.boardModify(boarddata);
		//수정에 실패한 경우
		if(result==false) {
			System.out.println("게시판 수정 실패");
			return null;
		}
		//수정 성공의 경우
		System.out.println("게시판 수정 완료");
		
		forward.setRedirect(true);
		//수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기위해 경로를 설정
		forward.setPath("./BoardDetailAction.bo?num=" + boarddata.getBOARD_NUM());
		
		return forward;
	}
}








