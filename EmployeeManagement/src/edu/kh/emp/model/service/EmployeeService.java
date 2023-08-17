package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();
	
	/**
	 * 전체 사원 정보 조회 서비스
	 * @return 
	 */
	public List<Employee> selcetAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
		
	}

	/**
	 * 새로운 사원 추가 서비스
	 * @param emp 
	 */
	public int insertEmployee(Employee emp) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		int res = dao.insertEmployee(conn, emp);
		
		if(res > 0) {
			commit(conn);
			result = 1;	
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	/** 사번이 일치하는 사원 정보 조회 서비스
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpId(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		Employee selectEmp = dao.selectEmpId(conn, emp);
		
		close(conn);
		
		return selectEmp;
		
	
	}

	/** 사번이 일치하는 사원 정보 수정 서비스
	 * @param emp
	 * @return
	 */
	public int updateEmployee(Employee emp) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.updateEmployee(conn, emp);
		
		if(result > 0) {
			commit(conn);
			result = 1;	
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/**사번이 일치하는 사원 정보 삭제 서비스
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public int deleteEmployee(int empId) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.deleteEmployee(conn, empId);
		
		if(result > 0) {
			commit(conn);
			result = 1;	
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	/**입력 받은 부서와 일치하는 모든 사원 정보 조회 서비스
	 * @param dept 
	 * @return
	 */
	public List<Employee> selcetDept(String dept) throws Exception{
		
		Connection conn = getConnection();
		
		List<Employee> empList = dao.selectDept(conn, dept);
		
		close(conn);		
		
		return empList;
	}
	
	/**입력 받은 급여 이상을 받는 모든 사원 정보 조회 서비스
	 * @param sal
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalary(String sal) throws Exception {
		Connection conn = getConnection();
		
		List<Employee> empList = dao.selectSalary(conn, sal);
		
		close(conn);		
		
		return empList;
		
	}
	
	/** 부서별 급여 합 전체 조회 서비스
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalarySum() throws Exception {
		Connection conn = getConnection();
		
		List<Employee> salarySum = dao.selectSalary(conn);
		
		close(conn);		
		
		return salarySum;
		
	}

	/**주민등록번호가 일치하는 사원 정보 조회 서비스
	 * @param empNo
	 * @return
	 */
	public Employee selectEmpNo(String empNo)throws Exception {
		Employee emp = new Employee();
		
		Connection conn = getConnection();
		
		emp = dao.selectEmpNo(conn, empNo);
		
		close(conn);
		
		return emp;
		
	}
	
	/**직급별 급여 평균 조회 서비스
	 * @return
	 */
	public List<Employee> avgSalary() throws Exception{
		Connection conn = getConnection();
		
		List<Employee> avgSalary = dao.avgSalary(conn);
		
		close(conn);		
		
		return avgSalary;
	}

	


	
	
}
