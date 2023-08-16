package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {

	public static void main(String[] args) {
		
		// XML파일 읽어오기(Properties, FileInputStream 필요)
		
		try {
			
			Properties prop = new Properties();
			
			// driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			// 연결된 driver.xml 파일에 있는 내용을 모두 읽어와
			// Properties 객체에 K:V 형식으로 저장
			prop.loadFromXML(fis);
			
			System.out.println(prop); // K=V 형식으로 출력
			
			// prop.gerProperty : key가 일치하는 속성을 얻어옴
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pw = prop.getProperty("password");
			
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println(conn);
			
			/*
			 * XML 파일을 이용하는 이유
			 * 
			 * 1. 코드 중복 제거
			 * 2. 별도 관리 용도
			 * 3. 재 컴파일을 진행하지 않기 위해서
			 * - 코드가 길수록 컴파일에 소요되는 시간이 큼 
			 * - 코드 수정으로 인한 컴파일 소요시간을 없앰. <- XML로 방지
			 * (파일에 내용을 읽어오는 코드만 작성해두면, 
			 * 자바 코드 수정 없이 외부 파일만 수정 -> 재컴파일 미실행)
			 * 
			 *  4. XML파일 작성된 문자열 형태를 그대로 읽어옴 -> sql 작성 용이
			 * 
			 * */ 
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
