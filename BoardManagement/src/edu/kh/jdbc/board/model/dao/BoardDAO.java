package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("login.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public Board loginDao(Connection conn, Board mem) throws Exception{
		
		Board member = new Board();
		
		String sql = prop.getProperty("");
		
		
		return member;
	}
	
	
	
}
