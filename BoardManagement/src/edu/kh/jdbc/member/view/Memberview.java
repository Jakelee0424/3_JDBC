package edu.kh.jdbc.member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

public class Memberview {

	
	private Scanner sc = new Scanner(System.in);
	MemberService service = new MemberService();

	public void mainMenu() {

		int input = 0;

		do {

			try {

				System.out.println(" ------------------------------------------------------- ");
				System.out.println("|                       회원 관리                       |");
				System.out.println(" ------------------------------------------------------- ");
				System.out.println();
				System.out.println("1. 개인정보 조회");
				System.out.println("2. 개인정보 수정");
				System.out.println("3. 회원 목록 조회");
				System.out.println("4. 비밀번호 변경");
				System.out.println("5. 회원 탈퇴");
				System.out.println();
				System.out.println("9. 메인메뉴");
				System.out.println("0. 프로그램 종료");
				System.out.println("--------------------------------------------------------");

				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); // 개행 제거

				switch(input) {
				case 1: selectInfo(); break;
				case 2: updateInfo(); break;
				case 3: selectAllInfo(); break;
				case 4: updatePw(); break;
				case 9: System.out.println("\n---------------- 프로그램을 종료합니다 -----------------\n"); break;
				case 0: System.out.println("\n---------------- 프로그램을 종료합니다 -----------------\n"); break;
				default: System.out.println("\n메뉴에 존재하는 번호만 입력하세요.\n");

				}


			}catch(Exception e) {
				System.out.println();
				e.printStackTrace();
			}

		}while(input != 0);
	}


	/** 개인정보 조회
	 * @throws Exception
	 */
	private void selectInfo() throws Exception{
		System.out.println("\n----------------개인 정보 조회-----------------\n");
		Member member = new Member();		
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.nextLine();
		
		member = service.selectInfo(Session.loginMember.getMemName(), pw);
		
		printOne(member);
		
	}

	/**
	 * 개인 정보 수정
	 */
	private void updateInfo() throws Exception{
		System.out.println("\n----------------개인 정보 수정-----------------\n");
		Member member = new Member();
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.next();
		
		member = service.selectInfo(Session.loginMember.getMemName(), pw);
		
		printOne(member);
	
		if(member != null) {

			int result = 0;

			System.out.println("개인 정보 수정을 진행합니다.");
			System.out.print("수정할 이름 >> ");
			String updateName = sc.next();

			System.out.print("수정할 성별 >> ");
			String updateGender = sc.next();
			
			sc.nextLine();
			
//			System.out.println(updateGender);

			result = service.updateInfo(pw, updateName, updateGender);

			if (result > 0 ) {
				System.out.println("수정이 완료되었습니다.");
			} else {
				System.out.println("수정에 실패했습니다.");
			}
			
		} 
	}
	
	/**
	 * 회원 목록 조회
	 */
	private void selectAllInfo() throws Exception{
		System.out.println("\n----------------회원 목록 조회-----------------\n");
		
		List<Member> list = new ArrayList<Member>();
		
		list = service.selectAllInfo();
		
		printAll(list);
		
		
	}

	/**
	 * 비밀번호 변경
	 */
	private void updatePw() throws Exception{
		
		Member member = new Member();		
		Random random = new Random();
		int result = 0;
		int memNo = Session.loginMember.getMemberNo();
		int randomNum = random.nextInt(899999) + 100000;
		
		System.out.println("현재 비밀번호를 입력하세요");
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.nextLine();

		member = service.selectInfo(Session.loginMember.getMemName(), pw);

		if(member == null) {
			System.out.println("비밀번호를 잘못 입력하셨습니다.");
		} else {
			
			System.out.println("보안문자 [ " + randomNum + " ]");
			System.out.print("보안 문자 입력 >> ");
			int numCheck = sc.nextInt();
		
			if(numCheck == randomNum) {
				
				System.out.println("수정할 비밀번호를 입력하세요");
				System.out.print("비밀번호 입력 >> ");
				String updatePw = sc.nextLine();
				
				result = service.updatePw(memNo, updatePw);

				if(result > 0) {
					System.out.println("비밀번호가 수정되었습니다.");
				} else {
					System.out.println("비밀먼호 수정이 실패하였습니다.");
				}
			}else {
				System.out.println("보안문자를 잘못 입력하셨습니다.");
			}
		}
	}
	

// 보조메서드
	
	/**
	 * 전달받은 List 모두 출력 
	 */
	public void printAll(List<Member> memList) {
		
		if(memList.isEmpty()) {
			System.out.println("조회된 회원 정보가 없습니다.");
			
		} else {
			System.out.println("\n 회원번호 |   아이디  |   이름   |   성별   |    가입일   " );
			System.out.println("------------------------------------------------------------------------------------------------");

			for(Member mem : memList) { 
				System.out.printf(" %7d  | %9s | %5s | %8s | %20s \n\n",
						mem.getMemberNo(), mem.getMemberId(), mem.getMemName(), mem.getMemberGender(), mem.getEnrollDate());
			}
		}	
		return;
	}
	
	/** 전달받은 멤버 한명 출력
	 * @param mem
	 */
	public void printOne(Member mem) {

		if(mem == null) {
			System.out.println("조회된 회원 정보가 없습니다.");

		} else {
			System.out.println("\n 회원번호 |   아이디  |   이름   |   성별   |    가입일   " );
			System.out.println("------------------------------------------------------------------------------------------------");

			System.out.printf(" %7d  | %9s | %5s | %8s | %20s \n",
			mem.getMemberNo(), mem.getMemberId(), mem.getMemName(), mem.getMemberGender(), mem.getEnrollDate());
		}

	}
}