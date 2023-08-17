package edu.kh.emp.view;

import static edu.kh.emp.common.JDBCTemplate.commit;
import static edu.kh.emp.common.JDBCTemplate.rollback;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.emp.model.vo.Employee;
import edu.kh.emp.model.service.EmployeeService;

// 화면용 클래스

public class EmployeeView {

	private Scanner sc = new Scanner(System.in);
	private EmployeeService service = new EmployeeService(); // 서비스 객체 생성

	
	/**
	 * 메인 메뉴
	 */
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 전체 사원 정보 조회");
				System.out.println("2. 새로운 사원 추가");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				System.out.println("8. 부서별 급여 합 전체 조회");
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				System.out.println("10. 직급별 급여 평균 조회");
				System.out.println("---------------------------------------------------------");
				System.out.println("0. 프로그램 종료");
				System.out.println("---------------------------------------------------------");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); //  추가!
				
				System.out.println();				
				
				switch(input) {
				case 1: selectAll();   break;
				case 2: insertEmployee();  break;
				case 3: selectEmpId();   break;
				case 4: updateEmployee();   break;
				case 5: deleteEmployee();   break;
				case 6: selectDept();   break;
				case 7: selectSalary();   break;
				case 8: selectSalarySum();   break;
				case 9: selsectEmpNo();   break;
				case 10: avgSalary();   break;
				case 0: System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}while(input != 0);
		
		
		
	}


	// 주 기능 메서드

		/**
		 * 전체 사원 조회 
		 */
		private void selectAll() throws Exception{

			System.out.println("<전체 사원 정보 조회>");

			List<Employee> emplist = service.selcetAll();
			printAll(emplist);

		}
		
		/**
		 * 새로운 사원 추가
		 */
		private void insertEmployee() throws Exception{
			
			System.out.println("<새로운 사원 추가>");
			
			System.out.print("사번 >> ");
			int empId = sc.nextInt();
					
			sc.nextLine();
			System.out.print("사원명 >> ");
			String empName = sc.nextLine();
			
			System.out.print("주민등록번호 >> ");
			String empNo = sc.nextLine();
			
			System.out.print("이메일 >> ");
			String email = sc.nextLine();
			
			System.out.print("전화번호 >> ");
			String phone = sc.nextLine();
			
			System.out.print("부서코드 >> ");
			String deptCode = sc.nextLine();
			
			System.out.print("직급코드 >> ");
			String jobCode = sc.nextLine();
			
			System.out.print("급여레벨 >> ");
			String salaryLevel = sc.nextLine();
			
			System.out.print("급여 >> ");
			int salary = sc.nextInt();
			
			System.out.print("보너스 >> ");
			double bonus = sc.nextDouble();

			sc.nextLine();
			System.out.print("사수 번호 >> ");
			int managementId = sc.nextInt();

			Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salaryLevel, salary, bonus, managementId);
			
			int result = service.insertEmployee(emp);
			
			if(result > 0) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
			
		}
		
		/**
		 * 사번이 일치하는 사원 정보 조회
		 * @return 
		 */
		private int selectEmpId() throws Exception{
			System.out.println("<사번으로 사원 검색>");
					
			Employee emp = new Employee(inputEmpId());
			
			emp = service.selectEmpId(emp);
			printOne(emp);
			
			return emp.getEmpId();
			
			
		}

		/**
		 * 사번이 일치하는 사원 정보 수정
		 */
		private void updateEmployee() throws Exception{
			
//			int result = 0;
//			
//			System.out.println("<사번으로 사원 검색 후 수정>\n");
//			
//			int empId = inputEmpId();
//			
//			System.out.print("수정할 사원명 >> ");
//			String empName = sc.nextLine();
//			
//			System.out.print("수정할 주민등록번호 >> ");
//			String empNo = sc.nextLine();
//			
//			System.out.print("수정할 이메일 >> ");
//			String email = sc.nextLine();
//			
//			System.out.print("수정할 전화번호 >> ");
//			String phone = sc.nextLine();
//			
//			System.out.print("수정할 부서코드 >> ");
//			String deptCode = sc.nextLine();
//			
//			System.out.print("수정할 직급코드 >> ");
//			String jobCode = sc.nextLine();
//			
//			System.out.print("수정할 급여레벨 >> ");
//			String salaryLevel = sc.nextLine();
//			
//			System.out.print("수정할 급여 >> ");
//			int salary = sc.nextInt();
//			
//			System.out.print("수정할 보너스 >> ");
//			double bonus = sc.nextDouble();
//
//			System.out.print("수정할 사수 번호 >> ");
//			int managementId = sc.nextInt();
//			
//			Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salaryLevel, salary, bonus, managementId);
//			
//			result = service.updateEmployee(emp);
//			
//			if(result > 0) {
//				System.out.println("성공");
//			}else {
//				System.out.println("실패");
//			}
//			Employee emp = new Employee();
//			
			int empId = selectEmpId();
			
			if(empId != 0) {
				System.out.println("\n----- 해당 사원의 정보를 수정합니다 -----\n");
								
				System.out.print("수정할 사원명 >> ");
				String empName = sc.nextLine();
				
				System.out.print("수정할 주민등록번호 >> ");
				String empNo = sc.nextLine();
				
				System.out.print("수정할 이메일 >> ");
				String email = sc.nextLine();
				
				System.out.print("수정할 전화번호 >> ");
				String phone = sc.nextLine();
				
				System.out.print("수정할 부서코드 >> ");
				String deptCode = sc.nextLine();
				
				System.out.print("수정할 직급코드 >> ");
				String jobCode = sc.nextLine();
				
				System.out.print("수정할 급여레벨 >> ");
				String salaryLevel = sc.nextLine();
				
				System.out.print("수정할 급여 >> ");
				int salary = sc.nextInt();
				
				System.out.print("수정할 보너스 >> ");
				double bonus = sc.nextDouble();

				System.out.print("수정할 사수 번호 >> ");
				int managementId = sc.nextInt();
				
				Employee emp = new Employee(empName, empNo, email, phone, deptCode, jobCode, salaryLevel, salary, bonus, managementId, empId);
				
				int result = service.updateEmployee(emp);
				
				
				if(result > 0) {
					System.out.println("성공");
				}else {
					System.out.println("실패");
				}
				
			} else {
				System.out.println("해당 사번은 비어있습니다.");
			}
									
			
		}

		/**
		 * 사번이 일치하는 사원 정보 삭제
		 */
		private void deleteEmployee() throws Exception{
			int result = 0;
			System.out.println("<사번으로 사원 삭제>");
			
			int empId = inputEmpId();
			
			System.out.println("정말 삭제 하시겠습니까? (Y/N)");
			char input = sc.next().toUpperCase().charAt(0);
			
			if(input == 'Y') {
				
				result = service.deleteEmployee(empId);
				
				if(result > 0) {
					System.out.println("성공");
				}else {
					System.out.println("실패");
				}
		
				
			}else {
				System.out.println("취소 되었습니다.");
			}
			
//			Employee emp = new Employee(inputEmpId());
			
			
			
		}
		
		/**
		 * 입력 받은 부서와 일치하는 모든 사원 정보 조회
		 */
		private void selectDept() throws Exception {
			
			System.out.println("<부서별 사원 정보 조회>");

			System.out.print("조회할 부서를 입력하세요 >> ");
			String dept = sc.nextLine();
			
			List<Employee> emplist = service.selcetDept(dept);
			
			printAll(emplist);
		}
		
		/**
		 * 입력 받은 급여 이상을 받는 모든 사원 정보 조회
		 */
		private void selectSalary() throws Exception{
			
			System.out.println("<입력한 급여보다 높은 급여를 받는 사원 정보 조회>");

			System.out.print("급여 정보를 입력하세요 >> ");
			String sal = sc.nextLine();
			
			List<Employee> emplist = service.selectSalary(sal);
			
			printAll(emplist);
		}
			
		/**
		 * 부서별 급여 합 전체 조회
		 */
		private void selectSalarySum() throws Exception{
			System.out.println("<부서별 급여 합 조회>");

			List<Employee> salarySum = service.selectSalarySum();
					
			for(Employee emp : salarySum) {
				System.out.printf("부서 : %s / 급여합 : %d \n", emp.getDepartmentTitle(), emp.getSalary());
			}
			

			
		}

		/**주민등록번호가 일치하는 사원 정보 조회
		 * 
		 */
		private void selsectEmpNo() throws Exception{
			
			System.out.println("<주민등록번호로 사원 검색>");
			System.out.print("주민등록번호 입력 >> ");
			String empNo = sc.nextLine();
			
			Employee emp = new Employee();
			
			emp = service.selectEmpNo(empNo);
			
			printOne(emp);
		
			
			
			
	}
		
		/**직급별 급여 평균 조회
		 * 
		 */
		private void avgSalary() throws Exception {
			System.out.println("<직급별 급여 평균 조회>");

			List<Employee> avgSalary = service.avgSalary();
					
			for(Employee emp : avgSalary) {
				System.out.printf("직급명 : %s / 급여평균 : %d \n", emp.getDepartmentTitle(), emp.getSalary());
			}
			

			
			
		}

	
//보조 메서드
	
		/**
		 * 전달받은 사원 List 모두 출력 
		 */
		public void printAll(List<Employee> empList) {
			
			if(empList.isEmpty()) {
				System.out.println("조회된 사원 정보가 없습니다.");
				
			} else {
				System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
				System.out.println("------------------------------------------------------------------------------------------------");
				for(Employee emp : empList) { 
					System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
							emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
							emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
				}
			}	
			return;
		}

		
		/** 사원 한명 출력
		 * @param emp
		 */
		public void printOne(Employee emp) {

			if(emp == null) {
				System.out.println("조회된 사원 정보가 없습니다.");
				
			} else {
				System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
				System.out.println("------------------------------------------------------------------------------------------------");
				
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
			
		}


		/** 사번을 입력받아 반환하는 메서드
		 * @return
		 */
		public int inputEmpId() {
	
			System.out.print("사번 입력 >> ");
			int empId = sc.nextInt();
			sc.nextLine();
			return empId;
		}
}
