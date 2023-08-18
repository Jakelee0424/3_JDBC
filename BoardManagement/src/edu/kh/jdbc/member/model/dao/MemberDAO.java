package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	

	public Member selectInfo(Connection conn, String pw) throws Exception {
		
		Member member = null;

		try {
			String sql = prop.getProperty("selectInfo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pw);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int memNo = rs.getInt("MEMBER_NO");
				String memId = rs.getString("MEMBER_ID");
				String memName = rs.getString("MEMBER_NM");
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

}
