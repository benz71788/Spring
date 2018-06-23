package net.member.db;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
public class MemberDAO {
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("sqlMapConfig_member.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			
			session = sf.openSession(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public int isId(String id, String pass) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			
			Member member = (Member) session.selectOne("idcheck", id);
			if(member != null) {
				if(member.getId().equals(id)) {
					result = -1;
					if(member.getPassword().equals(pass)) {
						result = 1;
					}
				}
			} else {
				System.out.println("idcheck() 결과 = null"); 
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public List<Member> getList(){
		List<Member> list = new ArrayList<Member>();
		SqlSession session = null;
		try {
			session = getSession();
			list = session.selectList("getList");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public Member member_info(String id) {
		Member member = new Member();
		SqlSession session = null;
		try {
			session = getSession();
			member = (Member) session.selectOne("idcheck", id);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return member;
	}
	
	public void delete(String id) {
		SqlSession session = null;
		try {
			session = getSession();
			session.delete("delete", id);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public int update(Member m) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.update("update", m);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public int insert(Member m) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.insert("insert", m);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return result;
	}
}
