package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	/**
	 * sql 반환하는 기본생성자
	 */
	public MemberDAO(){

		
		try {
		prop = new Properties();	
		prop.loadFromXML(new FileInputStream("member-sql.xml"));
		
		// prop.getProperty("key") 호출 -> value인 sql이 반환
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/** 개인정보 조회 DAO
	 * @param conn
	 * @param memName
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public Member selectInfo(Connection conn, String memName, String pw) throws Exception {
		
		Member member = null;

		try {
			String sql = prop.getProperty("selectInfo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pw);
			pstmt.setString(2, memName);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int memNo = rs.getInt("MEMBER_NO");
				String memId = rs.getString("MEMBER_ID");
//				String memName = rs.getString("MEMBER_NM");
				String memgender = rs.getString("MEMBER_GENDER");
				String enrollDT = rs.getString("ENROLL_DT");

				member = new Member();

				member.setMemberNo(memNo);
				member.setMemberId(memId);
				member.setMemName(memName);
				member.setMemberGender(memgender);
				member.setEnrollDate(enrollDT);	

			}
		}finally {
			close(pstmt);
			close(rs);
		}
		return member;
	}

	/** 개인정보 수정 DAO
	 * @param conn
	 * @param pw
	 * @param updateName
	 * @param updateGender
	 * @return
	 * @throws Exception
	 */
	public int updateInfo(Connection conn, String pw, String updateName, String updateGender) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateInfo");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updateName);
			pstmt.setString(2, updateGender);
			pstmt.setString(3, pw);

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원 목록 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectAllInfo(Connection conn) throws Exception{
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectAllInfo");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int memNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDT = rs.getString("ENROLL_DT");
				
				list.add( new Member(memNo, memberId, memName, memberGender, enrollDT));
			
			}
				
		
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param updatePw
	 * @return
	 */
	public int updatePw(Connection conn, int memNo, String updatePw) throws Exception{

		int result = 0;
		
		try {
		
			String sql = prop.getProperty("updatePw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updatePw);
			pstmt.setInt(2, memNo);

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	
	
	/** 회원 탈퇴 서비스
	 * @param conn
	 * @param memNo
	 * @return
	 */
	public int deleteInfo(Connection conn, int memNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteInfo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "Y");
			pstmt.setInt(2, memNo);

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}
		return result;
	}

}
