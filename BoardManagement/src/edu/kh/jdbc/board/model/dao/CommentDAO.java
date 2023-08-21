package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Comment;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class CommentDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CommentDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("comment-sql.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/** 내가 쓴 댓글 전체 조회 DAO
	 * @param conn
	 * @param memNo
	 * @return
	 */
	public List<Comment> selectMyComment(Connection conn, int memNo) throws Exception{
		List<Comment> list = new ArrayList<Comment>();
		
		try {
			
			String sql = prop.getProperty("selectMyComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int commentNo = rs.getInt("COMMENT_NO"); 
				String commentContent = rs.getString("COMMENT_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				int memberNo = rs.getInt("MEMBER_NO");
				
				list.add( new Comment(commentNo, commentContent, createDate, memberNo) );
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


	/** 작성자별 댓글 조회 DAO
	 * @param conn
	 * @param memId
	 * @return
	 * @throws Exception
	 */
	public List<Comment> selectMyComment(Connection conn, String memId) throws Exception {
		List<Comment> list = new ArrayList<Comment>();
		
		try {
			
			String sql = prop.getProperty("selectUserComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int commentNo = rs.getInt("COMMENT_NO"); 
				String commentContent = rs.getString("COMMENT_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				int memberNo = rs.getInt("MEMBER_NO");
				
				list.add( new Comment(commentNo, commentContent, createDate, memberNo) );
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


	/** 게시글 별 댓글 조회 DAO
	 * @param conn
	 * @param borNum
	 * @return
	 */
	public List<Comment> selectCommentFromBor(Connection conn, int borNum) throws Exception{
		List<Comment> list = new ArrayList<Comment>();
		
		try {
			
			String sql = prop.getProperty("selectCommentFromBor");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, borNum);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int commentNo = rs.getInt("COMMENT_NO"); 
				String commentContent = rs.getString("COMMENT_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				int memberNo = rs.getInt("MEMBER_NO");
				
				list.add( new Comment(commentNo, commentContent, createDate, memberNo) );
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	
}
