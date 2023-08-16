package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {

	public static void main(String[] args) {
		
		// XML (Extensible Markup Language) : 단순화된 데이터 기술 형식
		// XML 에 저장되는 데이터 형식 : Map 형식 (key:value)
		//  -> key, value 모두 String
		
		// 자바가 재컴파일 하지 않음 (외부 파일로 인식) -> 효율적인 메모리 관리
	
		// XML 파일을 읽고, 쓰기 위한 IO관련 클래스 필요
		// 	** Properties 컬렉션 객체 ** 
		//  - Map의 후손 클래스
		//  - key, value 모두 스트링(key는 comment 등 태그 안의 내용, value는 태그와 태그사이 내용)
		//  - XML 파일을 읽고, 쓰는데 특화된 메서드 제공
		
		try {
			
			Scanner sc = new Scanner(System.in);
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.nextLine();
			
			//properties 객체 생성
			Properties prop = new Properties();
			
			//FileOutputStream 생성
			FileOutputStream fos = new FileOutputStream(fileName + ".xml"); 
			
			//Properties 객체를 이용해서 XML 파일 생성
			prop.storeToXML(fos, fileName);
			
			
			System.out.println(fileName + ".xml 파일 생성 완료");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
