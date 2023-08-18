package edu.kh.jdbc.member.view;

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
				System.out.println("3. 회원 탈퇴");
				System.out.println();
				System.out.println("9. 메인메뉴");
				System.out.println("0. 프로그램 종료");
				System.out.println("--------------------------------------------------------");

				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); // 개행 제거

				switch(input) {
				case 1: selectInfo(); break;
				case 2: ; break;
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

	private void selectInfo() throws Exception{
		System.out.println("\n----------------개인 정보 조회-----------------\n");
		Member member = new Member();		
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.nextLine();
		
		member = service.selectInfo(pw);
		
		printOne(member);
		
	}


// 보조메서드
	public void printOne(Member mem) {

		if(mem == null) {
			System.out.println("조회된 회원 정보가 없습니다.");

		} else {
			System.out.println("\n 회원번호 |   아이디  |   이름   |   성별   |    가입일   " );
			System.out.println("------------------------------------------------------------------------------------------------");

			System.out.printf(" %7d  | %9s | %5s | %8s | %20s \n\n",
					mem.getMemberNo(), mem.getMemberId(), mem.getMemName(), mem.getMemberGender(), mem.getEnrollDate());
		}

	}
}