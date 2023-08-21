package edu.kh.jdbc.main.view;

import java.util.Scanner;

import edu.kh.jdbc.board.view.BoardView;
import edu.kh.jdbc.board.view.CommentView;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.Memberview;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	private Memberview memberView = new Memberview();
	private BoardView boardView = new BoardView();
	private CommentView commentView = new CommentView();
	/**
	 * 메인 메뉴 출력
	 */
	public void mainMenu() {
		
		int input = 0;
		
		do {
			
			try {
				
				if(Session.loginMember == null) { // 로그아웃 상태
					
					System.out.println(" ------------------------------------------------------- ");
					System.out.println("|               회원제 게시판 프로그램                  |");
					System.out.println(" ------------------------------------------------------- ");
					System.out.println();
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println();
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 선택 >> ");
					input = sc.nextInt();
					sc.nextLine(); // 개행 제거
					
					switch(input) {
					case 1: login(); break;
					case 2: signUp(); break;
					case 0: System.out.println("\n---------------- 프로그램을 종료합니다 -----------------\n"); break;
					default: System.out.println("\n메뉴에 존재하는 번호만 입력하세요.\n");
					
					}
					
				} else { // 로그인 상태
				
					System.out.println(" ------------------------------------------------------- ");
					System.out.println("|                      회원 메뉴                        |");
					System.out.println(" ------------------------------------------------------- ");
					System.out.println();
					System.out.println("1. 회원 관리");
					System.out.println("2. 게시판 관리");
					System.out.println("3. 댓글 관리");
					System.out.println("4. 로그아웃");
					System.out.println();
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n[ 메뉴 선택 ] >> ");
					input = sc.nextInt();
					sc.nextLine(); // 개행 제거
					
					switch(input) {
					case 1: memberView.mainMenu(); break;
					case 2: boardView.displayMenu(); break;
					case 3: commentView.displayMenu(); break;
					case 4: System.out.println("\n------------- 로그아웃 되었습니다 -------------\n");
							Session.loginMember = null;
							break;
					case 0: System.out.println("\n----------------- 프로그램을 종료합니다 -----------------\n"); break;
					default: System.out.println("\n메뉴에 존재하는 번호만 입력하세요.\n");
					}
				}
				
			}catch(Exception e) {
				System.out.println();
				e.printStackTrace();
			}
			
		}while(input != 0);
	}



	/**
	 * 로그인 
	 */
	private void login() throws Exception {
		System.out.println("\n----------------------- [로그인] -----------------------");
		
		Member member = new Member();
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		member = service.checkYN(id, pw);
		
		if(member.getUnregisterFlag().equals("Y")) {
			System.out.println("\n [ 탈퇴한 회원입니다 ] \n");
		}else {

			try {

				// 로그인 서비스 호출 후 결과 반환 -> 세션에 저장
				Session.loginMember = service.login(id, pw);

				if(Session.loginMember == null) {
					System.out.println("\n------------------ [ 로그인 실패 ] ---------------------");
				}else {
					System.out.printf("\n----------------[ %s 님 환영합니다 ]-----------------\n", Session.loginMember.getMemName());
				}

			}catch(Exception e) {
				System.out.println("[ 로그인 중 예외 발생 ]");
			}
		}
	}
	
	/**
	 * 회원가입
	 */
	private void signUp() throws Exception{
		System.out.println("\n------------------- [회원 가입] -----------------------");
		
		Member member = new Member();
		
		int result;
		int idCheck = -1;
		int pwCheck = -1;
		
		while(idCheck != 0) { // 아이디 중복 검사
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			
			member.setMemberId(id);
			
			idCheck = service.idTest(id);	
			
			if(idCheck > 0) {
				System.out.println("\n이미 사용중인 아이디 입니다.");
				System.out.println("아이디를 다시 입력해주세요.\n");
				
			} else {
				idCheck = 0;
				System.out.println("** 사용 중이지 않은 아이디 입니다. 회원가입을 계속 진행합니다.**");
			}
			
		}
		
		while(pwCheck != 0) {
			
			System.out.print("비밀번호 : ");
			String pw = sc.nextLine();

			System.out.print("비밀번호 확인: ");
			String pw2 = sc.nextLine();
			
			if(!pw.equals(pw2)) {
				System.out.println("비밀번호가 틀렸습니다. 다시 확인해주세요.\n");
			}else {
				pwCheck = 0;
				member.setMemberPw(pw);
			}

		}

		System.out.print("이름 : ");
		String name = sc.nextLine();

		member.setMemName(name);
		
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();

		member.setMemberGender(gender);

		System.out.println(member.getMemName());
		System.out.println(member.getMemberPw());
		System.out.println(member.getMemberId());
		System.out.println(member.getMemberGender());
		
		result = service.signUp(member);
		
		if(result > 0) {
			System.out.println("회원이 되신 것을 축하드립니다.");
		} else {
			System.out.println("회원가입에 실패하였습니다.");
		}
	}

}


