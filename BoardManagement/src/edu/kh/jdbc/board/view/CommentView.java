package edu.kh.jdbc.board.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.common.Session;

/**
 * 
 */
public class CommentView {
	
	private Scanner sc = new Scanner(System.in);
	private BoardView boardview = new BoardView();
	private BoardService boardService = new BoardService();
	private CommentService commentService = new CommentService();
	
	public void displayMenu() {

		int input = 0;

		do {

			try {

				System.out.println(" ------------------------------------------------------- ");
				System.out.println("|                       댓글 관리                       |");
				System.out.println(" ------------------------------------------------------- ");
				System.out.println();
				System.out.println("1. 댓글 조회");
				System.out.println("2. 댓글 작성");
				System.out.println("3. 댓글 수정");
				System.out.println("4. 댓글 삭제");
				System.out.println();
				System.out.println("9. 메인메뉴");
				System.out.println("0. 프로그램 종료");
				System.out.println("--------------------------------------------------------");

				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); // 개행 제거

				switch(input) {
				case 1: selectComment(); break;
				case 2: insertComment(); break;
				case 3: updateComment(); break;
				case 4: deleteComment(); break;
//				case 5: deleteInfo(); break;
				case 9: return; 
				case 0: System.out.println("\n---------------- 프로그램을 종료합니다 -----------------\n"); break;
				default: System.out.println("\n메뉴에 존재하는 번호만 입력하세요.\n");

				}

			}catch(Exception e) {
				System.out.println();
				e.printStackTrace();
			}

		}while(input != 0);
	}


	/** 댓글 조회
	 * 
	 */
	private void selectComment() throws Exception{
		List<Comment> list = new ArrayList<Comment>();
		
		System.out.println("----------------------- [ 댓글 조회 ] -----------------------");
		System.out.println("1. 내가 쓴 댓글 조회");
		System.out.println("2. 게시글 별 댓글 조회");
		System.out.println("3. 작성자 별 댓글 조회");
		System.out.print("번호 입력 >> ");
		int selectComment = sc.nextInt();
		sc.nextLine();
		
		switch(selectComment){
			
		case 1: // 내가 쓴 댓글
			
			int memNo = Session.loginMember.getMemberNo();
			
			list = commentService.selcetMyComment(memNo);
			
			printAllCom(list);

			break;
		
		case 2:

			List<Board> borlist = new ArrayList<Board>();

			borlist = boardService.selectBoard();
			printAllBor(borlist);

			System.out.println("댓글 확인을 원하는 글의 번호를 입력하세요");
			System.out.print("글 번호 입력 >> ");
			int borNum = sc.nextInt();

			list = commentService.selcetCommentFromBor(borNum);
			
			printAllCom(list);
			
			break;
			
		case 3 : 
			
			System.out.println("검색을 원하는 작성자의 아이디를 입력하세요");
			System.out.print("아이디 입력 >> ");
			String memId = sc.nextLine();
			
			list = commentService.selcetUserComment(memId);
			
			printAllCom(list);
			
			break;
		
		default : System.out.println("번호를 잘못 입력하셨습니다.");
		}		
	}

	/** 댓글 작성
	 * 
	 */
	private void insertComment() throws Exception{
		
		System.out.println("----------------------- [ 댓글 작성 ] -----------------------");
		int result = 0;
		int memNo = Session.loginMember.getMemberNo();
		
		List<Board> borlist = new ArrayList<Board>();

		borlist = boardService.selectBoard();
		printAllBor(borlist);
		
		System.out.println("댓글 작성을 원하는 게시글 번호를 입력하세요");
		System.out.print("번호 입력 >> ");
		int boardNum = sc.nextInt();
		sc.nextLine();
		
		boolean borCheck = true;
		
		for(int i = 0 ; i <borlist.size() ; i++){
			if(boardNum == borlist.get(i).getBoardNo()) {
				System.out.print("댓글 입력 >> ");
				String commentContent = sc.nextLine();
				
				result = commentService.insertComment(memNo, boardNum, commentContent);
				
				if(result > 0 ) {
					System.out.println("댓글이 작성되었습니다");
					borCheck = false;
					break;
				} else {
					System.out.println("댓글 작성에 실패했습니다");
					borCheck = false;
					break;
				}
			}
		}
		
		if(borCheck) {
			System.out.println("해당 번호의 게시글은 존재하지 않습니다.");
				
		}
		
	}

	/** 댓글 수정
	 * 
	 */
	private void updateComment() throws Exception{
		System.out.println("----------------------- [ 댓글 수정 ] -----------------------\n");

		List<Comment> list = new ArrayList<Comment>();

		int memNo = Session.loginMember.getMemberNo();

		list = commentService.selcetMyComment(memNo);

		System.out.println("------------------- [ 작성자 댓글 목록 ] --------------------");
		printAllCom(list);

		boolean comCheck = true;
		int result = 0;

		System.out.println("수정을 원하는 댓글 번호를 입력하세요");
		System.out.print("번호 입력 >> ");
		int comNum = sc.nextInt();
		sc.nextLine();

		for(int i = 0 ; i <list.size() ; i++){
			if(comNum == list.get(i).getCommentNo()) {
				System.out.println("수정할 댓글 내용을 입력하세요 >>");
				String updateComment = sc.nextLine();

				result = commentService.updateComment(comNum, updateComment);

				if(result > 0 ) {
					System.out.println("댓글이 수정되었습니다");
					comCheck = false;
					break;
				} else {
					System.out.println("댓글 수정에 실패했습니다");
					comCheck = false;
					break;
				}
			}

		}
		
		if(comCheck) {
			System.out.println("해당 번호의 댓글은 존재하지 않습니다.");
		}
		
}
	
	/** 댓글 삭제
	 * 
	 */
	private void deleteComment() throws Exception{
		System.out.println("----------------------- [ 댓글 삭제 ] -----------------------\n");

		List<Comment> list = new ArrayList<Comment>();

		int memNo = Session.loginMember.getMemberNo();
		int result = 0;
		boolean comCheck = false;
		
		list = commentService.selcetMyComment(memNo);

		System.out.println("------------------- [ 작성자 댓글 목록 ] --------------------");
		printAllCom(list);

		System.out.println("삭제를 원하는 댓글 번호를 입력하세요");
		System.out.print("번호 입력 >> ");
		int comNum = sc.nextInt();
		sc.nextLine();

		for(int i = 0 ; i <list.size() ; i++){
			if(comNum == list.get(i).getCommentNo()) {

				System.out.print("진짜 삭제하시겠습니까? [Y/N]  >> ");
				char yesOrNo = sc.next().toUpperCase().charAt(0);

				if(yesOrNo == 'Y') {
					result = commentService.deleteComment(comNum);

					if(result > 0) {
						System.out.println("삭제가 완료되었습니다.");
					}else {
						System.out.println("댓글 삭제에 실패했습니다.");
					}

				}
			}
		}
		if(comCheck) {
			System.out.println("해당 번호의 댓글은 존재하지 않습니다.");
		}
	}
	
	
	// 보조 메서드
	
	/**
	 * 전달받은 List 모두 출력 
	 */
	public void printAllBor(List<Board> borList) {


		if(borList.isEmpty()) {
			System.out.println("조회된 게시글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 글 번호 |        글 제목        |            글 내용            |        작성일       |  작성자   " );
			System.out.println("---------------------------------------------------------------------------------------------------------");

			for(Board bor : borList) { 
				System.out.printf(" %6d  | %-10s\t | %-15s\t | %10s | %3s \n\n",
						bor.getBoardNo(),bor.getBoardTitle(),bor.getBoardContent(),bor.getCreateDate(),bor.getMemberNo());
			}
		}	
		return;
	}

	/** 전달받은 게시글 한개 출력
	 * @param mem
	 */
	public void printOneBor(Board bor) {

		if(bor == null) {
			System.out.println("조회된 게시글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 글 번호 |     글 제목     |          글 내용          |        작성일       |  작성자   " );
			System.out.println("--------------------------------------------------------------------------------------");

			System.out.printf(" %6d  | %11s | %15s | %8s | %3s \n\n",
					bor.getBoardNo(),bor.getBoardTitle(),bor.getBoardContent(),bor.getCreateDate(),bor.getMemberNo());
		}

	}

	/**
	 * 전달받은 List 모두 출력 
	 */
	public void printAllCom(List<Comment> comList) {


		if(comList.isEmpty()) {
			System.out.println("조회된 댓글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 댓글 번호 |          댓글 내용          |        작성일       |  작성자   " );
			System.out.println("--------------------------------------------------------------------------------------");
			
			for(Comment com : comList) { 
				System.out.printf(" %8d  | %-15s\t | %15s | %-10s \n\n",
						com.getCommentNo(),com.getCommentContent(),com.getCreateDate(),com.getMemberId());
			}
		}	
		return;
	}

	/** 전달받은 댓글 한개 출력
	 * @param mem
	 */
	public void printOneCom(Comment com) {

		if(com == null) {
			System.out.println("조회된 댓글 정보가 없습니다.");

		} else {
			System.out.println("\n\n 댓글 번호 |     댓글 내용     |        작성일       |  작성자   " );
			System.out.println("--------------------------------------------------------------------------------------");

			System.out.printf(" %7d  | %11s | %15s | %8s | %-10s \n\n",
					com.getCommentNo(),com.getCommentContent(),com.getCreateDate(),com.getMemberId());
		}

	}




	
}
