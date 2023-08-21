package edu.kh.jdbc.main.model.service;

import java.sql.Connection;


import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MainService {

	private MainDAO dao = new MainDAO();
	
	/** 로그인 서비스
	 * @param id
	 * @param pw
	 * @return
	 */
	public Member login(String id, String pw) throws Exception {
		
		Connection conn = getConnection();
		
		Member member = dao.login(conn, id, pw);
		
		close(conn);
		
		return member;
	}


	/**
	 * 아이디 중복 검사
	 */
	public int idTest(String id) throws Exception {
		
		int result = 0;
		Connection conn = getConnection();
		
		result = dao.idTest(conn, id);
		
		close(conn);
		
		return result;
	}


	/** 회원가입 서비스
	 * @param member
	 * @return
	 */
	public int signUp(Member member) throws Exception{
		int result;
		
		Connection conn = getConnection();
		
		result = dao.signUp(conn, member);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	/** 로그인을 위한 탈퇴여부 체크 서비스
	 * @return
	 */
	public Member checkYN(String id, String pw) throws Exception {
		
		Member member = new Member();
		
		Connection conn = getConnection();
		
		member = dao.checkYN(conn, id, pw);
		
		close(conn);
		
		return member;
	}

}
