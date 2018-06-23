package dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import model.Member;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDao {
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("sqlMapConfig2.xml");

			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);

			session = sf.openSession(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return session;
	}

	public int chk(Member member) {
		int result=0; //아이디가 없는 경우
		SqlSession session=null;
		try {
			session = getSession();
			//조회결과가 없는 경우는 mem은 null입니다.
			Member mem=
			(Member) session.selectOne("select", member.getId());
			if(mem != null) {
				if(mem.getId().equals(member.getId())) {
					result = -1; //아이디는 같고 비번이 다른 경우
					if (mem.getPassword().equals(member.getPassword()));{
						result = 1; //아이디와 비번이 같은 경우
					}
				}
			} else {
			System.out.println("chk() 결과 = null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return result;
	}

	public int insert(Member member) {
		int result=0; //아이디가 없는 경우
		SqlSession session=null;
		
		/*첫번째 매개변수 "Member1.insert"는 SQL 맵퍼 파일에서
					namespace가 "Member1"이고 아이디가 "insert"로 정의도니
					태그의 아이디입니다.
			"Member1.insert" : Member.xml(SQL이 저장된 맵퍼 파일)에서
			namespace가 "Member1"이고 아이디가 "insert"인 태그를 찾습니다.
			
		 두번째 매개변수 : SQL문을 실행할 때 입력 매개 변수에 값을 공급할 객체입니다.
		 	member의  자료형은 paramemterType과 일치해야 합니다.
		 	<insert id="insert" parameter="Member">
		 	insert into member22 values (#{id}, #{password})
		 	</insert>
		*/
		try {
			session=getSession();
			result = session.insert("Member1.insert", member);
			//result = session.insert("insert", member);
		} catch( Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return result;
	}

	public List<Member> list() {
		List<Member> list =new ArrayList<Member>();
		SqlSession session=null;
		try {
			session = getSession();
			
			list = session.selectList("list");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return list;
	}

	public Member select(String id) {
		Member mem = null;
		SqlSession session = null;
		try {
			session = getSession();
			mem = (Member) session.selectOne("select", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return mem;
	}

	public int update(Member mem) {
		int result =0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.update("update", mem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return result;
	}
	public int delete(String id) {
		int result =0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.update("delete", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();	//SqlSession 을 닫아 자원을 해제합니다.
		}
		return result;
		
	}
}
