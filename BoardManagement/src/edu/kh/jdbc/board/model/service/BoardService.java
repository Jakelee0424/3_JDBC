package edu.kh.jdbc.board.model.service;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;


/**
 * 
 */
public class BoardService {

	private BoardDAO dao = new BoardDAO();

	
	/** 게시글 조회 서비스
	 * @return
	 */
	public List<Board> selectBoard() throws Exception {
		
		List<Board> list = new ArrayList<Board>();
		
		Connection conn = getConnection();
		
		list = dao.selectBoard(conn);
		
		close(conn);
		
		return list;
	}

	/** 작성자별 게시글 조회 서비스
	 * @param selectId 
	 * @return
	 */
	public List<Board> selectMyBoard(String selectId) throws Exception {
		
		List<Board> list = new ArrayList<Board>();
		
		Connection conn = getConnection();
		
		list = dao.selectMyBoard(conn, selectId);
		
		close(conn);
		
		return list;
		
	}

	/** 게시글 작성 서비스
	 * @param content 
	 * @param title 
	 * @param idNum 
	 * @return
	 */
	public int insertBoard(String title, String content, int idNum) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.insertBoard(conn, title, content, idNum);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/** 본인 게시글 조회 서비스
	 * @param idNum
	 * @return
	 */
	public List<Board> selectMyBoard2(int idNum) throws Exception {
		
		List<Board> list = new ArrayList<Board>();
		
		Connection conn = getConnection();
		
		list = dao.selectMyBoard2(conn, idNum);
		
		close(conn);
		
		return list;
	}
	
	/** 게시글 수정 서비스
	 * @param boardNum
	 * @param updateTitle
	 * @param updateContent
	 * @return
	 */
	public int updateBoard(int boardNum, String updateTitle, String updateContent) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.updateBoard(conn, boardNum, updateTitle, updateContent);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 게시글 삭제 서비스
	 * @param boardNum 
	 * @return
	 */
	public int deleteBoard(int boardNum) throws Exception {
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.deleteBoard(conn, boardNum);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

	/** 게시글 삭제 2(업데이트)
	 * @param boardNum
	 * @return
	 */
	public int deleteBoard2(int boardNum) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.deleteBoard2(conn, boardNum);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	
	/** 게시글 상세 조회 서비스
	 * @param memNum
	 * @param boardNum
	 * @return
	 */
	public Board selectSpecificBoard(int memNum, int boardNum) throws Exception{
		Board board = null;
		int result = 0;
		Connection conn = getConnection();

		// 게시글 호출 
		board = dao.selectSpecificBoard(conn, boardNum);

		if(board == null) {
//			System.out.println("해당 번호의 게시글이 없습니다.");
		} else {

			// 게시글 조회수 증가
			// 동기화
			if(board.getMemberNo() != memNum) {

				result = dao.addReadCount(conn, boardNum);
				
				if(result > 0) {
					commit(conn);					
					board.setReadCount( board.getReadCount() + 1 );
				} else {
					rollback(conn);
				}
				
			}
		}
		return board;
	}
	
}



