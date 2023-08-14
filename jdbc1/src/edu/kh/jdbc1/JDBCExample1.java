package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {

	public static void main (String[] args) {
		
		// JDBC : Java에서 DB에 연결할 수 있게 해주는 Java Programming API(Java에 기본으로 내장되어 있는 인터페이스)
		// 		  java.sql 패키지에서 제공
		
		// JDBC를 이용한 애플리케이션을 만들 때 필요한 것
		// 1. Java의 JDBC 관련 인터페이스
		// 2. DBMS(Oracle)
		// 3. Oracle에서 Java 애플리케이션과 연결할 때 사용할 JDBC를 상송받아 구현한 클래스 모음(OJDBC)
		// 		-> OracleDriver.class 이용 (JDBC 드라이버)
		
		
	// 1단계 : JDBC 객체 참조변수 선언(java.sql 패키지의 인터페이스)
		
		Connection conn = null;  //java.sql에 포함
		// DB 연결 정보를 담은 객체
		// -> DBMS 타입, 이름 IP, Port, 계정명, 비밀번호 저장
		// DBeaver의 계정 접속 방법과 유사
		//  	즉 ** 자바와 DB사이를 연결해주는 통로 생성 **
		
		Statement stmt = null; //java.sql에 포함
		// 자바에서 쿼리를 실은 셔틀 버스를 DB로 보내고, 결과 값을 실고 자바로 돌아옴
		// Connection을 통해 SQL문을 DB에 전달하여 실행하고, 생성된 결과(RESULT SET, 성공한 행의 개수 등)을 반환(Java)에 사용되는 객체
		
		ResultSet rs = null;  //java.sql에 포함
		// SELECT 질의 성공시 반환될 때, 조회 결과 집합을 나타내는 객체
		// RESULT SET의 형태만 담을 수 있음
		
		
	// 2단계 : 참조변수에 알맞은 객체 대입
		
		// 1. DB연결에 필요한 Oracle JDBC Driver 메모리에 로드하기 
		try {
			// ()안에 작성된 클래스의 객체를 반환
			// 메모리에 객체가 생성되어지고 JDBC 필요시 알아서 참조하여 사용
			// 생략해도 자동으로 메모리 로드가 진행됨(명시적 작성을 권장)
			
					// 클래스 로더가 ()를 읽어낸 후 인스턴스를 만듦.
					// -> 만든 인스턴스를 DriverManager에 등록 
					// -> Connection 인터페이스 구현 객체 생성(getConnection(DBMS 타입, 이름 IP, Port, 계정명, 비밀번호)메서드를 통해)
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  // 런타임 시점에 해당 경로의 클래스를 동적으로 로드함
	
			
		// 2. 연결 정보를 담은 Connection을 생성
		// DriverManager 객체를 이용해 Connection 객체를 만들어 얻어옴.
			
			String type = "jdbc:oracle:thin:@";  // jdbc 드라이버의 종류 중
			String ip = "localhost";  // db서버 컴퓨터 ip 
			String port = ":1521";  // 포트번호
			String sid = ":XE";  // DB 이름
			String user = "kh";  // 사용자 계정
			String pw = "kh1234"; // 비밀번호
			
			//	url = jdbc:oracle:thin:@localhost:1521:XE
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
		
			// DriverManager : 메모리에 로드된 JDBC 드라이버를 이용해서 Connection 객체를 만드는 역할.
			
			
			// 중간확인
			// System.out.println(conn);
			
		
		// 3. SQL 작성
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE FROM EMPLOYEE";
			// JAVA에서 작성되는 SQL은 " "안에서 ; 제외
			
			
		// 4. Statement 객체 생성
			stmt = conn.createStatement(); // Connection 객체를 통해서 생성
			
		
		//5. 생성된 Statement 객체에 sql적재하여 실행 후, 결과 반환 받아 rs에 저장
			rs = stmt.executeQuery(sql); // executeQuery() : SELECT문 수행 메서드, ResultSet 반환
			
	
	// 3단계 : SQL을 수행해서 반환 받은 결과를 한 행씩 접근해서 컬럼값 받아오기	
			
			while(rs.next()) { // rs.next() : rs가 참조하고 있는 ResultSet 객체의 첫번째 컬럼부터 한행씩 이동하며 
							   //			  다음 행이 있을 경우 true, 없으면 false 반환
				
				String empId = rs.getString("EMP_ID"); // 현재 행의 "EMP_ID" 문자열 컬럼의 값을 얻어옴
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				Date hireDate = rs.getDate("HIRE_DATE");
				
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d / 입사일 : %s \n", empId, empName, salary, hireDate.toString());
																								// java.sql.Date의 toString()은 yyyy-mm-dd형식으로 오버라이딩
			}
	
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 경로가 잘못 작성되었습니다.");
		} catch(SQLException e ) { // DB관련 최상위 예외
			e.printStackTrace();;
		} finally {
	
	// 4단계 : 사용한 JDBC 객체 자원 반환 ( close() )		
			// 생성 역순으로 닫을 것을 권장 ResultSet, Statement, Connection -> Connection, Statement, ResultSet
			
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();	
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}