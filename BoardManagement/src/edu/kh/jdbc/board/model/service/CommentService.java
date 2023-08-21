package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.dto.Comment;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class CommentService {

	CommentDAO dao = new CommentDAO();

	/** 내가 쓴 댓글 전체 조회 서비스
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public List<Comment> selcetMyComment(int memNo) throws Exception{
		
		List<Comment> list = new ArrayList<Comment>();
		
		Connection conn = getConnection();
		
		list = dao.selectMyComment(conn, memNo);
		
		close(conn);		
		
		return list;
	}

	/** 작성자별 댓글 조회 서비스
	 * @param memId
	 * @return
	 * @throws Exception
	 */
	public List<Comment> selcetUserComment(String memId) throws Exception {
		List<Comment> list = new ArrayList<Comment>();
		
		Connection conn = getConnection();
		
		list = dao.selectMyComment(conn, memId);
		
		close(conn);		
		
		return list;
	}

	/** 게시글 별 댓글 조회 서비스
	 * @param borNum
	 * @return
	 */
	public List<Comment> selcetCommentFromBor(int borNum) throws Exception{
	List<Comment> list = new ArrayList<Comment>();
		
		Connection conn = getConnection();
		
		list = dao.selectCommentFromBor(conn, borNum);
		
		close(conn);		
		
		return list;
	}
	
	
	
	
}
