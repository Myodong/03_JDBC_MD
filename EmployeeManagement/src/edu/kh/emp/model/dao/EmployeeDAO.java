package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// DAO(Data Access Object, 데이터 접근 객체)
// -> 데이터 베이스에 접근(연결)하는 객체
// -> JDBC 코드 작성
public class EmployeeDAO {

	//JDBC 객체 참조 변수로 필드 선언(class 내부에서 공통 사용)
		private Connection conn;      // 필드 (Heap, 변수가 비어있을 수 없음)
		private Statement stmt; 	    // -> JVM이 지정한 기본값으로 초기
		private ResultSet rs = null;	// -> 참조형의 초기값은 null;
										// -> 별도 초기화 안해도 된다!
		
		private String url = "jdbc:oracle:thin:@localhost:49679:XE";
		private String user = "kh_khd";
		private String pw = "Myodong312";
				
				
		public void method() {
			Connection conn2; //지역변수 (Stack, 변수가 비어 있을 수 있음)
		}
	
}
