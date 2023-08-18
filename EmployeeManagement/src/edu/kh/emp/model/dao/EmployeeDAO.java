package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public EmployeeDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/** 전체 사원 정보 조회 DAO
	 * @param conn
	 * @return 
	 */
	public List<Employee> selectAll(Connection conn) throws Exception {

		// 결과 저장용 변수 선언
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
		
			// 쿼리 생성
			String sql = prop.getProperty("updateAll");	

			// Statement 객체 생성
			stmt = conn.createStatement(); 

			// sql을 수생후 결과 반환
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 얻어와 한 행씩 접근하여 Employee 객체를 생성 후 컬럼 값 담기
			// -> List에 추가
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID"); // EMP_ID는 문자열이지만 값은 숫자 -> 자동 형변환됨 -> int로 저장 가능
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
								
				empList.add( new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary) );
			}

		}finally {
			close(stmt);
		}
		return empList;
	}

	/** 사원 추가 DAO
	 * @param conn
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public int insertEmployee(Connection conn, Employee emp) throws Exception{
		int res = 0;
		
		String sql = prop.getProperty("insert");
		
		pstmt = conn.prepareStatement(sql);
				
		pstmt.setInt(1, emp.getEmpId());
		pstmt.setString(2, emp.getEmpName());
		pstmt.setString(3, emp.getEmpNo());
		pstmt.setString(4, emp.getEmail());
		pstmt.setString(5, emp.getPhone());
		pstmt.setString(6, emp.getDeptCode());
		pstmt.setString(7, emp.getJobCode());
		pstmt.setString(8, emp.getSalLevel());
		pstmt.setInt(9, emp.getSalary());
		pstmt.setDouble(10, emp.getBouns());
		pstmt.setInt(11, emp.getManagementId());
		
		res = pstmt.executeUpdate();
		
		close(pstmt);
		
		return res;
	}

	/** 사번 일치하는 사원 조회 DAO
	 * @param conn
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpId(Connection conn, Employee emp) throws Exception{
		
		Employee selectEmp = new Employee();
		
		try {
			
			String sql = prop.getProperty("selectEmpId");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpId());
			
			rs = pstmt.executeQuery();

//			while(rs.next()) {
			
			if(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");

				selectEmp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary);
			}
			
		}finally {
			close(pstmt);
		}
		
		return selectEmp;

	}
	
	/** 사번 받아 정보 수정 DAO
	 * @param conn
	 * @param em
	 * @return
	 */
	public int updateEmployee(Connection conn, Employee emp) throws Exception{

		int result = 0;
		
		String sql = prop.getProperty("updateEmployee");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpNo());
		pstmt.setString(3, emp.getEmail());
		pstmt.setString(4, emp.getPhone());
		pstmt.setString(5, emp.getDeptCode());
		pstmt.setString(6, emp.getJobCode());
		pstmt.setString(7, emp.getSalLevel());
		pstmt.setInt(8, emp.getSalary());
		pstmt.setDouble(9, emp.getBouns());
		pstmt.setInt(10, emp.getManagementId());
		pstmt.setInt(11, emp.getEmpId());
		
		result = pstmt.executeUpdate();
		System.out.println(result);
		return result;
		
		
	}

	/** 사번 받아 정보 삭제
	 * @return
	 */
	public int deleteEmployee(Connection conn, int empId) throws Exception{
		
		int result = 0;
		
		String sql = prop.getProperty("delete");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		
		result = pstmt.executeUpdate();
		
		close(pstmt);
		
		return result;
	}

	/**입력 받은 부서와 일치하는 모든 사원 정보 조회 DAO
	 * @param conn
	 * @param dept 
	 * @return
	 */
	public List<Employee> selectDept(Connection conn, String dept) throws Exception {
	
		List<Employee> empList = new ArrayList<Employee>();
		
		try {

			String sql = prop.getProperty("selcetDept");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dept);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
//				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");

				empList.add(new Employee(empId, empName, empNo, email, phone, dept, jobName, salary) );

			}
		
		}finally {
			close(pstmt);
		}
		return empList;
	}
	
	/**입력 받은 급여 이상을 받는 모든 사원 정보 조회 DAO
	 * @param conn
	 * @param sal
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalary(Connection conn, int sal) throws Exception{
		
		List<Employee> empList = new ArrayList<Employee>();
		
		try {

			String sql = prop.getProperty("selectSalary");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, sal);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");

				empList.add(new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary) );

			}
		
		}finally {
			close(pstmt);
		}
		return empList;
	
	}

	/** 부서별 급여 합 전체 조회 DAO
	 * @param conn
	 * @return
	 */
	public Map<String, Integer> selectDeptTotalSalary(Connection conn) throws Exception{
		
		Map<String, Integer> salarySum = new LinkedHashMap<String, Integer>();
		
		//LinkedHashMap : 정렬 결과 유지 
		
		
		try {

			String sql = prop.getProperty("selectSalarySum");

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String departmentCode = rs.getString("DEPT_CODE");
				int salary = rs.getInt("SUM(SALARY)");

				salarySum.put(departmentCode, salary);

			}
		
		}finally {
			close(pstmt);
		}
		return salarySum;
	}
	
	/** 주민등록번호가 일치하는 사원 정보 조회 서비스 DAO
	 * @param conn
	 * @param empNo
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpNo(Connection conn, String empNo) throws Exception{
		
		Employee emp = null;
		
		try {
			String sql = prop.getProperty("selsectEmpNo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME");
				String empNo2 = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");

				emp = new Employee(empId, empName, empNo2, email, phone, departmentTitle, jobName, salary);
			}
		
		}finally {
			
		close(pstmt);
	}
		
		return emp;
	}

	/**직급별 급여 평균 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> avgSalary(Connection conn)throws Exception {
		
		Map<String, Integer> avgSalary = new HashMap<String, Integer>();
		
		try {

			String sql = prop.getProperty("avgSalary");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");

				avgSalary.put(jobName, salary);
			}
		
		}finally {
			close(pstmt);
		}
		return avgSalary;
	
		
	}
	
	
	
	
	
}
