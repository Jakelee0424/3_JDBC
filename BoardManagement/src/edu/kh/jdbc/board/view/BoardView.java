package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;

public class BoardView {

	Scanner sc = new Scanner(System.in);
	
	public void displayMenu() {
	
		
		
		
		int input = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("-                 게시판 관리 프로그램                  -");
				System.out.println("---------------------------------------------------------");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("---------------------------------------------------------");
				System.out.println("0. 프로그램 종료");
				System.out.println("---------------------------------------------------------");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); 
				
				System.out.println();				
				
				switch(input) {
				case 1: login();   break;
				case 2: signIn();  break;
//				case 3: selectEmpId();   break;
//				case 4: updateEmployee();   break;
//				case 5: deleteEmployee();   break;
//				case 6: selectDept();   break;
//				case 7: selectSalary();   break;
//				case 8: selectDeptTotalSalary();   break;
//				case 9: selsectEmpNo();   break;
//				case 10: avgSalary();   break;
				case 0: System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; 
				sc.nextLine(); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}while(input != 0);
		
	}
	
	/**
	 * 로그인 
	 */
	private void login() throws Exception {
	
		System.out.println("\n< 로그인 >");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
////		Member mem = new Member();
//		mem.setMemberId(id);
//		mem.setMemberPw(pw);
//		
//		mem = BoardService.login(mem);
//		
	}
	

	private void signIn() {
		
		
	}

	
	private void displatMenuAfterLogin() {
		
	}
}
	/**
	 * 전달받은 사원 List 모두 출력 
	 */
//	public void printAll(List<Member> memList) {
//		
//		if(memList.isEmpty()) {
//			System.out.println("조회된 회원 정보가 없습니다.");
//			
//		} else {
//			System.out.println("회원번호 |   아이디  | 이름 |  성별 " );
//			System.out.println("------------------------------------------------------------------------------------------------");
//			for(Member mem : memList) { 
//				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
//						mem.getMemberNo(), mem.getMemberId(), mem.getMemName(), mem.getMemberGender();
//			}
//		}	
//		return;
//	}
//
//	
//	/** 사원 한명 출력
//	 * @param emp
//	 */
//	public void printOne(Employee emp) {
//
//		if(emp == null) {
//			System.out.println("조회된 사원 정보가 없습니다.");
//			
//		} else {
//			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
//			System.out.println("------------------------------------------------------------------------------------------------");
//			
//			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
//					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
//					emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
//		}
//		
//	}
//
//
//	/** 사번을 입력받아 반환하는 메서드
//	 * @return
//	 */
//	public int inputEmpId() {
//}
