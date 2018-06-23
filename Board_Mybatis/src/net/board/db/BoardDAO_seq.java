package net.board.db;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * DAO(Data Access Object) 클래스
 * - 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이
 * 		이루어지는 클래스 입니다.
 * - 어떤 Action 클래스가 호출되더라도 그에 해당하는
 * 		데이터 베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */
public class BoardDAO_seq {
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("sqlMapConfig_board.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			
			session = sf.openSession(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
	public int getListCount() {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.selectOne("getListCount");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
	
	//글 목록 보기
	public List<BoardBean> getBoardList(int page, int limit){
		List<BoardBean> list = new ArrayList<BoardBean>();
		Map<String, Object> map = new HashMap<String, Object>();
		SqlSession session = null;
		try {
			session = getSession();
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			map.put("startrow", startrow);
			map.put("endrow", endrow);
			list = session.selectList("getBoardList", map);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean boardInsert(BoardBean board) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			
			result = session.insert("boardInsert", board);
			if(result == 0) {
				return false;
			}
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public void setReadCountUpdate(int num) {
		SqlSession session = null;
		try {
			session = getSession();
			session.update("setReadCount", num);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public BoardBean getDetail(int num) {
		BoardBean board = new BoardBean();
		SqlSession session = null;
		try {
			session = getSession();
			board = (BoardBean) session.selectOne("getDetail", num);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return board;
	}
	
	//글 답변
	public int boardReply(BoardBean board) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			session.update("setReplyCount", board);
			
			board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ() + 1);
			board.setBOARD_RE_LEV(board.getBOARD_RE_LEV() + 1);
			
			result = session.insert("replyInsert", board);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public boolean isBoardWriter(int num, String pass) {
		BoardBean board = new BoardBean();
		SqlSession session = null;
		try {
			session = getSession();
			board = (BoardBean) session.selectOne("isBoardWriter", num);
			if(pass.equals(board.getBOARD_PASS())) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean boardModify(BoardBean board) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.update("boardModify", board);
			if(result != 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean boardDelete(int num) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.delete("boardDelete", num);
			if(result != 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}
