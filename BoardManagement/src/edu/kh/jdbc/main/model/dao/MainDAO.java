package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	/**
	 * sql 반환하는 기본생성자
	 */
	public MainDAO(){
		
		try {
		prop = new Properties();	
		prop.loadFromXML(new FileInputStream("main-sql.xml"));
		
		// prop.getProperty("key") 호출 -> value인 sql이 반환
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** login DAO(아이디, 비밀번호 일치 회원 조회)
	 * @param conn
	 * @param id
	 * @param pw
	 * @return
	 */
	public Member login(Connection conn, String id, String pw) throws Exception {

		Member member= null;
		
		try {
		
			String sql = prop.getProperty("login");
	
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String memberNM = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDT = rs.getString("ENROLL_DT");
				
			member = new Member();
			member.setMemberNo(memberNo);
			member.setMemName(memberNM);
			member.setMemberGender(memberGender);
			member.setEnrollDate(enrollDT);
			}
				
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return member;
	}

	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int idTest(Connection conn, String id) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("idTest");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
		
//			result = pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("COUNT");
			}
		
		}finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	/** 회원가입 DAO
	 * @param conn
	 * @param member
	 * @return
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("signUp");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemberGender());

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 로그인을 위한 탈퇴여부 체크 DAO
	 * @param conn
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public Member checkYN(Connection conn, String id, String pw) throws Exception{
		
		Member member = new Member();
		
		try {
			
			String sql = prop.getProperty("checkYN");
			
			pstmt = conn.prepareStatement(sql);
			
					
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberPw = rs.getString("MEMBER_ID");
				String unregisterFlag = rs.getString("UNREGISTER_FL"); 
				
				member = new Member();
				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				member.setUnregisterFlag(unregisterFlag);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

}
