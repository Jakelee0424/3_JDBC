package edu.kh.jdbc.run;

import java.sql.SQLException;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run3 {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		

		TestVO vo1 = new TestVO(1, "제목70", "내용70");
		TestVO vo2 = new TestVO(2, "제목80", "내용80");
		TestVO vo3 = new TestVO(3, "제목90", "내용90");
		TestVO vo4 = new TestVO(4, "제목100", "내용100");
		TestVO vo5 = new TestVO(5, "제목110", "내용110");
		
		try {
			int result = service.insert3(vo1, vo2, vo3, vo4, vo5);
			
			if(result > 0) {
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
		
}
