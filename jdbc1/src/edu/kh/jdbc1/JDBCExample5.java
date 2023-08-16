package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc3.model.vo.Employee;

public class JDBCExample5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입사일을 입력받아 (2022-09-06)
		// 입력받은 값보다 먼저 입사한 사람의
		// 이름, 입사일, 성별(M/F) 조회
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sb = null;
		
		try {
			
			System.out.print("입사일을 입력하세요('yyyy-mm-dd'): ");
			String input = sc.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(url, user, pw);
						
			String sql = "SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') AS HIRE_DATE,"
					+ " (CASE WHEN SUBSTR(EMP_NO, 8, 1) = 1 THEN 'M' ELSE 'F' END) AS GENDER"
//					+ " DECODE(SUBSTR(EMP_NO, 8, 1), '1', 'M', '2', 'F') AS GENDER"
					+ " FROM EMPLOYEE"
					+ " WHERE HIRE_DATE < TO_DATE('" + input + "')";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				
				String empName = rs.getString("EMP_NAME");
				String hireDate = rs.getString("HIRE_DATE");
				char gender = rs.getString("GENDER").charAt(0); // DB에는 char 없음
				
				Employee emp = new Employee(empName, hireDate, gender); // 기본 생성자로 세터 해도 됨
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회결과 없음");
			}else {
				
				for(int i = 0 ; i < list.size() ; i++) {
					System.out.println(i+1 + ") " + list.get(i));
				}
				
//				for(Employee emp : list)
//				System.out.println(emp);
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
