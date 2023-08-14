package edu.kh.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		// 1단계 : JDBC 객체 참조변수 생성
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// 2단계 : 참조변수에 알맞은 객체 대입
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";  // jdbc 드라이버의 종류 중
			String ip = "localhost";  // db서버 컴퓨터 ip 
			String port = ":1521";  // 포트번호
			String sid = ":XE";  // DB 이름
			String user = "kh";  // 사용자 계정
			String pw = "kh1234"; // 비밀번호
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
//--------------------------------------------------------------------------------- conn
			stmt = conn.createStatement();

			System.out.println("<입력 받은 급여보다 많이 받는(초과) 직원만 조회");
			System.out.print("금액 입력 : ");
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input;
			 
//--------------------------------------------------------------------------------- stmt			
			rs = stmt.executeQuery(sql);
//--------------------------------------------------------------------------------- rs
			
		// 3단계 : sql 수행 후 반환받은 결과 출력	
			
			while(rs.next()) {
				
				String empId=rs.getString("EMP_ID");
				String empName=rs.getString("EMP_NAME");
				int salary=rs.getInt("SALARY");
				
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d\n", empId, empName, salary);
				 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {

			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		
	}
}
