package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc3.model.vo.Emp;

public class JDBCExample3 {

	public static void main(String[] args) {
		
		// 부서명을 입력받아 같은 부서에 있는 사원의 
		// 사원명, 부서명, 급여 조회

		Scanner sc = new Scanner(System.in);
		StringBuilder sb;
		
		//JDBC 객체 참조 변수
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("부서명 입력 : ");
			String input = sc.nextLine();
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE";
			String user = "kh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);

			stmt = conn.createStatement();
			
			String sql = "SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY FROM EMPLOYEE LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE) WHERE NVL(DEPT_TITLE, '부서없음') = '" + input + "'" ;
			// '' 혹은 띄어쓰기 등이 누락되지 않게 조심
			
			rs = stmt.executeQuery(sql); // stmt를 이용해 DB를 실행한 후 결과값을 rs에 대입
			
			
			//조회 결과(rs)를 리스트에 옮겨담기
			List<Emp> list = new ArrayList<Emp>();
			
			
			while(rs.next()) {
			
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				int salary = rs.getInt("SALARY");
				
			// emp 객체에 컬럼값 담기
				Emp emp = new Emp(empName, deptTitle, salary);
			
			// 생성된 emp 객체 list에 추가 
				list.add(emp);
				
			}
			
			// 만약 list에 추가된 Emp객체가 없다면 "조회 결과가 없습니다"	
			
			if(list.isEmpty()) {
				System.out.println("조회결과가 없습니다.");
			}else {		
//				System.out.println(list.toString());	
				for(Emp emp : list) {
					System.out.print(emp);
				}
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
