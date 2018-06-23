/*
 * 답변글을 테이블에 저장해 주는 Action 클래스
 * 글 답변 폼 페이지에서 답변 내용을 입력하고 답변을 클릭했을 경우 답변글을
 * 테이블에 저장해주는 역할을 하는 액션 클래스입니다.
 */
package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO_seq;

public class BoardReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터로 가져올 때 한글이 깨지지 않도록 하기 위한 문장입니다.
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		BoardDAO_seq boardDAO = new BoardDAO_seq();
		BoardBean boardData = new BoardBean();
		int result = 0;
		int num = 0;
		
		//파라미터로 넘어온 값들을 boardData 객체에 저장합니다.
		boardData.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boardData.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boardData.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boardData.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boardData.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		boardData.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		boardData.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		boardData.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
		
		//답변을 DB에 저장하기 위해 boardData 객체를 파라미터로
		//DAO의 메서드 boardReply를 호출합니다.
		result = boardDAO.boardReply(boardData);
		
		//답변 저장에 실패한 경우
		if(result == 0) {
			System.out.println("답장 실패");
			return null;
		}
		
		num = boardDAO.getListCount();
		//답변 저장이 제대로 된 경우
		System.out.println("답장 완료");
		forward.setRedirect(true);
		//답변 글 내용을 확인하기 위해 글 내용 보기 페이지를 경로로 설정합니다.
		forward.setPath("./BoardDetailAction.bo?num=" + num);
		return forward;
	}

}
