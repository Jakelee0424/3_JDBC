package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc3.model.vo.Employee;

public class JDBCExample4 {

	public static void main(String[] args) {
		//직급명, 급여를 입력받아
		//해당 직급에서 입력 받은 급여보다 많이 받는 사원의
		//이름, 직급명, 급여, 연봉을 조회하여 출력
		
		// 단 조회 결과가없으면 "조회결과 없음" 출력
		
		Scanner sc = new Scanner(System.in);
				
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		System.out.print("직급명을 입력하세요 : ");
		String input = sc.nextLine();
		
		
		System.out.print("급여를 입력하세요 : ");
		int input2 = sc.nextInt();
		System.out.println();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE";
			String user = "kh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY*12 AS ANNUALINCOME"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN JOB USING(JOB_CODE)"
					+ " WHERE JOB_NAME = '" + input + "'"
					+ " AND SALARY > " + input2;
			
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				String annulIncome = rs.getString("ANNUALINCOME");
				
				Employee emp = new Employee(empName, jobName, salary, annulIncome);
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회결과 없음");
			}else {
				
				for(Employee emp : list)
				System.out.println(emp);
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
