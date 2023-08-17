package edu.kh.jdbc.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import static edu.kh.jdbc.common.JDBCTemplate.*; 
// import static 구문
// static이 붙은 필드, 메서드를 호출할 때
// 클래스명 생략할 수 있게 해줌

import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {
	// Service : 비즈니스 로직(데이터 가공, 트랜잭션 제어) 처리
	// --> 즉, 실제 프로그램이 제공하는 기능을 모아놓은 클래스
	
	// 하나의 Service 메서드에서 n개의 DAO 메서드를 호출하여 
	// 이를 하나의 트랜잭션 단위로 취급하여 
	// 한번에 commit, rollback을 수행할 수 있다.
	
	
	private TestDAO dao = new TestDAO();
	
	
	public int insert(TestVO vo1) throws SQLException {		
		
	// 1) 커넥션 생성(템플릿에 작성됨)
		Connection conn = getConnection(); // static 메서드
		
	// 2) DAO 메서드 호출 	
		int result = dao.insert(conn, vo1);// service에서 생성한 Connection 객체를 같이 전달
	
	// 3) 트랜잭션 제어
		if(result > 0) commit(conn);
		else rollback(conn);
		
	// 4) 커넥션 반환
		close(conn);
		
	// 5) 결과 반환	
		return result;
	
	}
	
	public int insert2(TestVO vo1, TestVO vo2, TestVO vo3) throws SQLException{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		try {
			
			int res1 = dao.insert(conn, vo1);
			int res2 = dao.insert(conn, vo2);
			int res3 = dao.insert(conn, vo3);
			
			if(res1 + res2 + res3 == 3) {
				commit(conn);
				result = 1;
			}else {
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
//		int result = dao.insert2(conn, vo1, vo3, vo3);
//		
//		if(result > 0) commit(conn);
//		else rollback(conn);
//		
//		close(conn);
//		
//		return result;
	}

	public int insert3(TestVO vo1, TestVO vo2, TestVO vo3, TestVO vo4, TestVO vo5) throws SQLException{
	
		int result = 0;
	
		Connection conn = getConnection();
		
		int res1 = dao.insert2(conn, vo1);
		int res2 = dao.insert2(conn, vo2);
		int res3 = dao.insert2(conn, vo3);
		int res4 = dao.insert2(conn, vo4);
		int res5 = dao.insert2(conn, vo5);
		
		if(res1+res2+res3+res4+res5 == 5) {
			commit(conn);
			result = 1;
		}else {
			rollback(conn);
		}
		
		return result;
		
	}

	/** 제목, 내용 수정 Service
	 * @param vo
	 * @return result
	 */
	public int update(TestVO vo) throws Exception {
		int result = 0;
		
		Connection conn = getConnection();
		
		int res = dao.update(conn, vo);
		
		
		if(res > 0) {
			commit(conn);
			result = 1;
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
