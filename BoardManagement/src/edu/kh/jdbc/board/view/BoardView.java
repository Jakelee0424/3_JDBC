package edu.kh.jdbc.board.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;

public class BoardView {

	private Scanner sc = new Scanner(System.in);
	private BoardService service = new BoardService();
	private Random random = new Random();
	
	public void displayMenu() {
	
		int input = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("|                      게시판 관리                       |");
				System.out.println("---------------------------------------------------------");
				System.out.println("1. 게시글 조회");
				System.out.println("2. 게시글 상세 조회");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 수정");
				System.out.println("5. 게시글 삭제");
				System.out.println();
				System.out.println("9. 이전 메뉴");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n[ 메뉴 선택 ] >> ");
				input = sc.nextInt();
				sc.nextLine(); 
				
				System.out.println();				
				
				switch(input) {
				case 1: selectBoard();   break;
				case 2: selectSpecificBoard();   break;
				case 3: insertBoard();  break;
				case 4: updateBoard();   break;
				case 5: deleteBoard();   break;
				case 9: return; 
				case 0: System.out.println("프로그램을 종료합니다...");   
						System.exit(0); // JVM 강제 종료 구문
						break;
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
	




	/** 게시글 조회
	 * @throws Exception
	 */
	private void selectBoard() throws Exception {
		
		List<Board> list = new ArrayList<Board>();
		int memNo = Session.loginMember.getMemberNo();
		
		System.out.println("----------------------- [게시글 조회] -----------------------");
		System.out.println("1. 전체 게시글 목록");
		System.out.println("2. 작성자별 게시글 목록");
		System.out.println("3. 내가 쓴 게시글 목록");
		System.out.println();
		System.out.print("번호 입력 >> ");
		int selectNum = sc.nextInt();
		sc.nextLine();
		
		switch(selectNum) {
		
		case 1: list = service.selectBoard();
				printAll(list);
				break;
		case 2: System.out.print("작성자 아이디 입력 >> ");
				String selectId = sc.nextLine();
				list = service.selectMyBoard(selectId);
				printAll(list);
				break;
		case 3: list = service.selectMyBoard2(memNo);
				printAll(list);
				break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
		
	}

	/** 게시글 상세 조회
	 * 
	 */
	private void selectSpecificBoard() throws Exception {
		// 게시글 번호 입력받아
		// 해당 게시글 출력
		// 해당 게시글 조회수 증가(내가 쓴 글은 조회수 증가 X)
		
		Board board = null;
		int memNum = Session.loginMember.getMemberNo();
		
		System.out.println("조회하실 게시글의 번호를 입력하세요");
		System.out.print("번호 입력 >> ");
		int boardNum = sc.nextInt();
		
		board = service.selectSpecificBoard(memNum, boardNum);
				
		printOne(board);
		
		// 로그인한 회원이 작성한 게시물이면
		// 게시글에 수정/삭제 기능 노출
		
	}
	
	/** 게시글 작성
	 * 
	 */
	private void insertBoard() throws Exception {
		System.out.println("----------------------- [게시글 작성] -----------------------");

		int result = 0;
		int idNum = Session.loginMember.getMemberNo();
		
		System.out.print("게시글 제목 : ");
		String title = sc.nextLine();
		
		System.out.print(" [ 게시글 내용 작성(!wq 작성시 종료) ]\n => ");
		StringBuffer sb = new StringBuffer();
		
		while(true) {
			String content = sc.nextLine();
			
			if(content.equals("!wq")) break;
			
			sb.append(content);
			sb.append("\n");
		}
		
		
		result = service.insertBoard(title, sb.toString(), idNum);
		
		if (result > 0) {
			System.out.println("게시글 작성이 완료되었습니다.");
		} else {
			System.out.println("게시글 작성에 실패하였습니다.");
		}
		
	}
	
	
	/**
	 *  게시글 수정
	 */
	private void updateBoard() throws Exception{
		
		int result = 0;
		int idNum = Session.loginMember.getMemberNo();
		List<Board> list = new ArrayList<Board>();
		
		System.out.println("-----------------------[ 게시글 수정 ]-----------------------");

		list = service.selectMyBoard2(idNum);
		printAll(list);
		
		System.out.println("수정을 원하는 글의 번호를 입력하세요 ");
		System.out.print("번호 입력(이전메뉴는 0) >> ");
		int boardNum = sc.nextInt();
		sc.nextLine();
		
		if(boardNum == 0) {
			return;			
		} else {
			
			System.out.print("게시글 제목 :");
			String updateTitle = sc.nextLine();
			
			System.out.print("게시글:");
			String updateContent = sc.nextLine();
			
			result = service.updateBoard(boardNum, updateTitle, updateContent);
			
			if(result > 0 ) {
				System.out.println("게시글 수정이 완료되었습니다.");
			}else {
				System.out.println("게시글 수정이 취소되었습니다.");
			}
			
		}
		
	}
	

	/**
	 * 게시글 삭제
	 */
	private void deleteBoard() throws Exception{
		
		int result = 0;
		int idNum = Session.loginMember.getMemberNo();
		int checkNum = 0;
		List<Board> list = new ArrayList<Board>();
		
		System.out.println("-----------------------[ 게시글 삭제 ]-----------------------");

		list = service.selectMyBoard2(idNum);
		printAll(list);
		
		System.out.println("삭제를 원하는 글의 번호를 입력하세요");
		System.out.print("번호 입력(뒤로가기 0번) >>");
		int boardNum = sc.nextInt();
		
		if(boardNum == 0) {
			return;			
		} else {
			checkNum = random.nextInt(899999) + 100000;
			System.out.println("보안문자 [ " + checkNum + " ]");
			System.out.print("보안 문자 입력 >> "); 
			int doubleCheckNum = sc.nextInt();
			
			if (checkNum == doubleCheckNum) {
				result = service.deleteBoard2(boardNum);
				
				if(result>0) {
					System.out.println("게시글 삭제가 완료되었습니다.");
				} else {
					System.out.println("게시글 삭제에 실패했습니다.");
				}
			}
			
			
			
		}
	}

	
// 보조 메서드
	
	/**
	 * 전달받은 List 모두 출력 
	 */
	public void printAll(List<Board> borList) {

		if(borList.isEmpty()) {
			System.out.println("조회된 게시글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 글 번호 |        글 제목        |        작성일       |  작성자  |  조회수2 | " );
			System.out.println("---------------------------------------------------------------------------------");

			for(Board bor : borList) { 
				System.out.printf(" %6d  | %s [%d] \t | %10s |  %-3s  |    %s    | \n\n",
				bor.getBoardNo(),bor.getBoardTitle(),bor.getCommentCount(),bor.getCreateDate(),bor.getMemberId(),bor.getReadCount());
			}
		}	
		return;
	}

	/** 전달받은 게시물 상세 페이지 출력
	 * @param mem
	 */
	public void printOne(Board bor) {

		if(bor == null) {
			System.out.println("조회된 게시글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 글 번호 |     글 제목     |        작성일        |    작성자  " );
			System.out.println("-------------------------------------------------------------------");

			System.out.printf(" %6d  | %11s |  %8s | %-3s \n\n",
					bor.getBoardNo(),bor.getBoardTitle(),bor.getCreateDate(),bor.getMemberId());
			System.out.println("--------------------------------------------------------------------\n");
			System.out.println(bor.getBoardContent());
			System.out.println("\n--------------------------------------------------------------------\n\n");
			
		}

	}

}


