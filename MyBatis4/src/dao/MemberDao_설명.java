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

	/*5) SQL 맵퍼 파일 : SQL문을 담고 있는 파일로 SqlSession 객체가 참조합니다.*/

	/*SqlSession의 주요 메서드
	1) selectList() : Select 문을 실행합니다.
					 값 객체(Value Object) 목록을 반환합니다.
	2) selectOne() : select 문을 실행합니다. 하나의 값 객체를 반환합니다.
	3) insert() : insert문을 실행합니다. 반환값은 입력한 데이터의 갯수입니다.
	4) update() : update문을 실행합니다. 반환값은 변경한 데이터의 갯수입니다.
	5. delete() : delete문을 실행합니다. 반환값은 삭제한 데이터의 갯수입니다.
	*/				 
					 
public class MemberDao_설명 {
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader= null;
		try {
			/*sqlMapConfig.xml을 읽어오기 위해 Resources 클래스를 사용합니다.
			getResourceAsReader()메서드를 이용하면 자바 클래스 경로에 있는
			파일의 입력 스트림을 손쉽게 얻을 수 있습니다.
			*/
			reader = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			/*SqlSessionFactoryBuilder : 마이바티스 설정 파일의 내용을 토대로
			SqlSessionFactory를 생성합니다.
			build()를 통해 SqlSessionFactory 객체를 생성합니다.
			build()의 매개변수 값으로 마이바티스 설정 파일의 입력 스트림을 넘겨주어야 합니다.
			마이바티스 설정 파일은 보통 자바 클래스 경로(CLASSPATH)에 둡니다.
			*/
			
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			
			/*
			SqlSessionFactory를 통해서 SqlSession객체를 생성합니다.
			openSession(boolean)의 매개변수 값을 true로 지정하면 자동 커밋을 수행하는
			Sqlsession 객체를 반환합니다.
			기본값인 false인 경우 자동 커밋이 되지 않아 직접 commit()을 호출합니다.
			*/
			session = sf.openSession(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return session;
	}

	public int chk(Member mem) {
		return 0;
	}

	public int insert(Member mem) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}








