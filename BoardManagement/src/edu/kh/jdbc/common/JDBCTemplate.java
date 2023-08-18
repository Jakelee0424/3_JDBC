package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	// 1. DB연결(Connection 생성), 자동 커밋 off
	// 2. 트랜잭션 제어
	// 3. JDBC 객체 반환(close)
	
	// JDBC에서 반복사용하는 코드 모음 클래스
	
	// 모든 필드, 메서드가 ** Static **
	// -> 별도 객체 생성 X 
	// 어디서든지 클래스명.필드명 / 클래스명.메서드명 호출
	
	private static Connection conn = null;
//	private static Statement stmt = null;
//	private static ResultSet rs = null;
	
	/** DB 연결 정보를 담고있는 Connection 객체 생성 및 반환 메서드 
	 * @return conn
	 */
	public static Connection getConnection() {
		
		try {
			
			// 현재 커넥션 객체가 없을 경우만 새 커넥션 생성
			if(conn == null || conn.isClosed()) { // isClosed : 클로즈 상태면 true 반환
				
				Properties prop = new Properties();
				
				// XML 파일에 저장된 내용이 Properties객체에 모두 저장
				FileInputStream fis = new FileInputStream("driver.xml"); 
				prop.loadFromXML(fis);
				
//				prop.loadFromXML(new FileInputStream("driver.xml"));
				
				//XML에서 읽어온 값을 모두 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String pw = prop.getProperty("password");
				
				// 커넥션 생성
				Class.forName(driver);
				
				// 드라이버 매니저로 커넥션 객체 생성
				conn = DriverManager.getConnection(url, user, pw);
				
				// 자동커밋 비활성화
				conn.setAutoCommit(false);
				
			}
			
		}catch(Exception e) {
			System.out.println("[Connection 생성중 예외 발생]");
			e.printStackTrace();
		}

		return conn;
	}
	
	/** Connection 객체 자원 반환 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {

		try {
			
			// 전달 받은 conn이 참조하는 Connection 객체가 있고, 반환되지 않았을 경우
			if(conn != null && !conn.isClosed()) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/** Statement(부모), PreparedStatement(자식) 객체 자원 반환 메서드
	 * @param stmt
	 */
	public static void close(Statement stmt) {

		try {
			
			// 전달 받은 stmt가 참조하는 Connection 객체가 있고, 반환되지 않았을 경우
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/** ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {

		try {
			
			// 전달 받은 rs가 참조하는 ResultSet 객체가 있고, 반환되지 않았을 경우
			if(rs != null && !rs.isClosed()) rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) { // 커넥션 객체 안에 있음
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
	}

	/** 트랜잭션 rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) { // 커넥션 객체 안에 있음

		try {

			if(conn != null && !conn.isClosed()) conn.rollback();

		}catch(Exception e) {
			e.printStackTrace();			
		}

	}
}




