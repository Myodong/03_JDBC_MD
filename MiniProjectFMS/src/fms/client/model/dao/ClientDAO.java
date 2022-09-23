package fms.client.model.dao;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;

public class ClientDAO {

	//필드 (== 멤버 변수)
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	// 기본 생성자
	public ClientDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("client-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
