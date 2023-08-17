package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*; 
import edu.kh.jdbc.model.vo.TestVO;

public class TestDAO {
// DAO (Data Access Object) : 데이터 접근 객체
// 데이터가 저장된 DB에 접근하는 객체
// -> sql 수행, 결과 반환 기능
	
	//JDBC객체를 참조하기 위한 참조변수 선언
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	//xml로 SQL을 다룰 것이다 -> Properties 객체 사용
	private Properties prop;
	
	
	//기본 생성자
	public TestDAO() {
		// TestDAO객체 생성시 test-query.xml 파일의 내용을 읽어와
		// Properties 객체에 저장
		
		// 기본생성자에 xml 로드 -> service에서 DAO객체 생성시 xml(필요한 쿼리)은 이미 실행
		
		try{
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("test-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	public int insert(Connection conn, TestVO v) throws SQLException{
	
		// 1. 결과 저장용 변수 선언 
		int result = 0; // DML -> 성공하면 1 아니면 0 반환 -> int형
				
		try {
				
		// 2. SQL 작성(test-query.xml에 작성된 SQL얻어오기)
		String sql = prop.getProperty("insert");
		
		// 3. PreparedStatement 객체 생성 : 위치홀더(?표시)를 쓸 때 사용 
		
		pstmt =	conn.prepareStatement(sql);  // 생성과 동시에 stmt에 sql 탑승 
										     // but 위치 홀더값 입력 필요
		
		//4. 위치홀더 값 세팅
		pstmt.setInt(1, v.getTestNo());    	// Set자료형(위치홀더 순서, 값)
		pstmt.setString(2, v.getTestTitle());
		pstmt.setString(3, v.getTestContent());
		
		// 5. SQL수행 후 결과 반환 받기
		result = pstmt.executeUpdate();   // -> DML 수행, 반영된 행의 개수(int형) 반환
		
		} finally {
		// 6. 사용한 JDBC 객체 자원 반환
			close(pstmt);
			
		}
		// 7. 수행 결과 반환
		return result;
		
	}

	
	public int insert2(Connection conn, TestVO v) throws SQLException{

		int res = 0;
		
		String sql = prop.getProperty("insert");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, v.getTestNo());
		pstmt.setString(2, v.getTestTitle());
		pstmt.setString(3, v.getTestContent());
		
		res = pstmt.executeUpdate();
		
		pstmt.close();
		
		return res;

	
	}

	/** 번호가 일치하는 행 제목, 내용 수정
	 * @param conn
	 * @param vo
	 * @return res
	 */
	public int update(Connection conn, TestVO vo) throws Exception {
		
		int res = 0; // 결과 저장용 변수
		
		try {
			
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTestTitle());
			pstmt.setString(2, vo.getTestContent());
			pstmt.setInt(3, vo.getTestNo());
			
			res = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return res;
	}
}
