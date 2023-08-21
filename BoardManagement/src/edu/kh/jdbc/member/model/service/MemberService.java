package edu.kh.jdbc.member.model.service;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

	MemberDAO dao = new MemberDAO();
	

	/** 개인정보 조회 서비스
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public Member selectInfo(String memName, String pw) throws Exception {
		
		Member member = null;
		
		Connection conn = getConnection();
		
		member = dao.selectInfo(conn, memName, pw);
		
		close(conn);
		
		return member;
		
	}

	
	/** 개인정보 수정 서비스
	 * @param updateName
	 * @param updateGender
	 * @return
	 */
	public int updateInfo(String pw, String updateName, String updateGender) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.updateInfo(conn, pw, updateName, updateGender);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 회원 목록 조회 서비스
	 * @return
	 */
	public List<Member> selectAllInfo() throws Exception{
		
		List<Member> list = new ArrayList<Member>();
		
		Connection conn = getConnection();
		
		list = dao.selectAllInfo(conn);
		
		close(conn);
		
		return list;
	}

	
	/** 비밀번호 변경 서비스
	 * @param updatePw
	 * @return
	 */
	public int updatePw(int memNo, String updatePw) throws Exception{
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.updatePw(conn, memNo, updatePw);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
				
		return result;
	}



	
	
}
