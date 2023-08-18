package edu.kh.jdbc.member.model.service;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {

	MemberDAO dao = new MemberDAO();
	

	/** 개인정보 조회 서비스
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public Member selectInfo(String pw) throws Exception {
		
		Member member = null;
		
		Connection conn = getConnection();
		
		member = dao.selectInfo(conn, pw);
		
		close(conn);
		
		return member;
		
	}

	
	
}
