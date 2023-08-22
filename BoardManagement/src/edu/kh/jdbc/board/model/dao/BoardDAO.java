package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("board-sql.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	
	/** 게시글 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoard(Connection conn) throws Exception{

		List<Board> list = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectBoard");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				String memberId = rs.getString("MEMBER_ID");
				int readCount = rs.getInt("READ_COUNT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				list.add( new Board(boardNo, boardTitle, boardContent, createDate, memberId, readCount, commentCount) );

			}
		}finally {
			close(rs);
			close(stmt);
		}

		return list;
	}

	/** 작성자별 게시글 조회DAO
	 * @param conn
	 * @param selectId
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectMyBoard(Connection conn, String selectId) throws Exception {
		
		List<Board> list = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectMyBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, selectId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				String memberId = rs.getString("MEMBER_ID");
				int readCount = rs.getInt("READ_COUNT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				list.add( new Board(boardNo, boardTitle, boardContent, createDate, memberId, readCount, commentCount) );

			}
			
		}finally {
			close(rs);
			close(stmt);
		}

		return list;
	}

	/** 게시글 작성 DAO
	 * @param conn 
	 * @param title
	 * @param content
	 * @param idNum 
	 * @return
	 */
	public int insertBoard(Connection conn, String title, String content, int idNum) throws Exception {
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idNum);


			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 본인 게시글 조회 DAO
	 * @param conn
	 * @param idNum
	 * @return
	 */
	public List<Board> selectMyBoard2(Connection conn, int idNum) throws Exception{
		
		List<Board> list = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectMyBoard2");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				String memberId = rs.getString("MEMBER_ID");
				int readCount = rs.getInt("READ_COUNT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				list.add( new Board(boardNo, boardTitle, boardContent, createDate, memberId, readCount, commentCount) );

			}
			
		}finally {
			close(rs);
			close(stmt);
		}

		return list;
	}

	/** 게시글 수정 DAO
	 * @param conn
	 * @param boardNum
	 * @param updateTitle
	 * @param updateContent
	 * @return
	 */
	public int updateBoard(Connection conn, int boardNum, String updateTitle, String updateContent) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateTitle);
			pstmt.setString(2, updateContent);
			pstmt.setInt(3, boardNum);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNum
	 * @return
	 */
	public int deleteBoard(Connection conn, int boardNum) throws Exception{
		int result = 0;
		
		try {

			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 게시글 삭제 2
	 * @param conn
	 * @param boardNum
	 * @return
	 */
	public int deleteBoard2(Connection conn, int boardNum) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteBoard2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}



	
	/** 게시글 호출 DAO
	 * @param conn
	 * @param boardNum
	 * @return
	 */
	public Board selectSpecificBoard(Connection conn, int boardNum) throws Exception {
		Board board = null;
		
		try {
			
			String sql = prop.getProperty("selectSpecificBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				String memberId = rs.getString("MEMBER_ID");
				int readCount = rs.getInt("READ_COUNT");
				
				board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCreateDate(createDate);
				board.setMemberId(memberId);
				board.setReadCount(readCount);
			}
			
		} finally {
			close(pstmt);
		}
		
		return board;
	}



	
	/** 조회수 증가 DAO
	 * @param conn
	 * @param memNum
	 * @return
	 */
	public int addReadCount(Connection conn, int boardNum) throws Exception {
		int result = 0;
		
		try {

			String sql = prop.getProperty("addReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNum);

			result = pstmt.executeUpdate();
					
		}finally {
			close(rs);
			close(stmt);			
		}
		return result;
	}
	
	
	


}

